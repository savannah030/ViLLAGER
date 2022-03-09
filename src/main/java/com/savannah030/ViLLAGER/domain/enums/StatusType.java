package com.savannah030.ViLLAGER.domain.enums;

public enum StatusType {
    ONSALE("판매중"),
    RESERVED("예약중"),
    SOLDOUT("판매완료");

    private String value;

    StatusType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
