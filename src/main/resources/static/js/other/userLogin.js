$(function () {
    $("form :input.required").each(function () {
        var $required = $("<strong>*</strong>"); //创建元素
        $(this).parent().append($required); //然后将它追加到文档中
    });

    //3.文本框失去焦点后开始验证
    $('#email').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();

        //3.2验证邮件

        var reMail = /([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
        if (this.value == "" || (this.value != "" && !reMail.test(this.value))) {
            $parent.append('<span class="formtips">' + '请输入正确的email地址.' + '</span>');
        }


    })
    $('#pwd').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        //验证密码

        if (this.value.length < 8 || this.value.length > 16) {
            $parent.append('<span class="formtips">' + '请输入正确的密码.' + '</span>');
        }

    })
})

function loginUseEmail() {
    $(document).ready(function () {
        var element = $("#loginButton");
        element.html("<div class=\"text-center\">\n" +
            "  <div class=\"spinner-border text-success\" role=\"status\">\n" +
            "    <span class=\"sr-only\">Loading...</span>\n" +
            "  </div>\n" +
            "</div>");
    })
    // var email = $("#email").val();
    // var pwd = $("#pwd").val();
    // console.log("email: " + email + ",pwd: " + pwd);
    // var reMail = /([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
    // if (!reMail.test(email) || pwd.length < 8 || pwd.length > 16 || email == "" || pwd == "") {
    //     alert("邮箱或密码格式输入错误！");
    // } else {
    $.ajax({
        url: '/qingblog/userLogin',
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({"userMail": $("#email").val(), "userPassword": $("#pwd").val()})
    }).done(function (data) {
        if (data.status == 200) {
            console.log(data.toString());
            $(location).attr('href', '/qingblog/homePage');
        } else {
            alert(data.msg);
        }
    })
    // }
}
