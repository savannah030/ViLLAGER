package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.domain.Position;
import com.savannah030.ViLLAGER.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /*
    @GetMapping({"", "/"})
    public String place(@RequestParam(value = "position",defaultValue = 현재위치) Position position, Model model){
        model.addAttribute("place", placeService.findNearPlaces());
    }
     */
}
