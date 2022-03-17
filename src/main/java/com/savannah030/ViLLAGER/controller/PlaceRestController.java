package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.service.PlaceService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceRestController {

    private final PlaceService placeService;
    @GetMapping
    public ResponseEntity<?> getPlaces(@RequestParam(value="lat")Double latitude, @RequestParam(value="lon")Double longitude){
        log.info("lat: {}, lon: {}",latitude,longitude); // ok
        return Optional.ofNullable(placeService.findNearPlaces(new Address(latitude,longitude)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build()); //ok
    }


}
