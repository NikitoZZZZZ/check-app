package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.UserInfo;
import com.netcracker.checkapp.server.service.userinfoservice.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegistrationController {

    private UserInfoService userInfoService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    RegistrationController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registration(@RequestBody Map<String, String> body) {
        UserInfo userInfo = new UserInfo();

        userInfo.setLogin(body.get("login"));
        if (userInfoService.existsByUsername(userInfo.getLogin())) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        userInfo.setPwd(bCryptPasswordEncoder.encode(body.get("pwd")));
        userInfo.setRole("ROLE_USER");
        userInfoService.save(userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
