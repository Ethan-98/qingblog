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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassNameInterceptor
 * @Description
 * @Author
 * @Date2020/3/30 16:11
 * @Version V1.0
 **/
@Aspect
@DeclarePrecedence("InterceptorAspect,AuthorityAspect,LoginAspect,ParamsCheckAspect,LoggerAspect")
public class InterceptorAspect {
    private Logger logger = LoggerFactory.getLogger(QingblogApplication.class);

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

    @Pointcut("execution(* com.zakary.qingblog.controller..*.*(..)) && "+
            "(!execution(* com.zakary.qingblog.controller.LoginController.userLogin(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.BlogController.viewAllBlogList(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.UserController.selectInfoByUserId(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.LabelController.selectLabelByBlogId(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.FileController.getImg(..))) && " +
            "(!execution(* com.zakary.qingblog.controller.UserController.userRegister(..)))")
    public void interceptorAop() {
    }
    @Before("interceptorAop()")
    public void before(JoinPoint point) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        if("".equals(userId)||userId==null){
            throw new BusinessException("未登录状态");
        }
    }
}
