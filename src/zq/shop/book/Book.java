package zq.shop.book;

import java.util.Date;

import zq.shop.categorysecond.CategorySecond;

/**
 * 书籍的实体类（属二级分类下）
 * @author ZhouQi
 *
 */
public class Book {
	private Integer bid;			//书籍主键ID
	private String bname;			//书籍名称
	private Double market_price;	//书籍市场价
	private Double shop_price;		//书籍本店价格
	private Long num;				//书籍库存
	private String image;			//书籍图片地址
	private String bdesc;			//书籍详细信息
	private Integer is_hot;			//是否是热门书籍
	private Date bdate;				//书籍的日期
	private CategorySecond categorySecond;	//书籍所属的二级分类的对象
	
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBdesc() {
		return bdesc;
	}
	public void setBdesc(String bdesc) {
		this.bdesc = bdesc;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
}
