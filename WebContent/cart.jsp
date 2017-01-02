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
<title>购物车</title>
<link rel="shortcut icon" href="images/logo.jpg" >
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" type="text/css" href="css/cart.css"/>


</head>

<body>
<div id="container">
  <div>
  <% if(request.getAttribute("msg")!=null){
	  if(request.getAttribute("msg").equals("success")){
		  out.println("成功登陆");
	  }else{
		  out.println("登陆失败");
	  }
  }%>

  </div>
  <div id="header">
         <div id="logo">
             <img src="images/logo.jpg"width="91" height="91" id="logo">
         </div>
         <div id="title">
             <span id="myname_01"><a href="index.html">购物车</a></span>
         </div>

  </div>
     <div id="middle">
         <div id="left">
             <ul id="menu_left">
                <li><a href="index.jsp">首&nbsp&nbsp&nbsp页</a></li>
                <li><a href="aboutMe.html">订&nbsp&nbsp&nbsp单</a></li>
                <li><a href="cart.jsp">购&nbsp物&nbsp车</a></li>
             </ul>
             <%if(session.getAttribute("userid")!=null){ %>
             <div id="user-info">
             <img src="images/logo.jpg"width="80" height="80" id="logo">
             <span>&nbsp;用户ID:<%=session.getAttribute("userid")%></span>
             <span></span>
             </div>
             <%}else{ %>
             
             <div class="sign-in-box">
							<h2>请登录</h2>
			   <form action="login.do" method="post">
				   <div class="text-boxs">
						   <input name="username" type="text" class="text-box" placeholder="用户名" required /> 
						   <div class="clearfix"> </div>
							<input name="password" type="password" class="text-box" placeholder="密码" required /> 
							<div class="clearfix"> </div>
				 </div>
				 <input type="submit" value="登陆" /><br>
                 <a href=''>注册</a>
			   </form>
			</div>
			 
			<%} %>
         </div>
         <div id="right">
             <div id="right-content">
                 <div id="cart-block">
                 <%!Collection<Integer> goods = null; %>
                 <%!GoodsInfoInitDAO dao = new GoodsInfoInitDAO(); %>
                 <%Map<Integer, Integer> map = null; %>
                 <%int totalPrice = 0; %>
                 <%if(session.getAttribute("GOODS_IN_CART") != null) { 
	               map = (Map<Integer, Integer>)session.getAttribute("GOODS_IN_CART");
	               goods = map.keySet();
                   }
                 %>
                     <table class="cart-table">
                         <tr><th colspan=2>商品</th><th>单价</th><th>数量</th><th>小计</th><th>操作</th></th>
                         <tr><td><img src="images/logo.jpg"width="70" height="70"></td><td>一只怪兽</td><td>￥100</td><td>1</td><td>1000</td><td>删除</td></tr>
                         <%if(map != null){%>
                         <%for(int gid:goods) {
                                GoodsInfoVO vo = dao.getGoodsInfo(gid);
                         %>
                         	<tr><td><%="<img src='"+vo.getPictureURL()+"' width='70' height='70'>" %></td>
                         	<td><%=vo.getGood() %></td>
                         	<td><%="￥"+vo.getPrice() %></td>
                         	<td><%= map.get(gid) %></td>
                         	<td><%="￥"+vo.getPrice()*map.get(gid) %></td>
                         	<td><%="<a href='removeGoods.do?id="+gid+"'>" %>删除</a></td>
                         	</tr>
                         <%totalPrice+=vo.getPrice()*map.get(gid);
                           }%>
                            <tr>
                                <td colspan=5>总价:￥<%= totalPrice %></td>
                                <td><a href='removeGoods.do?msg=clear'>清空</a><br>
                                    <a href='createOreder.jsp' id="pay-button">结算</a>
                                </td>
                            </tr>
                         <%
                         } else {%>
                         <tr><td colspan=6>购物车中没有商品</td></tr>
                         <%} %>
                         <tr></tr>
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
    