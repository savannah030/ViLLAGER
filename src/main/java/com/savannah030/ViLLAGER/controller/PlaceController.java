package com.savannah030.ViLLAGER.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/place")
public class PlaceController {
    @GetMapping
    public String place(){
        return "/place";
    }
}
