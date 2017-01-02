package cn.bjfu.im.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import cn.bjfu.im.vo.OrderVO;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class OrderDAO extends BaseDAO {
	public int addOrder(OrderVO vo) {// 添加订单//返回订单oid，-1时为错误插入
		Connection conn = open();
		int oid = -1;
		String sql = "insert into order (mid,total,address,date) values(?,?,?,?) ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, vo.getMid());
			ps.setDouble(2, vo.getTotal());
			ps.setString(3, vo.getAddress());
			ps.setTimestamp(4,
					new Timestamp(System.currentTimeMillis()));
			int count = ps.executeUpdate();
			if (count > 0) {// 添加成功//取回oid
				String sql2 = "SELECT LAST_INSERT_ID() as id";
				PreparedStatement ps2 = (PreparedStatement) conn
						.prepareStatement(sql2);
				ResultSet rs = (ResultSet) ps2.executeQuery();
				while (rs.next()) {
					oid = rs.getInt("id");
				}
			} else {
				oid = -1;
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
		return oid;
	}

	public HashMap<Integer, OrderVO> getOrder(int m_mid) {// 读取我的订单
		Connection conn = open();
		Map<Integer, OrderVO> orderMap =new HashMap<Integer, OrderVO>();
		String sql = "select oid,mid,total,address,date from order where mid = ? ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, m_mid);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				int oid = rs.getInt("oid");
				int mid = rs.getInt("mid");
				double total = rs.getDouble("total");
				String address = rs.getString("address");
				Timestamp timestamp = rs.getTimestamp("date");
				OrderVO vo = new OrderVO(mid, total, address,timestamp);
				orderMap.put(oid, vo);
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
		return (HashMap<Integer, OrderVO>) orderMap;
	}
}
