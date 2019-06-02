package zq.shop.categorysecond;

import java.util.List;

import zq.shop.category.Category;
import zq.shop.category.CategoryService;
import zq.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * web层：二级分类
 * @author ZhouQi
 *
 */
public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private Integer pageNum;
	private PageBean<CategorySecond> pageBean;
	private CategoryService categoryService;
	private Integer cid;
	//后台：查询关键字
	private String keywords;
	
	public CategorySecond getModel() {
		return this.categorySecond;
	}
	//注入service
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//获取前台页码
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	//获取pagebean
	public void setPageBean(PageBean<CategorySecond> pageBean) {
		this.pageBean = pageBean;
	}
	//将pagebean传入前台
	public PageBean<CategorySecond> getPageBean() {
		return pageBean;
	}
	//注入一级分类业务层
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//获取后台二级分类添加页面传入的cid
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * web层：管理员查询二级分类方法
	 */
	public String adminFindAll() {
		this.pageBean = categorySecondService.findByPage(this.pageNum);
		return "adminFindAllSuccess";
	}
	
	
	/**
	 * 跳转到二级分类添加页面
	 */
	public String adminAddPage() {
		//查询一级分类集合
		List<Category> cList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "adminAddPageSuccess";
	}
	
	/**
	 * 后台管理员保存二级分类
	 */
	public String adminSave() {
		Category category = new Category();
		category.setCid(this.cid);
		this.categorySecond.setCategory(category);
		categorySecondService.save(this.categorySecond);
		return "adminSaveSuccess";
	}
	
	/**
	 * 后台管理员删除二级分类
	 */
	public String adminDelete() {
		categorySecondService.delete(this.categorySecond);
		return "adminDeleteSuccess";
	}
	
	/**
	 * 后台管理员跳转到编辑页面
	 */
	public String adminEditPage() {
		//查询一级分类集合
		List<Category> cList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		this.categorySecond  = categorySecondService.findByCsid(this.categorySecond.getCsid());
		return "adminEditPageSuccess";
	}
	
	/**
	 * 后台管理员更新二级分类信息
	 */
	public String adminUpdate() {
		//通过cid关联一级分类
		Category category = new Category();
		category.setCid(this.cid);
		this.categorySecond.setCategory(category);
		this.categorySecondService.update(this.categorySecond);
		return "adminUpdateSuccess";
	}
	
	/**
	 * 后台：查询二级分类名
	 */
	public String searchKeys() {
		if (this.keywords == null || this.keywords.trim().equals(""))
			return "searchFail";
		List<CategorySecond> csList = categorySecondService.search(this.keywords.trim());
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "searchSuccess";
	}
}
