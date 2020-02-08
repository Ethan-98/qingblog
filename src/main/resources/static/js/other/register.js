$(function () {
    $("form :input.required").each(function () {
        var $required = $("<strong>*</strong>"); //创建元素
        $(this).parent().append($required); //然后将它追加到文档中
    });

    //3.文本框失去焦点后开始验证
    $('#email').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        var reMail = /([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
        if (this.value == "" || (this.value != "" && !reMail.test(this.value))) {
            $parent.append('<span class="formtips">' + '请输入正确的email地址.' + '</span>');
        }

    });
    $('#state').blur(function () {
        var $parent = $(this).parent();
        $parent.parent().find(".formtips").remove();
        //验证密码
        if (this.value.length < 8 || this.value.length > 16) {
            $parent.parent().append('<span class="formtips">' + '请输入正确的密码.' + '</span>');
        }


    })
    $('#userTel').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        if (this.value.length < 8 || this.value.length > 16) {
            $parent.append('<span class="formtips">' + '请输入正确的电话.' + '</span>');
        }

    })
    $('#username').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        if (this.value.length < 4 || this.value.length > 8) {
            $parent.append('<span class="formtips">' + '请输入正确的用户名.' + '</span>');
        }

    })
})


function changeState() {

    $(document).ready(function () {
        if ($('#state').attr('type') == "password") {
            console.log($('#state').attr('type'));
            $('#state').attr('type', 'text');
            $('#visibleButton').html("<i class=\"fa fa-eye\" aria-hidden=\"true\"></i>");
        } else {
            $('#state').attr('type', 'password');
            $('#visibleButton').html("<i class=\"fa fa-eye-slash\" aria-hidden=\"true\"></i>");
        }
    })
}

function register() {
    $().ready(function () {
        // var email = $('#email').val();
        // var pwd = $('#password').val();
        // var userTel = $('#userTel').val();
        // var username = $('#username').val();
        // // var profileImg=$('#img');
        // var reMail = /([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
        // if (username.length < 4 || username.length > 8 || pwd.length < 8 || pwd.length > 16 || !reMail.test(email) || userTel.length < 8 || userTel > 11) {
        //     alert("未输入有效字符！");
        // } else {
            $.ajax({
                url: '/qingblog/userRegister',
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({
                    "userName": $('#username').val(),
                    "userPassword": $('#state').val(),
                    "userTel": $('#userTel').val(),
                    "userMail": $('#email').val()
                })
            }).done(function (data) {
                if (data.status == 200) {
                    console.log(data);
                    alert("注册成功，请登录！");
                    $(location).attr('href', '/qingblog/homePage');
                } else {
                    alert(data.msg);
                }
            })
        // }

    })

}