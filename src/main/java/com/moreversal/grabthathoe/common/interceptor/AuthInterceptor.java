package com.moreversal.grabthathoe.common.interceptor;

import com.moreversal.grabthathoe.common.lib.Jwt;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equals("OPTIONS")) return true;

        String token = jwt.extract(request, "Bearer");
        User user = jwt.validateToken(token);
        request.setAttribute("user", user);
        return true;
    }
}
