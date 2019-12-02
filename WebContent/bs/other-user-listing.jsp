<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CH">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>书栈后台</title>
    <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link type="text/css" href="css/theme.css" rel="stylesheet">
    <link type="text/css" href="images/icons/css/font-awesome.css" rel="stylesheet">
    <link type="text/css" href="images/icons/css/iconfont.css" rel="stylesheet">
    <link type="text/css" href="images/icons/css/demo.css" rel="stylesheet">
    <link type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'
        rel='stylesheet'>
        
</head>
<body>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                    <i class="icon-reorder shaded"></i></a><a class="brand" href="index.html">书栈后台 </a>
                <div class="nav-collapse collapse navbar-inverse-collapse">
                    <ul class="nav nav-icons">
                        <li class="active"><a href="#"><i class="icon-envelope"></i></a></li>
                        <li><a href="#"><i class="icon-eye-open"></i></a></li>
                        <li><a href="#"><i class="icon-bar-chart"></i></a></li>
                    </ul>
                    <form class="navbar-search pull-left input-append" method="post">
                    <input type="text" class="span3" >
                    <button class="btn" type="button">
                        <i class="icon-search"></i>
                    </button>
                    </form>
                    <ul class="nav pull-right">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">管理员操作
                            <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/BookStack/bs/uploadfile.jsp">管理员文件上传</a></li>
                                <li><a href="#">Don't Click</a></li>
                                <li class="divider"></li>
                                <li class="nav-header">Example Header</li>
                                <li><a href="#">A Separated link</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Support </a></li>
                        <li class="nav-user dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="images/user.png" class="nav-avatar" />
                            <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Your Profile</a></li>
                                <li><a href="#">Edit Profile</a></li>
                                <li><a href="#">Account Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="/BookStack/bslogout.ado">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.nav-collapse -->
            </div>
        </div>
        <!-- /navbar-inner -->
    </div>
    <!-- /navbar -->
    <div class="wrapper">
        <div class="container">
            <div class="row">
                <div class="span3">
                    <div class="sidebar">
                        <ul class="widget widget-menu unstyled">
                            <li class="active"><a href="index.html"><i class="menu-icon icon-dashboard"></i>Dashboard
                            </a></li>
                            <li><a href="activity.html"><i class="menu-icon icon-bullhorn"></i>News Feed </a>
                            </li>
                            <li><a href="message.html"><i class="menu-icon icon-inbox"></i>Inbox <b class="label green pull-right">
                                11</b> </a></li>
                            <li><a href="task.html"><i class="menu-icon icon-tasks"></i>Tasks <b class="label orange pull-right">
                                19</b> </a></li>
                        </ul>
                        <!--/.widget-nav-->
                        <ul class="widget widget-menu unstyled">
                                <li><a href="ui-button-icon.html"><i class="menu-icon icon-bold"></i> Buttons </a></li>
                                <li><a href="ui-typography.html"><i class="menu-icon icon-book"></i>Typography </a></li>
                                <li><a href="form.html"><i class="menu-icon icon-paste"></i>Forms </a></li>
                                <li><a href="table.jsp"><i class="menu-icon icon-table"></i>Tables </a></li>
                                <li><a href="charts.html"><i class="menu-icon icon-bar-chart"></i>Charts </a></li>
                            </ul>
                        <!--/.widget-nav-->
                        <ul class="widget widget-menu unstyled">
                            <li><a class="collapsed" data-toggle="collapse" href="#togglePages"><i class="menu-icon icon-cog">
                            </i><i class="icon-chevron-down pull-right"></i><i class="icon-chevron-up pull-right">
                            </i>More Pages </a>
                                <ul id="togglePages" class="collapse unstyled">
                                    <li><a href="other-login.html"><i class="icon-inbox"></i>Login </a></li>
                                    <li><a href="other-user-profile.html"><i class="icon-inbox"></i>Profile </a></li>
                                    <li><a href="other-user-listing.jsp"><i class="icon-inbox"></i>All Users </a></li>
                                </ul>
                            </li>
                            <li><a href="/BookStack/bslogout.ado"><i class="menu-icon icon-signout"></i>Logout </a></li>
                        </ul>
                    </div>
                    <!--/.sidebar-->
                </div>
                <!--/.span3-->
                <div class="span9">
                    <div class="content">
                        <div class="module">
                            <div class="module-head">
                                <h3>
                                    All Members</h3>
                            </div>
                            <div class="module-option clearfix">
                            	<!-- 提交表单查询数据 -->
                                <form action="${pageContext.request.contextPath}/bs/bsquery.ado" method="post">
                                <div class="input-append pull-left">
                                    <input type="text" class="span3" placeholder="Filter by name..." name="username">
                                    <button type="submit" class="btn" id="btn-search">
                                        <i class="icon-search"></i>
                                    </button>
                                </div>
                                
                                </form >
                                <div class="btn-group pull-right" data-toggle="buttons-radio">
                                    <button type="button" class="btn">
                                        All</button>
                                    <button type="button" class="btn" >
                                        Male</button>
                                    <button type="button" class="btn">
                                        Female</button>
                                </div>
                            </div>
                            <div class="module-body">
								
								<!-- 在数据库中查询所有的用户对象- 并以列表的形式显示出来 --> 
								<c:if test="${not empty user_list}">
								<c:forEach items="${user_list}" var="user"  varStatus="status">
									
									<c:if test="${status.index % 2 == 0}">
										<div class="row-fluid">
									</c:if>
									<div class="span6">
                                        <div class="media user">
                                            <a class="media-avatar pull-left" href="#">
                                               <%//男女用户默认头像不同 %>
                                               <c:choose>
                                               <c:when test="${user.gender == 'Male' }">
                                               	<img src="images/male.png">
                                               </c:when>
                                                <c:when test="${user.gender == 'Female' }">
                                               	<img src="images/female.png">
                                               </c:when>
                                               <c:otherwise>
                                               	<img src="images/user.png">
                                               </c:otherwise>
                                               </c:choose>  
                                            </a>
                                            <div class="media-body">
                                                <h3 class="media-title">
                                                    <c:out value="${user.userName}"/>
                                                </h3>
                                                <p>
                                                    <small class="muted">${user.homeLand}</small>
                                                	<small class="muted">${user.gender}</small>
                                                </p>
                                                <div class="media-option btn-group shaded-icon">
                                                    <a class="btn btn-small email"  
                                                    href="${pageContext.request.contextPath}/BookStack/bsemail.ado?username=${user.userName}" >
                                                        <i class="icon-envelope"></i>
                                                    </a>
                                                    <a class="btn btn-small share"
                                                    href="${pageContext.request.contextPath}/BookStack/bsshare.ado?username=${user.userName}" >
                                                        <i class="icon-share-alt"></i>
                                                    </a>
                                                    <!-- 更新按钮 -->
                                                    <a class="btn btn-small update" 
                                                     href="${pageContext.request.contextPath}/BookStack/bsupdate.ado?username=${user.userName}">
                                                        <i class="iconfont icon-bianji"></i>
                                                    </a>
                                                    <!-- 删除按钮 -->
                                                    <button class="btn btn-small delete"  
                                                    value="${pageContext.request.contextPath}/BookStack/bsdelete.ado?username=${user.userName}">
                                                    		 <i class="iconfont icon-shanchu"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>	
                                    <c:if test="${status.index % 2 == 1 || status.last}" >
										</div>
									</c:if>
								</c:forEach>
								</c:if>
                                
                                <!--/.row-fluid-->
                                <br />
                                <div class="pagination pagination-centered">
                                    <ul>
                                        <li><a href="#"><i class="icon-double-angle-left"></i></a></li>
                                        <c:forEach items="${user_list}" step="6" varStatus="status">
                                        	<li><a href="#">${status.count }</a></li>
                                        </c:forEach>
                                        <li><a href="#"><i class="icon-double-angle-right"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/.content-->
                </div>
                <!--/.span9-->
            </div>
        </div>
        <!--/.container-->
    </div>
    <!--/.wrapper-->
    <div class="footer">
        <div class="container">
                <b class="copyright">&copy; 2019 书栈 </b>版权所有，保留所有权利 -   <a href="http://www.tblack.cn" title="进入书栈" target="_blank">进入书栈</a>
            </div>
    </div>
    <script src="scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="scripts/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script type="text/javascript" src="scripts/user_mgr.js"></script>
    <c:if test="${empty user_list}">
	<script>
		//当页面打开的时候自动去请求数据
		$(function(){
			document.getElementById("btn-search").click(); //打开的时候自动去请求后台的查询数据
		});
	</script>
</c:if>
</body>
