<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch-ZH" xmlns:method="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
	<title>书栈</title>
	<link rel="icon" href="img/L-log.png">
	<link rel="stylesheet" href="css/homePage.css">
	<link rel="stylesheet" href="MyIcon/iconfont.css">
	<link rel="stylesheet" href="css/main.css">
	<base target="_blank">
	<script src="js/jquery.js"></script>
	<script type="text/javascript" src="js/homePage.js"></script>
	<script type="text/javascript" src="js/navigation.js"></script>
	<meta name="keywords" content="书栈,技术分享,Oracle,C/C++,说栈">
</head>

<body class="default-font">
	
	<!-- 顶部导航栏 -->
	<nav  class="top-nav navbar-fixed nav-default" role="navigation">
		
		<div class="nav-box">
		<!--左方LOGO标签 -->
		<a href="#" class="M-log">
			<!-- 书栈logo -->
			<img src="img/M-log.png" alt="">	
		</a>
		<!-- 写文章按钮 -->
		<a href="${pageContext.request.contextPath}/user/user_upload.html" class="btn btn-write ">
			<i class="write-lg iconfont icon-maobi1"> 上传文件</i>
		</a>
		<!-- 用户未登录的情况下显示登录和注册 -->
		<c:if test="${empty sessionScope.username}">
			<!-- 注册 -->
			<a href="loginAndRegister.jsp#toregister" class="btn sign-up" target="_self">注册</a>
			<!-- 登录 -->
			<a href="loginAndRegister.jsp#tologin" class="btn log-in" target="_self">登录</a>
		</c:if>
		<!-- 用户登录之后显示下拉列表 -->
		<c:if test="${not empty sessionScope.username }">
			<div class="user">
				<div data-hover="dropdown">
					<a class="avatar" href="https://blog.csdn.net/David_TD"><img
						src="img/avatar.jpg" 
						alt="${sessionScope.username}"></a>
				</div>
				<ul class="dropdown-menu" >
					<li><a href="${pageContext.request.contextPath}/upage.do" > <i
							class="iconfont icon-yonghu"></i><span>我的主页</span>
					</a></li>
					<li>
						<!-- TODO bookmarks_path --> 
						<a href="${pageContext.request.contextPath}/bookmark.do"> <i
							class="iconfont icon-shoucang"></i><span>收藏的文章</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/wortelike.do"> <i
							class="iconfont icon-xin1"></i><span>喜欢的文章</span>
						</a>
					</li>
					<li>
						<a href="/my/paid_notes"> <i class="iconfont icon-shangpingoumai"></i><span>已购内容</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/wallout.do"> <i
							class="iconfont icon-qianbao"></i><span>我的钱包</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/setting.do"> <i
							class="iconfont icon-shezhi"></i><span>设置</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/help.do"> <i
							class="iconfont icon-bangzhu"></i><span>帮助与反馈</span>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/logout.do" target="_self">
							<i class="iconfont icon-tuichu-"></i><span>退出</span>
						</a>
					</li>
				</ul>
			</div>

		</c:if>
		<!-- 默认字体样式 -->
		<div class="style-mode">
			<a  class="style-mode-btn" target="_slef">
				<i class="iconfont icon-Aa1 ic-mode"></i>
			</a>	
			<!-- 隐藏的更多模式 -->
			<div class="more-mode" style="left: 1018px; display: none;">
				<div class="meta">
					<i class="iconfont icon-yueliang nav-night"></i>
					<span>夜间模式</span>
				</div>
				<div class="switch day-night">
					<a class="switch-btn">开</a>
					<a class="switch-btn active">关</a>
				</div>
				 <hr>
				<div class="switch font-family-grounp">
				 	<a class="switch-btn font-kai">楷体</a>
				 	<a class="switch-btn font-you active">幼圆</a>
				</div>
				<div class="switch font-mode">
					<a class="switch-btn active">简</a>
					<a class="switch-btn ">繁</a>
				</div>
			</div>
		</div>
		
		<!-- 导航栏中间布局 -->
		<div class="container">
			<div class="navbar-collapse	collapse in">
			<ul class="nav navbar-nav">
				<!-- 发现 -->
				<li class="tab active">
					<a href="/BookStack/loginAndRegister.jsp">
						<span class="menu-text">发现</span>
						<i class="iconfont menu-icon icon-faxian"></i>
					</a>
				</li>
				<li class="tab t-follows">
					<a href="#">
						<span class="menu-text">关注</span>
						<i class="iconfont menu-icon icon-shuben"></i>
					</a>
				</li>
				<li class="tab notification">
					<a class="notification-btn">
						<span class="menu-text">消息</span>
						<i class="iconfont menu-icon icon-xiaoxi1"></i>
					</a>
					<!-- 下拉的消息列表 -->
					<ul class="dropdown-menu">
						<li>
							<a href="#">
								<i class="iconfont ic-comments icon-_huaban"></i>
								<span>评论</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-mail icon-youxiang"></i>
								<span>简信</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-request icon-faqiqingqiu"></i>
								<span>投稿请求</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-likes icon-xihuan"></i>
								<span>喜欢和赞</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-follows icon-guanzhu"></i>
								<span>关注</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-money icon-zanshangjilu"></i>
								<span>赞赏和付费</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="iconfont ic-other icon-iconfontgengduo"></i>
								<span>其他提醒</span>
							</a>
						</li>
					</ul>
				</li>
				<!-- 搜索栏 -->
				<li class="search">
					<form action="/search" target="_blank" method="post">
						<input type="hidden" value="✓" name="uft8">
						<input type="text" name="q" id="q"  autocomplete="off" placeholder="搜索" class="search-input">	
						<a class="btn-search" >
							<i class="iconfont ic-search icon-sousuo"></i>
						</a>
						<!-- 搜索栏提示 -->
						<div id="navbar-search-tips">
							<!-- 最近搜索趋势 -->
							<div class="search-trending">
								<div class="search-trending-header clearfix">
									<span>热门搜索</span>
									<a>
										<i class="iconfont icon-shuaxin ic-change"></i>
										换一批
									</a>
								</div>
								<ul class="search-trending-tags">
									<li>
										<a href="#">区块链</a>
									</li>
									<li>
										<a href="#">小程序</a>
									</li>
									<li>
										<a href="#">C++</a>
									</li>
									<li>
										<a href="#">畅销书</a>
									</li>
									<li>
										<a href="#">网络编程</a>
									</li>
									<li>
										<a href="#">美食</a>
									</li>
									<li>
										<a href="#">创意</a>
									</li>
								</ul>
							</div>
						</div>
					</form>
				</li>
				</ul>
				</div>
			</div>
		</div>
	</nav>
	<!-- 中间容器 -->
	<div class="container index" id="top">
		<!-- 轮播图与介绍内容 -->
		<div class="row">
			<!-- 主内容区 -->
			<div class="main col-mode">
				<!-- 轮播图 -->
				<div id="indexCarousel" class="carousel slide">
					<div class="carousel-inner">
						<div class="item active">
							<div class="banner">
								<a href="#">
									<img src="img/img1.png" alt="">
								</a>
							</div>
						</div>
						<div class="item">
							<div class="banner">
								<a href="#">
									<img src="img/img2.jpg" alt="">
								</a>
							</div>
						</div>
						<div class="item">
							<div class="banner">
								<a href="#">
									<img src="img/img3.jpg" alt="">
								</a>
							</div>
						</div>
					</div>
					<!-- 圆角图标指示器 -->
					<ol class="carousel-indicators">
						<li data-target="#indexCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#indexCarousel" data-slide-to="1" ></li>
						<li data-target="#indexCarousel" data-slide-to="2"></li>
					</ol>	
				</div>
				<!-- 分隔线 -->
				<div class="split-line"></div>
				<!-- 文章列表 -->
				<div class="list-content">
					<ul class="note-list">
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/ImplicitCursor.png" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="Cursor.html" class="title">Oracle游标</a>
								<p class="abstract">
									在使用SQL编写查询语句的时候，所有的查询结果都会直接显示给用户，但是在很多情况下，用户需要对返回结果中的每一条数据分别进行操作....
								</p>
							</div>
						</li>
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/fantasy.jpg" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="fantasy.html" class="title">多好的幻想</a>
								<p class="abstract">
									我总是喜欢想的很多，很多不切实际的，很多离我很遥远的。有人美其名曰：梦想、规划....
								</p>
							</div>
						</li>
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/console.png" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="BuildWebServer.html" class="title">自写网站服务器搭建网站</a>
								<p class="abstract">
									这篇教程主要是告诉大家如何利用TCP和HTTP协议来完成网站的搭建。
	首先你需要有C/C++语言基础，且有服务器、客户端概念....
								</p>
							</div>
						</li>
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/note4.jpg" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="#" class="title">最美舞台剧-好好给那些‘演员’上一课</a>
								<p class="abstract">
									舞台剧在人们日常的娱乐活动中逐渐被边缘化，因为休闲时间资源已被其他替代娱乐形式无情瓜分，而舞台剧因为消费成本太高。虽然有人仍在大声疾呼，说舞台剧面临困境是由于剧作家不能写出“反映时代”的作品....
								</p>
							</div>
						</li>
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/note5.jpg" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="#" class="title">年轻的时候不多动动，年老了谁照顾你</a>
								<p class="abstract">
									跑步是日常方便的一种体育锻炼方法，是有氧呼吸的有效运动方式....
								</p>
							</div>
						</li>
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/note6.jpg" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="#" class="title">这种装饰风格你会不喜欢吗</a>
								<p class="abstract">
									地中海风格具有着独特的美学特点。一般选择自然的柔和色彩，在组合设计上注意空间搭配，充分利用每一寸空间....
								</p>
							</div>
						</li>
						
						<li class="have-img">
							<a href="#" class="wrap-img">
								<img src="img/note7.jpg" alt="">
							</a>
							<!-- 文章内容 -->
							<div class="content">
								<a href="#" class="title">那么美的月色怎能独自欣赏</a>
								<p class="abstract">
									月食可分为月偏食、月全食两种（没有月环食，因为地球比月球大）。当月球只有部分进入地球的本影时，就会出现月偏食；而当整个月球进入地球的本影之...
								</p>
							</div>
						</li>
					</ul>
					<div class="start">
		<section id="layout-default">
			<div class="header-wrap">
				<header id="shuzhan-header" class="header-normal">
					
				</header>
				<div class="header-shim"></div>
			</div>
			<div class="main-view" id="homepage">
				<div id="collection-recommended">
					<div class="top-title">
						<span>站长标签</span>
						<span class="collection-change">
							<i class="iconfont icon-shuaxin"></i>
							换一批
						</span>
					</div>
					<div class="recommend-collection">
						<a href="" class="tag">中二</a>
						<a href="" class="tag">读书</a>
						<a href="" class="tag">IT互联网</a>
						<a href="" class="tag">编程菜鸟</a>
						<a href="" class="tag">长得不帅</a>
						<a href="" class="tag">没得故事</a>
						<a href="" class="tag">未完</a>
					</div>
				</div>
				<hr>
			</div>
		</section>
	</div>
				</div>
			</div>
			<div class="aside col-xs-7 col-xs-offset-1">
					<div class="board">
						<a href="#">
							<img src="img/sub1.png" alt="">
						</a>
						<a href="#">
							<img src="img/sub2.png" alt="">
						</a>
						<a href="#">
							<img src="img/sub3.png" alt="">
						</a>
						<a href="#">
							<img src="img/sub4.png" alt="">
						</a>
						<a href="#">
							<img src="img/sub5.png" alt="">
						</a>
						<a href="#">
							<img src="img/sub6.png" alt="">
						</a>
					</div>
					<!-- 微信关注区 -->
					<a href="#" class="my-info" >
						<img src="img/id.png" alt="" class="qrcode">
						<div class="info">
							<div class="title">
							扫一扫加我好友
							<i class="iconfont icon-zuo-"></i>
							</div>
							<div class="description">每天都能收获多一点</div>
						</div>
					</a>
					<!-- 广告位 -->
					<div class="recommended-author-wrap">
						<a href="#" class="ad-1 img-ad">
							<span class="ad-badge">广告</span>
						</a>
						<div class="recommended-authors">
							<div class="title">
								<span>推荐作者</span>
								<a href="#" class="page-change">
									<i class="iconfont icon-shuaxin"></i>
									换一批
								</a>
							</div>
							<ul class="list">
								<li>
									<a href="#" class="avatar">
										<img src="img/avatar1.png" alt="">
									</a>
									<a  class="follow">
										<i class="iconfont icon-jia"></i>
										关注
									</a>
									<a href="#" class="name">
										咸蛋超人
									</a>
									<p>写了20k字 . 无限喜欢</p>
								</li>
								<li>
									<a href="#" class="avatar">
										<img src="img/avatar2.png" alt="">
									</a>
									<a  class="follow">
										<i class="iconfont icon-jia"></i>
										关注
									</a>
									<a href="#" class="name">
										甜蛋超人
									</a>
									<p>写了40k字 . 有几个喜欢</p>
								</li>
								<li>
									<a href="#" class="avatar">
										<img src="img/avatar3.png" alt="">
									</a>
									<a  class="follow">
										<i class="iconfont icon-jia"></i>
										关注
									</a>
									<a href="#" class="name">
										苦蛋超人
									</a>
									<p>写了30k字 . 1个喜欢</p>
								</li>
								<li>
									<a href="#" class="avatar">
										<img src="img/avatar4.png" alt="">
									</a>
									<a  class="follow">
										<i class="iconfont icon-jia"></i>
										关注
									</a>
									<a href="#" class="name">
										辣蛋超人
									</a>
									<p>写了1k字 . 2个喜欢</p>
								</li>
								<li>
									<a href="#" class="avatar">
										<img src="img/avatar5.png" alt="">
									</a>
									<a  class="follow">
										<i class="iconfont icon-jia"></i>
										关注
									</a>
									<a href="#" class="name">
										酸蛋超人
									</a>
									<p>没写字 . 没人喜欢</p>
								</li>
							</ul>
						</div>
					</div>
				</div>
		</div>
	</div>
	<!-- 跳转到顶部 -->
	<div class="side-tool">
		<ul>
			<li>
				<a  class="function-button" href="#top" target="_self">
					<i class="iconfont icon-shang ic-shang"></i>
				</a>
			</li>
		</ul>
	</div>
	<!-- 跳转到底部 -->
     <div class="side-tool-2">
        <ul>
            <li>
                <a  class="function-button" href="#bottom" target="_self">
                    <i class="iconfont icon-xia ic-xia"></i>
                </a>
            </li>
        </ul>
    </div>
	<footer class="container">
		<div class="row">
			<div class="col-xs-17 main">
				<a href="#">关于书栈</a>
				<em>·</em>
				<a href="#">不要联系</a>
				<em>·</em>
				<a href="#">可以加入</a>
				<em>·</em>
				<a href="#">没有帮助</a>
				<em>·</em>
				<div class="icp">
					@2019-很长的以后 BlueDream/书栈/赣ICP备19002802号
					<a href="#">不要找了 没有举报电话.</a>
					<a href="#">有问题请联系我@tblack.cn</a>
					<a href="http://www.tblack.cn">www.tblack.cn</a>
				</div>
			</div>
		</div>
	</footer>
	<div class="container short-word" id='bottom'>
		<div class="about">
			 站长心语
			<ul class="words" style="display:none;">
				<li>这个世界有两个我,写代码的我和不写代码的我.</li>
				<li>我留长发是为了在秃头之前多体验下这种飘逸的感觉.</li>
				<li>我写代码的时候只有两个想法: 1.这个功能实现了,我真棒!
					2.????怎么又出bug了?
				</li>
				<li>不要低估你语言的影响力,说不定你说完又多了几个讨厌你的人呢.</li>
				<li>每天我的头发像极了我的生活：一团糟。</li>
				<li>《美国众神》里劳拉死而复生之后对星期三说，在有Shadow的地方我就能看到一道金光。</li>
			</ul>
		</div>
	</div>
	<audio src="vedio/杨宗纬 - 天龙八部 (伴奏).mp3"></audio>
</body>
</html>
