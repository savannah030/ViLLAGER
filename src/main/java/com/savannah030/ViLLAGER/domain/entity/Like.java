package com.savannah030.ViLLAGER.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_idx")
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board;

    @Builder
    public Like(Long idx, Member member, Board board){
        this.idx = idx;
        this.member = member;
        this.board = board;
    }

}
