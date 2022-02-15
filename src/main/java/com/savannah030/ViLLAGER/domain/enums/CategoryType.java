package com.savannah030.ViLLAGER.domain.enums;

public enum CategoryType {
    CLOTHES("옷"),
    ELECTRONICS("전자기기");

    private String value;

    CategoryType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


}
