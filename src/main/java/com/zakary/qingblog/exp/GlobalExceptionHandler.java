package com.zakary.qingblog.exp;

import com.zakary.qingblog.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassNameGlobalExceptionHandler
 * @Description
 * @Author
 * @Date2020/2/5 12:09
 * @Version V1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler
    JSONResult handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        return JSONResult.errorMsg("未知错误");
    }
    @ExceptionHandler(BusinessException.class)
    JSONResult handleBusinessException(BusinessException e){
        logger.error(e.getMessage());
        return JSONResult.errorException(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    JSONResult handleValidException(MethodArgumentNotValidException e){
        StringBuffer sb = new StringBuffer();
        List<String> messages = new ArrayList<>();
        for(FieldError fieldError:e.getBindingResult().getFieldErrors()){
            messages.add(fieldError.getDefaultMessage());
        }
        logger.error(e.getLocalizedMessage());
        return JSONResult.errorException(String.join("|",messages));
    }
}
