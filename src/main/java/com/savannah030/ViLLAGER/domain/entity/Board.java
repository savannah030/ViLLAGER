package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column
    private Long hits;

    @Embedded Address address;

    @ManyToOne
    @JoinColumn(name = "member_idx") // NOTE: 연관관계 매핑(객체와 테이블 연결)
    private Member ownMember; // 이 board를 작성한 사람

    @OneToMany(mappedBy = "likeBoard")
    private List<Like> likeMembers = new ArrayList<>();

    @Builder
    public Board(Long idx, CategoryType categoryType, String title, String content,
                 StatusType statusType, Long hits, Address address, List<Like> likeMembers){
        this.idx = idx;
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.statusType = statusType;
        this.hits = hits;
        this.address = address; // TODO: 주소 설정 어떻게?
        this.likeMembers = likeMembers; // FIXME: 컬렉션인데 초기화 이렇게 하는 게 맞는걸까?
    }

    //FIXME: 영속성 컨텍스트
    public void update(Board board){
        this.categoryType = board.getCategoryType();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.statusType = board.getStatusType();
        // 조회수는 업데이트할 필요없음
        this.address = board.getAddress(); // TODO: 주소 업데이트는 어떻게?
    }
}
