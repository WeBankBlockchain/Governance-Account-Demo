package com.webank.blockchain.gov.acct.demo.chain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.webank.blockchain.gov.acct.demo.BaseTests;

/**
 * CredentailTest @Description: CredentailTest
 *
 * @author maojiayu
 * @data Jan 21, 2020 4:14:19 PM
 */
public class CredentailTest extends BaseTests {

    @Test
    public void testCredentials() {
        System.out.println(endUser1Keypair.getAddress());
        Assert.notNull(endUser1Keypair, "credential must be not null");
        System.out.println(governanceUser1Keypair);
        Assert.notNull(governanceUser1Keypair.getAddress(), "credential must be not null");
    }

}
