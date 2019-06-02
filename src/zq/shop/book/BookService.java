package zq.shop.book;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zq.shop.user.User;
import zq.shop.utils.PageBean;

/**
 * 业务层
 * @author ZhouQi
 *
 */
@Transactional
public class BookService {
	
	BookDao bookDao;
	//注入dao层的对象
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	/**
	 * 业务层：查询热门书籍，返回给首页
	 * @return
	 */
	public List<Book> findHot() {
		return bookDao.findHot();
	}
	/**
	 * 业务层：查询最新书籍，返回给首页
	 * @return
	 */
	public List<Book> findNew() {
		return bookDao.findNew();
	}
	/**
	 * 业务层：通过分页查询某一级分类下所有书籍，将所有数据封装到PageBean对象中
	 * @return
	 */
	public PageBean<Book> findByPage(Integer cid, Integer pageNum) {
		int limit = 12;		//设置每页记录数为12条
		Integer totalCount = bookDao.findCountByCid(cid);	//查询总记录数
		int index = (pageNum - 1) * limit;		//计算查询开始的索引位置
		List<Book> bookList = bookDao.findBookByPage(cid, index, limit);	//查询书籍对象集合
		PageBean<Book> pageBean = new PageBean<Book>(pageNum,limit,totalCount,bookList);
		return pageBean;
		
		//方法二：
		/*int limit = 12; // 每页显示记录数.
		int totalPage = 0; // 总页数
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setPage(pageNum);
		pageBean.setLimit(limit);
		// 查询总记录数:
		Integer totalCount = bookDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		// 总页数的封装
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 书籍数据集合:
		int begin = (pageNum - 1) * limit;
		List<Book> list = bookDao.findBookByPage(cid, begin ,limit);
		pageBean.setList(list);
		return pageBean;*/
	}
	/**
	 * 业务层：通过书籍ID查询书籍详细信息
	 * @return
	 */
	public Book findBookByBid(Integer bid) {
		return bookDao.findBookByBid(bid);
	}
	/**
	 * 业务层：通过二级分类ID查询该分类下所有书籍的信息
	 * @param csid
	 * @return
	 */
	public PageBean<Book> findByCsid(Integer csid, Integer pageNum) {
		int limit = 12;		//设置每页记录数为12条
		Integer totalCount = bookDao.findCountByCsid(csid);	//查询总记录数
		int index = (pageNum - 1) * limit;		//计算查询开始的索引位置
		List<Book> bookList = bookDao.findBookByCsid(csid, index, limit);	//查询书籍对象集合
		PageBean<Book> pageBean = new PageBean<Book>(pageNum, limit, totalCount, bookList);
		return pageBean;
	}
	/**
	 * 业务层：分页查询书籍记录，返回给后台
	 * @param pageNum
	 * @return
	 */
	public PageBean<Book> findByPage(Integer pageNum) {
		int limit = 10;
		int index = (pageNum - 1) * limit;
		Integer totalCount = bookDao.findCount();
		List<Book> list = bookDao.findByPage(index, limit);
		PageBean<Book> pageBean = new PageBean<Book>(pageNum, limit, totalCount, list);
		return pageBean;
	}
	/**
	 * 业务层：保存书籍
	 * @param book
	 */
	public void saveBook(Book book) {
		bookDao.saveBook(book);
	}
	/**
	 * 业务层：删除书籍
	 * @param book
	 */
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
	}
	
	/**
	 * 业务层：更新书籍
	 * @param book
	 */
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}
	
	/**
	 * 根据关键字查询书籍
	 * @param search
	 * @return
	 */
	public PageBean<Book> findBookByKey(String search, Integer pageNum) {
		int limit = 12;
		int index = (pageNum - 1) * limit;
		Integer totalCount = bookDao.findcountByKey(search);
		List<Book> list = bookDao.findBookByPage(search, index, limit);
		PageBean<Book> pageBean = new PageBean<Book>(pageNum, limit, totalCount, list);
		
		return pageBean;
	}
	
	/**
	 * 后台：查询书名
	 */
	public List<Book> search(String keywords) {
		return bookDao.search(keywords);
	}
	
}
