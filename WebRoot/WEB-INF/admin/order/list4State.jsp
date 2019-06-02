<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td align="center" bgColor="#afd1f3">
					<strong>订单列表</strong>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1" 
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<s:iterator var="order" value="pageBean.list">							
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="left" width="10%" colspan="5">
									订单号:&nbsp;<s:property value="#order.oid"/> &nbsp;&nbsp;&nbsp;&nbsp;
									订单金额:&nbsp;<s:property value="#order.total"/> &nbsp;&nbsp;&nbsp;&nbsp;
									订单状态:&nbsp;
									<s:if test="#order.state==0">
										未付款
									</s:if>
									<s:elseif test="#order.state==1">
										<a href="${pageContext.request.contextPath}/order_adminUpdateState.action?oid=<s:property value="#order.oid"/>"><font color="red">发货</font></a>
									</s:elseif>
									<s:elseif test="#order.state==2">
										已经发货
									</s:elseif>
									<s:elseif test="#order.state==3">
										订单完成
									</s:elseif>&nbsp;&nbsp;&nbsp;&nbsp;
									订单发货地址：&nbsp;<s:property value="#order.addr"/>&nbsp;&nbsp;&nbsp;&nbsp;
									收货人：&nbsp;<s:property value="#order.name"/>&nbsp;&nbsp;&nbsp;&nbsp;
									联系方式：&nbsp;<s:property value="#order.phone"/>							
								</td>
							</tr>
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<th style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
									序号
								</th>
								<th style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
									图片
								</th>
								<th style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
									名称
								</th>
								<th style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
									数量
								</th>
								<th style="CURSOR: hand; HEIGHT: 22px" align="center" width="23%">
									小计 
								</th>
							</tr>
							<s:iterator var="orderItem" value="#order.orderItems" status="status">								
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
										<s:property value="#status.count"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
										<img width="45" height="50" src="${pageContext.request.contextPath}/<s:property value="#orderItem.book.image"/>" title="<s:property value="#orderItem.book.bname"/>" alt="<s:property value="#orderItem.book.bname"/>"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
										<s:property value="#orderItem.book.bname"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
										<s:property value="#orderItem.count"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="23%">
										<s:property value="#orderItem.subtotal"/>
									</td>								
								</tr>
							</s:iterator>
						</s:iterator>	
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td align="center" width="18%" colspan="8">
								第 <s:property value="pageBean.page"/> / <s:property value="pageBean.totalPage"/> 页
								<s:if test="pageBean.page != 1">
									<a href="${ pageContext.request.contextPath }/order_adminFindByState.action?pageNum=1">首页</a> |
									<a href="${ pageContext.request.contextPath }/order_adminFindByState.action?pageNum=<s:property value="pageBean.page-1"/>">上一页</a> |
								</s:if>
								<s:if test="pageBean.page != pageBean.totalPage">
									<a href="${ pageContext.request.contextPath }/order_adminFindByState.action?pageNum=<s:property value="pageBean.page+1"/>">下一页</a> |
									<a href="${ pageContext.request.contextPath }/order_adminFindByState.action?pageNum=<s:property value="pageBean.totalPage"/>">尾页</a>
								</s:if>
							</td>	
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
