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
<title>购物网站-首页</title>
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
				<span id="myname_01"><a href="index.html">首页</a></span>
			</div>

			<div id='headerright'>
				<form action="search.do" method="post">
					<input type='text' name='searchname' >
					<input type='submit' name='查询' value='查询'>
				</form>
			</div>
		</div>
		<div id="middle">
			<div id="left">
				<ul id="menu_left">
					<li><a href="index.jsp">首&nbsp&nbsp&nbsp页</a></li>
					<li><a href="aboutMe.html">订&nbsp&nbsp&nbsp单</a></li>
					<li><a href="cart.jsp">购&nbsp物&nbsp车</a></li>
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
					int iPage = 1;
					int goodsCount = 4;
					if (request.getParameter("iPage") != null) {
						iPage = Integer.parseInt(request.getParameter("iPage"));
					} else {
						iPage = 1;
					}
					HashMap<Integer, GoodsInfoVO> map = (HashMap<Integer, GoodsInfoVO>)request.getAttribute("map");
					int totalCount =(Integer)request.getAttribute("secount");
					for (Entry<Integer, GoodsInfoVO> entry : map.entrySet()) {
						int id = entry.getKey();
						GoodsInfoVO vo = entry.getValue();
						if (id % 2 == 1) {
							out.println("<div id='goodleft'>");
						} else {
							out.println("<div id='goodright'>");
						}
						int gid=vo.getGid();
						out.println("<a href='goodinfo.jsp?gid=" + gid + "'>");
						out.println("<img src='" + vo.getPictureURL() + "'>");
						out.println("</a><br>");
						out.println(vo.getGood());
						out.println("</div>");

					}
				%>
				<div id='goodinfotop'>
					<%
						int totalPage = totalCount / goodsCount;
						if (totalCount % goodsCount > 0) {
							totalPage++;
						}
						if (iPage < 1) {
							iPage = 1;
						}
						if (iPage > totalPage) {
							iPage = totalPage;
						}
						for (int i = 1; i <= totalPage; i++) {

							out.println("<a href='index.jsp?iPage=" + i + "'>" + i
									+ "&nbsp;&nbsp;</a>");
						}
					%>
				</div>
			</div>
			<div id="tail">
				<div id="copyright">Copyright © 2017 imis. All Rights Reserved</div>
			</div>
		</div>
</body>
</html>
