package zq.shop.cart;

import zq.shop.book.Book;

/**
 * 实体类：购物项：封装书籍信息，指定购物车，与书籍1对多；与购物车多对1
 * 注：一个购物项代表一种书籍商品
 * @author ZhouQi
 *
 */
public class CartItem {
	private Integer cartItemId;
	private Book book;			//书籍对象
	private Integer count;		//该本书籍的数量
	private Double subtotal;	//小计
	private Cart cart;			//
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		this.subtotal = this.count * this.book.getShop_price();
		return subtotal;
	}
	//小计的值由自己算出，setter方法不用提供，getter方法中计算好后返回出去计科
	/*public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}*/
	public Integer getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
}
