<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<style>
	body {
		SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
		
	}
</style>
</head>  
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/admin_topFrame.action" name="topFrame" scrolling="NO" noresize/>
		<frameset cols="170,*" frameborder="0" border="0" framespacing="0">
			<frame src="${pageContext.request.contextPath}/admin_leftFrame.action" name="leftFrame" noresize scrolling="YES"/>
			<frame src="${pageContext.request.contextPath}/admin_welcomeFrame.action" name="mainFrame"/>
		</frameset>
	<frame src="${pageContext.request.contextPath}/admin_bottomFrame.action" name="bottomFrame" scrolling="NO"  noresize/>
</frameset>
</html>
