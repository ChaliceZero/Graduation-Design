<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>首页 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
window.onload = function() {
	var oDiv = document.getElementById("box1");
	oDiv.style.top = document.documentElement.clientHeight - oDiv.offsetHeight + "px";
}
window.onscroll = function() {
	var oDiv1 = document.getElementById("box1");
	var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;	//分别兼容chrome、IE;
	//oDiv1.style.top = document.documentElement.clientHeight - oDiv1.offsetHeight + scrollTop + "px";		移动时会抖动
	move1(document.documentElement.clientHeight - oDiv1.offsetHeight + scrollTop);
};
var timer1 = null;
function move1(iTarget) {
	var oDiv1 = document.getElementById("box1");
	clearInterval(timer1);
	timer1 = setInterval(function() {
		var speed = (iTarget - oDiv1.offsetTop)/4;
		speed = speed>0?Math.ceil(speed):Math.floor(speed);
		if(oDiv1.offsetTop==iTarget) {
			clearInterval(timer1);
		}
		else
			oDiv1.style.top = oDiv1.offsetTop + speed + "px";
	},30);
}
</script>
</head>
<body id="div2">
<%@ include file="./head.jsp"%>
<div class="container index">
	<div class="span24">
		<div id="hotProduct" class="hotProduct clearfix">
			<div class="title">
				<strong>热门图书</strong>
			</div>
			<ul class="tab current"></ul>
			<ul class="tabContent" style="display: block;">
				<s:iterator value="hotList">
					<li>
						<a href="${pageContext.request.contextPath}/book_findByBid.action?bid=<s:property value='bid'/>">
							<img src="${pageContext.request.contextPath}/<s:property value='image'/>" title="<s:property value="bname"/>" alt="<s:property value="bname"/>" style="display:block;"/>
						</a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="span24">
		<div id="newProduct" class="newProduct clearfix">
			<div class="title">
				<strong>最新图书</strong>
			</div>
			<ul class="tab current"></ul>			
			<ul class="tabContent" style="display:block;">
				<s:iterator value="newList">
					<li>
						<a href="${pageContext.request.contextPath}/book_findByBid.action?bid=<s:property value='bid'/>">
							<img src="${pageContext.request.contextPath}/<s:property value="image"/>" title="<s:property value="bname"/>" alt="<s:property value="bname"/>" style="display:block;"/>
						</a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="span24">
		<div class="friendLink">
			<dl>
				<dt>新手指南</dt>
				<dd>
					<a  target="_blank">支付方式</a>
					|
				</dd>
				<dd>
					<a  target="_blank">配送方式</a>
					|
				</dd>
				<dd>
					<a  target="_blank">售后服务</a>
					|
				</dd>
				<dd>
					<a  target="_blank">购物帮助</a>
					|
				</dd>
				<dd>
					<a  target="_blank">图书卡</a>
					|
				</dd>
				<dd>
					<a  target="_blank">礼品卡</a>
					|
				</dd>
				<dd>
					<a target="_blank">银联卡</a>
					|
				</dd>
				<dd>
					<a  target="_blank">教育卡</a>
					|
				</dd>
				<dd>
					&nbsp;&nbsp;&nbsp;邮箱:
					<strong>2026992950@qq.com</strong>
				</dd>	
				<dd class="more">
					<a >更多</a>
				</dd>
			</dl>
		</div>
	</div>
</div>
<%@ include file="./foot.jsp" %>
<div id="box1" class="phone">
	<font color="#CF1B34">
		客服热线：
		<strong>12138--88888888</strong>
	</font>
</div>
</body>
</html>