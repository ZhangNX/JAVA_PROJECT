package cn.bjfu.im.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class StockDAO extends BaseDAO {
	public int getStock(int gid) {// 获取库存
		Connection conn = open();
		int amount = 0;
		String sql = "select amount from stock where gid = ? ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, gid);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				amount = rs.getInt("amount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return amount;
	}

	public boolean decrStock(int gid,int num) {// 减少库存
		Connection conn = open();
		boolean f = false;
		int oldcount = getStock(gid);
		String sql = "update stock set amount = ? - ? where gid = ? ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, oldcount);
			ps.setInt(2, num);
			ps.setInt(3, gid);
			int count =ps.executeUpdate();
			if (count > 0) {
				f = true;// 添加成功返回true
			} else {
				f = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
}
