package com.example.json.schema.exception;

import com.example.json.schema.entity.Result;
import com.example.json.schema.enums.EnumResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-21 17:48
 */
@ControllerAdvice
@ResponseBody
public class GobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 全局异常处理
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String globaExceptionHandle(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        this.logger.error(ex.getMessage());
        Result result = new Result();
        result.setStatus(EnumResult.SERVER_ERROR.getStatus());
        result.setMessage(EnumResult.SERVER_ERROR.getMessage());
        return Result.toString(result);
    }

    /**
     * 参数异常处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ParamException.class)
    public String paramExceptionHandle (HttpServletRequest request, ParamException ex) {
        this.logger.warn(ex.getMessage());
        Result result = new Result();
        result.setStatus(EnumResult.PARAMS_ERROR.getStatus());
        result.setMessage(ex.getMessage());
        return Result.toString(result);
    }
}
