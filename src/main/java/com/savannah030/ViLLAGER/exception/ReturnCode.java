package com.savannah030.ViLLAGER.exception;

import lombok.Getter;

@Getter
public enum ReturnCode {
    // 400(잘못된 요청): 서버가 요청의 구문을 인식하지 못했다.
    // 404(Not Found, 찾을 수 없음): 서버가 요청한 페이지(Resource)를 찾을 수 없다. 예를 들어 서버에 존재하지 않는 페이지에 대한 요청이 있을 경우 서버는 이 코드를 제공한다.
    // 500(내부 서버 오류): 서버에 오류가 발생하여 요청을 수행할 수 없다.
    MEMBER_ID_DUPLICATE(400,"이미 사용중인 아이디입니다."),
    BOARD_IDX_DUPLICATE(400, "이미 생성된 글입니다."),
    SUCCESS(200,"요청을 처리했습니다."),
    TEST(404, "GlobalControllerAdvice test용"), // TEST
    FAIL_TO_CREATE_BOARD(500, "글을 저장하지 못했습니다."),
    BOARD_NOT_EXIST(404, "해당 글이 존재하지 않습니다");

    final int status;
    final String message;

    ReturnCode(int status, String message){
        this.status = status;
        this.message = message;
    }

}
