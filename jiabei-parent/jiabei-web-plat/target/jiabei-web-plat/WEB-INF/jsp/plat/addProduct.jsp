<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加商品</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="rs/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
    <link rel="stylesheet" type="text/css" href="rs/css/commom.css">
    <link rel="stylesheet" type="text/css" href="rs/css/addProduct.css">
</head>
<body>
<div class="locl">
    <strong><i class="fa fa-map-marker"></i> 位置</strong>
    <span>商品管理</span>
    <i class="fa fa-caret-right"></i>
    <span>商品发布</span>
</div>
<div class="container">
    <form action="#" method="post" id="item-from">
        <!--商品的基本信息-->
        <div class="item_base_info" id="ite-base">
            <div class="iui-row nowrop">
                <div class="input-wrap">
                    <span class="tit">货号 </span>
                    <input type="text" name="id" class="iui-input iui-input-default" placeholder="在这里填写你的宝贝货号" required>
                </div>
                <div class="input-wrap">
                    <span class="tit">宝贝名称</span>
                    <input type="text" name="name" class="iui-input iui-input-default" placeholder="填写宝贝名称" required>
                </div>
            </div>
            <div class="input-wrap">
                <span class="tit">副标题</span>
                <input type="text" name="subname" class="iui-input iui-input-default" placeholder="宝贝副标题" required>
            </div>
            <div class="input-wrap">
                <span class="tit">编码</span>
                <input type="text" name="code" class="iui-input iui-input-default" placeholder="宝贝编码" required>
            </div>
            <div class="input-wrap ">
                <span class="tit">品牌</span>
                <input type="hidden" name="brandid">
                <div class="select s-brand">
                    <div class="head">
                        <div class="selected" id="v-brand">
                        </div>
                        <span class="open"><i class="fa fa-caret-down" aria-hidden="true"></i></span>
                    </div>
                    <div class="body scroll">
                        <div class="search">
                            <input type="text" id="sbrand" autofocus="autofocus" placeholder="搜索品牌...">
                        </div>
                        <div class="options">
                            <table class="iui-table">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>编号</th>
                                        <th>名称</th>
                                        <th>英文名</th>
                                        <th>首字母</th>
                                        <th>logo</th>
                                    </tr>
                                </thead>
                                <tbody id="brands">
                                    <td colspan="5" height="40" style="line-height: 40px;text-align: center; font-size: 14px;">
                                        <i class="fa fa-spinner fa-pulse fa-fw" aria-hidden="true"></i> 数据加载中... 请稍等！
                                    </td>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="input-wrap">
                <span class="tit">类型</span>
                <input type="hidden" name="producttype">
                <div class="select s-types">
                    <div class="head">
                        <div class="selected" id="selected"></div>
                        <span class="open"><i class="fa fa-caret-down" aria-hidden="true"></i></span>
                    </div>
                    <div class="body scroll">
                        <div class="search">
                            <input type="text" tofocus="autofocus">
                        </div>
                        <div class="options" id="selectTree"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <span class="tit" style="line-height: 100px;">商品简介 </span>
            <div class="area">
                <textarea placeholder="简单的介绍一下商品" name="description"></textarea>
            </div>
        </div>

        <div class="row">
            <span class="tit" style="line-height: 40px">商品浏览图</span>
            <span class="iui-btn iui-btn-primary" id="upload">上传图片</span>
            <span style="color: #666; font-size: 12px;display: inline-block;"> * 最多上传5张</span>
            <form id="uploadForm" enctype="multipart/form-data">
                <input type="file" name="file[]" id="file" multiple="multiple"/>
            </form>
            <div class="img-wrap" id="viewPic"></div>
        </div>

        <div class="iui-row">
            <div class="editer">
                <h4 class="tit">商品详情</h4>
                <textarea id="editer_id" name="richcontent"></textarea>
            </div>
        </div>

        <div class="iui-row submit-wrap">
            <span class="iui-btn iui-btn-defalut" id="pre-btn">取消</span>
            <span class="iui-btn iui-btn-primary" id="submit">发 布</span>
        </div>
    </form>
</div>

<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript" src="rs/js/common.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>rs/lib/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="rs/js/addProduct.js"></script>
</body>
</html>