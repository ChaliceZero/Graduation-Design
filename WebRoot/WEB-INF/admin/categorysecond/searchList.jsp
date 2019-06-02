<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
	function addCategorySecond(){
		window.location = "${pageContext.request.contextPath}/categorySecond_adminAddPage.action";				
	}
	function search() {
		var keywords = document.getElementById("keywords").value;
		window.location.href = "${pageContext.request.contextPath}/categorySecond_searchKeys.action?keywords="+keywords;
	}
	//&#28155;&#21152;添加
</script>
</head>
<body>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tbody>	
				<tr>
					<td align="center" bgColor="#afd1f3">
						<strong>二级分类列表</strong>
					</td>
				</tr>
				<tr>
					<td align="left">
						<button type="button" value="添加" style="width:154px; height:28px;" onclick="addCategorySecond()">
							添加二级分类
						</button>
						&nbsp;&nbsp;二级分类名：<input type="text" id="keywords" name="keywords"/>
						&nbsp;&nbsp;<input type="button" value="检索" onclick="search()"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; 
								WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="5%">
									序号
								</td>
								<td align="center" width="9%">
									二级分类名称
								</td>
								<td align="center" width="9%">
									所属一级分类
								</td>
								<td width="4%" align="center">
									编辑
								</td>
								<td width="4%" align="center">
									删除
								</td>
							</tr>
							<s:iterator var="cs" value="csList" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">
										<s:property value="#status.count"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="9%">
										<s:property value="#cs.csname"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="9%">
										<s:property value="#cs.category.cname"/>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<a href="${pageContext.request.contextPath}/categorySecond_adminEditPage.action?csid=<s:property value="#cs.csid"/>">
											<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<a href="${pageContext.request.contextPath}/categorySecond_adminDelete.action?csid=<s:property value="#cs.csid"/>">
											<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</s:iterator>	
						</table>
					</td>
				</tr>
			</tbody>
		</table>
</body>
</html>

