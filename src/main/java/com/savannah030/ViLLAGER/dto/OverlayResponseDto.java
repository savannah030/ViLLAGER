package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OverlayResponseDto {
    private Long idx;
    private CategoryType categoryType;
    private String title;
    private Double latitude;
    private Double longitude;


    @Builder
    public OverlayResponseDto(Board entity) {// Board 엔티티의 정보를 갖고 온다
        this.idx = entity.getIdx();
        this.categoryType = entity.getCategoryType();
        this.title = entity.getTitle();
        this.latitude = entity.getAddress().getLatitude();
        this.longitude = entity.getAddress().getLongitude();
    }
}
