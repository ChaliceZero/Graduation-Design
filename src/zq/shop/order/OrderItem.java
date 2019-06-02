package zq.shop.order;

import zq.shop.book.Book;
import zq.shop.cart.Cart;

/**
 * 实体类：订单项表，作为图书表和订单表的中间表
 * @author ZhouQi
 *
 */
public class OrderItem {
	private Integer itemid;		//订单项ID
	private Integer count;		//订单项书籍数量
	private Double subtotal;	//小计
	private Book book;			//订单项包含的书籍对象
	private Order order;		//订单项属于哪个订单对象
	private Cart cart;			//unused
	
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
