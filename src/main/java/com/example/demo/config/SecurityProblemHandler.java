package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SecurityProblemHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private static final String TEMPLATE = "SecurityProblem [{}] requestURI - {} method - {} Message - {}";

    private final HandlerExceptionResolver resolver;

    public SecurityProblemHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        logSecurityProblem(request, authException);
        resolver.resolveException(request, response, null, authException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        logSecurityProblem(request, accessDeniedException);
        resolver.resolveException(request, response, null, accessDeniedException);
    }

    private void logSecurityProblem(HttpServletRequest request, Exception e) {
        log.error(TEMPLATE, e.getClass().getSimpleName(), request.getRequestURI(), request.getMethod(), e.getMessage());
    }
}
