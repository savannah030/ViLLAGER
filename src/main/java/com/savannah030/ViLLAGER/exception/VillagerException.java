package com.savannah030.ViLLAGER.exception;

import lombok.Getter;

@Getter
public class VillagerException extends RuntimeException {

    ReturnCode returnCode;

    public VillagerException(ReturnCode returnCode){
        this.returnCode = returnCode;
    }
}
