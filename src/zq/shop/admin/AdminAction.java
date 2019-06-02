package zq.shop.admin;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * web层：管理员用户
 * @author ZhouQi
 *
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private Admin admin = new Admin();
	private AdminService adminService;

	public Admin getModel() {
		return this.admin;
	}
	//注入AdminService
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	/**
	 * 后台登录
	 */
	public String login() {
		Admin existAdmin = adminService.login(this.admin);
		if(existAdmin == null) {
			this.addActionError("用户名或密码错误，请重新登录");
			return "loginInput";
		}
		ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
		
		return "loginSuccess";
	}
	
	/**
	 * 管理员管理页面
	 */
	public String adminFindAll() {
		List<Admin> aList = adminService.findAll();
		ActionContext.getContext().getValueStack().set("aList", aList);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 管理员信息保存
	 */
	public String adminSave() {
		adminService.save(this.admin);
		return "adminSaveSuccess";
	}
	
	/**
	 * 管理员添加
	 */
	public String adminAddPage() {
		
		return "adminAddPageSeccess";
	}
	
	/**
	 * 后台退出，未使用
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "logoutSuccess";
	}
	
	/**************************以下为后台管理员跳转页面方法*******************************/
	/**
	 * 跳转到后台登录页面index.jsp
	 */
	public String adminPage() {
		return "adminPageSuccess";
	}
	/**
	 * 跳转到后台登录页面top.jsp
	 */
	public String topFrame() {
		return "topFrameSuccess";
	}
	/**
	 * 跳转到后台登录页面left.jsp
	 */
	public String leftFrame() {
		return "leftFrameSuccess";
	}
	/**
	 * 删除普通管理员
	 */
	public String adminDelete() {
		Admin a = adminService.find(this.admin.getAid());
		if (a.getAdminName().equals("admin")) {
			this.addActionMessage("您的权限不够，请重新操作");
			return "msg2";
		}
		adminService.delete(this.admin);
		return "adminDeleteSuccess";
	}
	/**
	 * 跳转到后台登录页面welcome.jsp
	 */
	public String welcomeFrame() {
		return "welcomeFrameSuccess";
	}
	/**
	 * 跳转到后台登录页面bottom.jsp
	 */
	public String bottomFrame() {
		return "bottomFrameSuccess";
	}
	/*****************************以下方法页面通过其他action访问转向***********************/
	/**
	 * 跳转到后台一级分类add页面
	 */
	public String categoryAdd() {
		return "categoryAddPage";
	}
	/**
	 * 跳转到后台一级分类edit页面
	 *//*
	public String categoryEdit() {
		return "categoryEditPage";
	}
	*//**
	 * 跳转到后台二级分类add页面
	 *//*
	public String categorySecondAdd() {
		return "categorySecondAddPage";
	}
	*//**
	 * 跳转到后台书籍分类add页面
	 *//*
	public String bookAdd() {
		return "bookAddPage";
	}*/
}
