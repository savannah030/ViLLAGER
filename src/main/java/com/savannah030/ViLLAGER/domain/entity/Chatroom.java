package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
@Table
public class Chatroom extends BaseEntity {

    @Id
    @Column(name = "chatroom_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //NOTE: 사용자는 여러개의 채팅방을 가질 수 있음
    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member candBuyer; // 구매자만 채팅을 생성한다고 가정

    //NOTE: 채팅메세지와 채팅방은 다대일 관계
    @OneToMany(mappedBy = "chatroom", fetch = FetchType.LAZY)
    private List<Chatmessage> chatmessages = new ArrayList<>();


}

