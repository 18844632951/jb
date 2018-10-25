<!DOCTYPE html>
<html>
<head>
	<title>宝贝参数管理</title>
	<link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome.min.css">
	<style type="text/css">
		*{margin:0;padding: 0; font-family: "微软雅黑"}			
		.head{height: 60px; background-color: #f9f9f9; border-bottom: 1px solid rgba(0,0,0,.1);}
		.head .container{width: 94%; height: 100%; margin: auto; overflow: hidden;}
	 	.head .nav{width: 50%;height: 100%; line-height: 60px;float: left; white-space: nowrap; overflow: hidden;}	
		.head .nav .iui-search{}
		.head .nav a{line-height: 60px;margin:0 8px;}
		.head .info{width: 50%; line-height: 60px; float: right; font-size: 14px;text-align: right;}	
		.head .info a{text-decoration: none; margin-right: 10px;}
		.head .info i{color: #bbb;}
		#view{width: 100%; height: 100%;}
		

		.iui-group{width:auto; display: inline-flex; height: 40px;}	
		.iui-group >.iui-input{width: 180px;}
		.add{text-decoration: none; color:#3434A5; margin:4px;}
	</style>
</head>
<body>
	<header>
		<div class="head">
			<div class="container">
				<div class="nav">
					<a href="javascript:void(0)" id="switch-items"><i class="fa fa-navicon iui-tips-bottom" data-tips="显示/隐藏"></i></a>
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
					<a href="index.html">退出 <i class="fa fa-sign-out"></i></a>
				</div>
			</div>
		</div>
	</header>
	<div class="iui-main">
		<div class="iui-title">
			规格管理
		</div>
		<div class="iui-panel">	
			<p class="iui-row"><span class="iui-tag">创建规格模板</span><span class="iui-tag">浏览规格模板</span></p>
			<div class="iui-row select-wrap" >
				<div class="iui-group">
					<select class="iui-input iui-input-default" id="root-cat">
						<option>选择宝贝分类</option>
						<option>服装</option>
						<option>手饰</option>
					</select>
					<select class="iui-input iui-input-default" style="display: block;" id="child-cat">
						<option>选择宝贝分类</option>
						<option>服装</option>
						<option>手饰</option>
					</select>
				</div>
			</div>
			<p class="iui-line"></p>

			<div class="iui-row" id="app">

				<div class="iui-row mdName">
					<input type="text" class="iui-input iui-input-default" placeholder="模板名称">
					<a href="##" class="iui-btn iui-btn-primary create">创建</a>
					<a href="##" class="iui-btn iui-btn-worring remove">移除</a>
					<div class="wrap"><!--这里动态创建规格名称--></div>
					<div class="iui-row">
						<span class="iui-btn iui-btn-primary" id="save">保存</span>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
<script type="text/javascript">
	var JSP = {
		data:{

		},
		methods:{
			pageEvent: function(){
				var app = document.getElementById("app");

				var md = app.getElementsByClassName("mdName")[0];
				//创建事件并绑定事件 
				var nodes = md.getElementsByClassName("wrap")[0];
				md.getElementsByClassName("create")[0].onclick =  function(){	
					var html = '<div class="iui-row pms-wrap"><span class="fa fa-long-arrow-right" style="margin-left: 10px;"></span><input type="text" class="iui-input iui-input-default" placeholder="输入规格名称"><a href="##" class="fa fa-trash rm add"></a></div>';	
					
					//nodes.innerHTML += html;
					nodes.insertAdjacentHTML("beforeend", html);

					var rm = app.getElementsByClassName("rm");
					for(var i=0; i< rm.length; i++){		//绑定属性删除事件
						rm[i].onclick  = function(){
							this.parentNode.remove();
						}
					}

				}	

			
				//移除事件
				md.getElementsByClassName("remove")[0].onclick = function(){
					nodes.innerHTML="";
				}
			
				//保存事件 save
				document.getElementById("save").onclick  = function(){
					var vals = md.getElementsByTagName("input");
					for (var i = vals.length - 1; i >= 0; i--) {
						if(vals[i].value != ""){

						}
					}
				}			
			}
		},
	};

	window.onload = function(){

		JSP.methods.pageEvent();
	}
</script>
</body>
</html>