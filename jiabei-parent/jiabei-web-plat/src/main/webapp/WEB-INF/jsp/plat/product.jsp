<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>"/>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
    <link rel="stylesheet" type="text/css" href="rs/css/product.css">
    <link rel="stylesheet" type="text/css" href="rs/fonts/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet"/>

</head>
<body>
<div class="locl">
    <strong><i class="fa fa-map-marker"></i> 位置</strong>
    <span>商品管理</span><em> / </em><span>商品列表</span>
</div>

<div class="container">
    <div class="bar">
        <div class="iui-row">
            <a href="javascript:void(0)"
               onclick="parent.home.dialog('back/admin/addProduct',{width:'800px',height:'400px'})"
               class="iui-btn iui-btn-primary">
                <i class="fa fa-plus-circle"></i> 发布商品
            </a>
            <a href="javascript:void(0)" class="iui-btn iui-btn-worring">
                <i class="fa fa-trash-o"></i> 删除商品
            </a>
            <a href="javascript:void(0)" class="iui-btn iui-btn-worring">
                <i class="fa fa-send"></i> 规格管理
            </a>
            <a href="javascript:void(0)" class="iui-btn iui-btn-worring">
                <i class="fa fa-cube"></i> SKU管理
            </a>
            <span class="iui-btn btn-group">
						<a href="javascript:void(0)" class="left-btn">升序</a>
						<a href="javascript:void(0)" class="right-btn">降序</a>
					</span>
        </div>
        <div class="iui-row">
            <span> 关键字 </span>
            <input type="text" class="iui-input iui-input-default" placeholder="关键字查找">
            <span> 时间段 </span>
            <input type="text" class="iui-input iui-input-default" placeholder="请选择开始日期" id="startDate">
            <input type="text" class="iui-input iui-input-default" placeholder="请选择结束日期" id="endDate">
            <span> 类型 </span>
            <select style="padding:3px 10px;margin-bottom: 15px;">
                <option><i class="fa fa-file"></i>手机/数码/电脑</option>
                <option><i class="fa fa-file"></i>服装/男装/女装</option>
                <option><i class="fa fa-file"></i>食品/辣条/饼干</option>
            </select>
            <span class="iui-btn iui-btn-primary">筛选</span>
        </div>
    </div>
    <div class="iui-panel">
        <table class="iui-table iui-table-border">
            <thead>
            <tr>
                <th class="check"><i class="fa fa-square-o"></i></th>
                <th>商品号</th>
                <th>商品名</th>
                <th>状态</th>
                <th>管理</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tb-list">
            <tr class="test">
                <td class="check"><i class="fa fa-check-square-o"></i></td>
                <td>86327672</td>
                <td>华为P20</td>
                <td><span class="iui-tag-default">正常</span></td>
                <td><span class="on-off iui-btn iui-btn-primary iui-btn-min">下架</span></td>
                <td>
                    <a href="javascript:void(0)" class="edt"><i class="fa fa-edit"></i> 编辑</a>
                    <a href="javascript:void(0)" class="del"><i class="fa fa-trash"></i> 删除</a>
                </td>
            </tr>
            <tr>
                <td class="check"><i class="fa fa-square-o"></i></td>
                <td>78975544</td>
                <td>华为meta10</td>
                <td><span class="iui-tag-default">缺货</span></td>
                <td><span class="on-off iui-btn iui-btn-worring iui-btn-min">上架</span></td>
                <td>
                    <a href="javascript:void(0)" class="edt"><i class="fa fa-edit"></i> 编辑</a>
                    <a href="javascript:void(0)" class="del"><i class="fa fa-trash"></i> 删除</a>
                </td>

            </tr>
            <tr>
                <td class="check"><i class="fa fa-square-o"></i></td>
                <td>1564548548</td>
                <td>小米note10</td>
                <td><span class="iui-tag-default">正常</span></td>
                <td><span class="on-off iui-btn iui-btn-primary iui-btn-min">下架</span></td>
                <td>
                    <a href="javascript:void(0)" class="edt"><i class="fa fa-edit"></i> 编辑</a>
                    <a href="javascript:void(0)" class="del"><i class="fa fa-trash"></i> 删除</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="iui-row inline-center">
        <div class="iui-paging" id="itemPage">
            <a href="javascript:void(0)" class="button flip">上一页</a>
            <a href="javascript:void(0)" class="button flip">下一页</a>
        </div>
    </div>
</div>


<!--模板-->
<div id="body" class="ibody scroll" style="display: none;">
    <div class="igroup">
        <label class="title">商品名称：</label>
        <input type="text" name="" class="iui-input iui-input-default">
    </div>
    <div class="igroup">
        <label class="title">商品价格：</label>
        <input type="number" name="" class="iui-input iui-input-default">
    </div>
    <div class="igroup">
        <label class="title">商品库存：</label>
        <input type="number" name="" class="iui-input iui-input-default">
    </div>
    <div class="igroup">
        <label class="title">商品类型：</label>
        <select class="iui-input-d">
            <option>选择宝贝分类</option>
            <option>服装</option>
            <option>手饰</option>
        </select>
    </div>
</div>

<script src="rs/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript" src="rs/js/product.js"></script>
<script type="text/javascript" src="rs/js/jquery-3.1.1.min.js"></script>

<script type="text/javascript">
    window.onload = function () {
        //TODO TEST 。。。
        new iPage("itemPage", 1, 20);
    }
</script>
</body>
</html>
