function loginUseEmail(){
    $(document).ready(function () {
        var element=$("#loginButton");
        element.html("<div class=\"text-center\">\n" +
            "  <div class=\"spinner-border text-success\" role=\"status\">\n" +
            "    <span class=\"sr-only\">Loading...</span>\n" +
            "  </div>\n" +
            "</div>");
    })
    var email=$("#email").val();
    var pwd=$("#pwd").val();
    console.log("email: "+email+",pwd: "+pwd);
    var reMail=/([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
    if(!reMail.test(email)||pwd.length<8||pwd.length>16||email==""||pwd==""){
        alert("邮箱或密码格式输入错误！");
    }
    else{
        $.ajax({
            url: '/qingblog/userLogin',
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            data : JSON.stringify({"userMail":email,"userPassword":pwd})
        }).done(function (data) {
            if(data.status==200){
                console.log(data.toString());
                $(location).attr('href', '/qingblog/homePage');
            }
            else{
                alert(data.msg);
            }
        })
    }
}

function changeState() {

    $(document).ready(function () {
        if($('#state').attr('type')=="password"){
            console.log($('#state').attr('type'));
            $('#state').attr('type','text');
            $('#visibleButton').html("<i class=\"fa fa-eye\" aria-hidden=\"true\"></i>");
        }
        else{
            $('#state').attr('type','password');
            $('#visibleButton').html("<i class=\"fa fa-eye-slash\" aria-hidden=\"true\"></i>");
        }
    })
}

function register(){
    $().ready(function () {
        var email=$('#email');
        var pwd=$('#password');
        var userTel=$('#userTel');
        var username=$('#username');
        var profileImg=$('#img');
        var reMail=/([a-z]|\d)+\@([a-z]|\d)+\.([a-z]|\d)+/i;
        if(username.length<4||username.length>8||pwd.length<8||pwd.length>16||!reMail.test(email)||userTel.length<8||userTel>11||profileImg==null){
            alert("未输入有效字符！");
        }
        else{
            $.ajax({
                url: '/qingblog/register',
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data : JSON.stringify({"userName":username,"userPassword":pwd,"uerTel":userTel,"userMail":email})
            }).done(function (data) {
                if(data.status==200){
                    console.log(data);
                    alert("注册成功，请登录！");
                    $(location).attr('href', '/qingblog/homePage');
                }
                else{
                    alert(data.msg);
                }
            })
        }

    })

}
