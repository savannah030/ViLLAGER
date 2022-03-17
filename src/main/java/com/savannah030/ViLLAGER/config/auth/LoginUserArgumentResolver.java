package com.savannah030.ViLLAGER.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
// NOTE: HandlerMethodArgumentResolver는 컨트롤러의 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 조건을 바인딩 해주는 인터페이스
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    // NOTE: supportsParameter는 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    public boolean supportsParameter(MethodParameter parameter){
        // @LoginUser이 붙어 있고
        boolean isLoginUserAnnotation = (parameter.getParameterAnnotation(LoginUser.class) != null);
        // 파라미터 클래스 타입이 SessionMemberDto인 경우에만
        boolean isUserClass = SessionMemberDto.class.equals(parameter.getParameterType());
        // true 반환
        return isLoginUserAnnotation && isUserClass;
    }

    //NOTE: resolveArgument는 파라미터에 전달할 객체를 생성(여기서는 세션에서 객체를 가져옴)
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("member");
    }
}
