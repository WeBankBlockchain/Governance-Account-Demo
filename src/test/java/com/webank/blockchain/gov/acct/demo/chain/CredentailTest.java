package com.webank.blockchain.gov.acct.demo.chain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.webank.blockchain.gov.acct.demo.GovAcctDemoApplicationTests;

/**
 * CredentailTest @Description: CredentailTest
 *
 * @author maojiayu
 * @data Jan 21, 2020 4:14:19 PM
 */
public class CredentailTest extends GovAcctDemoApplicationTests {

    @Test
    public void testCredentials() {
        System.out.println(u1.getAddress());
        Assert.notNull(u1, "credential must be not null");
        System.out.println(p1);
        Assert.notNull(p1.getAddress(), "credential must be not null");
    }

}
