package zq.shop.admin;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdminDao extends HibernateDaoSupport {

	/**
	 * 持久层：管理员登录
	 * @param admin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Admin login(Admin admin) {
		String hql = "from Admin where adminName = ? and adminPwd = ?";
		List<Admin> list = this.getHibernateTemplate().find(hql, admin.getAdminName(), admin.getAdminPwd());
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	/**
	 * 持久层：查询所有管理员方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Admin> findAll() {
		String hql = "from Admin";
		List<Admin> list = this.getHibernateTemplate().find(hql);
		if (list.size() != 0)
			return list;
		return null;
	}

	/**
	 * 持久层：添加管理员信息
	 */
	public void save(Admin admin) {
		this.getHibernateTemplate().save(admin);
	}

	/**
	 * 持久层：查找某个管理员信息
	 * @param aid
	 * @return
	 */
	public Admin find(Integer aid) {
		return this.getHibernateTemplate().get(Admin.class, aid);
	}

	/**
	 * 持久层：删除普通管理员
	 * @param admin
	 */
	public void delete(Admin admin) {
		this.getHibernateTemplate().delete(admin);
	}
	
}
