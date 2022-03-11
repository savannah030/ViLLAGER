package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.exception.GlobalControllerAdvice;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // TEST : ok
    @GetMapping("/error-test")
    public String globalControllerAdviceTest(){
        log.info("GlobalControllerAdvice: {}",ReturnCode.TEST);
        throw new VillagerException(ReturnCode.TEST);
    }
}
