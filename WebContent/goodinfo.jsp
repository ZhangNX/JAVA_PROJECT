<%@page import="cn.bjfu.im.dao.StockDAO"%>
<%@page import="cn.bjfu.im.dao.GoodsInfoInitDAO"%>
<%@page import="cn.bjfu.im.vo.GoodsInfoVO"%>
<%@page import="cn.bjfu.im.vo.MemberVO"%>
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
						<input type="submit" value="登陆" /><br> <a href='register.jsp'>注册</a>
					</form>
				</div>

				<%
					}
				%>
			</div>
			<div id="right">
				<div id='goodinfotop'>
					<div id='goodinfotopleft'>
						<%
							int gid = Integer.valueOf(request.getParameter("gid"));
							GoodsInfoInitDAO dao = new GoodsInfoInitDAO();
							GoodsInfoVO vo = dao.getGoodsInfo(gid);
							out.println("<img src='" + vo.getPictureURL() + "'><br>");
							out.println(vo.getGood());
						%>
					</div>
					<div id='goodinfotoprigth'>
						<h1>
							<%
								out.println(vo.getPrice() + "元");
							%>
						</h1>
						<br>
						<%
							StockDAO sdao = new StockDAO();
							int amount = sdao.getStock(gid);
							out.println("库存：" + amount);
						%><br> <br>
						<form action="addGoods.do" method="post">
						购买数量：<input onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
						name = 'num' value=1>
						<input type='hidden' name = 'id' value='<%=gid%>'>
						<input type ='submit'name='submit' value='添加到购物车'> 
						
						</form>
					</div>
				</div>
				<div id='goodinfomid'>
					<h4>详细信息:</h4><br>
					<%
						out.println(vo.getIntroduction());
					%>
				</div>
			</div>
			<div id="tail">
				<div id="copyright">Copyright © 2015 周兵杰. All Rights Reserved</div>
			</div>
		</div>
</body>
</html>
