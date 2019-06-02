package zq.shop.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import zq.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * web层：用户模块
 * @author ZhouQi
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	//注入与前端交互的模型驱动
	private User user = new User();
	//注入用户模块,业务处理层的类
	private UserService userService;
	//接收验证码
	private String checkcode;
	private String newPassword;
	private Integer pageNum;
	private PageBean<User> pageBean;
	//支付后跳转登录，暂未使用
	private Integer uid;
	//后台检索
	private String keywords;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getModel() {
		return user;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public PageBean<User> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<User> pageBean) {
		this.pageBean = pageBean;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	//
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	//
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 前台：注册页面跳转方法
	 */
	public String registPage() {
		return "registPageSuccess";
	}
	
	/**
	 * 前台：用户注册方法
	 */
	@InputConfig(resultName="registInput")
	public String regist() {
		//校验前台验证码
		String s_checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(checkcode == null || !checkcode.equalsIgnoreCase(s_checkcode)) {
			this.addActionError("验证码错误");
			return "registInput";
		}
		userService.regist(user);
		this.addActionMessage("注册成功，请前往邮箱激活账户！");
		return "registSuccess";
	}
	/**
	 * 前台：激活用户账户方法
	 */
	public String active() {
		User existuser = userService.findByCode(user.getCode());
		if(existuser != null) {
			existuser.setStatus(1);
			userService.update(existuser);
			this.addActionMessage("激活成功，快去登录试试吧！");
			return "activeSuccess";
		}
		this.addActionMessage("激活失败，请联系管理员！");
		return "activeFail";
	}
	/**
	 * 前台：登录页面跳转方法
	 */
	public String loginPage() {
		return "loginPageSuccess";
	}
	/**
	 * 前台：用户登录方法
	 */
	@InputConfig(resultName="loginInput")
	public String login() {
		//校验前台验证码
		String s_checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(checkcode == null || !checkcode.equalsIgnoreCase(s_checkcode)) {
			this.addActionError("验证码错误");
			return "loginInput";
		}
		User existuser = userService.login(user);
		if(existuser == null) {
			this.addActionError(" 登录失败，请确认用户名密码正确及用户账户已激活");
			return "loginInput";
		}
		ServletActionContext.getRequest().getSession().setAttribute("existUser", existuser);
		return "loginSuccess";
	}
	/**
	 * 
	 */
	public String login2() {
		User existuser = userService.findByUid(this.uid);
		if(user == null) {
			return "login2Fail";
		}
		ServletActionContext.getRequest().getSession().setAttribute("existUser", existuser);
		return "loginSuccess";
	}
	/**
	 * 异步校验用户名方法（注册AJAX校验用户名）
	 */
	public String checkUserName() throws IOException {
		User existuser = userService.findByUserName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existuser == null) {
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else {
			response.getWriter().print("<font color='red'>用户名已被使用</font>");
		}
		return NONE;	//不需要返回注册页面，使用response带回消息即可
	}
	/**
	 * 前台：用户退出方法
	 */
	public String quit() {
		//销毁用户的session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quitSuccess";
	}
	/**
	 * 前台：用户查找自己的信息
	 */
	public String findSelf() {
		//查询自己的信息
		this.user = userService.findByUid(this.user);
		return "findSelfSuccess";
	}
	/**
	 * 前台：更新用户普通信息
	 */
	public String updateUser() {
		User user = userService.findByUid(this.user);
		
		user.setUsername(this.user.getUsername());
		user.setEmail(this.user.getEmail());
		user.setPhone(this.user.getPhone());
		user.setName(this.user.getName());
		user.setGender(this.user.getGender());
		user.setAddress(this.user.getAddress());
		
		userService.update(user);
		this.addActionMessage("用户信息修改成功");
		
		return "updateUserSuccess";
	}
	/**
	 * 前台：跳转修改用户密码页面
	 */
	public String updatePwdPage() {
		this.user = userService.findByUid(this.user);
		return "updatePwdPageSuccess";
	}
	/**
	 * 前台：修改用户密码
	 */
	public String updatePwd() {
		//查询出用户信息
		User user = userService.findByUid(this.user);
		if(!user.getPassword().equalsIgnoreCase(this.user.getPassword())) {
			this.addActionMessage("原密码错误，请重新输入");
			return "updatePwdFail";
		}
		//设置用户密码
		user.setPassword(this.newPassword);
		//更新数据库
		userService.update(user);
		this.addActionMessage("账户密码修改成功");
		
		return "updatePwdSuccess";
	}
	/**
	 * 后台：用户管理模块
	 */
	public String adminFindAll() {
		this.pageBean = userService.findByPage(this.pageNum);
		
		return "adminFindAllSuccess";
	}
	/**
	 * 后台：激活用户账户
	 */
	public String activeUser() {
		this.user = userService.findByUid(this.user);
		this.user.setStatus(1);
		userService.update(this.user);
		
		return "activeUserSuccess";
	}
	/**
	 * 后台：删除账户
	 */
	public String deleteUser() {
		userService.deleteUser(this.user);
		
		return "deleteUserSuccess";
	}
	
	/**
	 * 后台：检索
	 */
	public String searchKeys() {
		List<User> uList = userService.search(this.keywords.trim());
		ActionContext.getContext().getValueStack().set("uList", uList);
		
		return "searchSuccess";
	}
}
