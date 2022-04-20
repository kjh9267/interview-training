package me.jun.meeting.common;


import lombok.extern.slf4j.Slf4j;
import me.jun.meeting.meeting.application.dto.RequestUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static me.jun.meeting.support.UrlUtils.I_USER;

@Slf4j
@Component
public class UserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String email = webRequest.getHeader(I_USER);

        log.info("email: {}", email);

        return RequestUser.from(email);
    }
}
