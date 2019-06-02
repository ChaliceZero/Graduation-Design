<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body style="font-family:'微软雅黑';">
	<form action="${pageContext.request.contextPath}/admin_adminSave.action" method="post" enctype="multipart/form-data">
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>添加系统管理员</strong>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe">
					管理员名称：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="adminName" width="320"/>
				</td>
				<td width="18%" align="center" bgColor="#f5fafe">
					管理员密码：
				</td>
				<td bgColor="#ffffff">
					<input type="text" name="adminPwd" width="320"/>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4">
					<img src="${pageContext.request.contextPath}/images/shim.gif">
				</td>
			</tr>
			<tr>
				<td style="width:100%" align="center" bgColor="#f5fafe" colSpan="4">
					<button type="submit" value="确定" style="width:80px;height:28px;">
						&#30830; &#23450;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<button type="reset" value="重置" style="width:80px;height:28px;">
						&#37325; &#32622;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input type="button" onclick="history.go(-1)" style="width:80px;height:28px;" value="返 回"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>