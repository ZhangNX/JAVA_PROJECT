package cn.bjfu.im;
//zbj test
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public Connection open() {
		Connection conn = null;
		try {
			// 1. ��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2. ��ȡ��ָ�����ݿ�����Ӷ��� 
			String url = "jdbc:mysql://localhost/java_2017project";////���ݿ�����
			//String url = "jdbc:mysql://118.228.180.150/java_2017project";////���ݿ�����
			conn = DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

