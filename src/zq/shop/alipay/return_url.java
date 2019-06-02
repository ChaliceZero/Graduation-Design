package zq.shop.alipay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zq.shop.order.Order;
import zq.shop.order.OrderService;
import zq.shop.user.User;
import zq.shop.utils.MessyCode;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.*;
import com.alipay.api.internal.util.*;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 功能：支付宝服务器同步通知页面
 * 日期：2019-04-20
 * 说明：
 *************************页面功能说明*************************
 * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
 */
public class return_url extends ActionSupport {
	private OrderService orderService;
	private Order order;
	private Integer uid = null;//暂未使用
	
	public String execute() {
		//获取支付宝GET过来反馈信息
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = MessyCode.getUtf8(valueStr);
			
			params.put(name, valueStr);
		}
		//调用SDK验证签名
		boolean signVerified = false;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
		} catch (AlipayApiException e) {
			this.addActionMessage("验签出现未知错误，请联系管理员");
		}
	
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//支付宝商户订单号
			String out_trade_no = MessyCode.getUtf8(request.getParameter("out_trade_no"));
		
			//更新订单状态
			String orderId = out_trade_no.substring(0, out_trade_no.lastIndexOf("_"));
			this.order = orderService.findByOid(Integer.valueOf(orderId));
			this.order.setState(1);
			orderService.updateOrder(this.order);
			
			//查找UID
			/*User user = (User) ServletActionContext.getRequest().getAttribute("existUser");
			this.uid = user.getUid();*/
			
			//支付宝交易号
			String trade_no = MessyCode.getUtf8(request.getParameter(request.getParameter("trade_no")));
		
			//付款金额
			String total_amount = MessyCode.getUtf8(request.getParameter("total_amount"));
			
			//交易号为空
			//this.addActionMessage("订单付款成功，订单号："+ out_trade_no +"，付款金额："+ total_amount + "，支付宝交易号："+ trade_no);
			this.addActionMessage("订单付款成功，订单号："+ out_trade_no +"，付款金额："+ total_amount);
		}else {
			this.addActionMessage("验签失败，请联系管理员");
			return "msg2";
		}
		return "msg2";
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}

