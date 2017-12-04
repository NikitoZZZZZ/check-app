package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.model.place.Place;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import com.netcracker.checkapp.server.persistance.PlaceRepository;
import com.netcracker.checkapp.server.persistance.UserInfoRepository;
import com.netcracker.checkapp.server.service.checkservice.CheckService;
import com.netcracker.checkapp.server.service.placeservice.PlaceService;
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

    CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping(value = "/places")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> getNearPlace(@RequestParam("longitude") String longitude,
                                                    @RequestParam("latitude") String latitude,
                                                    @RequestParam("radius") String radius ) {

        return new ResponseEntity<List<Check>>(checkService.getNearPlacesAndChecks(longitude,
                latitude,radius),
                HttpStatus.OK);
    }

    @PostMapping()
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> load(@RequestBody Map<String, String> body) {
        Check check = checkService.save(checkService.getCheck(body.get("fdriven"), body.get("fdocumentn"),
                body.get("fs")));

        return new ResponseEntity<Check>(check, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<Check> getById(@PathVariable String id) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            if (checkService.exists(id, principal.getUsername())) {
                return new ResponseEntity<Check>(checkService.findWithId(id), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Check>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<Check>(checkService.findWithId(id), HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Check>> getByUserInfoLogin(@RequestBody(required = false) Map<String, String> body) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return new ResponseEntity<List<Check>>(checkService.findWithLogin(principal.getUsername()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<List<Check>>(checkService.findWithLogin(body.get("login")),
                HttpStatus.OK);
    }

}