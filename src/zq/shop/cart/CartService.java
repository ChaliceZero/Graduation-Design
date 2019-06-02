package zq.shop.cart;

import org.springframework.transaction.annotation.Transactional;

import zq.shop.user.User;

/**
 * 购物车模块
 * @author ZhouQi
 *
 */
@Transactional
public class CartService {

	private CartDao cartDao;

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	/**
	 * 将购物车存入数据库中
	 * @param cart
	 */
	public void saveCart(Cart cart) {
		cartDao.saveCart(cart);
	}

	/**
	 * 获取购物车
	 * @param existUser
	 * @return
	 */
	public Cart getCart(User existUser) {
		return cartDao.getCart(existUser);
	}

	/**
	 * 更新购物车
	 * @param cart
	 */
	public void updateCart(Cart cart) {
		cartDao.updateCart(cart);
	}

	/**
	 * 保存或更新购物车
	 * @param cart
	 */
	public void saveOrUpdateCart(Cart cart) {
		cartDao.saveOrUpdate(cart);
	}
	
}
