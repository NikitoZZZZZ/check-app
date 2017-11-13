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

    @PostMapping
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> load(@RequestParam Map<String, String> params) {
        checkRepository.save(checkService.getCheck(params.get("fdocumentn"), params.get("fdriven"),
                params.get("fs")));

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping
////    @Secured({"ROLE_ADMIN"})
//    @ResponseBody
//    public ResponseEntity<List<Check>> getAll() {
//        return new ResponseEntity<List<Check>>(checkRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Check> getById(@PathVariable String id) {
        return new ResponseEntity<Check>(checkRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping/*(value = "/{login}")*/
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> getByUserInfoLogin() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<List<Check>>(checkRepository.findByUserInfoLogin(principal.getUsername()),
                HttpStatus.OK);
    }
}