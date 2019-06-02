package zq.shop.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zq.shop.book.Book;
import zq.shop.book.BookService;
import zq.shop.user.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * web层：购物车模块
 * @author ZhouQi
 *
 */
public class CartAction extends ActionSupport {
	
//	private Cart cart = new Cart();
	private Integer bid;		//接收前台购车页面的购物项的map的ID
	private Integer count;		//接收前台购物车书籍数量
	private BookService bookService;	//获取书籍业务层对象，使用setter方法注入
//	private CartService cartService;	//
	
//	public Cart getModel() {
//		return this.cart;
//	}
	
	//获取bid
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	//获取count
	public void setCount(Integer count) {
		this.count = count;
	}
	//注入service
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	//
//	public void setCartService(CartService cartService) {
//		this.cartService = cartService;
//	}
	
	/**
	 * 提供从session中获取Cart的方法
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public Cart getCart(HttpServletRequest request) {
		// 从session的范围获得Cart对象.
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断:
		if (cart == null) {
			// 创建购物车对象
			cart = new Cart();
			// 将购物车对象放入到session范围:
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 判断用户是否登录了
	 */
	public boolean is_login() {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser != null) {
			return true;	//已经登录
		}else {
			return false;	//未登录
		}
	}
	
	/**
	 * 添加到购物车的方法
	 */
	public String addCart() {
		//查询判断用户是否已经登录
		if (!is_login()) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "msg";
		}		
		//查询书籍信息
		Book book = bookService.findBookByBid(this.bid);
		//创建一个购物项，并初始化对象参数
		CartItem cartItem = new CartItem();
		cartItem.setCount(this.count);
		cartItem.setBook(book);
		// 获取购物车 需要依赖request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//补全cart
		Cart cart = this.getCart(request);
		cart.addCartItem(cartItem);
		
//		cartService.saveOrUpdateCart(this.cart);
		this.addActionMessage("添加到购物车成功");
		
		return "addCartSuccess";
	}
	
	/**
	 * 清空购物车模块
	 */
	public String clearCart() {
		//查询判断用户是否已经登录，防止出现，用户停留在我的购物车页面太久，session失效的情况
		if (!this.is_login()) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "msg";
		}
		// 获取Cart对象.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		//清空购物车
		cart.clearCart();
		
		return "clearCartSuccess";
	}
	
	/**
	 * 移除购物项
	 */
	public String removeCart() {
		//查询判断用户是否已经登录，防止session失效的情况
		if (!this.is_login()) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "msg";
		}
		//获取Cart对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		//移除购物项
		cart.removeCartItem(this.bid);
		
		return "removeCartSuccess";
	}
	
	/**
	 * 我的购物车
	 */
	public String myCart() {
		//查询判断用户是否已经登录
		if (!this.is_login()) {
			this.addActionMessage("您还没有登录，请先去登录吧！");
			return "msg";
		}
		
		return "myCartSuccess";
	}
	
}
