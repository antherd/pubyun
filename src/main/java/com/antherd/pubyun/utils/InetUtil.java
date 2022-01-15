package com.antherd.pubyun.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;


/**
 * @author antherd
 */
public class InetUtil {

    private InetUtil() {
    }

    public static String getPublicIp() {
        String ipToolUri = "https://ip.tool.chinaz.com";
        StringBuilder ipv4 = new StringBuilder();
        String lineData;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(ipToolUri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((lineData = bufferedReader.readLine()) != null) {
                ipv4.append(lineData).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Document document = Jsoup.parse(ipv4.toString());
        return document.select("dd[class=fz24]").text();
    }
}
