/**
 * Created by 27654 on 2018/10/23.
 */
var brandPage = {
    pg: null,       //分页对象
    data: null,      //本页数据
    tree: null,     //分类树对象
    types: null,    //加载的类目
    chboxSelected: [],   //复选框选择行id
    plug: {save: null, del: null, dialog: null},
    icon: {
        small: "<i class='fa fa-small-o fa-3x fa-fw' aria-hidden='true'></i> ",
        loading: "<i class='fa fa-spinner fa-pulse fa-3x fa-fw' aria-hidden='true'></i> ",
        net_bad: "<i class='fa fa-spinner fa-meh-o fa-3x fa-fw' aria-hidden='true'></i> ",
        worring: "<i class='fa fa-exclamation-triangle fa-2x' aria-hidden='true'></i> "
    },  //常用插件图标
    formate: function (tim) {
        if (tim == undefined) {
            return "----";
        }
        var time = new Date(tim);
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return y + "年 " + m + "月 " + d + " 日" + h + " : " + mm + " : " + s;
    },  //时间转换
    staticData: function (rs) {
        $("#dataWrap").empty();
        var data = JSON.parse(rs.data);
        this.data = data;
        if (data == "" || data == [] || data == null) {
            var html = "<tr><td colspan='12' style='text-align: center;font-size:14px;'><i class='fa fa-spinner fa-meh-o fa-fw' aria-hidden='true'></i> 没有找该品牌!</td></tr>";
            $("#dataWrap").append(html);
            return;
        }
        for (var i = 0; i < data.length; i++) {
            var html = '<tr><td><i class="fa fa-square-o checkbox" data-index="0" data-rowid="' + data[i].id + '"></i></td><td>' + (i + 1) + '</td><td>' + data[i].name + '</td><td>' + data[i].englishname + '</td><td>' + data[i].fristletter + '</td><td>' + data[i].description + '</td><td>' + data[i].types.name + '</td><td>' + data[i].sortindex + '</td><td><img src="' + data[i].logo + '"width=" 80"height="40"alt=""></td><td>' + this.formate(data[i].createtime) + '</td><td>' + this.formate(data[i].updatetime) + '</td><td> <a href="javascript:void(0)" class="edt" data-index="' + data[i].id + '"><i class="fa fa-edit"></i></a> <a href="javascript:void(0)" class="del" data-index="' + data[i].id + '"><i class="fa fa-trash"></i></a></td></tr>';
            $("#dataWrap").append(html);
        }
    },  //静态化数据
    checkPage: function (row, length, search) {
        var json = {};
        if (row == null || row == "" || isNaN(row)) {
            prompt(this.icon.net_bad + "抱歉！ 数据加载失败！", 2000);
            return false;
        }
        if (length != null || length != undefined) {
            if (isNaN(length)) {
                json.length = length;
            } else {
                prompt(this.icon.net_bad + "抱歉！ 数据加载失败！", 2000);
                return false;
            }
        }
        if (search != undefined) {
            json = search;
        }
        return json;
    },  //检验分页参数是否完整
    getData: function (row, length, search) {
        var dom = this;

        var json = this.checkPage(row, length, search);
        if (!json) {        //检验不通过
            return;
        }
        $.get("http://localhost/brand/" + row + "/json", json, function (ajRs) {
            var rs = JSON.parse(ajRs);
            if (rs.state == 201) {
                dom.staticData(rs.data);
                if (dom.pg == null) {
                    dom.pg = new iPage("myPage", rs.data.row, rs.data.size, function (row) {
                        dom.getData(row);
                    });
                }
            } else {
                prompt(dom.icon.net_bad + data.msg, 2000);
            }
        });
    },  //带分页的数据加载
    loadSelect: function (e) {   //加载树状下拉列表
        var dom = this;
        $(e).selectMenu({
            title: "选择分类",
            showField: 'name',
            keyField: 'id',
            initSelected: 1,
            formatItem: function () {   //自定义菜单项目显示模板
                var html = '<div id="selectTree"></div>';
                return html;
            },
            data: dom.types
        });

        var tree = this.tree;

        if (tree != null) {
            tree.refresh(this.types);
        } else {
            tree = new iTree();
            tree.create("selectTree", this.types, function (row, e) {
                $("#btnMenu").val(row.name);
                $("#types").val(row.id);
            }, function () {
            });
        }
    },  //初始化分类插件
    getTree: function () {      //从服务端获取 【分类树】 数据
        var dom = this;
        $.get("http://localhost/type/tree", function (json) {
            dom.types = JSON.parse(json);
        });
    },      //读取分类
    submitForm: function (data) {
        var dom = this;
        if (this.plug.save == null) {
            dom.plug.save = new prompt(this.icon.loading + " 数据处理中...稍等！");
            $.post("brand/save", data, function (ajaxRs) {
                var json = JSON.parse(ajaxRs);
                if (json.state == 200) {
                    //重新加载数据
                    dom.getData(dom.pg.row, dom.size);
                    dom.plug.save.flush(dom.icon.small + json.msg);
                    dom.plug.save.close(2000);
                } else {
                    dom.plug.save.flush(dom.icon.net_bad + json.msg);
                    dom.plug.save.close(2000);
                }
            });
        }
    },  //提交数据 统一服务
    deleteRow: function (row) {
        var dom = this;
        if (this.plug.del == null) {
            this.plug.del = new prompt(this.icon.loading + "删除中...");
            $.post("brand/" + row.id + "/remove", {url: row.logo}, function (ajaxRs) {
                var json = JSON.parse(ajaxRs);
                if (json.state == 200) {
                    dom.plug.del.flush(dom.icon.small + " 删除成功！");
                    dom.plug.del.close(2000);
                    dom.getData(dom.pg.row, dom.size);
                } else {
                    dom.plug.del.flush(dom.icon.small + json.msg);
                    dom.plug.del.close(2000);
                }
            })
        }

    },  //删除指定行信息
    uploadFile: function (e) {
        var formData = new FormData();
        formData.append("file", $(e)[0].files[0]);
        new util().upload("brand/upload", formData, function (data) {
            $("#view").html("<img src='" + data.data + "' width=100 height= 40 alt='浏览图'/>");
        });
    },  //文件上传统一服务
    checkForm: function (data) {
        if (data.name == null || data.name == "" || data.name == undefined || data.name == "请输入...") {
            $("input[name='name']").val("请输入...");
            $("input[name='name']").focus();
            return false;
        }
        if (data.producttype == null || data.producttype == "" || data.producttype == undefined || data.producttype == "请输入...") {
            $("input[name='producttype']").val("请输入...");
            $("input[name='producttype']").focus();
            return false;
        }
        if (data.logo == null || data.logo == "" || data.logo == undefined || data.logo == "请输入...") {
            $("#view").html(" <stong style='color:red'> 请选择品牌 LOGO</stong>");
            return false;
        }
        if (data.englishname == null || data.englishname == "" || data.englishname == undefined || data.englishname == "请输入...") {
            $("input[name='englishname']").val("请输入...");
            $("input[name='englishname']").focus();
            return false;
        }
        if (data.fristletter == null || data.fristletter == "" || data.fristletter == undefined || data.fristletter == "请输入...") {
            $("input[name='fristletter']").val("请输入...");
            $("input[name='fristletter']").focus();
            return false;
        }

        return true;
    },  //校验表单信息
    openDialog: function (title, btnName, row) {
        var dom = this;
        var data = {};
        var body = document.getElementById("body");
        if (this.plug.dialog == null) {
            this.plug.dialog = new Dialog(title, "editer", body, btnName, function (close) {
                data.name = $("#editer").find("input[name='name']").val();
                data.producttype = $("#editer").find("input[name='productType']").val();
                data.englishname = $("#editer").find("input[name='englishname']").val();
                data.fristletter = $("#editer").find("input[name='fristletter']").val();
                data.sortindex = $("#editer").find("input[name='sortindex']").val();
                data.logo = $("#editer").find("#view >img").attr("src");
                data.description = $("#editer").find("input[name='description']").val();
                var bool = dom.checkForm(data);
                if (bool) {
                    dom.submitForm(data);
                    close();
                }
            });
            this.plug.dialog.setting("width: 700px;height: 520px;");
        }
        this.plug.dialog.show();
        if (row != null) {
            data.id = row.id;
            data.url = row.logo;
            $("#editer").find("input[name='name']").val(row.name);
            $("#editer").find("input[name='typeName']").val(row.types.name);
            $("#editer").find("input[name='productType']").val(row.producttype);
            $("#editer").find("input[name='englishname']").val(row.englishname);
            $("#editer").find("input[name='fristletter']").val(row.fristletter);
            $("#editer").find("input[name='sortindex']").val(row.sortindex);
            $("#editer").find("#view").html("<img src='" + row.logo + "' width=100 height=40 />");
            $("#editer").find("input[name='description']").val(row.description);
        } else {
            $("#editer").find("input").val("");
            $("#editer").find("#view").html("");
        }
    },      //打开对话框
    init: function () {
        this.getData(1);
        this.getTree();
    },  //初始化品牌页
    initEvent: function () {
        var dom = this;
        //添加品牌
        $("#add").click(function () {
            dom.openDialog("新增品牌", "提交", null);
        });

        //编辑品牌
        $("body").on("click", ".edt", function () {
            var id = $(this).data("index");
            for (var i in dom.data) {
                if (dom.data[i].id == id) {
                    dom.openDialog("编辑品牌", "提交", dom.data[i]);
                    break;
                }
            }
        });

        //删除
        $("body").on("click", ".del", function () {
            var id = $(this).data("index");
            var dialog = new Dialog("操作提示", "isdel", dom.icon.worring + "确认要删除吗？", "删除", function (close) {
                var bool;
                for (var i in dom.data) {
                    if (dom.data[i].id == id) {
                        bool = dom.deleteRow(dom.data[i]);
                        break;
                    }
                }
                close();
            });
            dialog.setting("width: 320px;height:200px;");
            dialog.show();
        });

        //分类事件
        $('body').on("click", "#btnMenu", function () {
            dom.loadSelect(this);
        });

        //文件上传事件
        $("body").on("change", "#file", function () {
            dom.uploadFile(this);
        });

        //选择框选择事件
        $('body').on('click', ".checkbox", function () {
            if ($(this).data("index") == 0) { //为被选中
                this.className = "fa fa-check-square-o checkbox";
                $(this).data("index", 1);
                var id = $(this).data("rowid");
//                    dom.chboxSelected[id] = id;  TODO 待续....
                dom.chboxSelected.push(id);
            } else {
                var id = $(this).data("rowid");
                dom.chboxSelected[id] = null;
                this.className = "fa fa-square-o checkbox";
                $(this).data("index", 0);
            }
        });

        //全选、反选
        $("#checkAll").click(function () {
            var className;
            if ($(this).data("index") == 0) { //全选
                this.className = "fa fa-check-square-o";
                className = "fa fa-check-square-o checkbox";
                $(this).data("index", 1)
            } else {//反选
                this.className = "fa fa-square-o";
                className = "fa fa-square-o checkbox";
                $(this).data("index", 0)
            }
            var boxs = $("#dataWrap").find(".checkbox");
            boxs.each(function () {
                this.className = className;
            });
        });

        //搜索
        $("#search").click(function () {
            var search = {};
            var key = $("#keys").val();
            var value = $("input[name='value']").val();
            if (key == 'name') {
                search.name = value;
            }
            if (key == 'id') {
                search.id = value;
            }
            if (key == 'englishname') {
                search.englishname = value;
            }
            if (key == 'fristletter') {
                search.fristletter = value;
            }
            if (key == 'sortindex') {
                search.sortindex = value;
            }
            dom.getData(dom.pg.row, null, search);
        });

        //批量删除
        $("#dels").click(function () {
            //TODO  待续。。。。。。
        });
    },  //初始化事件
}


