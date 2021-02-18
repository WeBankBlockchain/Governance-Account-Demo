package com.webank.blockchain.gov.acct.demo.chain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.math.BigInteger;

import org.fisco.bcos.sdk.client.protocol.response.BcosBlock;
import org.fisco.bcos.sdk.client.protocol.response.ConsensusStatus;
import org.fisco.bcos.sdk.client.protocol.response.GroupList;
import org.fisco.bcos.sdk.client.protocol.response.GroupPeers;
import org.fisco.bcos.sdk.client.protocol.response.NodeIDList;
import org.fisco.bcos.sdk.client.protocol.response.ObserverList;
import org.fisco.bcos.sdk.client.protocol.response.Peers;
import org.fisco.bcos.sdk.client.protocol.response.SealerList;
import org.fisco.bcos.sdk.client.protocol.response.SyncStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.webank.blockchain.gov.acct.demo.GovAcctDemoApplicationTests;
import com.webank.blockchain.gov.acct.tool.JacksonUtils;

/**
 * ChainInfoTest @Description: ChainInfoTest
 *
 * @author maojiayu
 * @data Jan 21, 2020 3:48:23 PM
 */
public class ChainInfoTest extends GovAcctDemoApplicationTests {

    @Test
    public void testChainInfo() throws IOException {
        long id = client.getBlockNumber().getBlockNumber().longValue();
        System.out.println(id);
        Assertions.assertTrue(id >= 0);
    }

    @Test
    public void getConsensusStatus() throws Exception {
        ConsensusStatus consensusStatus = client.getConsensusStatus();
        assertNotNull(consensusStatus);
    }

    @Test
    public void getSyncStatus() throws Exception {
        SyncStatus syncStatus = client.getSyncStatus();
        assertNotNull(syncStatus);
    }

    @Test
    public void peers() throws Exception {
        Peers peers = client.getPeers();
        assertNotNull(peers.getPeers());
    }

    @Test
    public void groupPeers() throws Exception {
        GroupPeers groupPeers = client.getGroupPeers();
        assertNotNull(groupPeers.getGroupPeers());
    }

    @Test
    public void groupList() throws Exception {
        GroupList groupList = client.getGroupList();
        assertNotNull(groupList.getGroupList());
    }

    @Test
    public void getSealerList() throws Exception {
        SealerList sealerList = client.getSealerList();
        assertNotNull(sealerList.getSealerList());
    }

    @Test
    public void getObserverList() throws Exception {
        ObserverList observerList = client.getObserverList();
        assertNotNull(observerList.getObserverList());
    }

    @Test
    public void getNodeIDList() throws Exception {
        NodeIDList nodeIDList = client.getNodeIDList();
        assertNotNull(nodeIDList.getNodeIDList());
    }

    @Test
    public void getBlock() throws IOException {
        BcosBlock bcosBlock = client.getBlockByNumber(BigInteger.ZERO, true);
        System.out.println(JacksonUtils.toJson("bcosBlock: " + bcosBlock.getBlock()));
        assertNotNull(bcosBlock.getBlock());
    }
}
