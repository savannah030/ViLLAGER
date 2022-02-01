package com.savannah030.ViLLAGER.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    /*
    @Column
    private Position position;
    */

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Builder
    public Board(Long idx, String title, String content, Double latitude, Double longitude){
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.position = position;
    }

    public void update(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.latitude = board.getLatitude();
        this.longitude = board.getLongitude();
    }
}
