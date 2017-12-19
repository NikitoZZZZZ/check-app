package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.service.checkservice.CheckService;
import com.netcracker.checkapp.server.service.fdspservice.FDSPService;
import com.netcracker.checkapp.server.service.httpservice.HttpService;
import com.netcracker.checkapp.server.service.placeservice.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stats")
public class StatsController {

    private CheckService checkService;
    private FDSPService fdspService;
    private PlaceService placeService;
    private HttpService  httpService;

    StatsController(CheckService checkService,
                    PlaceService placeService,
                    FDSPService fdspService,
                    HttpService httpService) {
        this.checkService = checkService;
        this.placeService = placeService;
        this.fdspService = fdspService;
        this.httpService = httpService;
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> getStats() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(checkService.findByUsername(principal.getUsername()), HttpStatus.OK);
    }

}
