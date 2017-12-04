package com.netcracker.checkapp.server.service.userinfoservice;

import com.netcracker.checkapp.server.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    boolean exists(String login);

    void save(UserInfo userInfo);

    UserInfo delete(String login);

    UserInfo findWithLogin(String login);

    List<UserInfo> findAll();
}
