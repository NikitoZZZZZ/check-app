package com.netcracker.checkapp.server.service.httpservice;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public interface HttpService {
    HttpHeaders addHeaders(Map<String, String> map);

    Map<String, String> addMessage(String message);
}