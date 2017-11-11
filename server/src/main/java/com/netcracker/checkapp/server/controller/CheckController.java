package com.netcracker.checkapp.server.controller;


import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import com.netcracker.checkapp.server.service.checkservice.CheckService;
import com.netcracker.checkapp.server.service.checkservice.CheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CheckController {
    CheckService checkService;
    CheckRepository checkRepository;

    CheckController(CheckService checkService, CheckRepository checkRepository) {
        this.checkService = checkService;
        this.checkRepository = checkRepository;
    }

    @RequestMapping(value = "/api/receipt", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Check> load(@RequestParam Map<String, String> params) {
        ResponseEntity<Check> responseEntity;

        responseEntity = new ResponseEntity<Check>(checkService.getCheck(params.get("fdocumentn"), params.get("fdriven"),
                params.get("fs")), HttpStatus.OK);
        checkRepository.save(responseEntity.getBody());

        return responseEntity;
    }

    @RequestMapping(value = "/api/receipt", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Check>> getAll() {
        ResponseEntity<List<Check>> responseEntity;

        responseEntity = new ResponseEntity<List<Check>>(checkRepository.findAll(), HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(value = "/api/receipt/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Check> getById(@PathVariable String id) {
        ResponseEntity<Check> responseEntity;

        responseEntity = new ResponseEntity<Check>(checkRepository.findById(id), HttpStatus.OK);

        return responseEntity;
    }
}