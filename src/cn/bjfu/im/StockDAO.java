package cn.bjfu.im;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class StockDAO extends BaseDAO {
	public int getStock(int gid) {// ��ȡ���
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

	public boolean decrStock(int gid,int num) {// ���ٿ��
		Connection conn = open();
		boolean f = false;
		String sql = "update stock set amount = (select amount from stock where gid = ?) - ? where gid = ? ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, gid);
			ps.setInt(2, num);
			ps.setInt(3, gid);
			int count =ps.executeUpdate();
			if (count > 0) {
				f = true;// ��ӳɹ�����true
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
