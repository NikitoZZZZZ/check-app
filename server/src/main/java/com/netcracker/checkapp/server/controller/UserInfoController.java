package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.UserInfo;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "users")
public class UserInfoController {

    private UserInfoRepository userInfoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody Map<String, String> body) {
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

    @DeleteMapping("/admin")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserInfo> adminDelete(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(userInfoRepository.deleteUserInfoByLogin(body.get("login")), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<UserInfo> userDelete() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(userInfoRepository.deleteUserInfoByLogin(principal.getUsername()),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admin")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<UserInfo> adminGet(@RequestBody Map<String, String> body) {
        System.out.println(body);
        return new ResponseEntity<>(userInfoRepository.findByLogin(body.get("login")), HttpStatus.OK);
    }

    @GetMapping("/user")
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<UserInfo> userGet() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(userInfoRepository.findByLogin(principal.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/admin/all")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<UserInfo>> getAll() {
        return new ResponseEntity<>(userInfoRepository.findAll(), HttpStatus.OK);
    }

}
