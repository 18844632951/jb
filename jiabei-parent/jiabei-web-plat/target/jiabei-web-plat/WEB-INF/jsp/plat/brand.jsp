<%--
  Created by IntelliJ IDEA.
  User: 27654
  Date: 2018/10/14
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>品牌管理</title>
    <base href="<%=basePath%>"/>
    <link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
    <link rel="stylesheet" type="text/css" href="rs/css/brand.css">
    <link rel="stylesheet" type="text/css" href="rs/lib/jquery/css/selectmenu.css"/>
    <link rel="stylesheet" type="text/css" href="rs/fonts/font-awesome.min.css">
</head>
<body>
<div class="locl">
    <strong><i class="fa fa-map-marker"></i> 位置</strong>
    <span>宝贝中心</span> / <span>品牌管理</span>
</div>

<div class="container">
    <div class="iui-bar">
        <span class="iui-btn iui-btn-primary" id="add"><i class="fa fa-plus"></i> 添加</span>
        <span class="iui-btn iui-btn-worring" id="del"><i class="fa fa-trash"></i> 批量删除</span>
        <form class="search-warp">
            <select class="iui-input iui-input-default" id="keys" style="width: 80px;">
                <option selected value="name">品牌名</option>
                <option value="id">品牌号</option>
                <option value="englishname">英文名</option>
                <option value="fristletter">首字母</option>
                <option value="sortindex">排序号</option>
                <option value="createtime">日期</option>
            </select>
            <input type="text" class="iui-input iui-input-default" name="value" placeholder="条件">
            <span class="iui-btn iui-btn-primary" id="search">查找</span>
        </form>
    </div>
    <div class="iui-panel">
        <table class="iui-table">
            <thead>
            <tr>
                <th><i class="fa fa-square-o" id="checkAll" data-index="0"></i></th>
                <th>编号</th>
                <th>品牌名</th>
                <th>英文名</th>
                <th>首字母</th>
                <th>描述</th>
                <th>类型</th>
                <th>排序号</th>
                <th>logo</th>
                <th>创建日期</th>
                <th>最近更新</th>
                <th width="70">操作</th>
            </tr>
            </thead>
            <tbody id="dataWrap">
            <tr>
                <td colspan="12" height="60" style="line-height: 60px;text-align: center; font-size: 16px;">
                    <i class="fa fa-spinner fa-pulse fa-fw" aria-hidden="true"></i> 数据加载中... 请稍等！
                </td>
            </tr>
            </tbody>
        </table>

        <div class="iui-row inline-center">
            <div class="iui-paging" id="myPage">
                <a href="javascript:void(0)" class="button flip">上一页</a>
                <a href="javascript:void(0)" class="button flip">下一页</a>
            </div>
        </div>
    </div>
</div>

<!--模板-->
<script type="myModel" id="body">
<div class="ibody scroll">
    <div class="igroup nowrap">
        <label class="title">名称 </label>
        <input type="text" name="name" class="iui-input iui-input-default">
    </div>
    <div class="igroup nowrap">
        <label class="title">分类 </label>
        <input type="text" name="typeName" class="iui-input iui-input-default" id="btnMenu">
        <input type="hidden" name="productType" id="types">
    </div>
    <div class="igroup nowrap">
        <label class="title">英文名 </label>
        <input type="text" name="englishname" class="iui-input iui-input-default">
    </div>
    <div class="igroup nowrap">
        <label class="title">首字母 </label>
        <input type="text" name="fristletter" class="iui-input iui-input-default">
    </div>
     <div class="igroup nowrap">
        <label class="title">排序号 </label>
        <input type="text" name="sortindex" class="iui-input iui-input-default">
    </div>
    <div class="igroup">
        <label class="title">logo </label>
        <span class="iui-btn iui-btn-primary" onclick = "document.getElementById('file').click();">选择文件</span>
        <input type="file" name="file" style="display: none" id="file">
        <span id="view"></span>
    </div>
    <div class="igroup nowrap">
        <label class="title">描述 </label>
        <input type="text" name="description" class="iui-input iui-input-default">
    </div>
</div>

</script>

<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/selectmenu.min.js"></script>
<script type="text/javascript" src="rs/js/common.js"></script>
<script type="text/javascript" src="rs/js/brand.js"></script>
<script type="text/javascript">
    window.onload = function () {
        brandPage.init();
        brandPage.initEvent();
    }
</script>
</body>
</html>