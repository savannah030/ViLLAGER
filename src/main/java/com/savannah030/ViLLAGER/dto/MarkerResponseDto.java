package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글을 마커에 찍을 때 사용할 dto
// 따라서 Board 엔티티의 필드 중 위도, 경도만 갖고 온다.
@NoArgsConstructor
@Getter
public class MarkerResponseDto {
    private Double latitude;
    private Double longitude;

    @Builder
    public MarkerResponseDto(Board entity) {// Board 엔티티의 정보를 갖고 온다
        this.latitude = entity.getAddress().getLatitude();
        this.longitude = entity.getAddress().getLongitude();
    }
}
