package zq.shop.cart;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import zq.shop.user.User;

/**
 * 
 * 实体类：购物车：包含购物项，通过购物项联系书籍
 * @author ZhouQi
 *
 */
public class Cart {
	//购物项集合，使用书籍表的ID作为主键
	private Integer cartId;			//unused
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	private Double total = 0D;		//购物车总计
	private Date createtime;		//unused
	private User user;				//unused
	
	/**
	 * 提供一个获取购物项的值的集合的方法，方便前台进行遍历
	 * 若成员变量和方法getter后缀名称不同，则该方法可以当作有一个属性，该属性名称为后缀
	 */
	public Collection<CartItem> getCartItems() {
		return this.map.values();
	}
	//提供前台获取total的方法
	public Double getTotal() {
		return total;
	}

	//提供一些购物车的功能方法：
	/**
	 * 将购物项添加到购物车
	 * @param cartItem
	 */
	public void addCartItem(CartItem cartItem) {
		//获取购物项的ID
		Integer bid = cartItem.getBook().getBid();
		//判断购物车中是否已经有了该类书籍商品
		if(this.map.containsKey(bid)) {
			//购物车中有该类书籍,先获得该书籍所在购物项
			CartItem _cartItem = this.map.get(bid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());//数量相加
		}else{
			//购物车中没有该类书籍
			this.map.put(bid, cartItem);
		}
		//设置加入了新购物项后的总计
		this.total += cartItem.getSubtotal();
	}
	/**
	 * 将购车项从购物车中移除
	 * @param bid
	 */
	public void removeCartItem(Integer bid) {
		//将购物项从map中移除：获取移除的那个对象
		CartItem cartItem = this.map.remove(bid);//会将移除的那个对象作为返回
		//重新计算购物车总计
		this.total -= cartItem.getSubtotal();
	}
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		//清空Map
		this.map.clear();
		//总计设为0
		this.total = 0D;
	}
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
