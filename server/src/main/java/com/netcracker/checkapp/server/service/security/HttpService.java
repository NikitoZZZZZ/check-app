package com.netcracker.checkapp.server.service.security;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface HttpService {
    HttpHeaders addHeaders(Map<String, String> map);
}