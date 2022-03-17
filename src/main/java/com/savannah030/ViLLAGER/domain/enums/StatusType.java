package com.savannah030.ViLLAGER.domain.enums;

import lombok.Getter;

@Getter
public enum StatusType {
    ONSALE("판매중"),
    RESERVED("예약중"),
    SOLDOUT("판매완료");

    private final String value;

    StatusType(String value){
        this.value = value;
    }


}
