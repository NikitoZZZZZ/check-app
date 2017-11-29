package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.place.Coords;
import com.netcracker.checkapp.server.model.place.Place;
import com.netcracker.checkapp.server.model.place.ShortPlace;
import com.netcracker.checkapp.server.service.fdspservice.FDSPService;
import com.netcracker.checkapp.server.service.placeservice.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/places")
public class PlaceController {

    PlaceService placeService;
    FDSPService fdspService;

    PlaceController(PlaceService placeService, FDSPService fdspService) {
        this.placeService = placeService;
        this.fdspService = fdspService;
    }

    @PostMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<?> load(@RequestBody Place place) {
        return new ResponseEntity<Place>(placeService.addNewPlace(place), HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Place>> getPlaces(@RequestParam("longitude") double longitude,
                                                 @RequestParam("latitide") double latitude,
                                                 @RequestParam("radius") double radius) {
        Coords coords = new Coords();
        coords.setLatitude(latitude);
        coords.setLongitude(longitude);
        List<Place> places = placeService.getNearPlaces(coords, radius);

        return new ResponseEntity<List<Place>>(places,HttpStatus.OK);
    }

    @GetMapping()
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<ShortPlace> getShortPlaceByFDriveN(@RequestParam("fdriven") String fdriven) {
        try {
            return new ResponseEntity<ShortPlace>(fdspService.findFDSP(fdriven).getShortPlace(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<ShortPlace>(new ShortPlace(),HttpStatus.NO_CONTENT);
        }
    }
}
