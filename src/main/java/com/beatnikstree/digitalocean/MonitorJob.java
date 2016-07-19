package com.beatnikstree.digitalocean;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.stream.Collectors;

/**
 * Created by nullpointer0x00 on 7/18/16.
 */
public class MonitorJob {

    private static Logger log = LoggerFactory.getLogger(MonitorJob.class);

    @Autowired
    private DigitalOcean digitalOcean;

    @Scheduled(initialDelay = 1000,fixedDelay=500)
    public void getLogStatus(){
        try {
            Droplets droplets = digitalOcean.getAvailableDroplets(1, 10);
            droplets.getDroplets().stream().forEach(d ->
            {
                log.info(constructStatusString(d));
                try {
                    log.info(digitalOcean.getAccountInfo().getRateLimit().toString());
                } catch (DigitalOceanException e) {
                    e.printStackTrace();
                } catch (RequestUnsuccessfulException e) {
                    e.printStackTrace();
                }
            }
            );

        } catch (DigitalOceanException e) {
            e.printStackTrace();
        } catch (RequestUnsuccessfulException e) {
            e.printStackTrace();
        }
    }

    private static String constructStatusString(Droplet droplet){
        StringBuilder sb = new StringBuilder("Droplet " + droplet.getName() + " status: " + droplet.getStatus().toString());
        sb.append(" Id: " + droplet.getId());
        sb.append(" Image Id: " + droplet.getImage().getId());
        sb.append(" Image Type: " + droplet.getImage().getType().toString());
        sb.append(" Region Name: " + droplet.getRegion().getName());
        sb.append(" Region Slug: " + droplet.getRegion().getSlug().toString());
        sb.append(" Networks: " + droplet.getNetworks().getVersion4Networks().stream().map(n -> n.getIpAddress()).collect(Collectors.joining(",")));
        sb.append(" Created: " + droplet.getCreatedDate().toString());
        return sb.toString();
    }


}
