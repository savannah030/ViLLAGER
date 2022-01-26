package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.domain.Location;
import com.savannah030.ViLLAGER.repository.BoardRepository;
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
    private BoardRepository boardRepository;

    @GetMapping({"", "/"})
    public String place(Model model){
        model.addAttribute("boardList", boardRepository.findAll());
        return "place";
    }
    /*
    @Autowired
    private PlaceService placeService;

    @GetMapping({"", "/"})
    public String place(Position position, Model model){
        model.addAttribute("place", placeService.findNearPlaces());
    }
    */

}
