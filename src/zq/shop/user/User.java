package zq.shop.user;

import java.util.HashSet;
import java.util.Set;

import zq.shop.cart.Cart;
import zq.shop.order.Order;

/**
 * 用户的实体类
 * @author ZhouQi
 *
 */
public class User {
	private Integer uid;		//用户的主键ID
	private String username;	//用户的账户名
	private String password;	//用户的账户密码
	private String name;		//用户真实姓名
	private String email;		//用户的邮箱
	private String phone;		//用户的手机号
	private String gender;		//用户性别
	private String address;		//用户地址
	private Integer status;		//用户账户的状态
	private String code;		//用户账户的激活码
	private Set<Order> orders = new HashSet<Order>();	//订单集合

	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
