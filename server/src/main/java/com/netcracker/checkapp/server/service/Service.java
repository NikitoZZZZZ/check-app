package com.netcracker.checkapp.server.service;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface Service {
    public HttpHeaders addHeaders(Map<String, String> map);
}