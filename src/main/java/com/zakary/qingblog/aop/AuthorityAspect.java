package com.zakary.qingblog.aop;

import com.zakary.qingblog.QingblogApplication;
import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.mapper.UserMapper;
import com.zakary.qingblog.service.LoginService;
import com.zakary.qingblog.utils.AnalysisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassNameAuthorityAspect
 * @Description
 * @Author
 * @Date2020/3/31 22:25
 * @Version V1.0
 **/
//博客权限
    /*
    0：正常
    1：受限，不可上传新博客
    2：锁定：不可上传博客，不可评论点赞收藏，添加标签
    3：注销
     */
@Aspect
@DeclarePrecedence("InterceptorAspect,AuthorityAspect,LoginAspect,ParamsCheckAspect,LoggerAspect")
public class AuthorityAspect {

    @Autowired
    public LoginService loginService;

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
    @Pointcut("execution(* com.zakary.qingblog.controller.LoginController.userLogin(..))")
    public void loginAuthority() { }

    @Before("loginAuthority()")
    public void loginAuthority(JoinPoint point) throws Throwable{
        Object[] objArgs = point.getArgs();
        String mail= AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        User user=loginService.getUserInfo(mail);
        int userState=user.getUserState();
        if(userState==3){
            throw new BusinessException("此账户已被注销！");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller.BlogController.upLoadMarkdown(..)) || " +
            "execution(* com.zakary.qingblog.controller.BlogController.alterBlog(..)) || " +
            "execution(* com.zakary.qingblog.controller.BlogPraiseController.addBlogPraise(..)) || " +
            "execution(* com.zakary.qingblog.controller.BlogPraiseController.delBlogPraise(..)) || " +
            "execution(* com.zakary.qingblog.controller.LabelController.addLabel(..))")
    public void lockedUserAuthority() {}

    @Before("lockedUserAuthority()")
    public void lockedUserAuthority(JoinPoint point) throws Throwable{
        Object[] objArgs = point.getArgs();
        String mail= AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        User user=loginService.getUserInfo(mail);
        int userState=user.getUserState();
        if(userState==2){
            throw new BusinessException("此账户已被锁定！");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller.BlogController.upLoadMarkdown(..)) || " +
            "execution(* com.zakary.qingblog.controller.BlogController.alterBlog(..))")
    public void limitedserAuthority() { }

    @Before("limitedserAuthority()")
    public void limitedserAuthority(JoinPoint point) throws Throwable{
        Object[] objArgs = point.getArgs();
        String mail= AnalysisUtils.getObjectToMap(objArgs[0]).get("userMail").toString();
        User user=loginService.getUserInfo(mail);
        int userState=user.getUserState();
        if(userState==1){
            throw new BusinessException("此账户已被锁定！");
        }
    }

    @Pointcut("execution(* com.zakary.qingblog.controller..*.*(..)) && "+
            "(!execution(* com.zakary.qingblog.controller.LoginController.userLogin(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.BlogController.viewAllBlogList(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.UserController.selectInfoByUserId(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.LabelController.selectLabelByBlogId(..))) && "+
            "(!execution(* com.zakary.qingblog.controller.FileController.getImg(..))) && " +
            "(!execution(* com.zakary.qingblog.controller.UserController.userRegister(..)))")
    public void abnormalAuthority(){ }

    @Around("abnormalAuthority()")
    public Object abnormalAuthority(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        String userId=session.getAttribute("userId")!=null?session.getAttribute("userId").toString():null;
        int state=loginService.selectExceptPwd(Integer.parseInt(userId)).getUserState();
        if(state!=0){
            logger.info("Illegal User("+userId+") : Method : "+point.getTarget());
        }
        Object object =point.proceed();
        return object;
    }
}
