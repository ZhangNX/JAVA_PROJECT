<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="shortcut icon" href="images/logo.jpg" >
<link rel="stylesheet" type="text/css" href="css/index.css"/>

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
             <div class="sign-in-box">
							<h2>我的朋友？</h2>
			   <form method="post" action="login.do">
				   <div class="text-boxs">
					   <span class="text-box">
						   <input type="text" name="username" placeholder="用户名" required /> 
						   <div class="clearfix"> </div>
					   </span>
					   <span class="text-box">
							<input type="text" name="password" placeholder="密码" required /> 
							<div class="clearfix"> </div>
					 </span>
				 </div>
				 <input type="submit" value="登陆" /><br>
                 <a ref="registor.do">注册</a>
			   </form>
			</div>
         </div>
         <div id="right">
             <div id="homepage_image">
                 <dl><dd>欢迎光临</dd><dd>欢迎再次光临</dd><dd>祝你吃好喝好</dd></dl>
                 <div .class="photos"><img src="images/meat03.jpg" width="374" height="374">
                 </div>
             </div>
         </div>
     <div id="tail">
      <div id="copyright">Copyright © 2015 周兵杰. All Rights Reserved</div>
     </div>
</div>
</body>
</html>
    