<%@page import="java.util.Map.Entry"%>
<%@page import="cn.bjfu.im.vo.*"%>
<%@page import="cn.bjfu.im.dao.*"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="shortcut icon" href="images/logo.jpg">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/goods.css" />

</head>

<body>
	<div id="container">
		<div>
			<%
				if (request.getAttribute("msg") != null) {
					if (request.getAttribute("msg").equals("success")) {
						out.println("成功登陆");
					} else {
						out.println("登陆失败");
					}
				}
			%>

		</div>
		<div id="header">
			<div id="logo">
				<img src="images/logo.jpg" width="91" height="91" id="logo">
			</div>
			<div id="title">
				<span id="myname_01"><a href="index.html">购物网站</a></span>
			</div>

		</div>
		<div id="middle">
			<div id="left">
				<ul id="menu_left">
					<li><a href="index.html">首&nbsp&nbsp&nbsp页</a></li>
					<li><a href="aboutMe.html">订&nbsp&nbsp&nbsp单</a></li>
					<li><a href="北林.html">购&nbsp物&nbsp车</a></li>
				</ul>
				<%
					if (session.getAttribute("userid") != null) {
				%>
				<div id="user-info">
					<img src="images/logo.jpg" width="80" height="80" id="logo">
					<span>&nbsp;用户ID:<%=session.getAttribute("userid")%></span> <span></span>
				</div>
				<%
					} else {
				%>
				<div id="sign-in-box">
					<h2>请登录</h2>
					<form method="post" action="login.do">
						<div class="text-boxs">
							<span> <input type="text" name="username" class="text-box"
								placeholder="用户名" required />
								<div class="clearfix"></div>
							</span> <span> <input type="password" name="password"
								class="text-box" placeholder="密码" required />
								<div class="clearfix"></div>
						</div>
						<input type="submit" value="登陆" /><br> <a ref="registor.do">注册</a>
					</form>
				</div>
				<%
					}
				%>
			</div>
			<div id="right">
				<%
					GoodsInfoInitDAO dao = new GoodsInfoInitDAO();
					HashMap<Integer, GoodsInfoVO> map = dao.getAllGoodsInfo();
					for (Entry<Integer, GoodsInfoVO> entry : map.entrySet()) {
						int gid = entry.getKey();
						GoodsInfoVO vo = entry.getValue();
						if (gid % 2 == 1) {
							out.println("<div id='goodleft'>");
						} else {
							out.println("<div id='goodright'>");
						}
						out.println("<a href='goodinfo.jsp?gid=" + gid + "'>");
						out.println("<img src='" + vo.getPictureURL() + "'>");
						out.println("</a><br>");
						out.println(vo.getGood());
						out.println("</div>");

					}
				%>

				<!-- <div id="goodleft">123</div>
				<div id="goodrigth">456</div> -->
			</div>
			<div id="tail">
				<div id="copyright">Copyright © 2015 周兵杰. All Rights Reserved</div>
			</div>
		</div>
</body>
</html>
