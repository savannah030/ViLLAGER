package com.savannah030.ViLLAGER.config.auth.dto;

import com.savannah030.ViLLAGER.domain.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
// NOTE: 인증된 사용자의 정보만 담는 dto(직렬화)
public class SessionMemberDto implements Serializable {
    private Long idx;
    private String memberName;
    private String email;
    private String picture;

    public SessionMemberDto(Member member){
        this.idx = member.getIdx();
        this.memberName = member.getMemberName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}

