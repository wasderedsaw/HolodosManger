package com.example.demo.util.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.requireNonNullElseGet;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFoundException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthentication(AuthenticationException ex, WebRequest webRequest) {
        return handleGenericException(ex, null, new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest webRequest) {
        return handleGenericException(ex, null, new HttpHeaders(), HttpStatus.FORBIDDEN, webRequest);
    }


    private ResponseEntity<Object> handleGenericException(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(requireNonNullElseGet(body, () -> mapDefaultResponse(ex, request)), headers, status);
    }

    private Map<String, Object> mapDefaultResponse(final Throwable t, final WebRequest webRequest) {
        Assert.notNull(webRequest, "Request mustn't be null");
        final var response = new HashMap<String, Object>();
        if (webRequest instanceof ServletWebRequest) {
            final var request = ((ServletWebRequest) webRequest).getRequest();
            response.put("path", request.getRequestURI());
            final Map<String, Object> details = new LinkedHashMap<>();
            details.put("uri", request.getRequestURI());
            details.put("httpMethod", request.getMethod());
            details.put("remoteAddr", request.getRemoteAddr());
            details.put("queryString", request.getQueryString());
            response.put("details", details);
        }
        return response;
    }

}

