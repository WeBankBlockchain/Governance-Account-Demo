package com.webank.blockchain.gov.acct.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webank.blockchain.gov.acct.manager.AdminModeGovernManager;
import com.webank.blockchain.gov.acct.manager.EndUserOperManager;
import com.webank.blockchain.gov.acct.manager.GovernAccountInitializer;
import com.webank.blockchain.gov.acct.manager.SocialVoteManager;
import com.webank.blockchain.gov.acct.manager.VoteModeGovernManager;

@Configuration
public class GovAcctDemoBeanConfig {

    @Bean
    public GovernAccountInitializer getGovernAccountInitializer() {
        return new GovernAccountInitializer();
    }

    @Bean
    public EndUserOperManager getEndUserOperManager() {
        return new EndUserOperManager();
    }

    @Bean
    public SocialVoteManager getSocialVoteManager() {
        return new SocialVoteManager();
    }

    @Bean
    public AdminModeGovernManager getAdminModeGovernManager() {
        return new AdminModeGovernManager();
    }

    @Bean
    public VoteModeGovernManager getVoteModeGovernManager() {
        return new VoteModeGovernManager();
    }

}
