package com.savannah030.ViLLAGER.config.auth;

import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
public class OAuthAttributesDto {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String memberName;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributesDto(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.memberName = memberName;
        this.email = email;
        this.picture = picture;
    }

    /**
     * @param registerId            인증 서버 id (TODO 나중에 구글 말고 다른 인증 서버 사용할 때 더 구현할 예정)
     * @param nameAttributeKey      로그인 시 사용할 키
     * @param attributes            OAuth2User 속성값 저장
     * @return                      구글 ...
     */
    public static OAuthAttributesDto of(String registerId, String nameAttributeKey, Map<String, Object> attributes) {
        /**
        * attributes.forEach((key,value)
        *    ->log.info("key: {}, value: {}",key,value));
        * key: sub, value: 108126460795166503826
        * key: name, value: Yooni윤승
        * key: given_name, value: 윤승
        * key: family_name, value: Yooni
        * key: picture, value: https://lh3.googleusercontent.com/a/AATXAJxq-AZAJxbkOox2nql6hYeHsiUhT22T7c9FJEKh=s96-c
        * key: email, value: syhan97@gmail.com
        * key: email_verified, value: true
        * key: locale, value: ko
        */
        return ofGoogle(nameAttributeKey, attributes);
    }

    public static OAuthAttributesDto ofGoogle(String nameAttributeKey, Map<String, Object> attributes){
        return OAuthAttributesDto.builder()
                            .attributes(attributes) // 속성값 전체(Map 컬렉션에 저장)
                            .nameAttributeKey(nameAttributeKey)
                            .memberName( (String) attributes.get("name"))
                            .email( (String) attributes.get("email"))
                            .picture( (String) attributes.get("picture"))
                            .build();
    }

    // 해당 이메일로 가입되지 않은 경우 Member 엔티티 새로 생성
    public Member toEntity(){
        return Member.builder()
                .memberName(memberName)
                .email(email)
                .roleType(RoleType.GUEST)
                .picture(picture)
                .build();
    }
}
