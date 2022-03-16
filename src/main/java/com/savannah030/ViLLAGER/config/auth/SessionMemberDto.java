package com.savannah030.ViLLAGER.config.auth;

import com.savannah030.ViLLAGER.domain.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
// NOTE: 직렬화
public class SessionMemberDto implements Serializable {
    private String memberName;
    private String email;
    private String picture;

    public SessionMemberDto(Member member){
        this.memberName = member.getMemberName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}

