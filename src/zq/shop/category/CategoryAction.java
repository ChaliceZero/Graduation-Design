package zq.shop.category;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 一级分类访问的Action
 * @author ZhouQi
 *
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category> {

	private Category category = new Category();
	
	private CategoryService categoryService;
	
	public Category getModel() {
		return category;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * web层：后台获取一级分类集合
	 * @return
	 */
	public String adminFindAll() {
		List<Category> clist = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", clist);
		return "adminFindAllSuccess";
	}
	/**
	 * web层：后台添加一级分类种类
	 */
	public String adminSave() {
		categoryService.saveCategory(this.category);
		return "adminSaveSuccess";
	}
	/**
	 * web层：后台删除一级分类
	 */
	public String adminDelete() {
		categoryService.deleteCategory(this.category);
		return "adminDeleteSuccess";
	}
	/**
	 * web层：后台跳转到编辑一级分类页面
	 */
	public String adminEditPage() {
		//查询一级分类
		this.category = categoryService.findByCid(this.category.getCid());
		return "adminEditPage";
	}
	/**
	 * web层：后台更新一级分类信息
	 */
	public String adminUpdate() {
		categoryService.saveCategory(this.category);
		return "adminUpdateSuccess";
	}
}
