<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	BODY {
		MARGIN: 0px;
		BACKGROUND-COLOR: #ffffff
	}
	
	BODY {
		FONT-SIZE: 12px;
		COLOR: #000000
	}
	
	TD {
		FONT-SIZE: 12px;
		COLOR: #000000
	}
	
	TH {
		FONT-SIZE: 12px;
		COLOR: #000000
	}
</style>
<script type="text/javascript">
function logout() {
	parent.window.location.href="${pageContext.request.contextPath}/admin_logout.action";
}
</script>
</head>
<body>
	<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- <img width="100%" src="${pageContext.request.contextPath}/images/top_01.jpg"> --%>
			</td>

			<td width="100%" style="background:#77BCF5;">
				<h1 style="color:#fff;margin:8px auto auto 80px;">教育书城 - 后台管理系统</h1>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" valign="bottom" background="${pageContext.request.contextPath}/images/mis_01.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#000000">
								<SCRIPT LANGUAGE="JavaScript">
									var tmpDate = new Date();
									//var year = tmpDate.getYear();
									//var year = "20" + (tmpDate.getYear() - 100);
									//var year = tmpDate.getYear()<1900 ? (tmpDate.getYear() + 1900):tmpDate.getYear();
									var year = tmpDate.getFullYear();
									var month = tmpDate.getMonth() + 1;
									var date = tmpDate.getDate();
									document.write(year);
									document.write(" 年 ");
									document.write(month);
									document.write(" 月 ");
									document.write(date);
									document.write(" 日 ");
									
									myArray=new Array(6);
									myArray[0]="星期日"
									myArray[1]="星期一"
									myArray[2]="星期二"
									myArray[3]="星期三"
									myArray[4]="星期四"
									myArray[5]="星期五"
									myArray[6]="星期六"
									weekday=tmpDate.getDay();
									if (weekday==0 | weekday==6) {
										document.write(myArray[weekday])
									} else {
										document.write(myArray[weekday])
									};
								</SCRIPT>
							</font>
						</td>
						<td width="20%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="16" background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/images/mis_05a.jpg" width="6" height="18">
									</td>
									<td width="150" valign="middle" background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										当前用户：
										<font color="blue"><s:property value="#session.existAdmin.adminName"/></font>&nbsp;&nbsp;|&nbsp;&nbsp;
										<font color="blue"><a style="text-decoration: none;" onclick="logout()" href="#">退出系统</a></font>
									</td>
									<td width="10" align="right" background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/images/mis_05c.jpg" width="6" height="18">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>
