package com.zakary.qingblog.exp;

/**
 * @ClassNameBusinessException
 * @Description
 * @Author
 * @Date2020/2/5 12:06
 * @Version V1.0
 **/
public class BusinessException extends RuntimeException {
    public BusinessException(String msg){
        super(msg);
    }
}
