package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Board")
public class Board extends BaseEntity implements Serializable {

    /**
     * // @GeneratedValue 기본키 자동생성
     * // IDENTITY 데이터베이스에 위임
     */
    @Id
    @Column
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
    private Double latitude;

    @Column
    private Double longitude;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "MEMBER_IDX")
    private Member member;

    // 연관관계 설정
    public void setMember(Member member){
        this.member = member;
    }

    @Builder
    public Board(Long idx, CategoryType categoryType, String title, String content, StatusType statusType, Double latitude, Double longitude){
        this.idx = idx;
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.statusType = statusType;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public void update(Board board){
        this.categoryType = board.getCategoryType();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.statusType = board.getStatusType();
        this.latitude = board.getLatitude();
        this.longitude = board.getLongitude();
    }

}
