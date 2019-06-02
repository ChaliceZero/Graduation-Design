<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>我的订单 - 教育书城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script> -->
<script type="text/javascript">
	/* $(function(){ 
		int limit = 1;
		pushHistory();
		window.addEventListener("popstate", function(e) {
			//alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能 
			window.location = "${pageContext.request.contextPath}/cart_myCart.action";
		}, false); 
		function pushHistory() {
			
			var state = { 
				title: "title", 
				url: "#"
			}; 
			window.history.pushState(state, "title", "#"); 
		} 
	}); */
	
/* $(document).ready(function(e) {   
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
	function zhifubao() {
		var oid = $("#oid").val();
		var name = $("#name").val();
		var addr = $("#addr").val();
		var phone = $("#phone").val();
		window.location.href = "${pageContext.request.contextPath}/order_getParams2Zfb.action?oid="+ oid +"&name="+ name +"&addr="+ addr +"&phone="+ phone;
	}
</script>
</head>
<body>
<%@ include file="./head.jsp"%>
<!-- <a href="javascript:" onclick="self.location=document.referrer;">返回</a> -->
<div class="container cart">
	<div class="span24">
		<div class="step1">
			<span style="font-size:16px; margin:10px auto 6px 8px; display:block;">生成订单成功，订单号：<s:property value="order.oid"/></span>
		</div>
		<table>
			<tbody>
				<tr>
					<th>图片</th>
					<th>书籍</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
				</tr>		
				<s:iterator var="orderItem" value="order.orderItems">
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
			</tbody>
		</table>
			<dl id="giftItems" class="hidden" style="display:none;"></dl>
			<div class="total">
				<a href="${pageContext.request.contextPath}/order_removeOrder.action?oid=<s:property value="order.oid"/>" class="delete">取消订单</a>
				<em id="promotion"></em>
				总金额: <strong id="effectivePrice">￥&nbsp;<s:property value="order.total"/>&nbsp;元</strong>
			</div>
		<form id="orderForm" target="_blank" action="${pageContext.request.contextPath}/order_payOrder.action" method="post">
			<input type="hidden" id="oid" name="order.oid" value="<s:property value='order.oid'/>"/>
			<div class="span24">
				<p>
					收货地址：<input name="order.addr" id="addr" type="text" value="<s:property value='order.user.address'/>" style="width:350px" />
						<br />
					收货人&nbsp;&nbsp;&nbsp;：<input name="order.name" id="name" type="text" value="<s:property value='order.user.name'/>" style="width:150px" />
						<br /> 
					联系方式：<input name="order.phone" type="text" id="phone" value="<s:property value='order.user.phone'/>" style="width:150px" />
				</p>
				<hr/>
				<span><a style="font-size:18px;color:#FF6600;"> 银联卡支付 </a></span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a onclick="zhifubao()" title="Alipay"style="font-size:18px;"> 支付宝支付 </a></span>
				<hr/>
				<p>
					
					<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
					<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
					<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
					<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle"/>
					<br/>
					<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
					<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
					<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
					<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle"/>
					<br/>
					<input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
					<img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
					<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle"/>
				</p>
				<hr />
				<p style="text-align:right">
					<a href="javascript:document.getElementById('orderForm').submit();">
						<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" />
					</a>
				</p>
			</div>
		</form>
	</div>
</div>
<%@ include file="./foot.jsp" %>
</body>
</html>