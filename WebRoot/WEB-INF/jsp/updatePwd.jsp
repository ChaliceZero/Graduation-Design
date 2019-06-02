<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改密码 - 教育书城</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
	function checkForm(){
		
		// 校验密码:
		var password = $("#password").val();
		var newpassword = $("#newPassword").val();
		//var hwd = $("#hwd").val();
		
		if(password == ''){
			alert("原密码不能为空");
			return false;
		}
		
		if(newpassword == ''){
			alert("新密码不能为空");
			return false;
		}
		
		if(newpassword.length<6 || newpassword.length>12) {
			alert("密码长度在 6 到 12 位之间")
			return false;
		}
		
		return true;
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
					<strong>修改密码</strong>USER PWD
					<!-- <button onclick="window.history.back(-1);" style="float:right;margin-right:30px;margin-top:8px;height:28px;">返回上一页</button> -->
				</div>
				<form id="registerForm"
						action="${pageContext.request.contextPath}/user_updatePwd.action?uid=<s:property value="model.uid"/>"
						method="post" onsubmit="return checkForm()">
					
					<table align="center">
						<tbody>
							<tr>
								<th><span class="requiredField">*</span>原密码:</th>
								<td><input type="password" id="password" name="password" style="float:left;"
									class="text" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th><span class="requiredField">*</span>新密码:</th>
								<td><input type="password" id="newPassword" style="margin-top:2px;"
									name="newPassword" class="text" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th></th>
								<td><input type="submit" class="submit" value="确认修改"/></td>
							</tr>
							<tr>
								<th></th>
								<td>
									<span style="float:left;font-size:16px;margin-left:10px;margin-top:2px;color:red;">
										<s:actionmessage/>
									</span>
								</td>
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
