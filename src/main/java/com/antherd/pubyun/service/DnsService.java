package com.antherd.pubyun.service;

import com.antherd.pubyun.utils.InetUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author antherd
 */
@Slf4j
@Service
public class DnsService {

    @Value("${pubyun.domain}")
    private String domain;

    @Value("${pubyun.update-uri}")
    private String updateUri;

    @Value("${pubyun.auth-token}")
    private String authToken;

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public void checkOrUpdateIp() throws UnknownHostException {
        String publicIp = InetUtil.getPublicIp();
        String pubyunIp = InetAddress.getByName(domain).getHostAddress();
        log.info("========== Domain: {} ==========", domain);
        log.info("========== 公网ip: {}, 公云ip：{} ==========", publicIp, pubyunIp);

        if (!StringUtil.isBlank(publicIp) && !StringUtil.isBlank(pubyunIp) && !publicIp.equals(pubyunIp)) {
            updatePubyunIp(publicIp);
        }
    }

    public void updatePubyunIp(String publicIp) {
        ResponseEntity<String> exchange = REST_TEMPLATE.exchange(
                String.format(updateUri, "?hostname=", domain, "&myip=", publicIp),
                HttpMethod.GET,
                buildHeader(),
                String.class);
        String result = Objects.requireNonNull(exchange.getBody()).replace("\n", " ");
        log.info("============ 成功更新dns映射, 更新结果：{} =============", result);
    }

    public HttpEntity<String> buildHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(Base64.getEncoder().encodeToString(authToken.getBytes()));
        return new HttpEntity<>(headers);
    }
}
