package com.zakary.qingblog.validation;

/**
 * description: 参数校验分组,公用的字段建议抽离出来，由其他接口继承 <br>
 * date: 2020/2/8 22:41 <br>
 * author: cashew1 <br>
 * version: 1.0 <br>
 */
public abstract class ValidationGroups {
    //包含user类中userPassword
    public interface DefaultGroup{}
    //useremail,userpawwword，username,usertel
    public interface ResignGroup extends DefaultGroup{}
    //useremail,userpawwword
    public interface LoginGroup extends DefaultGroup{}
    //username usermail,usertel
    public interface UpdateGroup extends DefaultGroup{}
}
