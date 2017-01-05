<%@page import="sun.applet.resources.MsgAppletViewer"%>
<%@page import="cn.bjfu.im.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.Map.Entry"%>
<%@page import="cn.bjfu.im.vo.*"%>
<%@page import="cn.bjfu.im.dao.*"%>
<%@page import="java.util.HashMap"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>购物网站-注册</title>
<link rel="shortcut icon" href="images/logo.jpg">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/goods.css" />

</head>

<body>
	<div id="container">
		<div id="header">
			<div id="logo">
				<img src="images/logo.jpg" width="91" height="91" id="logo">
			</div>
			<div id="title">
				<span id="myname_01"><a href="index.jsp">注册</a></span>
			</div>

			<div id='headerright'>
				<form action="search.do" method="post">
					<input type='text' size="12" name="searchname"> <input
						type='submit' name='查询' value='查询'>
				</form>
			</div>
		</div>
		<div id="middle">
			<div id="left">
				<ul id="menu_left">
					<li><a href="index.jsp">首&nbsp&nbsp&nbsp页</a></li>
					<li><a href="orderList.jsp">订&nbsp&nbsp&nbsp单</a></li>
					<li><a href="cart.jsp">购&nbsp物&nbsp车</a></li>
				</ul>
				<%
					if (session.getAttribute("userid") != null) {
				%>
				<div id="user-info">
					<img src="images/logo.jpg" width="80" height="80" id="logo">
					<span>&nbsp;用户ID:<%=session.getAttribute("userid")%></span><br>
					<span>&nbsp;<a href="logOff.do">注销</a></span>
				</div>
				<%
					} else {
				%>

				<div class="sign-in-box">
					<h2>请登录</h2>
					<form action="login.do" method="post">
						<div class="text-boxs">
							<input name="username" type="text" class="text-box"
								placeholder="用户名" required />
							<div class="clearfix"></div>
							<input name="password" type="password" class="text-box"
								placeholder="密码" required />
							<div class="clearfix"></div>
						</div>
						<input type="submit" value="登陆" /><br> <a href=''>注册</a>
					</form>
				</div>

				<%
					}
				%>
			</div>
			<div id="right">
				<%
					String msg = null;
					msg = (String) request.getAttribute("msg");
					if (msg != null) {
						if (msg.equals("success")) {
				%>
				<div class="msg">
					<h2>
						注册成功！请在页面左侧登陆！
					</h2>
				</div>
				<%
					}
						if (msg.equals("existed")) {
				%>
				<div class="msg">
					<h3>注册失败！用户名已存在！</h3>
					<a href="register.jsp"><h3>重新注册</h3></a>
				</div>
				<%
					}
					} else {
				%>

				<div id="register">
					<h2>请输入账户信息</h2>
					<form action="register.do" method="post">
						<input type="text" name="username" class="text-box"> 
						<input type="password" name="password" class="text-box"> 
						<input type="submit" value="注册">
						
					</form>
				</div>

				<%
					}
				%>
			</div>
			<div id="tail">
				<div id="copyright">Copyright © 2017 imis. All Rights Reserved</div>
			</div>
		</div>
</body>
</html>
