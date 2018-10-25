<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/abstPath.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>"/>
	<title>后台登陆</title>
	<link rel="stylesheet" type="text/css" href="rs/css/index.css">
	<link rel="stylesheet" type="text/css" href="rs/lib/iui/iui.css">
</head>
<body>
	
	<div class="login-panel" id="main-panel">
		<div class="icon-wrap">
			<img src="rs/images/default/login/admin-ico.png" width="90" height="90" alt="" class="icon">
		</div>
		<div class="login-wrap">
			<form action="" id="from">
				<div class="group">
					<label class="ico user"></label>
					<input type="text" name="user" class="scanner" placeholder="管理员账号" autofocus>
				</div>
				<div class="group">
					<label class="ico psw"></label>
					<input type="password" name="user" class="scanner" placeholder="密码">
				</div>
				<div class="submit" id="login-sub">点击登录</div>
				<div class="link">
					<a href="#" class="regis">注册账号</a>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript" src="rs/lib/iui/iui.js"></script>
	<script type="text/javascript">
		(function(){	
			var submit  = document.getElementById('login-sub');
			var scan = document.getElementById('from').getElementsByClassName('scanner');
			for(var i = 0 ; i< scan.length; i++){
				scan[i].onblur=function(){
					if(this.value == ""){
						document.getElementById("login-sub").classList.remove('check');
					}
					if(scan[0].value != "" && scan[1].value != ""){
						submit.classList.add('check');
					}
				}
			}
			
			//绑定单击事件
			submit.onclick = function(){
				if(scan[0].value != "" && scan[1].value != ""){
					document.getElementById('main-panel').style.animation="none";
					var p = new prompt("登录中...");
					var user = scan[0].value;
					var password = scan[1].value;
					setTimeout(function(){
						if(user == 'admin' && password == 'root'){
							p.flush("登录成功！");
							window.location.href="home.jsp";
						}else{
							document.getElementById('main-panel').style.animation="shake .5s";
							p.flush("账号或密码错误！");
						}
					},1000);
				}
			}

			//绑定回车事件
			document.onkeyup = function(e){
				e = e || window.event;
				if(e.keyCode == 13 ){
					submit.onclick();
				}
			}
		})();
	</script>
</body>
</html>