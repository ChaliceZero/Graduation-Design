package zq.shop.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 业务层：一级分类
 * @author ZhouQi
 *
 */
@Transactional
public class CategoryService {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 业务层：查询首页所有的一级分类的方法
	 */
	public List<Category> findAll() {
		return categoryDao.findAllCategory();
	}

	/**
	 * 业务层：保存后台管理员添加的一级分类
	 * @param category
	 */
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 业务层：删除后台管理员删除的一级分类
	 * @param category
	 */
	public void deleteCategory(Category category) {
		categoryDao.delete(category);
	}
	
	/**
	 * 业务层：编辑一级分类之前先根据cid获取一级分类
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
}
