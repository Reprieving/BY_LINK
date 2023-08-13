package com.byritium.interceptor;

import com.byritium.types.ResultData;
import com.byritium.types.exception.BizException;
import com.byritium.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultData<?> systemExp(Exception ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return ResultData.error("system error");
    }

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResultData<?> bizExp(BizException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return ResultData.error(ex.getMessage());
    }
}
