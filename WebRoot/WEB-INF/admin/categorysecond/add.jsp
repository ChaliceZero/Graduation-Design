<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
	<form action="${pageContext.request.contextPath}/categorySecond_adminSave.action" method="post" enctype="multipart/form-data">
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>添加二级分类</strong>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe">
					二级分类名称：
				</td>
				<td bgColor="#ffffff" colspan="3">
					<input type="text" name="csname" value=""/>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe">
					所属一级分类：
				</td>
				<td bgColor="#ffffff" colspan="3">
					<select name="cid">
						<s:iterator var="c" value="cList">
							<option value="<s:property value="#c.cid"/>"><s:property value="#c.cname"/></option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4">
					<img src="${pageContext.request.contextPath}/images/shim.gif">
				</td>
			</tr>
			<tr>
				<td style="width:100%" align="center" bgColor="#f5fafe" colSpan="4">
					<button type="submit" style="width:80px;height:28px;" value="确定">
						&#30830;&#23450;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<button type="reset" value="重置" style="width:80px;height:28px;">
						&#37325;&#32622;
					</button>
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input type="button" onclick="history.go(-1)" value="返回" style="width:80px;height:28px;"/>
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>