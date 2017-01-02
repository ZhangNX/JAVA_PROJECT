package cn.bjfu.im.vo;

public class GoodsInfoVO {//商品信息VO类
	private String good;
	private double price;
	private String introduction;
	private String pictureURL;
	private int gid;
	public GoodsInfoVO(String t_good, double t_price,String t_introduction,
			String t_pictureURL){//构造方法
		this.good=t_good;
		this.price=t_price;
		this.introduction=t_introduction;
		this.pictureURL=t_pictureURL;
	}
	public GoodsInfoVO(int t_gid,String t_good, double t_price,String t_introduction,
			String t_pictureURL){//构造方法
		this.good=t_good;
		this.price=t_price;
		this.introduction=t_introduction;
		this.pictureURL=t_pictureURL;
		this.gid = t_gid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {//get和set方法
		this.good = good;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	@Override
	public String toString() {
		return "GoodsInfoVO [good=" + good + ", price=" + price
				+ ", introduction=" + introduction + ", pictureURL="
				+ pictureURL + "]";
	}
	
	
}
