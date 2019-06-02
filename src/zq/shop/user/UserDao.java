package zq.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zq.shop.utils.PageHibernateCallback;

/**
 * dao层：用户模块
 * @author ZhouQi
 *
 */
public class UserDao extends HibernateDaoSupport {

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 持久层：根据激活码查询用户
	 */
	@SuppressWarnings("unchecked")
	public User findByCode(String code) {
		List<User> list = this.getHibernateTemplate().find("from User where code=?", code);
		if(list.size() != 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 持久层：修改用户信息
	 */
	public void update(User user) {
		this.getHibernateTemplate().update(user);
		return;
	}

	/**
	 * 持久层：用户登录
	 */
	@SuppressWarnings("unchecked")
	public User login(User user) {
		List<User> list = this.getHibernateTemplate().find("from User where username = ? and password = ? and status = ?",user.getUsername(), user.getPassword(), 1);
//		List<User> list = this.getHibernateTemplate().find("from User where username = ? and password = ?",user.getUsername(), user.getPassword());
		if(list.size() != 0)
			return list.get(0);
		return null;
	}

	/**
	 * 持久层:根据用户名查找user对象
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		List<User> list = this.getHibernateTemplate().find("from User where username = ?", username);
		if(list.size() != 0)
			return list.get(0);
		return null;
	}

	/**
	 * dao层：查找用户自己的信息
	 * @param user
	 * @return
	 */
	public User findByUid(User user) {
		return this.getHibernateTemplate().get(User.class, user.getUid());
	}

	/**
	 * 
	 *  dao层：查询用户记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	/**
	 * dao层：分页查询用户集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findListByPage(Integer index, Integer limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<User>(
				hql, null, index, limit));
		if(list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 持久层：删除用户
	 * @param user
	 */
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	/**
	 * 根据用户ID查询用户对象
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	/**
	 * 后台：模糊查询用户数据
	 * @param index
	 * @param limit
	 * @param keywords
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findList4Back(String keywords) {
		String hql = null;
		if (keywords == null || keywords.equals(""))
			hql = "from User";
		else
			hql = "from User u where u.username like '%"+ keywords +"%'";
		List<User> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0)
			return list;
		return null;
	}

}
