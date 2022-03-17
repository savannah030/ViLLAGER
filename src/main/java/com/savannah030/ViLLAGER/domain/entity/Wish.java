package com.savannah030.ViLLAGER.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Wish {

    @Id
    @GeneratedValue
    @Column(name = "like_idx")
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member wishMember;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board wishBoard;

    @Builder
    public Wish(Long idx, Member member, Board board){
        this.idx = idx;
        this.wishMember = member;
        this.wishBoard = board;
    }

}
