package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import com.netcracker.checkapp.server.service.checkservice.CheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @PostMapping()
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> load(@RequestBody Map<String, String> body) {
        checkRepository.save(checkService.getCheck(body.get("fdriven"), body.get("fdocumentn"),
                body.get("fs")));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Check> getById(@PathVariable String id) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            if (checkRepository.existsByIdAndUserInfoLogin(id, principal.getUsername())) {
                return new ResponseEntity<Check>(checkRepository.findById(id), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Check>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<Check>(checkRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> getByUserInfoLogin(@RequestBody(required = false) Map<String, String> body) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return new ResponseEntity<List<Check>>(checkRepository.findByUserInfoLogin(principal.getUsername()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<List<Check>>(checkRepository.findByUserInfoLogin(body.get("login")),
                HttpStatus.OK);
    }

}