package zq.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * dao层：一级分类
 * @author ZhouQi
 *
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * Dao：查询所有一级分类的方法
	 */
	public List<Category> findAllCategory() {
		//返回一个list集合
		return this.getHibernateTemplate().find("from Category");
	}
	
	/**
	 * 持久层：添加或更新一级分类
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().saveOrUpdate(category);
	}

	/**
	 * 持久层：删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		//级联删除
		category = this.getHibernateTemplate().get(Category.class, category.getCid());
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * 持久层：在编辑一级分类之前先根据cid获取一级分类
	 * @param category
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

}
