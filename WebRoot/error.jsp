<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览页面出错</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, 
	abbr, acronym, address, big, cite, code, del, dfn, em, font, img, ins, kbd, q, s, samp, small, 
	strike, strong, sub, sup, tt, var, dd, dl, dt, li, ol, ul, fieldset, form, label, legend, table, 
	caption, tbody, tfoot, thead, tr, th, td {
		padding: 0px;
		margin: 0px;
		border: 0px;
		outline: 0px;
	}
	body {background-color: #ECFBFE;}
	ul li {list-style: none;}
	#divcontent1 a {display:inline-block;font-weight:600; position:absolute; margin-left:20px;margin-top:25px;}
	#logo {
		margin: 4% auto 0px 16%;
		display:inline-block;
	}
	#logo h1 {font-size:18px;}
	#logo p {
		display:inline;
	}
</style>
</head>
<body>
<!-- <div id="logo">
	<h1>教育书城销售系统</h1>	
	<p> —— Management System of Educational Bookstore</p>
</div> -->
<br/> 
<div id="divcontent1">
<table width="850px" border="0" cellspacing="0">
	<tr>
		<td style="padding:30px; text-align:center">
	    	<table width="60%" border="0" cellspacing="0" style="margin-top:40px">
		     	<tr>
			       	<td style="padding-top:40px;">
				       	<font style="font-weight:bold;color:#FF0000;font-size:18px;position:absolute;left:340px;">
			       			页 面 出 错 了，客 官，请 重 新 登 录 吧！
			       		</font>
				       	<br/><br/><br/>
				        <a href="${pageContext.request.contextPath}/index.action" style="font-size:18px;left:320px;">首页</a>&nbsp;&nbsp;
				        <a href="${pageContext.request.contextPath}/user_registPage.action" style="font-size:18px;left:400px;">注册</a>&nbsp;&nbsp;
				        <a href="${pageContext.request.contextPath}/user_loginPage.action" style="font-size:18px;left:480px;">登录</a>
			        </td>
				</tr>
	   	 	</table>
    	</td>
    </tr>
</table>
</div>
</body>
</html>