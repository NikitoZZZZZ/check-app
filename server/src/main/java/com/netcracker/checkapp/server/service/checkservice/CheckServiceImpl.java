package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.Check;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private String NALOG_RU = "http://proverkacheka.nalog.ru:8888/v1/inns/*/kkts/*/" +
            "fss/%s/tickets/%s?fiscalSign=%s&sendToEmail=no";
    private final static String AUTHORIZATION = "Authorization";
    private final static String AUTHORIZATION_VALUE = "Basic Kzc5MTE3OTcyMDY0OjExMDM1MQ==";
    private final static String DEVICE_ID = "Device-Id";
    private final static String DEVICE_ID_VALUE = "546112";
    private final static String DEVICE_OS = "Device-OS";
    private final static String DEVICE_OS_ID = "Adnroid 6.0.1";
    private final static String VERSION = "Version";
    private final static String VERSION_ID = "2";
    private final static String CLIENT_VERSION = "ClientVersion";
    private final static String CLIENT_VERSION_ID = "1.4.2";
    private final static String HOST = "Host";
    private final static String HOST_ID = "proverkacheka.nalog.ru:8888";
    private final static String USER_AGENT = "User-Agent";
    private final static String USER_AGENT_ID = "okhttp/3.0.1";



    @Override
    public Check getCheck(String fiscalDocumentNumber, String fiscalDriveNumber, String fiscalSign) {
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTHORIZATION, AUTHORIZATION_VALUE);
        headers.put(DEVICE_ID, DEVICE_ID_VALUE);
        headers.put(DEVICE_OS, DEVICE_OS_ID);
        headers.put(VERSION, VERSION_ID);
        headers.put(CLIENT_VERSION, CLIENT_VERSION_ID);
        headers.put(HOST, HOST_ID);
        headers.put(USER_AGENT, USER_AGENT_ID);

        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", addHeaders(headers));
        /*System.out.println(new RestTemplate().exchange(String.format(NALOG_RU, fiscalDocumentNumber,
                fiscalDriveNumber, fiscalSign), HttpMethod.GET, httpEntity, String.class));*/
        return parseCheck(/*json input*/);
    }

    @Override
    public Check parseCheck(/*json input*/) {
        return new Check();
    }

    @Override
    public HttpHeaders addHeaders(Map<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> element : map.entrySet()) {
            headers.add(element.getKey(), element.getValue());
        }

        return headers;
    }
}