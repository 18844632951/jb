var addPage = {
    util: null,
    imgs: [],
    ppt: null,
    editor: null,
    icon: {
        small: "<i class='fa fa-small-o fa-3x fa-fw' aria-hidden='true'></i> ",
        loading: "<i class='fa fa-spinner fa-pulse fa-3x fa-fw' aria-hidden='true'></i> ",
        net_bad: "<i class='fa fa-spinner fa-meh-o fa-3x fa-fw' aria-hidden='true'></i> ",
        worring: "<i class='fa fa-exclamation-triangle fa-2x' aria-hidden='true'></i> "
    },  //常用插件图标
    getBrand: function (name) {
        $.get("brand/" + name + "/search", function (data) {
            var json = JSON.parse(data);
            $("#brands").empty();
            if (json.state == 200) {
                if (json.data.length < 1) {
                    $("#brands").append("<tr><td colspan='5'  class='vmsg'>没有找到！ <a href='#'>去申请？</a></td></tr>");
                    return;
                }
                for (var i = 0; i < json.data.length; i++) {
                    var html = "<tr class='option' data-index='" + json.data[i].id + "'><td><i class='fa fa-check-square-o'></i></td><td>" + (i + 1) + "</td><td>" + json.data[i].name + "</td><td>" + json.data[i].englishname + "</td><td>" + json.data[i].fristletter + "</td><td> <img src='" + json.data[i].logo + "' width='80' height='40' alt=''></td></tr>";
                    $("#brands").append(html);
                }
            } else {
                $("#brands").append("<tr><td colspan='5'  class='vmsg'>网络异常！ <a href='#'>联系我们</td></tr>");
            }
        });
    },      //从服务端 获取品牌信息
    upFile: function () {
        var formData = new FormData();
        var files = $("#file")[0].files;
        var imgs = $("#viewPic").find("img").length;  //显示图片数量
        if (files.length > 5 || imgs > 5 || files.length + imgs > 5) {
            prompt("最多只能上传5张图片", 2000);
            return;
        }
        for (var i = 0; i < files.length; i++) {
            formData.append("file", files[i]);
        }
        util = new util();
        var dom = this;
        util.upload("product/upload", formData, function (aRs) {
            if (aRs.state == 200) {
                dom.imgs = aRs.data;
                for (var i in aRs.data) {
                    if (i > 5) {
                        return;
                    }
                    var html = '<div class="item-img"><img src="' + aRs.data[i] + '" width="80" height="80" alt="" /></div>';
                    $("#viewPic").append(html);
                }
            }
        });
    },      //文件上传
    checkForm: function () {
        if ($("input[name='id']").val() == "") {
            $("input[name='id']").focus();
            return false;
        }
        if ($("input[name='name']").val() == "") {
            $("input[name='name']").focus();
            return false;
        }

        if ($("input[name='subname']").val() == "") {
            $("input[name='subname']").focus();
            return false;
        }

        if ($("input[name='code']").val() == "") {
            $("input[name='code']").focus();
            return false;
        }

        if ($("input[name='brandid']").val() == "") {
            $("#v-brand").click();
            return false;
        }

        if ($("input[name='producttype']").val() == "") {
            $("#selected").click();
            return false;
        }

        if ($("input[name='richcontent']").val() == "") {
            $("input[name='richcontent']").focus();
            return false;
        }

        //组织数据
        var form = $.param({'resource': JSON.stringify(this.imgs)}) + "&" + $("#item-from").serialize() + "&" + $.param({'richcontent': this.editor.html()});
        return form;
    },
    submitProduct: function (data) {
        if (!this.ppt) {
            this.ppt = new prompt(this.icon.loading + " 商品发布中...，稍等！");
        }
        var dom = this;
        $.post("product/save", data, function (ajaxRs) {
            var json = JSON.parse(ajaxRs);
            if (json.state == 200) {
                dom.ppt.flush(dom.icon.small + " 商品发布成功！");
                dom.close(2000);
            } else {
                dom.ppt.flush(dom.icon.net_bad + " 抱歉！ 商品发布失败");
                dom.close(2000);
            }
        });
    },  //上传商品基本信息
    initEvent: function () {
        var dom = this;
        //点击上传按钮
        $("#upload").click(function () {
            $("#file").click();
        });
        //上传图片
        $("#file").change(function () {
            dom.upFile();
        });
        //搜索品牌
        $("#sbrand").on("propertychange input focus", function () {
            var name = $(this).val();
            if (name != null && name != "" && name != undefined) {
                dom.getBrand(name);
            }
        });
        //选择品牌事件
        $("body").on("click", ".option", function () {
            var tds = $(this).find("td");
            $("#v-brand").html($(tds[2]).text());
            $("input[name='brandid']").val($(this).data("index"));
            $("#v-brand").click();
        })

        //提交表单事件
        $("#submit").click(function () {
            //1 校验

            var data = dom.checkForm();
            if (data) {   //通过
                //2 提交
                dom.submitProduct(data);
            }
        });
    },       //初始化事件
    init: function () {
        util = new util();
        util.init();
        util.getTree(function (data) {
            var tree = new iTree();
            tree.create("selectTree", data, function (row, e) {
                $("#selected").html(row.name);
                $("input[name='producttype']").val(row.id);
                $("#selected").click();
            }, function () {
            });
        });

    }       //初始化数据
};
KindEditor.ready(function (K) {
    addPage.editor = K.create('#editer_id');
});

window.onload = function () {
    addPage.initEvent();
    addPage.init();
}

