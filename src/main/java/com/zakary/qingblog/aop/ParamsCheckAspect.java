package com.zakary.qingblog.aop;

import com.zakary.qingblog.QingblogApplication;
import com.zakary.qingblog.exp.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.regex.Pattern;

import com.zakary.qingblog.utils.AnalysisUtils;

/**
 * @ClassNameParamsCheckAspect
 * @Description
 * @Author
 * @Date2020/4/1 9:22
 * @Version V1.0
 **/
@Aspect
//指定切面的执行顺序
@DeclarePrecedence("InterceptorAspect,AuthorityAspect,LoginAspect,ParamsCheckAspect,LoggerAspect")
public class ParamsCheckAspect {
    private Logger logger = LoggerFactory.getLogger(QingblogApplication.class);
    final String reMail = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
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
    public void loginParams(){
    }

    @Before("loginParams()")
    public void beforeLogin(JoinPoint point) throws Throwable {
        String userMail="";
        String userPassword="";
        Object[] objArgs = point.getArgs();
        String mail=AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        String password=AnalysisUtils.getObjectToMap(objArgs[0]).get("userPassword").toString();
        if(!Pattern.matches(reMail,mail)&&password.length()>=8&&password.length()<=16){
            throw new BusinessException("输入格式有误！");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller.UserController.select*(..))")
    public void selectInfo(){}

    @Before("selectInfo()")
    public void beforeSelectInfo(JoinPoint point) throws Throwable{
        Object[] objArgs = point.getArgs();
        String userId1=AnalysisUtils.getObjectToMap(objArgs[0]).get("userId").toString();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId2=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        logger.info("userID:     "+userId1+"  "+userId2);
        if(userId1==null&&userId2==null){
            throw new BusinessException("缺少必要参数");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller.UserController.userRegister(..)) || " +
            "execution(* com.zakary.qingblog.controller.UserController.userUpdateInfo(..))")
    public void userInfo(){}

    @Before("userInfo()")
    public void beforeRegister(JoinPoint joinPoint) throws Throwable{
        Object[] objArgs = joinPoint.getArgs();
        String userMail=AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        String userName=AnalysisUtils.getObjectToMap(objArgs[0]).get("userName").toString();
        String tel=AnalysisUtils.getObjectToMap(objArgs[0]).get("userTel").toString();
        if(!(Pattern.matches(reMail,userMail)&&userName.length()>=4&&userName.length()<=8&&tel.length()>=8&&tel.length()<=11)){
            throw new BusinessException("非法输入参数");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller.BlogController.upLoadMarkdown(..))")
    public void blogPaarm(){}

    @Before("blogPaarm()")
    public void blogParam(JoinPoint joinPoint) throws Throwable{
        Object[] objArgs = joinPoint.getArgs();
        String blogTitle=AnalysisUtils.getObjectToMap(objArgs[0]).get("blogTitle").toString();
        String blogContent=AnalysisUtils.getObjectToMap(objArgs[0]).get("blogContent").toString();
        String blogCategory=AnalysisUtils.getObjectToMap(objArgs[0]).get("blogCategory").toString();
        if(!("".equals(blogTitle)&&"".equals(blogContent)&&"".equals(blogCategory))){
            throw new BusinessException("非法输入参数");
        }
    }
}
