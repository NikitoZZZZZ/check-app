package com.netcracker.checkapp.server.controller;


import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.service.checkservice.CheckService;
import com.netcracker.checkapp.server.service.checkservice.CheckServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class CheckController {
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Check> load(@RequestParam Map<String, String> params) {
        //call CheckServiceImpl
        CheckService checkService = new CheckServiceImpl();

//        if (/*success or not*/true) {
//            return new ResponseEntity<>("Check's not been added to DB" + System.lineSeparator(),
//                    HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<Check>(checkService.getCheck(params.get("fdocumentn"), params.get("fdriven"),
                params.get("fs")), HttpStatus.OK);
    }
}