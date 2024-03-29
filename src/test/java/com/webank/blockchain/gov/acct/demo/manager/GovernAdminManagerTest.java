package com.webank.blockchain.gov.acct.demo.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webank.blockchain.gov.acct.contract.AdminGovernBuilder;
import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.BaseTests;
import com.webank.blockchain.gov.acct.manager.GovernContractInitializer;

/**
 * GovernAdminManagerTest @Description: GovernAdminManagerTest
 *
 * @author maojiayu
 * @data Feb 21, 2020 8:49:25 PM
 */
public class GovernAdminManagerTest extends BaseTests {
    @Autowired
    private GovernContractInitializer manager;

    @Test
    // create govern account of admin by user
    public void testCreate() throws Exception {
        AdminGovernBuilder a = AdminGovernBuilder.deploy(client, governanceUser1Keypair);
        System.out.println("AdminGovernBuilder " + a.getContractAddress());
        WEGovernance govern = manager.createGovernAccount(governanceUser1Keypair);
        System.out.println(govern.getContractAddress());
        Assertions.assertNotNull(govern);
    }
}
