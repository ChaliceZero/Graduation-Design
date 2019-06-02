package zq.shop.book;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import zq.shop.category.Category;
import zq.shop.category.CategoryService;
import zq.shop.categorysecond.CategorySecond;
import zq.shop.categorysecond.CategorySecondService;
import zq.shop.user.User;
import zq.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * web层：书籍商品的Action
 * @author ZhouQi
 * @param <Book>
 *
 */
public class BookAction extends ActionSupport implements ModelDriven<Book> {

	private Book book = new Book();		//模型驱动对象，封装与前端交互的书籍的数据	
	private Integer cid;				//接收前台传来的一级分类的ID，通过该值查找相对应的一级分类下的所有书籍
	private Integer csid;				//接收前台传来的二级分类的ID，通过该值查找相对应的二级分类下的所有书籍
	private BookService bookService;	//书籍模块的业务层对象
	private CategoryService categoryService;	//一级分类模块的业务层对象
	private CategorySecondService categorySecondService;	//二级分类业务层对象
	private Integer pageNum;			//接收前台传来的想查找的分页中的某一页的页数
	private PageBean<Book> pageBean;	//获取数据库的分页后的书籍集合数据，与前台交互
	//文件上传属性，提供setter方法
	private File upload;				//接收上传文件的属性
	private String uploadContentType;	//上传文件的Mime类型
	private String uploadFileName;		//上传文件的名称
	private String search;				//查询关键字
	//后台检索
	private String keywords;
	
	public Book getModel() {
		return this.book;
	}
	//注入业务层的对象方法
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	//通过setter方法注入cid的值
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//通过getter方法让前台获取cid
	public Integer getCid() {
		return cid;
	}
	//不必使用getter方法让前台获取csid的值，model对象中已经存在该外键值
	//通过setter方法获取前台的二级分类ID
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//通过setter方法注入一级分类的业务层的对象
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//通过setter方法得到来自前台的页数的数值
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	//通过getter方法将对象传入前台
	public PageBean<Book> getPageBean() {
		return pageBean;
	}
	//注入二级分类的Service
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//文件上传属性注入
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch() {
		return search;
	}
	//
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 通过一级分类的ID，查询该一级分类下的所有书籍
	 * @return
	 */
	public String findByCid() {
		System.out.println("============================页数============================="+this.pageNum);
		//查询分类（查询所有一级分类）
		List<Category> categoryList = categoryService.findAll();
		//查询一级分类的对象
		Category category = categoryService.findByCid(this.cid);
		//获得值栈（使用压栈的方法将数据传给前台页面）
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		ActionContext.getContext().getValueStack().set("category", category);
		
		//查询该分类下的所有书籍
		this.pageBean = bookService.findByPage(cid, this.pageNum);
		
		return "findByCidSuccess";
	}
	
	/**
	 * 查询书籍详情的方法
	 * @return
	 */
	public String findByBid() {
		//查询分类（查询所有一级分类）
		List<Category> categoryList = categoryService.findAll();
		//获得值栈（使用压栈的方法将数据传给前台页面）
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		this.book = bookService.findBookByBid(this.book.getBid());
		
		return "findByBidSuccess";
	}
	
	/**
	 * 通过二级分类的ID，查询该二级分类下所有的书籍
	 * @return
	 */
	public String findByCsid() {
		//查询分类（查询所有一级分类）
		List<Category> categoryList = categoryService.findAll();
		//查询二级分类的对象
		CategorySecond categorySecond = categorySecondService.findByCsid(this.csid);
		//获得值栈（使用压栈的方法将数据传给前台页面）
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		ActionContext.getContext().getValueStack().set("categorySecond", categorySecond);
		
		//查询二级分类下所有的书籍信息并将书籍list集合封装到分页对象中
		this.pageBean = bookService.findByCsid(this.csid, this.pageNum);
		
		return "findByCsidSuccess"; 
	}
	
	/**
	 * 后台：管理员访问后台书籍管理页面
	 */
	public String adminFindAll() {
		this.pageBean = bookService.findByPage(this.pageNum);
		
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台：管理员添加图书方法
	 */
	public String addBookPage() {
		//先查所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList" ,csList);
		return "addBookPageSuccess";
	}
	
	/**
	 * 后台：上传图书图片并保存图书信息进数据库
	 * @throws ParseException 
	 */
	public String saveBook() throws ParseException {
		//先查询二级分类所属一级分类的ID值，根据该一级分类ID值区分存储位置
		CategorySecond categorySecond = categorySecondService.findByCsid(csid);
		String cid = categorySecond.getCategory().getCid().toString();
		//获取路径
		String path = ServletActionContext.getServletContext().getRealPath("/books");
		System.out.println("********************1图片存储路径*****************************"+path);
		//图片上传的真实路径
		String realPath = path + "\\" + cid + "\\" + this.uploadFileName;
		//创建该文件
		File diskFile = new File(realPath);
		//使用文件上传工具上传图片
		try {
			FileUtils.copyFile(upload, diskFile);
		} catch (IOException e) {
			this.addActionError("图片上传失败");
		}
		//将书籍保存到数据库，先完善书籍对象的信息
		this.book.setCategorySecond(categorySecond);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sf.format(new Date());
		this.book.setBdate(sf.parse(format));
		this.book.setImage("books/" + cid + "/" + this.uploadFileName);
		bookService.saveBook(this.book);
		
		return "saveBookSuccess";
	}
	
	/**
	 * 后台：跳转到书籍信息编辑页面
	 * @return
	 */
	public String editBookPage() {
		//先查找出该ID的书籍信息
		this.book = bookService.findBookByBid(this.book.getBid());
		//在查所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList" ,csList);
		
		return "editBookPage";
	}
	
	/**
	 * 后台：更新书籍
	 * @throws ParseException 
	 */
	public String updateBook() throws ParseException {
		//先查询二级分类所属一级分类的ID值，根据该一级分类ID值区分存储位置
		CategorySecond categorySecond = categorySecondService.findByCsid(csid);
		String cid = categorySecond.getCategory().getCid().toString();
		//获取路径
		String path = ServletActionContext.getServletContext().getRealPath("/books");
		System.out.println("********************1图片存储路径*****************************"+path);
		//图片上传的真实路径
		String realPath = path + "\\" + cid + "\\" + this.uploadFileName;
		//创建该文件
		File diskFile = new File(realPath);
		//使用文件上传工具上传图片
		try {
			FileUtils.copyFile(upload, diskFile);
		} catch (IOException e) {
			this.addActionError("图片上传失败");
		}
		//更新书籍信息
		this.book.setCategorySecond(categorySecond);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sf.format(new Date());
		this.book.setBdate(sf.parse(format));
		this.book.setImage("books/" + cid + "/" + this.uploadFileName);
		bookService.updateBook(this.book);
		
		return "updateBookSuccess";
	}
	
	/**
	 * 后台：删除书籍
	 */
	public String deleteBook() {
		bookService.deleteBook(this.book);
		return "deleteBookSuccess";
	}
	/**
	 * 前台：模糊查询
	 */
	public String searchBook() {
		//查询分类（查询所有一级分类）
		List<Category> categoryList = categoryService.findAll();
		//获得值栈（使用压栈的方法将数据传给前台页面）
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		//查询相关关键字的所有书籍
		this.pageBean = bookService.findBookByKey(this.search.trim(), this.pageNum);
		return "searchBookSuccess";
	}
	
	/**
	 * 后台：查询
	 */
	public String searchKeys() {
		if (this.keywords == null || this.keywords.trim().equals(""))
			return "searchFail";
		List<Book> bList = bookService.search(this.keywords.trim());
		ActionContext.getContext().getValueStack().set("bList", bList);
		
		return "searchSuccess";
	}
}
