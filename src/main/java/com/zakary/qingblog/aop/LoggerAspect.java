package com.zakary.qingblog.aop;

import com.zakary.qingblog.QingblogApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @ClassNamelogger
 * @Description
 * @Author
 * @Date2020/3/24 21:59
 * @Version V1.0
 **/
@Aspect
@DeclarePrecedence("InterceptorAspect,AuthorityAspect,LoginAspect,ParamsCheckAspect,LoggerAspect")
public class LoggerAspect {
    private org.slf4j.Logger logger= LoggerFactory.getLogger(QingblogApplication.class);
    /**
     * 匹配规则
     * execution: 用于匹配方法执行的连接点;
     * execution(public * *(..)) ==> 匹配所有目标类的public方法,第一个*代表返回类型,第二个*代表方法名,而..代表任意入参的方法。
     * execution(* com.oysept.springboot.controller..*.*(..))                ==> 该包及所有子包下任何类的任何方法。
     * execution(* com.oysept.springboot.controller.*(..))                   ==> 该包下任何类的任何方法。
     * execution(* com.oysept.springboot.controller.AspectJController.*(..)) ==> 该包下AspectJController类的任何方法。
     * execution(* com..*.*Controller.method*(..)) ==> 匹配包名前缀为com的任何包下类名后缀为Controller的方法，方法名必须以method为前缀。
     * execution(* *To(..)) ==> 匹配目标类所有以To为后缀的方法。
     * 注: 该方法只是为了声明一个公共的环绕通知,也可以直接在具体方法配置,如: @Around("execution(* com.oysept.springboot.controller..*.*(..))")
     */
    @Pointcut("execution(* com.zakary.qingblog.controller..*.*(..))")
    public void loggerControllerAop() {}

    /**
     * 环绕通知,可以根据execution规则配置多个方法
     * @param point
     * @return
     */
    @Around("loggerControllerAop()")
    public Object loggerControllerAop(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        String inParams="";
        Object[] objArgs = point.getArgs();
        for(Object obj : objArgs) {
            inParams+=(obj+"\t");
        }
        try{
            Object object=point.proceed();
            String outParams=object.toString();
            logger.info("REQUEST \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], OUTPUT : "+outParams);
            return object;
        }
        catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error("REQUEST : \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], "+throwable.getMessage());
            return null;
        }

    }

    @Pointcut("execution(* com.zakary.qingblog.service.serviceImpl..*.*(..))")
    public void loggerTransactionAop() {}

    /**
     * 环绕通知,可以根据execution规则配置多个方法
     * @param point
     * @return
     */
    @Around("loggerTransactionAop()")
    public Object loggerTransactionAop(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        String inParams="";
        Object[] objArgs = point.getArgs();
        for(Object obj : objArgs) {
            inParams+=(obj+"\t");
        }
        try{
            Object object=point.proceed();
            String outParams=object.toString();
            logger.info("SERVICE \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], OUTPUT : "+outParams);
            return object;
        }
        catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error("SERVICE \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], "+throwable.getMessage());
            return null;
        }

    }

    @Pointcut("execution(* com.zakary.qingblog.mapper..*.*(..))")
    public void loggerMapperAop() {}

    /**
     * 环绕通知,可以根据execution规则配置多个方法
     * @param point
     * @return
     */
    @Around("loggerMapperAop()")
    public Object loggerMapperAop(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        String inParams="";
        Object[] objArgs = point.getArgs();
        for(Object obj : objArgs) {
            inParams+=(obj+"\t");
        }
        try{
            Object object=point.proceed();
            String outParams=object.toString();
            logger.info("MAPPER \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], OUTPUT : "+outParams);
            return object;
        }
        catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error("MAPPER \t: "+point.getTarget().toString()+"\t,USERID : "+userId+"\t,ARGS : ["+inParams+"\t], "+throwable.getMessage());
            return null;
        }
    }
}
