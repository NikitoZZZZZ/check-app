package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.UserInfo;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "user")
public class UserInfoController {

    private UserInfoRepository userInfoRepository;

    UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> add(@RequestParam(value = "login", required = true) String login,
                                        @RequestParam(value = "pwd", required = true) String pwd) {
        UserInfo userInfo = new UserInfo();

        userInfo.setLogin(login);
        userInfo.setPwd(pwd);
        userInfo.setRole("ROLE_USER");
        userInfoRepository.save(userInfo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
//    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<UserInfo> delete(@RequestParam(value = "login", required = true) String login) {
        return new ResponseEntity<>(userInfoRepository.deleteUserInfoByLogin(login), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{login}")
//    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<UserInfo> get(@PathVariable String login) {
        return new ResponseEntity<>(userInfoRepository.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping
//    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<UserInfo>> getAll() {
        return new ResponseEntity<>(userInfoRepository.findAll(), HttpStatus.OK);
    }

}
