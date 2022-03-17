package com.savannah030.ViLLAGER.domain.entity;

import com.savannah030.ViLLAGER.domain.BaseEntity;
import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import com.savannah030.ViLLAGER.dto.BoardUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor // 기본 생성자 만들어줌
@Entity
@Table
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(nullable = false) //TODO @Valid 검증
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column
    private Long hits;

    @Embedded Address address;

    @ManyToOne
    @JoinColumn(name = "member_idx", nullable = false) // NOTE: 연관관계 매핑(객체와 테이블 연결)
    private Member seller; // 이 board를 작성한 사람

    @OneToMany(mappedBy = "wishBoard")
    private Set<Wish> wishMembers = new LinkedHashSet<>(); // CONFUSED: final 붙여야되나?

    // NOTE: 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Board(CategoryType categoryType, String title, String content, Address address){
        //this.idx = idx; // GenerationType.IDENTITY 기본키 자동생성 데이터베이스에 위임
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.statusType = StatusType.ONSALE;    // 처음 등록한 글은 무조건 '판매중'
        this.hits = Long.valueOf("0");          // 조회수는 0으로 초기화
        this.address = address;
    }

    // NOTE: 영속성 컨텍스트
    public void update(BoardUpdateRequestDto boardDto){
        this.categoryType = boardDto.getCategoryType();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.statusType = boardDto.getStatusType();
        // 조회수는 업데이트할 필요없음
        this.address = new Address(boardDto.getLatitude(), boardDto.getLongitude());
    }

    public void increaseHits(){
        this.hits += 1;
    }

    // FIXME: 이 함수 지우고 대신 wishRepository.save() 쓰기
    public void addWishMember(Wish wish){ // CONFUSED: 파라미터 Wish 대신 Member쓰면 안되나?
        this.wishMembers.add(wish);
    }

}
