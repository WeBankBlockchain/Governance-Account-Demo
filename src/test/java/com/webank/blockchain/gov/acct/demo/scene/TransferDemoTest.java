/**
 * Copyright 2014-2019 the original author or authors.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.blockchain.gov.acct.demo.scene;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.webank.blockchain.gov.acct.contract.WEGovernance;
import com.webank.blockchain.gov.acct.demo.GovAcctDemoApplicationTests;
import com.webank.blockchain.gov.acct.demo.contract.TransferDemo;
import com.webank.blockchain.gov.acct.factory.AccountGovernManagerFactory;
import com.webank.blockchain.gov.acct.manager.GovernAccountInitializer;

/**
 * UserOfSelfAdminScene @Description: UserOfSelfAdminScene
 *
 * @author maojiayu
 * @data Feb 24, 2020 11:28:38 AM
 */
public class TransferDemoTest extends GovAcctDemoApplicationTests {

    @Test
    public void test() throws Exception {
        AccountGovernManagerFactory factory = new AccountGovernManagerFactory(client, credentials);
        GovernAccountInitializer initializer = factory.newGovernAccountInitializer();
        WEGovernance govern = initializer.createGovernAccount(u);
        System.out.println(govern.getAccountManager());
        Assertions.assertNotNull(govern);
        TransferDemo transferDemo =
                TransferDemo.deploy(client, credentials, govern.getAccountManager(), BigInteger.valueOf(1L));
        System.out.println(transferDemo.getContractAddress() + "deploy1111111");

        System.out.println(transferDemo.transfer(transferDemo.getContractAddress(), BigInteger.valueOf(1L)));
        System.out.println(transferDemo.balance(transferDemo.getContractAddress()));
    }

}
