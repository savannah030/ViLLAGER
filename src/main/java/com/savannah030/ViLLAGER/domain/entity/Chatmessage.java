package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    채팅 메세지도 DB에 저장하기 위해서 엔티티 따로만듦
 */
@Getter
@NoArgsConstructor
@Entity
@Table
public class Chatmessage extends BaseEntity {

    enum MessageType {
        ENTER, TALK
    }

    @Id
    @Column(name = "chatmessage_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private MessageType messageType;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_idx")
    private Chatroom chatroom;
}
