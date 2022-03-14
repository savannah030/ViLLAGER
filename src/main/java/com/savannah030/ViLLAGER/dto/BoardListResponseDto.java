package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BoardListResponseDto {

    private Long idx;

    private CategoryType categoryType;

    private String title;

    private String content;

    private StatusType statusType;

    private Long hits;

    @Builder
    public BoardListResponseDto(Board entity){
        this.idx = entity.getIdx();
        this.categoryType = entity.getCategoryType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.statusType = entity.getStatusType();
        this.hits = entity.getHits();
    }
}
