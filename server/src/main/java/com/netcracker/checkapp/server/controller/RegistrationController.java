package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.UserInfo;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegistrationController {
    private UserInfoRepository userInfoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    RegistrationController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registration(@RequestBody Map<String, String> body) {
        UserInfo userInfo = new UserInfo();

        userInfo.setLogin(body.get("login"));
        if (userInfoRepository.existsByLogin(userInfo.getLogin())) {
            return new ResponseEntity<String>("Login is taken", HttpStatus.CONFLICT);
        }
        userInfo.setPwd(bCryptPasswordEncoder.encode(body.get("pwd")));
        userInfo.setRole("ROLE_USER");
        userInfoRepository.save(userInfo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
