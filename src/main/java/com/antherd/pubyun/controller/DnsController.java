package com.antherd.pubyun.controller;

import com.antherd.pubyun.service.DnsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.UnknownHostException;

/**
 * @author antherd
 */
@RestController
public class DnsController {

    @Resource
    private DnsService dnsService;

    @GetMapping("updateIp")
    public String updateIp() throws UnknownHostException {
        dnsService.checkOrUpdateIp();
        return "success";
    }

    @Scheduled(cron="0 0/2 * * * ?")
    public void monitorIp() throws UnknownHostException {
        dnsService.checkOrUpdateIp();
    }
}
