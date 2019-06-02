package zq.shop.category;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import zq.shop.categorysecond.CategorySecond;

/**
 * 前台一级分类的实体类
 * @author ZhouQi
 *
 */
public class Category implements Serializable {

	private Integer cid;	//主键ID
	private String cname;	//分类名
	//所关联的二级分类的对象集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
