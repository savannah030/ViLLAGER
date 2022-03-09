package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
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
    private Long idx;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String password;

    //NOTE: 사용자가쓴글과 사용자는 다대일 관계
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY) // 지연 로딩(연관된 엔티티를 실제 사용할 때 조회)
    private List<Board> myBoards = new ArrayList<>(); //FIXME

    //NOTE: 사용자가좋아한글과 사용자는 다대다 관계
    @OneToMany(mappedBy = "likeMember", fetch = FetchType.LAZY)
    private List<Like> likeBoards = new ArrayList<>(); //FIXME

    @OneToMany(mappedBy = "candBuyer", fetch = FetchType.LAZY)
    private List<Chatroom> chatrooms = new LinkedList<>();


    @Builder
    public Member(String memberName, String email, String password) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        //this.myBoards = myBoards;
        //this.likeBoards = likeBoards;
    }

    // TODO: 비밀번호 변경


}
