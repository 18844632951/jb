<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品分类</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
    <link rel="stylesheet" type="text/css" href="rs/css/productType.css">
    <link rel="stylesheet" type="text/css" href="rs/lib/jquery/css/selectmenu.css"/>
    <link rel="stylesheet" type="text/css" href="rs/fonts/font-awesome.min.css">
</head>
<body>
<div class="container">
    <div class="iui-panel">
        <div class="left">
            <div class="iui-row">
                <span class="iui-title">分类列表</span>
            </div>
            <div class="iui-bar">
                <span class="iui-btn iui-btn-defalut iui-btn-min" id="add"><i class="fa fa-plus"></i> 创建</span>
                <span class="iui-btn iui-btn-defalut iui-btn-min" id="edit"><i class="fa fa-pencil"></i> 编辑</span>
                <span class="iui-btn iui-btn-defalut iui-btn-min" id="del"><i class="fa fa-trash"></i> 移除</span>
            </div>
            <div class="iui-line"></div>
            <!--树结构的容器-->
            <div id="tree"></div>
        </div>
        <div class="right">
            <div class="iui-row">
                <span class="iui-title">分类列表</span>
            </div>

            <div class="form" id="form">
                <form id="typeForm">
                    <div class="form-group">
                        <span class="title">名称</span>
                        <input type="text" name="name" class="iui-input iui-input-default" readonly="readonly" required>
                        <input type="hidden" name="id" disabled="disabled">
                    </div>
                    <div class="form-group">
                        <span class="title">父类</span>
                        <input type="text" name="pid" id="btnMenu" class="iui-input iui-input-default"
                               readonly="readonly">
                        <input type="hidden" name="path" disabled="disabled">
                    </div>
                    <div class="form-group">
                        <span class="title">排序</span>
                        <input type="text" name="sortIndex" class="iui-input iui-input-default" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <span class="title">logo</span>
                        <input type="text" name="logo" class="iui-input iui-input-default" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <span class="title">SEO关键字</span>
                        <input type="text" name="seoKeyword" class="iui-input iui-input-default" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <span class="title">分类标题</span>
                        <input type="text" name="seotitle" class="iui-input iui-input-default" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <span class="title">描述</span>
                        <textarea class="iui-input iui-input-default" name="description"
                                  style="width: 100%;height: 50px;padding: 0;" readonly="readonly"></textarea>
                    </div>
                    <div class="iui-row inline-center" style="height: 50px;line-height: 50px;">
                        <span class="iui-btn iui-btn-primary" id="save">保存</span>
                        <span class="iui-btn iui-btn-worring" class="back">取消</span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/selectmenu.min.js"></script>
<script type="text/javascript" src="rs/js/productType.js"></script>
<script type="text/javascript">
    window.onload = function () {
        type.init();
    }
</script>
</body>
</html>