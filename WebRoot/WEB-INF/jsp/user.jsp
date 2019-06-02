<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会员中心 - 教育书城</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function checkForm(){
		// 校验用户名:
		var username = document.getElementById("username").value;
		if(username.trim() == ''){
			alert("用户名不能为空");
			return false;
		}
		
		// 校验密码:
		var password = document.getElementById("password").value;
		if(password == ''){
			alert("密码不能为空");
			return false;
		}
		
		// 校验确认密码
		var repassword = document.getElementById("repassword").value;
		if(password != repassword){
			alert("两次密码不一致");
			return false;
		}
		// 校验email:
		var email = document.getElementById("email").value;
		if(email.trim() == ''){
			alert("邮箱不能为空");
			return false;
		}
		// 校验phone:
		var phone = document.getElementById("phone").value;
		if(phone.trim() == ''){
			alert("电话不能为空");
			return false;
		}
		return true;
	}
	

	function checkUserName() {
		var username = $("#username").val();
		var husername = $("#hun").val();
		
		if(username == husername) {
			$("#span1").empty();
			return;
		}
		if(username != "")
			$("#span1").load("${pageContext.request.contextPath}/user_checkUserName.action?i=1&="+new Date().getTime(),{'username':username});
	}
	
	function alterPwd() {
		var uid = $("#userid").val();
		window.open("${pageContext.request.contextPath}/user_updatePwdPage.action?uid="+uid);
	}
</script>
</head> 
<body>
<%@ include file="./head.jsp"%>
<div class="container register">
	<div class="span24">
		<div class="wrap">
			<div class="main clearfix">
				<div class="title">
					<strong>会员信息</strong>USER MSG
					<button onclick="alterPwd()" style="float:right;margin-right:30px;margin-top:8px;height:28px;">修改账户密码</button>
				</div>
				<form id="registerForm"
						action="${pageContext.request.contextPath}/user_updateUser.action?uid=<s:property value="model.uid"/>"
						method="post" onsubmit="return checkForm()">
					<input type="hidden" name="userid" id="userid" value="<s:property value="model.uid"/>" />
					<input type="hidden" id="hun" value="<s:property value="#session.existUser.username"/>"/>
					<table align="center">
						<tbody>
							<tr>
								<th>用户名：</th>
								<td><input type="text" id="username" name="username" value="<s:property value='model.username'/>"
											class="text" maxlength="20" onblur="checkUserName()"/>
									<span style="font-size:16px;margin-left:10px;margin-top:2px;color:red;" id="span1">
										<s:fielderror fieldName="username" />
									</span>
								</td>
							</tr>
							<tr>
								<th>E-mail：</th>
								<td><input type="text" id="email" name="email" style="float:left;" value="<s:property value='model.email'/>"
											class="text" maxlength="200"/>
									<span style="float:left;font-size:16px;margin-top:2px;color:red;margin-left:10px;">
										<s:fielderror fieldName="email" />
									</span>
								</td>
							</tr>
							<tr>
								<th>电话：</th>
								<td><input type="text" id="phone" name="phone" style="float:left;" value="<s:property value='model.phone'/>"
											class="text" maxlength="200"/>
									<span style="float:left;font-size:16px;margin-top:2px;color:red;margin-left:10px;">
										<s:fielderror fieldName="phone" />
									</span>
								</td>
							</tr>
							<tr>
								<th>姓名：</th>
								<td><input type="text" name="name" class="text" maxlength="200" style="margin-top:2px;" 
											value="<s:property value='model.name'/>"/>
								</td>
							</tr>
							<tr>
								<th>性别：</th>
								<td>
									<s:radio list="{'男','女'}" name="gender" value="%{model.gender}"></s:radio>
									<%-- <label>
										<input type="radio" name="gender" value="男" <s:if test="model.gender == 男">checked</s:if> />男 
									</label>
									<label>
										<input type="radio" name="gender" value="女" <s:if test="model.gender == 女">checked</s:if> />女
									</label> --%>
								</td>
							</tr>
							<tr>
								<th>地址：</th>
								<td><input type="text" name="address" class="text" style="margin-top:2px;" value="<s:property value='model.address'/>"
									maxlength="200"/>
								</td>
							</tr>
							<tr>
								<th>账户状态：</th>
								<td>
									<%--该功能在本系统中多余  <s:if test="modell.status == 0"><a href="${pageContext.request.contextPath}/user_active.action" title="去激活">未激活</a></s:if> --%>
									
									<s:if test="model.status == 0">未激活</s:if>
									<s:if test="model.status == 1">已激活</s:if>
								</td>
							</tr>
							<tr>
								<th></th>
								<td><input type="submit" class="submit" value="确认修改"/></td>
							</tr>
						</tbody>
					</table>
					<div class="login">
						<div class="ad">
							<dl>
								<dt>我们承诺</dt>
								<dd> * 绝不泄露用户信息</dd>
								<dd> * 绝不侵犯用户隐私</dd>
								<dd> * 绝不侵占用户权益</dd>
							</dl>
						</div>
						<dl>
							<dt>对我们不了解？</dt>
							<dd>
								立即查看本团队白皮书！ <a href="${pageContext.request.contextPath}/user_loginPage.action">点击查看</a>
							</dd>
						</dl>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="./foot.jsp"%>
</body>
</html>
