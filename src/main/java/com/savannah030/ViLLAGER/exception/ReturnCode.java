package com.savannah030.ViLLAGER.exception;

import lombok.Getter;

@Getter
public enum ReturnCode {
    // 400 잘못된 요청
    // 404 요청한 페이지를 찾을 수 없음
    // 500 내부 서버 오류
    MEMBER_ID_DUPLICATE(400,"이미 사용중인 아이디입니다."),
    BOARD_IDX_DUPLICATE(400, "이미 생성된 글입니다."),
    SUCCESS(201,"글이 성공적으로 생성되었습니다."),
    TEST(404, "GlobalControllerAdvice test용"), // TEST
    FAIL_TO_CREATE_BOARD(500, "글을 저장하지 못했습니다."),
    BOARD_NOT_EXIST(404, "해당 글이 존재하지 않습니다"),
    MEMBER_NOT_EXIST(400, "사용자를 찾을 수 없습니다.");

    final int status;
    final String message;

    ReturnCode(int status, String message){
        this.status = status;
        this.message = message;
    }

}
