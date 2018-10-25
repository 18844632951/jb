/**
 * 分类页脚本
 * Created by 邓水洪 on 2018/10/14.
 */
var type = {
    data: {
        types: null,
        tree: null, //左侧分类列表
        sTree: null, //下拉分类选择插件树
    },
    writeDataToInpit: function (path, type) { //  将数据写入文本框中展示
        $("input[name='id']").val(type.id);
        $("input[name='name']").val(type.name);
        $("input[name='pid']").val(path.name.join("/"));
        $("input[name='pid']").data("pid", path.id[path.id.length - 1]);
        $("input[name='path']").val(type.path);
        $("input[name='sortIndex']").val(type.sortindex);
        $("input[name='logo']").val(type.logo);
        $("input[name='seoKeyword']").val(type.seokeywords);
        $("input[name='seotitle']").val(type.seotitle);
        $("textarea[name='description']").val(type.description);
    },
    loadSelect: function (json) {   //加载树状下拉列表
        $('#btnMenu').focus(function () {
            if ($(this).attr("readonly")) {
                return;
            }
            $(this).selectMenu({
                title: "选择分类",
                showField: 'name',
                keyField: 'id',
                initSelected: 1,
                formatItem: function () {   //自定义菜单项目显示模板
                    var html = '<div id="selectTree"></div>';
                    return html;
                },
                data: json
            });

            if (type.data.sTree != null) {
                type.data.sTree.refresh(json);
            } else {
                type.data.sTree = new iTree();
                type.data.sTree.create("selectTree", json, function (row, e) {
                    if (row.pid == 0) {
                        $("input[name='pid']").val(e.innerText);
                    } else {
                        var name = $("input[name='pid']").val() + " / " + e.innerText;
                        $("input[name='pid']").val(name);
                    }
                    $("input[name='pid']").data("pid", row.id);
                    $("input[name='path']").val(row.path);
                });
            }
        });
    },
    getTree: function () {      //从服务端获取 【分类树】 数据
        var obj = this;
        $.get("http://localhost/type/tree", function (json) {
            json = JSON.parse(json);
            obj.data.types = json;
            if (type.data.tree != null) {
                type.data.tree.refresh(json);
            } else {
                type.data.tree = new iTree();
                type.data.tree.create("tree", json, function (data) {
                    $.get("http://localhost/type/" + data.id + "/get", function (dt) {
                        type.writeDataToInpit(type.data.tree.path, JSON.parse(dt));
                    });
                });
                obj.loadSelect(json);
            }
        });
    },
    getAndCkeckFrom: function (bool) {            //表单数据校验
        var inputs = $("#typeForm").find("input[type='text'],textarea");

        if ($("inputs[name='name']").val() == "") {
            $("inputs[name='name']").val("请输入...");
            $("inputs[name='name']").focus();
            return false;
        }
        if ($("inputs[name='name']").val() == "请输入...") {
            $("inputs[name='name']").focus();
            return false;
        }
        //清空path
        if ($("inputs[name='pid']").val() == "") {
            $("input[name='path']").val("");
        }
        var saveData = {};
        if (bool) {
            saveData.id = $("input[name='id']").val();
        } else {
            $("input[name='id']").val("");
        }
        saveData.name = $("input[name='name']").val();
        saveData.pid = $("input[name='pid']").data("pid");
        saveData.path = $("input[name='path']").val();
        saveData.logo = $("input[name='logo']").val();
        saveData.sortindex = $("input[name='sortIndex']").val();
        saveData.seotitle = $("input[name='seotitle']").val();
        saveData.seokeywords = $("input[name='seoKeyword']").val();
        saveData.description = $("textarea[name='description']").val();

        return saveData;
    },
    saveData: function (saveData, name) { //保存数据统一调用服务
        var ppt = new prompt('<i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i> ' + name + '中....');

        $.post("http://localhost/type/save", saveData, function (data) {
            data = JSON.parse(data);
            if (data.state == 200) {
                ppt.flush('<i class="fa fa-spinner fa-smile-o fa-3x fa-fw"></i> 恭喜！ ' + name + '成功');
                location.reload();
                ppt.close(2000);
            } else {
                ppt.flush('<i class="fa fa-spinner fa-meh-o fa-3x fa-fw"></i> 粗糙！ 网络跑丢了');
                ppt.close(2000);
            }
        });
    },
    initEvent: function () {
        function readOnlySetting(bool) {
            $(".form").find("input,textarea").attr("readonly", bool);
            if (!bool) {
                $(".form").find("input")[0].focus();
            }
        }

        $("#add").click(function () {  //添加事件
            $(".form").find("input,textarea").val("");
            $("#save").text("创建分类")
            readOnlySetting(false);
        });
        $("#edit").click(function () {  //编辑事件
            var id = $("input[name='id']").val();
            if (id == "") {
                popUp("操作提示", "请选择要编辑的类目");
                return;
            }
            $("#save").text("保存");
            readOnlySetting(false);
        });
        $("#del").click(function () {   //删除分类
            var id = $("input[name='id']").val();
            var name = $("input[name='name']").val();
            if (id == "") {
                popUp("操作提示", "<i class='fa fa-smile-o'></i> 请选择要移除的类目");
                return;
            }
            //popUp("温馨提示！", "<i class='fa fa-warning' style='color: red'>  确认要删除要 <strong>" + $("input[name='name']").val() + " </strong> 类目以及它的子类目吗？");
            var ppt = new prompt('<i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i> 正在删除类目...稍等！');
            var value = $("input[name='path']").val();

            $.ajax({
                url: "type/" + value + "/remove", type: "DELETE", success: function () {
                    ppt.flush(name + "已成功被移除！ fa fa-spinner fa-smile-o fa-3x fa-fw");
                    location.reload();
                    ppt.close(2000);
                }, error: function () {
                    ppt.flush("粗糙！  网络走丢了 <i class='fa fa-spinner fa-meh-o fa-3x fa-fw'>");
                    ppt.close(2000);
                }
            });
        });
        $("#save").click(function () {  //保存事件
            var rs;
            if ($(this).text() != "保存") {
                rs = type.getAndCkeckFrom(false);   //创建 不需要id
            } else {
                rs = type.getAndCkeckFrom(true);        //true  为更新 需要id
            }
            if (rs) {
                readOnlySetting(true);
                type.saveData(rs, $(this).text());
            }
        });
        $("#back").click(function () {  //取消事件
            readOnlySetting(true);
        });
    },
    init: function () { //初始化网页
        //初始化树
        this.getTree();
        //初始化事件
        this.initEvent();
    }
};
