package zq.shop.order;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import zq.shop.cart.Cart;
import zq.shop.cart.CartItem;
import zq.shop.user.User;
import zq.shop.utils.PageBean;
import zq.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * web层：订单模块
 * @author ZhouQi
 *
 */
public class OrderAction extends ActionSupport {
	private Order order;
	private String pd_FrpId;		//银行代码
	private OrderService orderService;
	private String r6_Order;	//接收回调后订单号
	private String r3_Amt;		//接收回调后订单金额
	private Integer oid;		//接收来自orderList页面的未付款书籍的ID
	//支付宝支付所需参数
	private String name;
	private String addr;
	private String phone;
	private String WIDout_trade_no;		//支付宝商家订单号
	private String WIDsubject;			//暂未使用
	private String WIDtotal_amount;		//暂未使用
	private String WIDbody;				//暂未使用
	//后台所需参数值
	private PageBean<Order> pageBean; //分页对象
	private Integer pageNum;	//接收分页的一页数
	private Integer state;		//接收订单的状态值
	//后台搜索关键字
	private String keywords;
	
	//提供getter方法供前台获取显示订单对象
	public Order getOrder() {
		return this.order;
	}
	//提供getter和setter方法使得前台能通过ognl的方式注入前台数据
	public void setOrder(Order order) {
		this.order = order;
	}
	//注入oid
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	//支付宝所需注入
	public void setName(String name) {
		this.name = name;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWIDout_trade_no() {
		return WIDout_trade_no;
	}
	//注入银行代码参数
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	//注入orderService
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//获取付款回调后需要的参数
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	//注入后台查询所需属性
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public PageBean<Order> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<Order> pageBean) {
		this.pageBean = pageBean;
	}	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 保存订单
	 * @throws ParseException 
	 */
	public String saveOrder() throws ParseException {
		//从session中获取有关对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = (Cart) request.getSession().getAttribute("cart");	//获取session中的cart
		User user = (User) request.getSession().getAttribute("existUser");	//获取session中的user
		
		//防止购物车为空的情况下直接被访问该方法，执行保存订单操作
		if(cart == null) {
			this.addActionMessage("您还没有购物，请先去购物吧！");
			return "saveOrderFail";
		}
		//防止没有登录的情况下直接被访问
		if(user == null) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "saveOrderFail";
		}
		
		this.order = new Order();
		/********************	封装订单的数据	*****************/
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sf.format(new Date());	//得到日期格式的字符串
		this.order.setOrdertime(sf.parse(format));	//订单时间
		this.order.setState(0);					//订单状态		0：未付款，1：已付款，2：已发货，3：已收货；
		this.order.setTotal(cart.getTotal());	//订单总价
		this.order.setUser(user);				//订单所属用户
/*	由下订单的用户指定
		this.order.setAddr(user.getAddress());	//订单发货地址
		this.order.setPhone(user.getPhone());	//订单收货用户的电话
		this.order.setName(user.getName());		//订单收货用户名称
*/		
		/*********	封装订单项的数据，订单项的数据是从购物项中获得的	******/
		for(CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setBook(cartItem.getBook());	//订单项的某类书籍商品对象
			orderItem.setCount(cartItem.getCount());	//订单项该类书籍的数量
			orderItem.setSubtotal(cartItem.getSubtotal());	//订单项该类书籍总价小计
			orderItem.setOrder(this.order);			//订单项所属的订单对象
			//将订单项装进订单中
			this.order.getOrderItems().add(orderItem);	//订单包含的各订单项
		}
		//保存订单，使用级联：在保存订单的时候也保存订单项到数据库
		Integer oid = this.orderService.save(order);
		//设置order对象的主键
		this.order.setOid(oid);
		//清空购物车
		cart.clearCart();
		//获取完整的order对象，便于前台获取订单主键（订单号），也可以直接将返回的oid通过setter方法写入order对象中，省的进行复杂的关联查询
//		this.order = orderService.findByOid(oid);
		
		return "saveOrderSuccess";
	}
	
	/**
	 * 支付方法
	 */
	public String payOrder() {
		/************** 修改订单部分信息（发货信息、订单状态等），先获取订单对象	********************/
		Order currOrder = orderService.findByOid(this.order.getOid());//隐藏字段input
		currOrder.setAddr(this.order.getAddr());
		currOrder.setName(this.order.getName());
		currOrder.setPhone(this.order.getPhone());
		
		orderService.updateOrder(currOrder);
		/*************************	付款	************************************/
		//获取项目名
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		String path = realPath.substring(realPath.lastIndexOf("\\", realPath.length()-2)+1,realPath.lastIndexOf("\\"));
		System.out.println("***************项目绝对路径："+ realPath);
		System.out.println("***************项目名："+ path);
		//定义付款参数
		String p0_Cmd = "Buy";					//业务类型，固定值
		String p1_MerId = "10001126856";		//商户编号
		String p2_Order = this.order.getOid().toString() +"_"+ System.currentTimeMillis();	//商户订单号
		String p3_Amt = "0.01";	//支付金额
		String p4_Cur = "CNY";					//交易币种
		String p5_Pid = "Edubook";				//商品名称
		String p6_Pcat = "book";				//商品种类
		String p7_Pdesc = "Edubook";			//商品描述
		String p8_Url = "http://localhost:8080/"+ path +"/order_callBack.action";		//商户接收支付成功数据的地址
		String p9_SAF = "";						//送货地址
		String pa_MP = "";						//商户扩展信息
		String pd_FrpId = this.pd_FrpId;		//支付通道编码，最好通过前台接收，不自定义
		String pr_NeedResponse = "1";			//应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";	//密钥
		//签名数据,使用密钥经过加密算法生成
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			this.addActionMessage("对不起，支付失败，请重新下单支付！");
			return "payOrderFail";
		}
		
		return NONE;
	}
	/**
	 * 付款成功后的回调函数，会携带来一些参数
	 */
	public String callBack() {
		String orderId = this.r6_Order.substring(0, this.r6_Order.lastIndexOf("_")-1);
		System.out.println("***************************************orderId:"+orderId);
		Order currOrder = orderService.findByOid(Integer.parseInt(orderId));
		currOrder.setState(1);
		orderService.updateOrder(currOrder);
		
		this.addActionMessage("订单付款成功，订单号："+ this.r6_Order +"，付款金额："+ this.r3_Amt);
		
		return "payOrderSuccess";
	}
	
	/**
	 * 支付宝支付后成功后的回调，暂时无用
	 * 说明：该方法由AlipayConfig进行配置，官方自动回调进returnUrl
	 */
	public String zhifubao() {
		return "payOrderSuccess";
	}
	/**
	 * 传入参数到index.jsp中
	 * @return
	 */
	public String getParams2Zfb() {
		this.order = orderService.findByOid(this.oid);
		this.order.setAddr(this.addr);
		this.order.setName(this.name);
		this.order.setPhone(this.phone);
		
		orderService.updateOrder(this.order);
		this.WIDout_trade_no = this.order.getOid().toString() +"_"+ System.currentTimeMillis();
		return "getParams2ZfbSuccess";
	}
	
	/**
	 * 前台：根据用户ID分页查询订单
	 */
	public String findOrdersByUid() {	
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "msg";
		}
		this.pageBean = orderService.findOrdersByPage(user.getUid(), this.pageNum);
		//压栈存入
//		ActionContext.getContext().getValueStack().set("orders", orders);
		
		return "findOrdersByUidSuccess";
	}
	/**
	 * 前台：根据oid查询订单
	 */
	public String findByOid() {
		this.order = orderService.findByOid(this.oid);
		return "findByOidSuccess";
	}
	
	/**
	 * 后台：查询所有订单
	 */
	public String adminFindAll() {
		this.pageBean = orderService.findByPage(this.pageNum);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台：根据状态、页数查询订单
	 */
	public String adminFindByState() {
		this.pageBean = orderService.findByPage(this.pageNum, this.state);
		return "adminFindByStateSuccess";
	}
	
	/**
	 * 后台：修改订单状态
	 */
	public String adminUpdateState() {
		this.order = orderService.findByOid(this.oid);
		this.order.setState(2);		//已发货
		orderService.updateOrder(this.order);
		
		return "adminUpdateStateSuccess";
	}
	
	/**
	 * 前台：修改订单状态
	 */
	public String userUpdateState() {
		this.order = orderService.findByOid(this.oid);
		this.order.setState(3);		//确认收货/订单完成
		orderService.updateOrder(this.order);
		
		return "userUpdateStateSuccess";
	}
	
	/**
	 * 前台：移除订单项
	 */
	public String removeOrder() {
		this.order = orderService.findByOid(this.oid);
		orderService.deleteOrder(this.order);
		
		return "removeOrderSuccess";
	}
	
	/**
	 * 后台：根据订单号查询
	 */
	public String searchKeys() {
		if (this.keywords == null || this.keywords.trim().equals(""))
			return "searchFail";
		List<Order> oList = orderService.search(this.keywords.trim());
		ActionContext.getContext().getValueStack().set("oList", oList);
		
		return "searchSuccess";
	}
}
