package com.netcracker.checkapp.server.controller;

import com.netcracker.checkapp.server.model.place.Coords;
import com.netcracker.checkapp.server.model.place.Place;
import com.netcracker.checkapp.server.service.fdspservice.FDSPService;
import com.netcracker.checkapp.server.service.placeservice.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> load(@RequestBody Map<String, String> body) {
        Place place = new Place();
        place.setName(body.get("name"));
        place.setNumOfChecks(Integer.valueOf(body.get(("numOfChecks"))));

        double longitude = Double.valueOf(body.get("longitude"));
        double latitude = Double.valueOf(body.get("longitude"));
        Coords coords = new Coords();
        coords.setLongitude(longitude);
        coords.setLatitude(latitude);
        place.setCoords(coords);

        // optional
        place.setAddress(body.get("address"));
        place.setRating(Double.valueOf(body.get("rating")));

        // very optional here - might use another url to update by existing ID
        place.setId(body.get("id"));

        placeService.addNewPlace(place);

        // optional - add FDSP here or send info to nested url ??

        return new ResponseEntity<Place>(new Place(), HttpStatus.OK);
    }

    @GetMapping("?latitude={latitude}&longitude={longitude}&radius={radius}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    public ResponseEntity<List<Place>> getPlaces(@PathVariable double longitude,
                                                 @PathVariable double latitude,
                                                 @PathVariable double radius) {
        Coords coords = new Coords();
        coords.setLatitude(latitude);
        coords.setLongitude(longitude);
        List<Place> places = placeService.getNearPlaces(coords, radius);

        places.sort(new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return o1.getNumOfChecks().compareTo(o2.getNumOfChecks());
            }
        });

        // we return sublist of places by 10 most popular by NumOfChecks
        return new ResponseEntity<List<Place>>(places.subList(0,9),HttpStatus.OK);
    }
}
