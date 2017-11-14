package com.netcracker.checkapp.server.controller;


import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import com.netcracker.checkapp.server.service.checkservice.CheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/receipts")
public class CheckController {

    CheckService checkService;
    CheckRepository checkRepository;

    CheckController(CheckService checkService, CheckRepository checkRepository, UserInfoRepository userInfoRepository) {
        this.checkService = checkService;
        this.checkRepository = checkRepository;
    }

    @PostMapping({"/admin", "/user"})
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> load(@RequestBody Map<String, String> body) {
        checkRepository.save(checkService.getCheck(body.get("fdriven"), body.get("fdocumentn"),
                body.get("fs")));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"/admin/{id}", "/user/{id}"})
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Check> getById(@PathVariable String id) {
        return new ResponseEntity<Check>(checkRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/admin/all")
    @Secured({"ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> adminGetByUserInfoLogin(@RequestBody Map<String, String> body) {
        return new ResponseEntity<List<Check>>(checkRepository.findByUserInfoLogin(body.get("login")),
                HttpStatus.OK);
    }

    @GetMapping("/user/all")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> userGetByUserInfoLogin() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<List<Check>>(checkRepository.findByUserInfoLogin(principal.getUsername()),
                HttpStatus.OK);
    }

}