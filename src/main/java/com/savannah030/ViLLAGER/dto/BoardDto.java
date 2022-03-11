package com.savannah030.ViLLAGER.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardDto {

    //@JsonProperty(value = "categoryType")
    private final CategoryType categoryType;

    //@JsonProperty(value = "title")
    private final String title;

    //@JsonProperty(value = "content")
    private final String content;

    //@JsonProperty(value = "latitude")
    private final Double latitude;

    //@JsonProperty(value = "longitude")
    private final Double longitude;
    
    @Builder
    public BoardDto(CategoryType categoryType, String title, String content, Double latitude, Double longitude){
        this.categoryType = categoryType;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
