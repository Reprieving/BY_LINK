package com.byritium.interceptor;

import com.byritium.types.ResponseBody;
import com.byritium.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class GlobalHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error("system error", e);
        ResponseBody<?> responseBody = new ResponseBody<>();
        responseBody.setMessage("System error");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(responseBody);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exceptionHandler(BusinessException e) {
        ResponseBody<?> responseBody = new ResponseBody<>();
        responseBody.setCode(e.getExpType().getCode());
        responseBody.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
