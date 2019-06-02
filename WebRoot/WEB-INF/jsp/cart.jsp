<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-store"/>
<meta http-equiv="expires" content="0"/>
<title>购物车 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
<!-- <script src="https://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
/* window.history.forward(1); */
   /*  $(document).ready(function(e) {   
            var counter = 0;  
            if (window.history && window.history.pushState) {  
                             $(window).on('popstate', function () {  
                                            window.history.pushState('forward', null, '#');  
                                            window.history.forward(1);  
                                          //alert("不可回退");  
                                             location.replace(document.referrer);//刷新
                                });  
              }  

              window.history.pushState('forward', null, '#'); //在IE中必须得有这两行  
              window.history.forward(1);  
});  */
</script>
</head>
<body>
<%@include file="./head.jsp"%>
<div class="container cart">
	<div class="span24">
		<s:if test="#session.cart == null || #session.cart.cartItems.size() == 0">
			<br/><br/><br/><br/>
			<div class="step1" style="margin-left:360px;"><a href="javascript:;"><span style="font-size:18px;color:blue;">购物车空空如也哦，请先去购物吧！</span></a></div>
			<br/><br/><br/><br/>
		</s:if>
		<s:else>
			<!-- <div class="step1">购物车列表：</div> -->
			<table>
				<tbody>
					<tr>
						<th align="center">图片</th>
						<th align="center">商品</th>
						<th align="center">价格</th>
						<th align="center">数量</th>
						<th align="center">小计</th>
						<th align="center">操作</th>
					</tr>
					<s:iterator value="#session.cart.cartItems">
						<tr>
							<td width="60" align="center">
								<img src="${pageContext.request.contextPath}/<s:property value="book.image"/>"/>
							</td>
							<td align="center">
								<a target="_blank"><s:property value="book.bname"/></a>
							</td>
							<td align="center">
								￥&nbsp;<s:property value="book.shop_price"/>
							</td>
							<td class="quantity" width="60" align="center">
								<input type="text" name="quantity" value="<s:property value='count'/>" onchange="updateCart(this)" maxlength="4" onpaste="return false;" readonly/>
								<!-- <div>
									<span class="increase">&nbsp;</span>
									<span class="decrease">&nbsp;</span>
								</div> -->
							</td>
							<td width="140" align="center">
								<span class="subtotal">￥&nbsp;<s:property value="subtotal"/>&nbsp;元</span>
							</td>
							<td align="center">
								<a href="${pageContext.request.contextPath}/cart_removeCart.action?bid=<s:property value='book.bid'/>" class="delete">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em>
						<em>
							登录后确认是否享有优惠
						</em>
				赠送积分: <em id="effectivePoint"><s:property value="#session.cart.total"/></em>
				商品金额: <strong id="effectivePrice">￥&nbsp;<s:property value="#session.cart.total"/>&nbsp;元</strong>
			</div>
			<div class="bottom">
				<a href="${pageContext.request.contextPath}/cart_clearCart.action?bid=<s:property value='#session.cart.cartItems.book.bid'/>" id="clear" class="clear">清空购物车</a>
				<a href="${pageContext.request.contextPath}/order_saveOrder.action" id="submit" class="submit">提交订单</a>
			</div>
		</s:else>
	</div>
</div>
<%@include file="./foot.jsp"%>
<script>
/*
     	//这里的test可以是任意的值,反正我们是获取不到这个值的。打印一下，发现获取不到的cookie为null
      $first=$.cookie('test');   、
      if(!$first||$first=='null'||$first==null){
		//第一次进页面，肯定是没有cookie的，所以这里设置cookie的值为1，时间为7天
        $.cookie('test','1',{ expires: 7 });
      }else{
		//然后等下次回到页面，肯定会取到cookie的值，然后我们就反其道而行，给cookie赋值为null，同时刷新页面
        $.cookie('test',null);
        location.reload(true);
      }\
      */
</script>
</body>
</html>