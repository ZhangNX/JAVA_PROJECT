package cn.bjfu.im.vo;

import java.sql.Timestamp;

public class OrderVO {// ∂©µ•VO¿‡
	private int mid;
	private double total;
	private String address;
	private Timestamp timestamp;

	public OrderVO(int t_mid, int t_total, String t_address) {
		this.mid = t_mid;
		this.total = t_total;		this.address = t_address;
	}

	public OrderVO(int t_mid, double t_total, String t_address, Timestamp t_timestamp) {
		this.mid = t_mid;
		this.total = t_total;
		this.address = t_address;
		this.timestamp = t_timestamp;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "OrderVO [mid=" + mid + ", total=" + total + ", address="
				+ address + "]";
	}

}
