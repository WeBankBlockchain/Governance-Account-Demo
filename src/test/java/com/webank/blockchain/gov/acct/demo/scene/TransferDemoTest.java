package com.webank.blockchain.gov.acct.demo.scene;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.BaseTests;
import com.webank.blockchain.gov.acct.demo.contract.TransferDemo;
import com.webank.blockchain.gov.acct.factory.AccountGovernManagerFactory;
import com.webank.blockchain.gov.acct.manager.GovernContractInitializer;

/**
 * UserOfSelfAdminScene @Description: UserOfSelfAdminScene
 *
 * @author maojiayu
 * @data Feb 24, 2020 11:28:38 AM
 */
public class TransferDemoTest extends BaseTests {

    @Test
    public void test() throws Exception {
        AccountGovernManagerFactory factory = new AccountGovernManagerFactory(client, governanceUser1Keypair);
        GovernContractInitializer initializer = factory.newGovernContractInitializer();
        WEGovernance govern = initializer.createGovernAccount(endUser1Keypair);
        System.out.println(govern.getAccountManager());
        Assertions.assertNotNull(govern);
        TransferDemo transferDemo =
                TransferDemo.deploy(client, governanceUser1Keypair, govern.getAccountManager(), BigInteger.valueOf(1L));
        System.out.println(transferDemo.getContractAddress() + "deploy1111111");

        System.out.println(transferDemo.transfer(transferDemo.getContractAddress(), BigInteger.valueOf(1L)));
        System.out.println(transferDemo.balance(transferDemo.getContractAddress()));
    }

}
