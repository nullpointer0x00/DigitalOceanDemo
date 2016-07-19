package com.beatnikstree.digitalocean;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by nullpointer0x00 on 7/18/16.
 */
@Configuration
@EnableScheduling
public class DigitalOceanClientConfig {

    @Autowired
    protected Environment env;


    @Bean
    public DigitalOcean digitalOcean() throws RequestUnsuccessfulException, DigitalOceanException {
        String auth = env.getProperty("authTokenRW");
        DigitalOcean apiClient = new DigitalOceanClient(auth);
        apiClient.getAccountInfo();
        return apiClient;
    }

    @Bean
    public MonitorJob monitorJob(){
        return new MonitorJob();
    }
}
