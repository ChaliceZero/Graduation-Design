<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add('01',-1,'系统菜单');
		d.add('0101','01','用户管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010101','0101','用户管理','${pageContext.request.contextPath}/user_adminFindAll.action?pageNum=1','','mainFrame');
		d.add('0102','01','图书管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010201','0102','图书管理','${pageContext.request.contextPath}/book_adminFindAll.action?pageNum=1','','mainFrame');
		d.add('0103','01','订单管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010301','0103','所有订单','${pageContext.request.contextPath}/order_adminFindAll.action?pageNum=1','','mainFrame');
		d.add('010302','0103','未付款订单','${pageContext.request.contextPath}/order_adminFindByState.action?pageNum=1&state=0','','mainFrame');
		d.add('010303','0103','已付款订单','${pageContext.request.contextPath}/order_adminFindByState.action?pageNum=1&state=1','','mainFrame');
		d.add('010304','0103','已发货订单','${pageContext.request.contextPath}/order_adminFindByState.action?pageNum=1&state=2','','mainFrame');
		d.add('010305','0103','已完成订单','${pageContext.request.contextPath}/order_adminFindByState.action?pageNum=1&state=3','','mainFrame');
		d.add('0104','01','一级分类管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010401','0104','一级分类管理','${pageContext.request.contextPath}/category_adminFindAll.action','','mainFrame');
		d.add('0105','01','二级分类管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010501','0105','二级分类管理','${pageContext.request.contextPath}/categorySecond_adminFindAll.action?pageNum=1','','mainFrame');
		d.add('0106','01','系统管理','${pageContext.request.contextPath}/admin_welcomeFrame.action','','mainFrame');
		d.add('010601','0106','系统管理员','${pageContext.request.contextPath}/admin_adminFindAll.action','','mainFrame');
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
