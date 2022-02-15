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
@Table
public class Board extends BaseEntity implements Serializable {

    /**
     * @GeneratedValue 기본키 자동생성
     * IDENTITY 데이터베이스에 위임
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

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

    /*
    public void update(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.latitude = board.getLatitude();
        this.longitude = board.getLongitude();
    }
     */
}
