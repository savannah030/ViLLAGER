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
    @Column(name = "MEMBER_IDX")
    private Long idx;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY) // 지연 로딩(연관된 엔티티를 실제 사용할 때 조회)
    private List<Board> myBoards = new ArrayList<Board>();

    @Builder
    public Member(Long idx, String memberName, String email, String password, List<Board> myBoards) {
        this.idx = idx;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.myBoards = myBoards;
    }
}
