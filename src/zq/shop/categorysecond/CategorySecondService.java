package zq.shop.categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zq.shop.utils.PageBean;

/**
 * 业务层：二级分类
 * @author ZhouQi
 *
 */
@Transactional
public class CategorySecondService {
	CategorySecondDao categorySecondDao;
	//注入方法
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 分页查询二级分类
	 * @return
	 */
	public PageBean<CategorySecond> findByPage(Integer pageNum) {
		int limit = 10;
		int totalCount = categorySecondDao.findCount();
		int index = (pageNum - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findListByPage(index, limit);
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>(pageNum, limit, totalCount, list);
		
		return pageBean;
	}

	/**
	 * 业务层：保存二级分类信息
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 业务层：删除二级分类信息
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * 业务层：根据csid查找二级分类对象
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 业务层：更新二级分类对象
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	/**
	 * 业务层：查询所有二级分类
	 * @return
	 */
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	/**
	 * 后台：根据二级分类名模糊查询
	 * @param trim
	 * @return
	 */
	public List<CategorySecond> search(String keywords) {
		return categorySecondDao.search(keywords);
	}
	
}
