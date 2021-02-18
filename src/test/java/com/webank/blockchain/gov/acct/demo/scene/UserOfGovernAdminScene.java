package com.webank.blockchain.gov.acct.demo.scene;

import java.util.List;

import org.assertj.core.util.Lists;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webank.blockchain.gov.acct.contract.AccountManager;
import com.webank.blockchain.gov.acct.contract.UserAccount;
import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.GovAcctDemoApplicationTests;
import com.webank.blockchain.gov.acct.enums.UserStaticsEnum;
import com.webank.blockchain.gov.acct.manager.AdminModeGovernManager;
import com.webank.blockchain.gov.acct.manager.EndUserOperManager;
import com.webank.blockchain.gov.acct.manager.GovernAccountInitializer;
import com.webank.blockchain.gov.acct.service.BaseAccountService;

/**
 * UserOfGovernAdminScene @Description: UserOfGovernAdminScene
 *
 * @author maojiayu
 * @data Feb 22, 2020 3:11:09 PM
 */
public class UserOfGovernAdminScene extends GovAcctDemoApplicationTests {
    @Autowired
    private GovernAccountInitializer governAdminManager;
    @Autowired
    private AdminModeGovernManager adminModeManager;
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private EndUserOperManager endUserAdminManager;
    @Autowired
    private BaseAccountService baseAccountService;

    // @Test
    // create govern account of admin by user, and set the address in application.properties
    public void testCreate() throws Exception {
        WEGovernance govern = governAdminManager.createGovernAccount(u);
        System.out.println(govern.getContractAddress());
        Assertions.assertNotNull(govern);
    }

    @Test
    public void testAdminScene() throws Exception {
        // create account by admin
        if (!accountManager.hasAccount(u1.getAddress())) {
            adminModeManager.createAccount(u1.getAddress());
        }
        String u1Acct = adminModeManager.getBaseAccountAddress(u1.getAddress());
        Assertions.assertNotNull(u1Acct);
        Assertions.assertTrue(accountManager.hasAccount(u1.getAddress()));
        String u1AccountAddress = accountManager.getUserAccount(u1.getAddress());
        Assertions.assertEquals(u1Acct, u1AccountAddress);

        // reset acct
        TransactionReceipt tr = adminModeManager.resetAccount(u1.getAddress(), u2.getAddress());
        Assertions.assertTrue(tr.isStatusOK());
        Assertions.assertTrue(!accountManager.hasAccount(u1.getAddress()));
        Assertions.assertTrue(accountManager.hasAccount(u2.getAddress()));

        // set back again
        tr = adminModeManager.resetAccount(u2.getAddress(), u1.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());

        // cancel
        tr = adminModeManager.cancelAccount(u1.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(2, baseAccountService.getStatus(u1Acct));
        Assertions.assertTrue(!accountManager.hasAccount(u1.getAddress()));

        // create again
        Assertions.assertTrue(!accountManager.hasAccount(u1.getAddress()));
        u1Acct = adminModeManager.createAccount(u1.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertTrue(accountManager.hasAccount(u1.getAddress()));

        // modify manager type
        List<String> voters = Lists.newArrayList();
        voters.add(u.getAddress());
        voters.add(u1.getAddress());
        voters.add(u2.getAddress());
        endUserAdminManager.setCredentials(u1);
        tr = endUserAdminManager.modifyManagerType(voters);
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(UserStaticsEnum.SOCIAL.getStatics(),
                UserAccount.load(accountManager.getUserAccount(u1.getAddress()), client, u1)._statics().intValue());

        // cancel
        tr = adminModeManager.cancelAccount(u1.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(2, baseAccountService.getStatus(u1Acct));
        Assertions.assertTrue(!accountManager.hasAccount(u1.getAddress()));
    }
}
