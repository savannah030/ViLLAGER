package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Member extends BaseEntity implements Serializable {

    @Id
    @Column(name = "member_idx")
    private Long idx;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String password;

    //NOTE: 사용자가쓴글과 사용자는 다대일 관계
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY) // 지연 로딩(연관된 엔티티를 실제 사용할 때 조회)
    private List<Board> myBoards = new ArrayList<Board>();

    //NOTE: 사용자가좋아한글과 글은 다대다 관계
    @OneToMany(mappedBy = "likeMember")
    private List<Like> likeBoards = new ArrayList<Like>();

    @Builder
    public Member(Long idx, String memberName, String email, String password, List<Board> myBoards, List<Like> likeBoards) {
        this.idx = idx;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.myBoards = myBoards;
        this.likeBoards = likeBoards;
    }
}
