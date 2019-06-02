package zq.shop.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zq.shop.utils.PageBean;

/**
 * 业务逻辑层：订单模块
 * @author ZhouQi
 *
 */
@Transactional
public class OrderService {

	private OrderDao orderDao;
	
	//注入orderDao
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 业务层：保存订单到数据库
	 */
	public Integer save(Order order) {
		Integer oid = orderDao.save(order);
		return oid;
	}
	/**
	 * 业务层：根据oid查询订单对象
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * 修改数据库的订单数据
	 * @param currOrder
	 */
	public void updateOrder(Order currOrder) {
		orderDao.updateOrder(currOrder);
	}
	
	/**
	 * 业务层：根据用户ID查询订单
	 * @param uid
	 * @return
	 */
	public List<Order> findOrdersByUid(Integer uid) {
		return orderDao.findOrdersByUid(uid);
	}

	/**
	 * 业务层：分页查询所有订单
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Order> findByPage(Integer pageNum) {
		int limit = 10;
		int index = (pageNum - 1) * limit;
		Integer totalCount = orderDao.findCount();
		List<Order> list = orderDao.findByPage(index, limit);
		PageBean<Order> pageBean = new PageBean<Order>(pageNum, limit, totalCount, list);
		
		return pageBean;
	}

	/**
	 * 业务层：根据订单状态分页查询订单
	 * @param pageNum
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Order> findByPage(Integer pageNum, Integer state) {
		int limit = 10;
		int index = (pageNum - 1) * limit;
		Integer totalCount = orderDao.findCount(state);
		List<Order> list = orderDao.findByPage(index, limit, state);
		PageBean<Order> pageBean = new PageBean<Order>(pageNum, limit, totalCount, list);
		
		return pageBean;
	}

	/**
	 * 分页查询订单集合
	 * @param uid
	 * @return
	 */
	public PageBean<Order> findOrdersByPage(Integer uid, Integer pageNum) {
		int limit = 6;
		int index = (pageNum - 1) * limit;
		Integer totalCount = orderDao.findCountByUid(uid);
		List<Order> list = orderDao.findPageByUid(uid, index, limit);
		PageBean<Order> pageBean = new PageBean<Order>(pageNum, limit, totalCount, list);
		return pageBean;
	}

	/**
	 * 业务层：删除订单
	 * @param order
	 */
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
	}

	/**
	 *后台：根据订单号查询记录
	 */
	public List<Order> search(String trim) {
		return orderDao.search(trim);
	}

}
