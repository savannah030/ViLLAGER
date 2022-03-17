package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.config.auth.SessionMemberDto;
import com.savannah030.ViLLAGER.exception.GlobalControllerAdvice;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model) {
        SessionMemberDto member = (SessionMemberDto) httpSession.getAttribute("member");
        if (member != null ){
            model.addAttribute("member",member);
            //model.addAttribute("memberName",member.getMemberName());
            //model.addAttribute("email",member.getEmail());
            //model.addAttribute("picture",member.getPicture());
        }
        return "home";
    }

    // TEST : ok
    @GetMapping("/error-test")
    public String globalControllerAdviceTest(){
        log.info("GlobalControllerAdvice: {}",ReturnCode.TEST);
        throw new VillagerException(ReturnCode.TEST);
    }
}
