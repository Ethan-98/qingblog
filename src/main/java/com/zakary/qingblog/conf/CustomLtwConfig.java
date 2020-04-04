package com.zakary.qingblog.conf;

import com.zakary.qingblog.aop.AuthorityAspect;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

import static org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving.AUTODETECT;
import static org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving.ENABLED;

/**
 * @ClassNameAopConfig
 * @Description
 * @Author
 * @Date2020/3/27 9:29
 * @Version V1.0
 **/
@Configuration
@ComponentScan("com.zakary.qingblog")
//@EnableLoadTimeWeaving(aspectjWeaving=ENABLED)
public class CustomLtwConfig{

    @Bean
    public AuthorityAspect authorityAspect(){
        AuthorityAspect authorityAspect= Aspects.aspectOf(AuthorityAspect.class);
        return authorityAspect;
    }

}
