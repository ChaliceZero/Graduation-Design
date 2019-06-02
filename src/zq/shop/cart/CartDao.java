package zq.shop.cart;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zq.shop.user.User;

/**
 * 购物车模块，dao层
 * @author ZhouQi
 *
 */
public class CartDao extends HibernateDaoSupport {

	/**
	 * 持久层：保存购物车到数据库
	 * @param cart
	 */
	public void saveCart(Cart cart) {
		this.getHibernateTemplate().save(cart);
	}

	/**
	 * 持久层：获取购物车
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Cart getCart(User existUser) {
		List<Cart> cList = this.getHibernateTemplate().find("from Cart c where c.user.uid = ?", existUser.getUid());
		if (cList.size() > 0)
			return cList.get(0);
		return null;
	}

	/**
	 * 更新购物车数据库
	 * @param cart
	 */
	public void updateCart(Cart cart) {
		this.getHibernateTemplate().update(cart);
	}

	/**
	 * 保存或更新购物车
	 * @param cart
	 */
	public void saveOrUpdate(Cart cart) {
		this.getHibernateTemplate().saveOrUpdate(cart);
	}

	
}
