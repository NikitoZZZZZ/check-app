package com.netcracker.checkapp.server.service.security;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface HttpService {
    public HttpHeaders addHeaders(Map<String, String> map);
}