$(function() {
    var editor = editormd("test-editor", {
        // width  : "100%",
        // height : "100%",
        path   : "editormd/lib/",
        emoji : true,
        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart : true,             // 开启流程图支持，默认关闭
        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
        imageUpload       : true,
        imageFormats      : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL    : "/qingblog/upLoadFile",
        // crossDomainUpload : true,
        uploadCallbackURL : "",
        onload : function() {
            // console.log('onload', this.data);
        }
    });
});


function save() {
    $().ready(function () {
        if($('#content').val()!=null){

        }
        else{
            alert("文本内容为空！");
        }
    })
}