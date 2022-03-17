package com.savannah030.ViLLAGER.domain.enums;

import lombok.Getter;

@Getter
public enum CategoryType {
    CLOTHES("옷"),
    ELECTRONICS("전자기기");

    final String value;

    CategoryType(String value){
        this.value = value;
    }

}
