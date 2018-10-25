/**
 * Created by 27654 on 2018/10/21.
 */
function util() {
    this.types = null;     //存放分类列表信息
    this.brands = null;     //存放商家的品牌信息
}

//文件上传
util.prototype.upload = function (url, stream, method) {
    var ppt = new prompt('<i class="fa fa-spinner fa-pulse"></i>  上传中...',1000000);
    $.ajax({
        url: url,
        type: "POST",
        data: stream,
        cache: false,
        processData: false,
        contentType: false,
        success: function (aRs) {
            var json = JSON.parse(aRs);
            method(json);
            ppt.flush('<i class="fa fa-cloud-upload"></i>  ' + json.msg);
            ppt.close(2000);
        },
        error: function () {
            ppt.flush('<i class="fa fa-cloud-upload"></i>  抱歉！网络异常，暂时无法上传');
            ppt.close(2000);
        }
    });

}

//从服务端获取 【分类树】 数据
util.prototype.getTree = function (method) {
    var dom = this;
    $.get("http://localhost/type/tree", function (json) {
        dom.types = JSON.parse(json);
        method(dom.types);
    });
}

//获取品牌
util.prototype.getBrand = function () {
    var dom = this;
    $.get("brand/get",function (data) {
        dom.brands = JSON.parse(data);
    });
}
//下拉事件
util.prototype.init = function () {
    var sub = null;
    $("body").on("click", ".select >.head" , function(e){
        e.stopPropagation();
        sub = $(this).siblings();
        sub.slideToggle(200);
    });
}
