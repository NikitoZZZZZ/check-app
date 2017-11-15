package com.netcracker.checkapp.server.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

public interface HttpService {
    public HttpHeaders addHeaders(Map<String, String> map);
}