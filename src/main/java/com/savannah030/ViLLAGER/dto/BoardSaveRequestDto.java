package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import lombok.*;

@NoArgsConstructor
@Getter
public class BoardSaveRequestDto {

    private CategoryType categoryType;

    private String title;

    private String content;

    private Double latitude;

    private Double longitude;
    
    @Builder
    public BoardSaveRequestDto(CategoryType categoryType, String title, String content, Double latitude, Double longitude){
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Board toEntity() {
        return Board.builder()
                .categoryType(categoryType)
                .title(title)
                .content(content)
                .address(new Address(latitude,longitude))
                .build();
    }
}
