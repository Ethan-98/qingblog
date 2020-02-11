function upload() {
    $().ready(function () {
        $.ajax({
            url: '/qingblog/upLoadFile',
            data: $('#img').val(),
            processData: false, //因为data值是FormData对象，不需要对数据做处理。
            contentType: 'multipart/form-data',
            type: "POST"
        })
    })
}