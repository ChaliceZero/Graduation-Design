<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="container header" style="border-bottom: 5px solid #fff">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index.jsp">
				<img src="${pageContext.request.contextPath}/image/logos/logo3.jpg" alt="教育书城"/>
			</a>
		</div>
	</div>
	<div class="span9">
		<%-- <div class="headerAd"> --%>
		<div>
			<img src="${pageContext.request.contextPath}/image/logos/header.jpg" width="320px" height="50px" alt="正品保障" title="正品保障"/>
		</div>
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.existUser != null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<s:property value="#session.existUser.name"/>
						<span style="color:#E6E6E6;">|</span>
					</li>
					<li id="headerLogout" class="headerLogout" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user_quit.action">退出</a>
						<span style="color:#E6E6E6;">|</span>
					</li>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user_findSelf.action?uid=<s:property value="#session.existUser.uid"/>">用户中心</a>
						<span style="color:#E6E6E6;">|</span>
					</li>
				</s:if>	
				<s:else>
					<li>
						<a href="${pageContext.request.contextPath}/user_loginPage.action">用户登录</a>
						<span style="color:#E6E6E6;">|</span>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/user_registPage.action">用户注册</a>
						<span style="color:#E6E6E6;">|</span>
					</li>
				</s:else>
				<li class="cart">
					<a href="${pageContext.request.contextPath}/cart_myCart.action">购物车</a>
				</li>
				<li>
					<span style="color:#E6E6E6;">|</span>
					<a href="${pageContext.request.contextPath}/order_findOrdersByUid.action?pageNum=1">我的订单</a>
				</li>
			</ul>
		</div>
		<%-- <div class="cart">
			<a href="${pageContext.request.contextPath}/cart_myCart.action">购物车</a>
		</div> --%>
		<%-- 
			border: 0 none;       /*去掉边框*/
			outline-style: none;  /*去掉轮廓线*/
		 --%>
		
		<div class="search">
			<form action="${pageContext.request.contextPath}/book_searchBook.action?pageNum=1" method="post">
				<input type="text" id="search" name="search" style="width:148px;height:15px;margin-left:27px;margin-top:2px;border:none;"autofocus="autofocus"/>
				<strong>
					<input type="submit" value="搜索" style="display:inline-block;float:right;color:#fff;width:50px;height:24px;border:none;background:none;"/>					
				</strong>
			</form>
		</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
			<li>
				<a href="${pageContext.request.contextPath}/index.action">首页</a>
			</li>
			<s:iterator var="c" value="#session.categoryList">
				<li>
					|<a href="${pageContext.request.contextPath}/book_findByCid.action?cid=<s:property value="#c.cid"/>&pageNum=1"><s:property value="#c.cname"/></a>
				</li>
			</s:iterator>
		</ul>
	</div>
</div>	