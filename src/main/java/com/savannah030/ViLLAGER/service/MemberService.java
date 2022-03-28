package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.controller.form.SignUpForm;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.dto.MyListDto;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.exception.VillagerException;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

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
     * 내가 쓴 글 가져오기
     */

    public Page<MyListDto> getMyBoards(Long idx, Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1, pageable.getPageSize());
        Page<Board> boardList = boardRepository.findAllByMember(idx, pageable);
        return new PageImpl<>(boardList.stream().map(MyListDto::new).collect(Collectors.toList()), pageable,boardList.getTotalElements());
    }


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
