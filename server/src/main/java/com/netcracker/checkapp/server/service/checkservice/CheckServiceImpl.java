package com.netcracker.checkapp.server.service.checkservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.checkapp.server.model.Check;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
    private final static String ROOT = "/document/receipt";

    @Override
    public Check getCheck(String fiscalDocumentNumber, String fiscalDriveNumber, String fiscalSign) {
        Map<String, String> headers = new HashMap<>();
        Check check = new Check();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.put(AUTHORIZATION, AUTHORIZATION_VALUE);
        headers.put(DEVICE_ID, DEVICE_ID_VALUE);
        headers.put(DEVICE_OS, DEVICE_OS_ID);
        headers.put(VERSION, VERSION_ID);
        headers.put(CLIENT_VERSION, CLIENT_VERSION_ID);
        headers.put(HOST, HOST_ID);
        headers.put(USER_AGENT, USER_AGENT_ID);

        HttpEntity<String> httpEntity = new HttpEntity<String>(addHeaders(headers));
        try {
            JsonNode node = objectMapper.readTree(new RestTemplate().exchange(String.format(NALOG_RU, fiscalDocumentNumber,
                    fiscalDriveNumber, fiscalSign), HttpMethod.GET, httpEntity, String.class).getBody());
            check = objectMapper.treeToValue(node.at(ROOT), Check.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseCheck(check);
    }

    @Override
    public Check parseCheck(Check check) {
        String regexPrice = "(\\d+)(\\d{2})";
        String regexDate = "(\\d{4}-\\d{2}-\\d{2})T(.+)";
        String resultPrice = "$1.$2";
        String resultDate = "$1 $2";

        check.setNds10(check.getNds10().replaceAll(regexPrice, resultPrice));
        check.setNds18(check.getNds18().replaceAll(regexPrice, resultPrice));
        check.setTotalSum(check.getTotalSum().replaceAll(regexPrice, resultPrice));

        /*
        CHECK THAT METHOD AGAIN CAUSE WE NEED TO DECIDE: user String or LocalDateTime Object?
         */
//        check.setDateTime(check.getDateTime().replaceAll(regexDate, resultDate));

        check.getItems().forEach(item -> {
            item.setPrice(item.getPrice().replaceAll(regexPrice, resultPrice));
            item.setNdsSum(item.getNdsSum().replaceAll(regexPrice, resultPrice));
        });

        return check;
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