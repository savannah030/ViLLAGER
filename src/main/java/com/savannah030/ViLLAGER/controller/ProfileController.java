package com.savannah030.ViLLAGER.controller;


import com.savannah030.ViLLAGER.config.auth.LoginUser;
import com.savannah030.ViLLAGER.config.auth.dto.SessionMemberDto;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import com.savannah030.ViLLAGER.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class ProfileController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    /**
     * 프로필 조회
     */
    @GetMapping("/profile")
    public String getProfile(@LoginUser SessionMemberDto sessionMember){
        Member member = memberRepository.findById(sessionMember.getIdx()).orElseThrow( () -> new VillagerException(ReturnCode.LOGIN_FIRST));
        return "/member/profile";
    }

    @GetMapping("/myList")
    public String getMyList(@PageableDefault Pageable pageable, Model model, @LoginUser SessionMemberDto sessionMember){
        // 1. 멤버가 있는지 확인
        Member member = memberRepository.findById(sessionMember.getIdx()).orElseThrow( () -> new VillagerException(ReturnCode.MEMBER_NOT_EXIST));
        model.addAttribute("myList", memberService.getMyBoards(member.getIdx(), pageable));
        return "/member/myList";
    }

    @GetMapping("/heartList")
    public String getHeartList(){

        return "/member/heartList";
    }
}
