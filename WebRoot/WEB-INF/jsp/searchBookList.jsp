<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>图书检索 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="./head.jsp"%>
<div class="container productList">
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
	<div style="width: 950px;height: 20px;border-width:1px solid red;margin:6px auto;">
		<span style="font-family:'微软雅黑'; font-size:16px;">
			搜索关键字：<s:property value="search"/>
		</span>
	</div>
	<div class="span18 last">
		<div id="result" class="result table clearfix">
			<ul>
				<s:iterator var="b" value="pageBean.list">
					<li>
						<a href="${pageContext.request.contextPath}/book_findByBid.action?bid=<s:property value="#b.bid"/>">
							<img src="${pageContext.request.contextPath}/<s:property value="#b.image"/>" width="170" height="170" style="display:inline-block;"/>   
							<span style="color:green;font-family:微软雅黑;" class="price">
							<s:property value="#b.bname"/>
							</span> 
							<span class="price">
								书城价： ￥<s:property value="#b.shop_price"/>
							</span> 
						</a>
					</li>
				</s:iterator>	
			</ul>
		</div>
		<div class="pagination">&nbsp;&nbsp;
		第	<s:property value="pageBean.page"/> / <s:property value="pageBean.totalPage"/> 页
			<s:if test="pageBean.page != 1">
				<a href="${pageContext.request.contextPath}/book_searchBook.action?search=<s:property value="search"/>&pageNum=1" class="firstPage">&nbsp;</a>		
				<a href="${pageContext.request.contextPath}/book_searchBook.action?search=<s:property value="search"/>&pageNum=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>	
			</s:if>	
			<s:iterator var="i" begin="1" end="pageBean.totalPage" step="1">
				<s:if test="pageBean.page==#i">
					<span class="currentPage"><s:property value="#i"/></span>
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath}/book_searchBook.action?search=<s:property value="search"/>&pageNum=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBean.page != pageBean.totalPage">
				<a class="nextPage" href="${pageContext.request.contextPath}/book_searchBook.action?search=<s:property value="search"/>&pageNum=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath}/book_searchBook.action?search=<s:property value="search"/>&pageNum=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>	
		</div>
	</div>
</div>
<%@ include file="./foot.jsp"%>
</body>
</html>