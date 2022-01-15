package com.antherd.pubyun;

import com.antherd.pubyun.utils.InetUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
@Log4j2
class JasyptTest {

    @Value("${hostname}")
    private String hostname;

    @Test
    void testIp() throws UnknownHostException {
        String pubyunIp = InetAddress.getByName(hostname).getHostAddress();
        String publicIp = InetUtil.getPublicIp();
        System.out.println("========== 公网ip: { " + publicIp + " }, 公云ip：{ " + pubyunIp + " } ==========");
    }
}
