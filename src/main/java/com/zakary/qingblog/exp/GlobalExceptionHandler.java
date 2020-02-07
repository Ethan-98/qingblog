package com.zakary.qingblog.exp;

import com.zakary.qingblog.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassNameGlobalExceptionHandler
 * @Description
 * @Author
 * @Date2020/2/5 12:09
 * @Version V1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ExceptionHandler
    @ResponseBody
    JSONResult handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        return JSONResult.errorMsg("未知错误");
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    JSONResult handleBusinessException(BusinessException e){
        logger.error(e.getMessage());
        return JSONResult.errorException(e.getMessage());
    }
}
