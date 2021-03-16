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
import com.webank.blockchain.gov.acct.demo.BaseTests;
import com.webank.blockchain.gov.acct.enums.UserStaticsEnum;
import com.webank.blockchain.gov.acct.manager.AdminModeGovernManager;
import com.webank.blockchain.gov.acct.manager.EndUserOperManager;
import com.webank.blockchain.gov.acct.manager.GovernContractInitializer;
import com.webank.blockchain.gov.acct.service.BaseAccountService;

/**
 * UserOfGovernAdminScene @Description: UserOfGovernAdminScene
 *
 * @author maojiayu
 * @data Feb 22, 2020 3:11:09 PM
 */
public class UserOfGovernAdminScene extends BaseTests {
    @Autowired
    private GovernContractInitializer governAdminManager;
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
        WEGovernance govern = governAdminManager.createGovernAccount(governanceUser1Keypair);
        System.out.println(govern.getContractAddress());
        Assertions.assertNotNull(govern);
    }

    @Test
    public void testAdminScene() throws Exception {
        // create account by admin
        if (!accountManager.hasAccount(endUser1Keypair.getAddress())) {
            adminModeManager.createAccount(endUser1Keypair.getAddress());
        }
        String u1Acct = adminModeManager.getBaseAccountAddress(endUser1Keypair.getAddress());
        Assertions.assertNotNull(u1Acct);
        Assertions.assertTrue(accountManager.hasAccount(endUser1Keypair.getAddress()));
        String u1AccountAddress = accountManager.getUserAccount(endUser1Keypair.getAddress());
        Assertions.assertEquals(u1Acct, u1AccountAddress);

        // reset acct
        TransactionReceipt tr = adminModeManager.resetAccount(endUser1Keypair.getAddress(), governanceUser2Keypair.getAddress());
        Assertions.assertTrue(tr.isStatusOK());
        Assertions.assertTrue(!accountManager.hasAccount(endUser1Keypair.getAddress()));
        Assertions.assertTrue(accountManager.hasAccount(governanceUser2Keypair.getAddress()));

        // set back again
        tr = adminModeManager.resetAccount(governanceUser2Keypair.getAddress(), endUser1Keypair.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());

        // cancel
        tr = adminModeManager.cancelAccount(endUser1Keypair.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(2, baseAccountService.getStatus(u1Acct));
        Assertions.assertTrue(!accountManager.hasAccount(endUser1Keypair.getAddress()));

        // create again
        Assertions.assertTrue(!accountManager.hasAccount(endUser1Keypair.getAddress()));
        u1Acct = adminModeManager.createAccount(endUser1Keypair.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertTrue(accountManager.hasAccount(endUser1Keypair.getAddress()));

        // modify manager type
        List<String> voters = Lists.newArrayList();
        voters.add(governanceUser1Keypair.getAddress());
        voters.add(endUser1Keypair.getAddress());
        voters.add(governanceUser2Keypair.getAddress());
        endUserAdminManager.setCredentials(endUser1Keypair);
        tr = endUserAdminManager.modifyManagerType(voters);
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(UserStaticsEnum.SOCIAL.getStatics(),
                UserAccount.load(accountManager.getUserAccount(endUser1Keypair.getAddress()), client, endUser1Keypair)._statics().intValue());

        // cancel
        tr = adminModeManager.cancelAccount(endUser1Keypair.getAddress());
        Assertions.assertEquals("0x0", tr.getStatus());
        Assertions.assertEquals(2, baseAccountService.getStatus(u1Acct));
        Assertions.assertTrue(!accountManager.hasAccount(endUser1Keypair.getAddress()));
    }
}
