package com.zakary.qingblog.aop;

import com.zakary.qingblog.QingblogApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassNamelogger
 * @Description
 * @Author
 * @Date2020/3/24 21:59
 * @Version V1.0
 **/
@Aspect
public class logger {
    private Logger logger= LoggerFactory.getLogger(QingblogApplication.class);
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
    public void loggerAop() {}

//    /**
//     * 环绕通知,可以根据execution规则配置多个方法
//     * @param point
//     * @return
//     */
//    @Around("loggerAop()")
//    public Object doProcess(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("==>@Around begin----- ");
//
//        Object[] objArgs = point.getArgs();
//        for(Object obj : objArgs) {
//            System.out.print("args: "+obj + "\t");
//        }
//        System.out.println();
//
//        System.out.println("==>@Around end----- ");
//        return point.proceed();
//    }
    @Before("loggerAop()")
    public void before(JoinPoint point) throws Throwable {
        String params="";
        Object[] objArgs = point.getArgs();
        for(Object obj : objArgs) {
            params=params+"args: "+obj+"\t";
//            System.out.print("args: "+obj + "\t");
        }
        logger.info("Method execute before: ["+params+"]");

//        System.out.println();
//
//        System.out.println("==>@Before end----- ");
    }
    @After("loggerAop()")
    public void releaseResource(JoinPoint point) {
//        System.out.println("==>@After：目标方法:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
//        System.out.println("==>@After：参数:" + Arrays.toString(point.getArgs()));
//        System.out.println("==>@After：被织入的目标对象:" + point.getTarget());
        String targetMethod="point.getSignature().getDeclaringTypeName() + \".\" + point.getSignature().getName()";
        String params="Arrays.toString(point.getArgs())";
        String targetObject="point.getTarget()";
        logger.info("Method execute after: ["+"targetMethod: "+targetMethod+", params: "+params+", targetObject: "+targetObject+"]");
    }

//    @AfterReturning(pointcut="loggerAop()", returning="returnValue")
//    public void log(JoinPoint point, Object returnValue) {
//        System.out.println("==>@AfterReturning：目标方法:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
//        System.out.println("==>@AfterReturning：参数:" + Arrays.toString(point.getArgs()));
//        System.out.println("==>@AfterReturning：返回值:" + returnValue);
//        System.out.println("==>@AfterReturning：被编译目标对象:" + point.getTarget());
//    }
}
