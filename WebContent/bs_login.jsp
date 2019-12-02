<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script src="js/jquery.js"></script>
	<script src="js/backstage_login.js"></script>
</head>
<body>
<header class="htmleaf-header">
	<h1>后台登录<span>书栈管理者使用</span></h1>
</header>
	<div class="login testtwo">
		<div class="login_title" style="opacity: 1;">
			<span>登录你的账户</span>
		</div>
		<div class="login_fields" style="opacity: 1;">
			<div class="login_fields__user" style="opacity: 1;">
				<div class="icon" style="opacity: 0.5;">
					<img src="img/user_icon_copy.png">
				</div>
				<input placeholder="Username" type="text" name="username" id="ipt-username">
				<div class="validation" style="opacity: 0;">
					<img src="img/tick_2.png">
				</div>

			</div>
			<div class="login_fields__password" style="opacity: 1;">
				<div class="icon" style="opacity: 0.5;">
					<img src="img/lock_icon_copy.png">
				</div>
				<input placeholder="Password" type="password" name="password" id="ipt-password">
				<div class="validation" style="opacity: 0;">
					<img src="img/tick_2.png">
				</div>
			</div>
			<p class="error-tips">
			</p>
			<div class="login_fields__submit" style="opacity: 1;">
				<input type="submit" value="登录">
				<div class="forgot" style="opacity: 1;">
					<a href="#">忘记密码?</a>
				</div>
			</div>
		</div>
		<div class="disclaimer" style="opacity: 1;">
			<p>当你想了解更多的时候,请输入www.tblack.cn。当你登录成功之后会自动跳转到后台首页。</p>
		</div>
	</div>
	<div class="authent" style="display: block;">
		<img src=" puff.svg">
		<p>正在验证中...</p>
	</div>
</html>