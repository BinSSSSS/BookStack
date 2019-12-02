<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ZH-CH">
<head>
<meta charset="UTF-8" />
<title>登录或注册书栈</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="书栈" />
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">     
<link rel="icon" href="img/L-log.png">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/loginAndRegister.css" />
<script src="js/jquery.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
</head>
<body>
	<c:if test="${not empty link}">
		<a href="#${link}" id="link"></a>
	</c:if>
	<div class="container">
		<header>
			<h1>
				登录和注册<a href="http://localhost:8080/BookStack/homePage.jsp">书栈</a>
			</h1>
			<nav class="codrops-demos">
				<span>岁月静好，尘世安稳</span>
			</nav>
		</header>
		<section>
			<div id="container_demo">
				<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
					id="tologin"></a>
				<div id="wrapper">
					<div id="login" class="animate form">
						<form action="${pageContext.request.contextPath}/login.do"
							method="POST" id="login-form">
							<h1>Log in</h1>
							<p>
								<label for="ipt-account" class="uname" data-icon="u">
									输入你的账户 </label> <input id="ipt-account" name="account"
									required="required" type="text" placeholder="账号或手机号码"
									value="${account}" /> <label for="ipt-account"
									class="error-tips"> <c:if test="${not empty tips}">${tips}</c:if>
								</label>
							</p>
							<p>
								<label for="password" class="youpasswd" data-icon="p">
									输入你的密码 </label> <input id="password" name="upassword"
									required="required" type="password" placeholder="eg. X8df!90EO" />
							</p>
							<p class="keeplogin">
								<input type="checkbox" name="loginkeeping" id="loginkeeping"
									value="loginkeeping" /> <label for="loginkeeping">保存密码</label>
							</p>
							<p class="login button">
								<input type="submit" value="登录" id="ipt-login" />
							</p>
							<p class="change_link">
								还不是会员？ <a href="#toregister" class="to_register">注册</a>
							</p>
						</form>
					</div>
					<!-- 用户注册成功的时候显示弹出框 -->
					<c:if test="${not empty uAccount}">
						<div class="reg-succ" id="regsucc">
							<div class="succ">
								<div>
									<div class="succ-logo"></div>
									<div class="succ-text">注册成功</div>
								</div>
								<div>
									<p class="text">你的账号为:</p>
									<div class="account">
										<span>${uAccount}</span>
									</div>
									<button class='btn-login'>立即登录</button>
								</div>
							</div>
						</div>
					</c:if>
					<div id="register" class="animate form" id="form-reg">
						<form action="${pageContext.request.contextPath}/register.do"
							method="POST">
							<h1>Sign up</h1>
							<p>
								<label for="usernamesignup" class="uname" data-icon="u">你的用户名</label>
								<input id="usernamesignup" name="userName" required="required"
									type="text" placeholder="张三" value="${username}" /> <label
									for="usernamesignup" class="error-tips"> <c:if
										test="${not empty user_tips}">${user_tips}</c:if>
								</label>
							</p>
							<p>
								<label for="phonenumsignup" class="youmail" data-icon="e">
									手机号</label> <input id="phonenumsignup" name="phoneNum"
									required="required" placeholder="1××" value="${phoneNum}" /> <label
									for="phonenumsignup" class="error-tips"> <c:if
										test="${not empty phone_tips}">${phone_tips}</c:if>
								</label>
							</p>
							<p>
								<label for="passwordsignup" class="youpasswd" data-icon="p">输入密码
								</label> <input id="passwordsignup" name="password" required="required"
									type="password" placeholder="eg. X8df!90EO" /> <label
									for="passwordsignup" class="error-tips"></label>
							</p>
							<p>
								<label for="passwordsignup_confirm" class="youpasswd"
									data-icon="p">请重复你的密码 </label> <input
									id="passwordsignup_confirm" name="password" required="required"
									type="password" placeholder="eg. X8df!90EO" /> <label
									for="passwordsignup_confirm" class="error-tips"> </label>
							</p>
							<p>
								<label class="youcode">验证码：</label> <input name="checkCode"
									type="text" style="width: 120px; padding-left: 5px;"> <img
									src="${pageContext.request.contextPath}/checkCode.do" alt="验证码"
									class="check-code"> <label for="passwordsignup_confirm"
									class="error-tips" style="display: block; margin: 5px 0 0 0">
									<c:if test="${not empty code_tips}">${code_tips}</c:if>
								</label>
							</p>
							<p class="signin button">
								<input type="submit" value="注册" id="ipt-register" />
							</p>
							<p class="change_link">
								已经是会员? <a href="#tologin" class="to_login"> 去登录</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>