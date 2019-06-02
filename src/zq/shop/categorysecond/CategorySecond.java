package zq.shop.categorysecond;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import zq.shop.book.Book;
import zq.shop.category.Category;

/**
 * 二级分类的实体类
 * @author ZhouQi
 *
 */
public class CategorySecond implements Serializable {

	private Integer csid;							//二级分类主键ID
	private String csname;							//二级分类名
	private Category category;						//二级分类所属一级分类的对象
	private Set<Book> books = new HashSet<Book>();	//二级分类下的书籍对象集合
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
