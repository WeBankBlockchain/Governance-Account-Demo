package com.webank.blockchain.gov.acct.demo.scene;

import java.math.BigInteger;

import org.fisco.bcos.sdk.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.GovAcctDemoApplicationTests;
import com.webank.blockchain.gov.acct.demo.contract.EvidenceDemo;
import com.webank.blockchain.gov.acct.factory.AccountGovernManagerFactory;
import com.webank.blockchain.gov.acct.manager.GovernAccountInitializer;

/**
 * UserOfSelfAdminScene @Description: UserOfSelfAdminScene
 *
 * @author maojiayu
 * @data Feb 24, 2020 11:28:38 AM
 */
public class EvidenceDemoTest extends GovAcctDemoApplicationTests {

    @Test
    public void test() throws Exception {
        AccountGovernManagerFactory factory = new AccountGovernManagerFactory(client, credentials);
        GovernAccountInitializer initializer = factory.newGovernAccountInitializer();
        WEGovernance govern = initializer.createGovernAccount(u);
        String userAccount = initializer.createAccount(credentials.getAddress());
        System.out.println("userAccount " + userAccount);
        System.out.println(govern.getAccountManager());
        System.out.println(govern.getContractAddress());
        Assertions.assertNotNull(govern);
        EvidenceDemo evidenceDemo = EvidenceDemo.deploy(client, credentials, govern.getAccountManager());
        System.out.println(JsonUtils.toJson(evidenceDemo.getContractAddress()));
        // 调用setData方法
        System.out.println(evidenceDemo.setData("hash", govern._owner(), BigInteger.valueOf(100L)));
        System.out.println(evidenceDemo.getData("hash"));
    }

}
