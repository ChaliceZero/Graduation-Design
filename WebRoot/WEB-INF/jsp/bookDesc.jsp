<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<title>图书详情 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="./head.jsp"%>
<form action="${pageContext.request.contextPath}/cart_addCart.action" target="_blank" method="post">
<input type="hidden" name="bid" value="<s:property value="model.bid"/>"/>
<div class="container productContent">
	<div class="span6">
		<div class="hotProductCategory">
			<%-- 一级分类显示 --%>
			<s:iterator var="c" value="categoryList">
				<dl>
					<dt>
						<a href="${pageContext.request.contextPath}/book_findByCid.action?cid=<s:property value="#c.cid"/>&pageNum=1"><s:property value="#c.cname"/></a>
					</dt>
					<!-- 显示二级分类 -->
					<s:iterator var="cs" value="#c.categorySeconds">
						<dd>
							<a href="${pageContext.request.contextPath}/book_findByCsid.action?csid=<s:property value="#cs.csid"/>&pageNum=1"><s:property value="#cs.csname"/></a>
						</dd>
					</s:iterator>
				</dl>
			</s:iterator>	
		</div>
	</div>
	<div id="introduction" class="introduction">
		<div class="title">
			<strong>图书详情</strong>
		</div>
	</div>
	<div class="span18 last">
		<div class="productImage">
			<a title="点击订购" href="#">
				<img style="opacity:1;" title="<s:property value="model.bname"/>" class="medium" src="${pageContext.request.contextPath}/<s:property value="model.image"/>"/>
			</a>
		</div>
		<div class="name"><s:property value="model.bname"/></div>
		<div class="sn">
			<div>编 号：<s:property value="model.bid"/></div>
		</div>
		<div class="info">
			<dl>
				<dt>书 城 价:</dt>
				<dd>
					<strong>￥&nbsp;<s:property value="model.shop_price"/>&nbsp;元/本</strong>
						市 场 价：
					<del>￥&nbsp;<s:property value="model.market_price"/>&nbsp;元/本</del>
				</dd>
			</dl>
			<dl>
				<dt>促 销:</dt>
				<dd>
					<a target="_blank" title="限时抢购 (2019-03-11 ~ 2020-01-01)">限 时 抢 购</a>
				</dd>
			</dl>
		</div>
		<div class="action">
			<dl class="quantity">
				<dt>购买数量:</dt>
				<dd>
					<input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text"/>
					<div>
						<span id="increase" class="increase">&nbsp;</span>
						<span id="decrease" class="decrease">&nbsp;</span>
					</div>
				</dd>
				<dd>件</dd>
			</dl>
			<div class="buy">
				<input id="addCart" class="addCart" value="加入购物车" type="submit"/>
			</div>
		</div>
			
		<div id="introduction" class="introduction">
			<div class="title">
				<strong>图书介绍</strong>
			</div>
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="model.bdesc"/>
			</div>
		</div>
	</div>
</div>
<%@ include file="./foot.jsp"%>
</form>
</body>
</html>