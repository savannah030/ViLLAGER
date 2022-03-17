package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import com.savannah030.ViLLAGER.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; //인덱스

    @Column//(nullable = false)
    private String memberId; // 아이디

    @Column
    private String memberName; // 닉네임(처음에는 설정안해도됨)

    @Column(nullable = false)
    private String email;

    @Column//(nullable = false)
    private String password;

    @Enumerated
    @Column(nullable = false)
    private RoleType roleType; // 각 사용자의 권한을 관리할 Enum 클래스

    private String picture;

    //NOTE: 사용자가쓴글과 사용자는 다대일 관계
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY) // 지연 로딩(연관된 엔티티를 실제 사용할 때 조회)
    private List<Board> myBoards = new ArrayList<>(); //FIXME

    //NOTE: 사용자가좋아한글과 사용자는 다대다 관계
    @OneToMany(mappedBy = "wishMember", fetch = FetchType.LAZY)
    private List<Wish> wishBoards = new ArrayList<>(); //FIXME

    @OneToMany(mappedBy = "candBuyer", fetch = FetchType.LAZY)
    private List<Chatroom> chatrooms = new LinkedList<>();

    @Builder
    public Member(String memberId, String memberName, String email, RoleType roleType, String picture, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
        this.roleType = roleType;
        this.picture = picture;
        this.password = password;
        //this.myBoards = myBoards;
        //this.wishBoards = wishBoards;
    }

    public Member update(String memberName, String picture){
        this.memberName = memberName;
        this.picture = picture;
        return this;
    }
    // TODO: 비밀번호 변경
}
