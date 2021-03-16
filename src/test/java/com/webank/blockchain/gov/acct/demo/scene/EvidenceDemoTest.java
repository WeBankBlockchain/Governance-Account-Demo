package com.webank.blockchain.gov.acct.demo.scene;

import java.math.BigInteger;

import org.fisco.bcos.sdk.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.BaseTests;
import com.webank.blockchain.gov.acct.demo.contract.EvidenceDemo;
import com.webank.blockchain.gov.acct.factory.AccountGovernManagerFactory;
import com.webank.blockchain.gov.acct.manager.GovernContractInitializer;

/**
 * UserOfSelfAdminScene @Description: UserOfSelfAdminScene
 *
 * @author maojiayu
 * @data Feb 24, 2020 11:28:38 AM
 */
public class EvidenceDemoTest extends BaseTests {

    @Test
    public void test() throws Exception {
        AccountGovernManagerFactory factory = new AccountGovernManagerFactory(client, governanceUser1Keypair);
        GovernContractInitializer initializer = factory.newGovernContractInitializer();
        WEGovernance govern = initializer.createGovernAccount(endUser1Keypair);
        String userAccount = initializer.createAccount(governanceUser1Keypair.getAddress());
        System.out.println("userAccount " + userAccount);
        System.out.println(govern.getAccountManager());
        System.out.println(govern.getContractAddress());
        Assertions.assertNotNull(govern);
        EvidenceDemo evidenceDemo = EvidenceDemo.deploy(client, governanceUser1Keypair, govern.getAccountManager());
        System.out.println(JsonUtils.toJson(evidenceDemo.getContractAddress()));
        // 调用setData方法
        System.out.println(evidenceDemo.setData("hash", govern._owner(), BigInteger.valueOf(100L)));
        System.out.println(evidenceDemo.getData("hash"));
    }

}
