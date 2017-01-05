<%@page import="cn.bjfu.im.vo.OrderVO"%>
<%@page import="cn.bjfu.im.dao.OrderDAO"%>
<%@page import="cn.bjfu.im.dao.GoodsInfoInitDAO"%>
<%@page import="cn.bjfu.im.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="cn.bjfu.im.vo.GoodsInfoVO" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单</title>
<link rel="shortcut icon" href="images/logo.jpg" >
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" type="text/css" href="css/cart.css"/>


</head>

<body>
<div id="container">
  <div id="header">
         <div id="logo">
             <img src="images/logo.jpg"width="91" height="91" id="logo">
         </div>
         <div id="title">
             <span id="myname_01"><a href="index.html">订单</a></span>
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
             <div id="right-content">
                 <div id="cart-block">
                 <%int userid = 0;
                   OrderDAO dao = new OrderDAO();
                   Map<Integer, OrderVO> map = null;
                   
                   if(session.getAttribute("userid") != null) { 
                       userid = (Integer)session.getAttribute("userid");
	                   map = dao.getOrder(userid);
                 %>
                     <table class="cart-table">
                         <tr><th>订单编号</th><th>时间</th><th>总价</th><th>操作</th></th>
                         <%if(map != null){%>
                         <%for (Map.Entry<Integer, OrderVO> entry : map.entrySet()) {
                               //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                               OrderVO orderVo = (OrderVO)entry.getValue();
                         %>
                         	<tr>
                         	    <td><%=entry.getKey() %></td>
                         	    <td><%=orderVo.getTimestamp() %></td>
                         	    <td><%=orderVo.getTotal() %></td>
                         	    <td><%="<a href='orderDetail.jsp?oid="+entry.getKey()+"'>" %>详情</a></td>
                         	</tr>
                         <%}
                           } else {%>
                            <tr><td colspan=4>没有订单，快去下单吧！</td></tr>
                  <%       } 
                    }else{%>
                      <table class="cart-table">
                          <tr><th colspan=4>请先登陆！</th></tr>
                      </table>
                    <%} %>
                     </table>
                 </div>
             </div>
         </div>
     <div id="tail">
      <div id="copyright">Copyright © 2017 imis14. All Rights Reserved</div>
     </div>
</div>
</body>
</html>
    