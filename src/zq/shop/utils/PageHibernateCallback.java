package zq.shop.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 封装的类，实现Hibernate中的接口
 * 功能：提供分页模版，使用时不必重写类方法，直接继承即可
 * @author Administrator
 *
 * @param <T>
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>>{
	
	private String hql;			//语句
	private Object[] params;	//语句中的参数
	private int startIndex;		//开始索引的位置
	private int pageSize;		//每页的数据条数
	

	public PageHibernateCallback(String hql, Object[] params,
			int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}



	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		//1 执行hql语句
		Query query = session.createQuery(hql);
		//2 实际参数
		if(params != null){
			for(int i = 0 ; i < params.length ; i ++){
				query.setParameter(i, params[i]);
			}
		}
		//3 分页
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		
		return query.list();
	}

}
