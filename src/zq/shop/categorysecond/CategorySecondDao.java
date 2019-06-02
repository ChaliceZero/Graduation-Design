package zq.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zq.shop.book.Book;
import zq.shop.utils.PageHibernateCallback;

/**
 * dao层：二级分类
 * @author ZhouQi
 *
 */
public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * 查询二级分类的记录条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}

	/**
	 * 分页查询二级分类
	 */
	@SuppressWarnings("unchecked")
	public List<CategorySecond> findListByPage(Integer index, Integer limit) {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<CategorySecond>(
				hql, null, index, limit));
		if (list.size() > 0)
			return list;
		return null;
	}

	/**
	 * dao层：保存二级分类信息
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * dao层：删除二级分类信息
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * dao层：根据csid查找二级分类对象
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * dao层：更新二级分类信息
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * dao：查询所有二级分类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 持久层：根据二级分类名模糊查询
	 * @param trim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CategorySecond> search(String keywords) {
		String hql = "from CategorySecond cs where cs.csname like '%"+ keywords +"%'";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list;
		return null;
	}
	
}
