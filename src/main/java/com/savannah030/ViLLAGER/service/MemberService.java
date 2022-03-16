package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.controller.form.SignUpForm;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    final MemberRepository memberRepository;

    /*
     * 회원가입
     * 비밀번호 암호화(Spring Security의  BCryptPasswordEncoder 이용)
     */
    public void signUp(SignUpForm form){
        Optional<Member> findMember = memberRepository.findByMemberName(form.getMemberName());
        if (findMember.isPresent()){
            throw new VillagerException(ReturnCode.MEMBER_ID_DUPLICATE);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member encryptedMember = Member.builder()
                                        .memberId(form.getMemberId())
                                        .memberName(form.getMemberName())
                                        .password(passwordEncoder.encode(form.getPassword())).build();
        memberRepository.save(encryptedMember);
    }


    /*
     * 소셜 회원가입(OAuth2)
     */

    /*
     * 로그인
     */

    /*
     * 로그아웃
     */

    /*
     * 탈퇴
     * 1. 프로필,글,리뷰,채팅 다나가야함
     */

    /*
     * 비밀번호 확인
     */

    /*
     * 비밀번호 변경
     */

    /*
     * 내가 쓴 글 가져오기
     */
    /*
    List<Board> getMyBoards(Long Id){

    }
    */


    /*
     * 좋아요한 글 가져오기
     */
    /*
    List<Board> getLikeBoards(Long Id){

    }

     */

    /*
     * 채팅방 리스트 가져오기기
     */
    /*
    List<Chatroom> getChatroomist(Long Id){

    }

     */


}
