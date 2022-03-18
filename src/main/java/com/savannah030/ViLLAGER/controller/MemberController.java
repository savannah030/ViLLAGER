package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.config.auth.LoginUser;
import com.savannah030.ViLLAGER.config.auth.dto.SessionMemberDto;
import com.savannah030.ViLLAGER.controller.form.SignUpForm;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import com.savannah030.ViLLAGER.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    /**
     *
     * home.html에서 authMember가 없을 때
     * @param httpSession
     * @param model
     * @return 회원가입 페이지로 이동
     */
    @GetMapping("/sign-up")
    public String signUp(HttpSession httpSession, Model model){
        /*
        if (!httpSession.getId().isEmpty()) {//FIXME: 로그인한 사용자가 이 페이지에 접근하려하는 경우
            return "redirect:/"; // 홈화면으로 이동
        }
         */
        model.addAttribute("signUpForm", new SignUpForm()); //NOTE: SignUpForm는 커맨드 객체
        return "/sign-up/form";
    }

    /**
     *  /sign-up/form에서 사용자가 입력한 폼이 유효한지 검사하고 회원가입진행
     */
    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpForm signUpForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("bindingResult : {}", bindingResult);
            return "/sign-up/form";
        }

        // 이미 있는 아이디를 입력했으면 다시 입력
        Optional<Member> findMember = memberRepository.findByMemberName(signUpForm.getMemberName());
        if(findMember.isPresent()){
            bindingResult.addError(new FieldError("signUpForm", "memberName", "이미 사용중인 아이디입니다."));
            log.info("bindingResult : {}", bindingResult);
            return "/sign-up/form";
        }

        // signUpForm 이 아무 문제 없으면
        memberService.signUp(signUpForm);
        return "redirect:/"; //회원가입이 끝나면 홈페이지로 이동
    }
}
