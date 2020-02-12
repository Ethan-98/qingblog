$(function () {
    var editor = editormd("test-editor", {
        // width  : "100%",
        // height : "100%",
        path: "editormd/lib/",
        saveHTMLToTextarea: true,
        emoji: true,
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/qingblog/upLoadImg",
        // crossDomainUpload : true,
        uploadCallbackURL: "",
        onchange:function () {
            // let dom = document.createElement("div");
            // dom.innerHTML = ;
            $('<div>'+editor.getHTML()+'</div>').find('img').each(function (index, element) {
                console.log(index+":"+element)
            })
        }
    });

    $('#submitButton').click(function () {
        var markdown = editor.getMarkdown();
        $.ajax({
            url: '/qingblog/upLoadMarkdown',
            data: {'title': 'title', 'text': btoa(encodeURI(markdown))},
            contentType: "application/json",
            dataType: 'json',
            type: 'post'
        }).done(function (data) {
            if (data.status != 200) {
                alert("提交成功！");
            } else {
                alert(data.msg);
            }

        })

    })
});
