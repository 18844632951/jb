<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>"/>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
    <link rel="stylesheet" type="text/css" href="rs/css/home.css">
    <link rel="stylesheet" type="text/css" href="rs/fonts/font-awesome.min.css">
    <style type="text/css">

        body {
            width: 100%;
            height: 100%;
        }

        #view {
            width: 100%;
            height: 100%;
        }

        .v-panel {
            display: none;
            width: 180px;
            height: 200px;
            border-radius: 4px;
            box-shadow: 0 0 30px rgba(0, 0, 0, .2);
            position: absolute;
            top: 55px;
            right: 130px;
            background: #f8f7f7;
        }

        .v-panel::after {
            width: 0;
            height: 0;
            content: "";
            border-left: 15px solid transparent;
            border-bottom: 10px solid #f8f8f8;
            border-right: 8px solid transparent;
            position: absolute;
            left: 100px;
            top: -10px;
        }

        .user-photo {
            height: 80px;
            text-align: center;
            line-height: 100px;
        }

        .user-photo > img {
            border: 1px solid rgba(0, 0, 0, .1);
            border-radius: 50%;
            margin-top: 20px;
        }

        #bgpanel {
            width: 100%;
            height: 100%;
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            background-color: rgba(0, 0, 0, .5);
            overflow: hidden;
            z-index: 99999999999;
            display: none;
        }

        #bgpanel .dialog {
            width: 70%;
            height: auto;
            margin: 80px auto;
            border-radius: 6px;
            overflow: hidden;
            box-shadow: 0 0 40px rgba(0, 0, 0, .3)
        }

        #bgpanel .dialog .winbar {
            height: 38px;
            background-color: #23282D;
        }

        #bgpanel .dialog .winbar .exit {
            display: block;
            line-height: 38px;
            text-align: center;
            float: right;
            color: #fff;
            font-size: 16px;
            font-family: "宋体";
            font-weight: 700;
            text-decoration: none;
            margin-right: 10px;
        }

        #bgpanel .dialog > iframe {
            width: 100%;
            height: 500px;
        }
    </style>
</head>
<body>
<header>
    <div class="head">
        <div class="container">
            <div class="logo">
                <span style="font-size: 20px;">JB平台管理中心</span>
            </div>
            <div class="nav">
                <a href="javascript:void(0)" id="switch-items"><i class="fa fa-navicon iui-tips-bottom"
                                                                  data-tips="显示/隐藏"></i></a>
                <div class="iui-search">
                    <input type="text" class="iui-input-null" placeholder="搜索...">
                </div>
            </div>
            <div class="info">
					<span id="i-tops">
						<i class="fa fa-edit"></i> 您好, 
						<a href="#"><strong>光绪</strong>
							<i class="fa fa-caret-down" data-tips="个人信息"></i>
						</a>
					</span>
                <a href="#">我的消息 <span class="iui-tag-num">10</span></a>
                <a href="index.jsp">退出 <i class="fa fa-sign-out"></i></a>
            </div>
        </div>
    </div>
</header>
<div id="container">
    <div id="left-wrap">
        <div class="topwrap">
            <div class="panel">
                <img class="photo" src="rs/images/default/login/admin-ico.png" width="60" height="60" alt="头像">
                <div class="info">
                    <p>管理员 <span class="iui-tag-num">10</span></p>
                    <p>276545@163.com</p>
                    <p><strong style="color:#fff">安全级别: </strong> 高</p>
                </div>
            </div>
        </div>
        <div class="items-wrap">
            <ul id="menus">
                <li class="item">
                    <a href="javascript:void(0)" class="alink">
                        <i class="fa fa-home"></i>
                        <span class="title"><strong>平台中心</strong> <i class="fa fa-caret-down"></i></span>
                    </a>
                    <ul class="sub-item">
                        <li>
                            <a href="about.html" target="windows" class="alink">
                                <span>门面装修</span>
                            </a>
                        </li>
                        <li>
                            <a href="about.html" target="windows" class="alink">
                                <span>店铺资料</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="item">
                    <a href="javascript:void(0)" class="alink">
                        <i class="fa fa-group"></i>
                        <span class="title">
								<strong>宝贝中心</strong> <i class="fa fa-caret-down"></i>
							</span>
                    </a>
                    <ul class="sub-item">
                        <li>
                            <a href="back/admin/product" target="windows" class="alink">
                                <span>商品列表</span>
                            </a>
                        </li>
                        <li>
                            <a href="back/admin/addProduct" target="windows" class="alink">
                                <span>发布宝贝</span>
                            </a>
                        </li>
                        <li>
                            <a href="back/admin/brand" target="windows" class="alink">
                                <span>品牌管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="back/admin/productType" target="windows" class="alink">
                                <span>分类管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="alink">
                                <span>规格管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="item">
                    <a href="#" class="alink">
                        <i class="fa fa-database"></i>
                        <span class="title">
								<strong>订单中心</strong> <i class="fa fa-caret-down"></i>
							</span>
                    </a>
                </li>
                <li class="item">
                    <a href="#" class="alink">
                        <i class="fa fa-globe"></i>
                        <span class="title">
								<strong>报表统计</strong> <i class="fa fa-caret-down"></i>
							</span>
                    </a>
                </li>
                <li class="item">
                    <a href="#" class="alink">
                        <i class="fa fa-cogs"></i>
                        <span class="title">
								<strong>营销中心</strong> <i class="fa fa-caret-down"></i>
							</span>
                    </a>
                    <ul class="sub-item">
                        <li>
                            <a href="#" class="alink">
                                <span>阿里巴巴</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="alink">
                                百货城
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div id="switchview">
        <iframe src="back/admin/product" id="view" name="windows" frameborder="0"></iframe>
    </div>
</div>

<div class="v-panel">
    <div class="user-photo">
        <img src="rs/images/default/login/admin-ico.png" width="60" height="60" alt="">
    </div>
</div>

<div id="bgpanel">
    <div class="dialog">
        <div class="winbar"><a href="javascript:void(0)" class="exit" id="exit"> × </a></div>
        <iframe src="back/admin/addProduct" frameborder="0" id="dialog" class="scroll"></iframe>
    </div>
</div>

<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript" src="rs/lib/jquery/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="rs/js/home.js"></script>

<script type="text/javascript">
    window.onload = function () {
        home.init();        //初始化主页事件

        var pm = null;
        document.getElementById("view").onload = function () {
            if (pm != null) {
                pm.close(0);
            }
            //pm = new prompt("加载中...",2000);

        }
    }
</script>
</body>
</html>