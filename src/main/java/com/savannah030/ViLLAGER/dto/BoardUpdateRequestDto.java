package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateRequestDto {

    private CategoryType categoryType;

    private String title;

    private String content;

    private StatusType statusType; // 판매상태는 글 수정시에만 바꿀 수 있음

    private Double latitude;

    private Double longitude;

    @Builder
    public BoardUpdateRequestDto(CategoryType categoryType, String title, String content, StatusType statusType, Double latitude, Double longitude){
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.statusType = statusType;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
