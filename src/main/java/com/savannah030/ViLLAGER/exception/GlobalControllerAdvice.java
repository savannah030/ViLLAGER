package com.savannah030.ViLLAGER.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@ControllerAdvice("com.savannah030.ViLLAGER")
public class GlobalControllerAdvice {

    private final HashMap<ReturnCode, String> redirectMap = new HashMap<>();

    // NOTE : @PostConstruct는 객체의 초기화 부분.
    //  객체가 생성된 후 별도의 초기화 작업을 위해 실행하는 메소드
    //  @PostConstruct 를 설정해놓은 init 메소드는 WAS가 띄워질 때 실행
    @PostConstruct
    private void postContruct() {
        redirectMap.put(ReturnCode.MEMBER_ID_DUPLICATE, "/member/sign-up");
        redirectMap.put(ReturnCode.BOARD_IDX_DUPLICATE,"/board/list");
        redirectMap.put(ReturnCode.TEST, "/");
        //redirectMap.put(ReturnCode.SUCCESS, "/board/list");
        redirectMap.put(ReturnCode.FAIL_TO_CREATE_BOARD,"/board/list");
        redirectMap.put(ReturnCode.BOARD_NOT_EXIST,"/board/list");
    }

    @ExceptionHandler({com.savannah030.ViLLAGER.exception.VillagerException.class})
    public ModelAndView handleVillagerException(com.savannah030.ViLLAGER.exception.VillagerException e){
        ModelAndView mav = new ModelAndView();
        String redirectURL = redirectMap.get(e.getReturnCode());
        mav.setViewName("/errors/error");
        mav.addObject("errorCode", e.getReturnCode().getMessage());
        mav.addObject("redirectURL", redirectURL);
        return mav;
    }
}
