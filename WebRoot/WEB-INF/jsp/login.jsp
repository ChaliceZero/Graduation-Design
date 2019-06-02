<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>会员登录 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function changeImg() {
		var checkImg = document.getElementById("checkImg");
		checkImg.src = "${pageContext.request.contextPath}/checkImg.action?i=1&=" + new Date().getTime();
	}
</script>
</head>
<body>
<%@ include file="./head.jsp"%>
<div class="container login">
	<div class="span12">
		<div class="ad">
			<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录"/>
		</div>		
	</div>
	<div class="span12 last">
		<div class="wrap">
			<div class="main">
				<div class="title">
					<strong>会员登录</strong>USER LOGIN
				</div>
				<div style="width:100%; height:36px;border-bottom: dashed 1px #ccc;">
					&nbsp;&nbsp;&nbsp;<span style="color:red;font-size:16px;margin-left:10px;display:block;"><s:actionerror/></span>
				</div>
				<form id="loginForm" action="${pageContext.request.contextPath}/user_login.action" method="post" novalidate="novalidate">
					<table>
						<tbody><tr>
							<th>
								用户名:
							</th>
							<td>
								<input type="text" id="username" name="username" class="text" maxlength="20" value="<s:property value="model.username"/>"/>
							</td>
						</tr>
						<tr>
							<th>
								密&nbsp;&nbsp;码:
							</th>
							<td>
								<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off" value="<s:property value="model.password"/>"/>
							</td>
						</tr>
							<tr>
								<th>验证码:</th>
								<td>
									<span class="fieldSet">
										<input type="text" id="checkcode" name="checkcode" class="text captcha" maxlength="4"/><img id="checkImg" class="captchaImage" onclick="changeImg()" src="${pageContext.request.contextPath}/checkImg.action" title="点击更换验证码"/>
									</span>
								</td>
							</tr>
						<tr>
							<th>&nbsp;</th>
							<td>
								<!-- <label>
									<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true"/>记住用户名
								</label>
								<label>
									&nbsp;&nbsp;<a href="#">找回密码</a>
								</label> -->
							</td>
						</tr>
						<tr>
							<th>&nbsp;
								
							</th>
							<td>
								<input type="submit" class="submit" value="登 录"/>
							</td>
						</tr>
						<tr class="register">
							<th>&nbsp;
								
							</th>
							<td>
								<dl>
									<dt>还没有注册账号？</dt>
									<dd>
										立即注册即可体验在线购物！
										<a href="${pageContext.request.contextPath}/user_registPage.action">立即注册</a>
									</dd>
								</dl>
							</td>
						</tr></tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="./foot.jsp" %>
</body></html>