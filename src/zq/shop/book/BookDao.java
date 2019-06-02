package zq.shop.book;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zq.shop.user.User;
import zq.shop.utils.PageHibernateCallback;

/**
 * 数据访问层
 * @author ZhouQi
 *
 */
public class BookDao extends HibernateDaoSupport {

	/**
	 * dao层：查询热门书籍的方法，控制首页只显示10个
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findHot() {
		//处理方法一（条件参数较多时比较麻烦）：
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		List<Book> list= this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;*/
		//处理方法二：
		/*this.getHibernateTemplate().executeFind(new HibernateCallback<List<Book>>() {
			public List<Book> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery("from Book where is_hot = 1");
				query.setFirstResult(0);
				query.setMaxResults(10);
				return query.list();
			}
		});*/
		List<Book> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Book>("from Book where is_hot=? order by bdate desc",
						new Object[]{1}, 0, 10));
		
		return list;
	}
	/**
	 * dao层：查询最新书籍的方法，显示10个
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findNew() {
		List<Book> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Book>("from Book order by bdate desc",
						null, 0, 10));
		
		return list;
	}
	/**
	 * dao层：根据一级分类ID查询该分类下所有的书籍的数量
	 * @param cid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCountByCid(Integer cid) {
		String hql = "select count(*) from Book b,CategorySecond cs where b.categorySecond = cs and cs.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		//将Long型的数据转成int型
		return list.get(0).intValue();
	}
	/**
	 * dao层：根据一级分类、查询页数、每页记录数	查询书籍对象集合
	 * @param cid
	 * @param index
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBookByPage(Integer cid, int index, int limit) {
		String hql = "select b from Book b,CategorySecond cs where b.categorySecond = cs and cs.category.cid = ?";
		List<Book> bookList = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Book>(hql, new Object[]{cid}, index, limit));
		
		return bookList;
	}
	/**
	 * dao层：查询书籍对象信息的方法
	 * @param bid
	 * @return
	 */
	public Book findBookByBid(Integer bid) {
		//直接通过get方法查询书籍对象
		return this.getHibernateTemplate().get(Book.class, bid);
	}
	/**
	 * dao层：查询二级分类下所有书籍的数量的方法
	 * @param csid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Book b join b.categorySecond cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}
	/**
	 * dao层：根据二级分类ID分页查询书籍
	 * @param csid
	 * @param index
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBookByCsid(Integer csid, int index, int limit) {
		String hql = "select b from Book b join b.categorySecond cs where cs.csid = ?";
		List<Book> bookList = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Book>(hql, new Object[]{csid}, index, limit));
				
		return bookList;
	}
	/**
	 * dao：查询所有书籍的记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from Book";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}
	
	/**
	 * dao：分页查询所有书籍
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findByPage(int index, int limit) {
		String hql = "from Book";
		List<Book> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Book>(hql, null, index, limit));
		if (list.size() > 0)		
			return list;
		return null;
	}
	/**
	 * dao层：保存书籍
	 * @param book
	 */
	public void saveBook(Book book) {
		this.getHibernateTemplate().save(book);
	}
	/**
	 * 持久层：删除书籍
	 * @param book
	 */
	public void deleteBook(Book book) {
		this.getHibernateTemplate().delete(book);
	}
	
	/**
	 * 持久层:更新书籍信息
	 * @param book
	 */
	public void updateBook(Book book) {
		this.getHibernateTemplate().update(book);
	}
	
	/**
	 * 持久层：根据关键字查询相关书籍数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findcountByKey(String search) {
		String hql = "select count(*) from Book b where b.bname like '%?%'";	//参数：search，报错：java.lang.IllegalArgumentException: No positional parameters in query: select count(*) from Book b where b.bname like '%?%'
		String hql2 = "select count(*) from Book b where b.bname like %?%";		//参数：search，
		
		String hql3 = "select count(*) from Book b where b.bname like ?";	//参数无单引号："%" + search + "%"，
		String hql4 = "select count(*) from Book b where b.bname like '%"+ search +"%'";	//参数：无，错误：参数为空
		
		List<Long> list = this.getHibernateTemplate().find(hql4);
		if(list.size() > 0)
			return list.get(0).intValue();
		return 0;
	}
	
	/**
	 * 持久层：根据关键字查询书籍数据集合
	 * @param search
	 * @param index
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBookByPage(String search, int index, int limit) {
		String hql = "from Book b where b.bname like '%"+ search +"%'";
		List<Book> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Book>(
				hql, null, index, limit));
		if (list.size() > 0)
			return list;
		return null;
	}
	
	/**
	 * 持久层：根据书名查询书籍
	 * @param keywords
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Book> search(String keywords) {
		String hql = "from Book b where b.bname like '%"+ keywords +"%'";
		List<Book> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0)
			return list;
		return null;
	}
	
}
