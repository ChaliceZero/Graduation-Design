package zq.shop.utils;

import java.util.List;

/**
 * 分页显示书籍信息
 * @author ZhouQi
 *
 */
public class PageBean<T> {
	private Integer page;		//当前页数
	private Integer limit;		//每页显示的记录条数
	private Integer totalCount;	//总记录数
	private Integer totalPage;	//总页数
	private List<T> list;		//显示到浏览器的数据集合（包括一级分类、二级分类、书籍等）
	
	//使用构造方法初始化成员变量
	public PageBean(Integer page, Integer limit, Integer totalCount, List<T> list) {
		this.page = page;
		this.limit = limit;
		this.totalCount = totalCount;
		this.list = list;
		if(totalCount % limit == 0) {	//判断页数是否为整数
			this.totalPage = totalCount / limit;
		}else {
			this.totalPage = totalCount / limit + 1;
		}
	}
	//无参的构造方法
	public PageBean() {}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
