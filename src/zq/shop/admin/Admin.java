package zq.shop.admin;

/**
 * 实体类：管理员表
 * @author ZhouQi
 *
 */
public class Admin {
	
	private Integer aid;		//管理员ID
	private String adminName;	//管理员登录名
	private String adminPwd;	//管理员登录密码
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
}
