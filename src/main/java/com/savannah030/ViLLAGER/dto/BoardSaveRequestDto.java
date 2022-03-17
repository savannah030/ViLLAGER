package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
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

    private Member seller;
    
    @Builder
    public BoardSaveRequestDto(CategoryType categoryType, String title, String content, Double latitude, Double longitude, Member seller){
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.seller = seller;
    }

    public Board toEntity(Member seller) {
        return Board.builder()
                .categoryType(categoryType)
                .title(title)
                .content(content)
                .address(new Address(latitude,longitude))
                .seller(seller)
                .build();
    }
}
