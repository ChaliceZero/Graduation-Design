<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript">
	function addAdmin(){
		window.location = "${pageContext.request.contextPath}/admin_adminAddPage.action";				
	}
	//&#28155;&#21152;添加
</script>
</head>
<body>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
		<tbody>	
			<tr>
				<td align="center" bgColor="#afd1f3">
					<strong>管理员列表</strong>
				</td>
			</tr>
			<tr>
				<td align="left">
					<button type="button" value="添加管理员" style="width:154px; height:28px;" onclick="addAdmin()">
						添加管理员
					</button>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1"
						style="BORDER-RIGHT:gray 1px solid; BORDER-TOP: gray 1px solid; 
						BORDER-LEFT:gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; 
						BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="5%">
								序号
							</td>
							<td align="center" width="18%">
								管理员名称
							</td>
							<!-- <td width="4%" align="center">
								编辑
							</td> -->
							<td width="4%" align="center">
								删除
							</td>
						</tr>
						<s:iterator var="c" value="aList" status="status">
							<tr onmouseover="this.style.backgroundColor = '#ccc'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR:hand; HEIGHT:22px" align="center" width="5%">
									<s:property value="#status.count"/>
								</td>
								<td style="CURSOR:hand; HEIGHT:22px" align="center" width="18%">
									<s:property value="#c.adminName"/>
								</td>										
								<%-- <td align="center" style="HEIGHT: 22px">
									<a href="${pageContext.request.contextPath}/admin_adminEditPage.action?aid=<s:property value="#c.aid"/>">
										<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
									</a>
								</td> --%>
								<td align="center" style="HEIGHT: 22px">
									<a href="${pageContext.request.contextPath}/admin_adminDelete.action?aid=<s:property value="#c.aid"/>">
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

