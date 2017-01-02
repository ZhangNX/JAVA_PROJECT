<%@page import="cn.bjfu.im.dao.OrderDetailDAO"%>
<%@page import="cn.bjfu.im.dao.GoodsInfoInitDAO"%>
<%@page import="cn.bjfu.im.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="cn.bjfu.im.vo.GoodsInfoVO" %>
<%@ page import="cn.bjfu.im.vo.OrderDetailVO" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单详情</title>
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
             <span id="myname_01"><a href="index.html">订单详情</a></span>
         </div>

  </div>
     <div id="middle">
         <div id="left">
             <ul id="menu_left">
                <li><a href="index.jsp">首&nbsp&nbsp&nbsp页</a></li>
                <li><a href="orderList.jsp">订&nbsp&nbsp&nbsp单</a></li>
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
                 <%Collection<Integer> goods = null;
                   Map<Integer, Integer> map = null;
                   if(session.getAttribute("GOODS_IN_CART") != null) { 
	               map = (Map<Integer, Integer>)session.getAttribute("GOODS_IN_CART");
	               goods = map.keySet();
                   }
                 %>
                 <%int oid = 0;
                   ArrayList<OrderDetailVO> voList = null;
                   int totalPrice = 0;
                   OrderDetailDAO oDao = new OrderDetailDAO();
                   GoodsInfoInitDAO gDao = new GoodsInfoInitDAO();
                   oid = Integer.parseInt(request.getParameter("oid"));
                   voList = oDao.getOrderDetail(oid);
                 %>
                     <table class="cart-table">
                         <tr><th>商品</th><th>单价</th><th>数量</th><th>小计</th></th>
                         <%for(OrderDetailVO ovo:voList) {
                        	 GoodsInfoVO gVo = gDao.getGoodsInfo(ovo.getGid());
                         %>
                         	<tr>
                         	<td><%=gVo.getGood() %></td>
                         	<td><%="￥"+gVo.getPrice() %></td>
                         	<td><%= ovo.getAmount() %></td>
                         	<td><%="￥"+ovo.getAmount()*gVo.getPrice() %></td>
                         	</tr>
                         <%totalPrice+=gVo.getPrice()*ovo.getAmount();
                           }%>
                            <tr>
                                <td colspan=4>总价:￥<%= totalPrice %></td>
                            </tr>
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
    