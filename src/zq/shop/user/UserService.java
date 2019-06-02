package zq.shop.user;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import zq.shop.utils.MailUtils;
import zq.shop.utils.PageBean;
import zq.shop.utils.UUIDUtils;
/**
 * 业务层：用户模块
 * @author ZhouQi
 */
//业务层需要开启事务管理
@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void regist(User user) {
		//保存用户
		user.setStatus(0);//0未激活，1已激活
		String code = UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//根据激活码查询用户对象
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	//修改用户信息
	public void update(User user) {
		userDao.update(user);
		return;
	}

	public User login(User user) {
		return userDao.login(user);
	}

	/**
	 * 业务层:根据用户名查找用户对象
	 * @param username
	 * @return
	 */
	public User findByUserName(String username) {
		User existuser = userDao.findByUserName(username);
		return existuser;
	}

	/**
	 * 业务层：查找用户自己的信息
	 * @param user
	 * @return
	 */
	public User findByUid(User user) {
		return userDao.findByUid(user);
	}

	/**
	 * 业务层：分页查询用户信息
	 * @param pageNum
	 * @return
	 */
	public PageBean<User> findByPage(Integer pageNum) {
		int limit = 10;
		int index = (pageNum - 1) * limit;
		Integer totalCount = userDao.findCount();
		List<User> list = userDao.findListByPage(index, limit);
		PageBean<User> pageBean = new PageBean<User>(pageNum, limit, totalCount, list);
		
		return pageBean;
	}

	/**
	 * 业务层：删除用户
	 * @param user
	 */
	public void deleteUser(User user) {
		userDao.delete(user);
		return;
	}

	/**
	 * 根据uid查询用户对象
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	/**
	 * 后台：检索用户
	 * @param keywords
	 * @return
	 */
	public List<User> search(String keywords) {
		List<User> list = userDao.findList4Back(keywords);
		
		return list;
	}
	
}
