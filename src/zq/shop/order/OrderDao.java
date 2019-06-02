package zq.shop.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zq.shop.utils.PageHibernateCallback;

/**
 * dao层：订单模块
 * @author ZhouQi
 *
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * 持久层：保存订单到数据库
	 * @param order
	 */
	public Integer save(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		return oid;
	}
	/**
	 * 持久层：根据oid查询订对象
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		//查询order时也查询到了orderItems
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	/**
	 * 持久层：根据订单对象修改订单数据的信息
	 * @param currOrder
	 */
	public void updateOrder(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	/**
	 * 持久层：根据用户ID查询订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> findOrdersByUid(Integer uid) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().find(hql, uid);
		return list;
	}
	
	/**
	 * 持久层：查询所有订单记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}
	
	/**
	 * 持久层：根据状态查询对象订单数
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount(Integer state) {
		String hql = "select count(*) from Order where state = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, state);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}
	
	/**
	 * dao层：分页查询所有订单信息
	 * @param index
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> findByPage(int index, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(
				hql, null, index, limit));
		if (list.size() > 0)
			return list;
		return null;
	}
	
	/**
	 * dao层:根据订单状态查询相关订单
	 * @param index
	 * @param limit
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> findByPage(int index, int limit, Integer state) {
		String hql = "from Order where state = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(
				hql, new Object[]{state}, index, limit));
		if (list.size() > 0)
			return list;
		return null;
	}
	
	/**
	 * dao层：根据用户ID查订单数量
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCountByUid(Integer uid) {
		String hql = "select count(*) from Order where user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}
	
	/**
	 * dao层：根据用户ID分页查询订单数据
	 * @param uid
	 * @param index
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> findPageByUid(Integer uid, int index, int limit) {
		String hql = "from Order where user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback(
				hql, new Object[]{uid}, index, limit));
		if (list.size() > 0)
			return list;
		return null;
	}
	
	/**
	 * dao层：删除订单
	 * @param order
	 */
	public void deleteOrder(Order order) {
		this.getHibernateTemplate().delete(order);
	}
	
	/**
	 * 持久层：根据关键字查询订单记录
	 * @param trim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> search(String trim) {
//		int oid = Integer.parseInt(trim);	//可行
		String hql = "from Order o where o.oid like '%"+ trim +"%'";	//可行
//		String hql = "from Order o where o.oid ='"+ oid +"'";
		List<Order> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list;
		return null;
	}
	
}
