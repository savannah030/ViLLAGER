package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MyBoardResponseDto {

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Long idx;

    private CategoryType categoryType;

    private String title;

    private String content;

    private StatusType statusType;

    private Long hits;

    // 주소는 글 저장버튼 누를 때 자바스크립트 코드에서 갖고 옴

    @Builder
    public MyBoardResponseDto(Board entity){
        this.idx = entity.getIdx();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
        this.categoryType = entity.getCategoryType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.statusType = entity.getStatusType();
        this.hits = entity.getHits();
    }
}
