package com.savannah030.ViLLAGER.dto;

import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MyListDto {


    private String title;

    private String content;

    private StatusType statusType;


    @Builder
    public MyListDto(Board entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.statusType = entity.getStatusType();
    }
}
