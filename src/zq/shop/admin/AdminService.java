package zq.shop.admin;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 业务层：管理员模块
 * @author ZhouQi
 *
 */
@Transactional
public class AdminService {
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	/**
	 * 管理员用户登录方法
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}
	/**
	 * 查询所有管理员方法
	 * @return
	 */
	public List<Admin> findAll() {
		return adminDao.findAll();
	}
	
	/**
	 * 保存管理员信息
	 */
	public void save(Admin admin) {
		adminDao.save(admin);
	}
	
	/**
	 * 查找某个管理员信息
	 * @param aid
	 * @return
	 */
	public Admin find(Integer aid) {
		return adminDao.find(aid);
	}
	/**
	 * 删除普通管理员
	 * @param admin
	 */
	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

}
