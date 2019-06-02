<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>订单中心 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="./head.jsp"%>
<div class="container cart">
	<div class="span24">
		<div class="step1">
			<span style="font-size:16px; margin:10px auto 6px 5px; display:block;">订单列表<s:property value="order.oid"/></span>
		</div>
		<table>
			<tbody>
				<%-- <s:iterator var="order" value="orders"> --%>
				<s:iterator var="order" value="pageBean.list">
					<tr>
						<th colspan="2">订单号：&nbsp;<s:property value="#order.oid"/></th>
						<th colspan="1">订单金额：&nbsp;<s:property value="#order.total"/>&nbsp;元</th>
						<th colspan="2">状态：&nbsp;
							<s:if test="#order.state == 0">
								<a href="${pageContext.request.contextPath}/order_findByOid.action?oid=<s:property value="#order.oid"/>" title="去付款"><font color="red">未付款</font></a>
							</s:if>
							<s:elseif test="#order.state == 1">
								<span>已付款</span>
							</s:elseif>
							<s:elseif test="#order.state == 2">
								<a href="${pageContext.request.contextPath}/order_userUpdateState.action?oid=<s:property value="#order.oid"/>" title="确认收货"><font color="red">确认收货</font></a>
							</s:elseif>
							<s:elseif test="#order.state == 3">
								<span>订单完成</span>
							</s:elseif>
						</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>书籍</th>
						<th>单价</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator var="orderItem" value="#order.orderItems">	
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.book.image"/>"/>
							</td>
							<td>
								<a target="${pageContext.request.contextPath}/book_findByBid.action?bid=<s:property value="#orderItem.book.bid"/>"><s:property value="book.bname"/></a>
							</td>
							<td>
								<s:property value="#orderItem.book.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥&nbsp;<s:property value="#orderItem.subtotal"/>&nbsp;元</span>
							</td>
						</tr>
					</s:iterator>
				</s:iterator>
			</tbody>
		</table>
		<div class="pagination">&nbsp;&nbsp;
		第	<s:property value="pageBean.page"/> / <s:property value="pageBean.totalPage"/> 页
			<s:if test="pageBean.page != 1">
				<a href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=1" class="firstPage">&nbsp;</a>		
				<a href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>	
			</s:if>	
			<s:iterator var="i" begin="1" end="pageBean.totalPage" step="1">
				<s:if test="pageBean.page==#i">
					<span class="currentPage"><s:property value="#i"/></span>
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBean.page != pageBean.totalPage">
				<a class="nextPage" href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>	
		</div>
	</div>
</div>
<%@ include file="./foot.jsp" %>
</body>
</html>