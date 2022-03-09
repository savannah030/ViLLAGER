package com.savannah030.ViLLAGER.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SignUpForm {
    // NOTE: SignUpForm 커맨드객체는 /sign-up/form.html 값 검증처리
    // memberId(아이디),memberName(이름), email(이메일), password(비밀번호)

    @NotBlank(message = "아이디를 입력해주세요")
    private String memberId;


    @NotBlank(message = "이름을 입력해주세요")
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String memberName;

    /*
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞춰주세요")
    private String email;
    */

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp="[A-Za-z0-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
    private String password;

}
