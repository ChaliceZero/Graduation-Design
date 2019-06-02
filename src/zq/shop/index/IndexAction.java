package zq.shop.index;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import zq.shop.book.Book;
import zq.shop.book.BookService;
import zq.shop.category.Category;
import zq.shop.category.CategoryService;
import zq.shop.user.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author ZhouQi
 *
 */
public class IndexAction extends ActionSupport {
	//注入一级分类的service
	private CategoryService categoryService;
	private BookService bookService;
	private List<Book> hotList;
	private List<Book> newList;
	
	//使用getter方法让前台能获取数据（方法命名一定要规范，否则前台无法获取数据）
	public List<Book> getHotList() {
		return hotList;
	}
	//使用getter方法让前台获取数据
	public List<Book> getNewList() {
		return newList;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	@Override
	/*
	 * 执行首页访问的方法
	 */
	public String execute() throws Exception {
		
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		//查询所有的一级分类
		List<Category> categoryList = categoryService.findAll();
		
		//将list存入session中
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		
		//查询热门书籍
		hotList = bookService.findHot();
		
		//查询热门书籍
		newList = bookService.findNew();
		
		return "indexSuccess";
	}
}
