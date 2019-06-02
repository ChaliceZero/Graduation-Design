package zq.shop.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import zq.shop.user.User;

/**
 * 实体类：订单表
 * @author ZhouQi
 *
 */
public class Order {
	private Integer oid;		//订单ID
	private Double total;		//订单价格
	private Date ordertime;		//订单时间
	private Integer state;		//订单状态：0为未支付，1为已支付
	private String addr;		//订单地址
	private String phone;		//订单电话
	private String name;		//订单所属用户名
	private User user;			//订单所属用户（在表中使用uid关联）
	//订单项表中包含订单ID，一个订单包含多个订单项，1：n的关系
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
