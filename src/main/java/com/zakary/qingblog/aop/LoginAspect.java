package com.zakary.qingblog.aop;

import com.zakary.qingblog.QingblogApplication;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.utils.AnalysisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @ClassNamelogin
 * @Description
 * @Author
 * @Date2020/3/25 10:26
 * @Version V1.0
 **/
@Aspect
@DeclarePrecedence("InterceptorAspect,AuthorityAspect,LoginAspect,ParamsCheckAspect,LoggerAspect")
public class LoginAspect {
//    * execution(* com.oysept.springboot.controller.AspectJController.*(..)) ==> 该包下AspectJController类的任何方法。
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
    @Pointcut("execution(* com.zakary.qingblog.controller.LoginController.userLogin(..))")
//    @Pointcut("execution(* com.zakary.qingblog.controller..*.*(..))")
    public void loginAop() {}

    /**
     * 环绕通知,可以根据execution规则配置多个方法
     * @param point
     * @return
     */

//    @Around("loginAop()")
//    public Object doProcess(ProceedingJoinPoint point) throws Throwable {
////        System.out.println("==>@Around begin----- ");
//        String args="";
//        Object[] objArgs = point.getArgs();
//        for(Object obj : objArgs) {
////            System.out.print("args: "+obj + "\t");
//            args+="args: "+obj +"\t";
//        }
//        logger.info("User login: ["+args+"]");
////        System.out.println();
//
////        System.out.println("==>@Around end----- ");
//        return point.proceed();
//    }
    @Before("loginAop()")
    public void before(JoinPoint point) throws Throwable {
        Object[] objArgs = point.getArgs();
        String mail= AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        String password=AnalysisUtils.getObjectToMap(objArgs[0]).get("userPassword").toString();
        logger.info("User Login : [ userMail : "+mail+"\t , password : "+password+"\t ]");
    }
}
