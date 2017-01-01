package cn.bjfu.im.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cn.bjfu.im.vo.MemberVO;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

//import cn.bjfu.im.MemberVO;

public class MemberDAO extends BaseDAO {

	public boolean isNoneByName(String name) {// ע��ǰ�ж��Ƿ����ظ���¼���ķ���
		Connection conn = open();
		boolean f = false;
		String sql = "select count(*) as count from member_info where name =?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = (ResultSet) ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 0)
				f = true;// û��������ֵ��û�������true
			else
				f = false;// ��������ֵ��û�������false
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

	public boolean add(MemberVO vo) {// ע���û��ķ���
		Connection conn = open();
		boolean f = false;
		try {
			// ���ʺŵ�sql���
			String sql = "insert into member_info (name,pwd) values (?,?)";
			// 3.��ȡPreparedStatement��������ִ��sql���
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			// sql��?�滻Ϊ����ֵ, setInt, setString,��Ŵ�1��ʼ
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			// 4.ͨ��PreparedStatement����ִ��sql���
			int count = ps.executeUpdate();// ִ��sql��
			// ���أ������ݿ����м�����¼�ܵ���Ӱ��
			if (count > 0) {
				f = true;// ��ӳɹ�����true
			} else {
				f = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close(conn);
		}
		return f;
	}

	public boolean updatePwd(MemberVO vo) {// ��������ķ���

		Connection conn = this.open();
		boolean f = false;
		// ���ʺŵ�sql���
		String sql = "update member_info set pwd=?  where name=?";
		// 3.��ȡPreparedStatement��������ִ��sql���
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			// sql�ص�?�滻Ϊ����ֵ, setInt, setString,��Ŵ�1��ʼ
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			// 4.ͨ��PreparedStatement����ִ��sql���
			int count = ps.executeUpdate();// ִ��sql��
			// ���أ������ݿ����м�����¼�ܵ���Ӱ��
			if (count > 0)
				f = true;// ���³ɹ�����true
			else
				f = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return f;
	}

	public int findMember(MemberVO vo) {// ��¼ʱʹ�õ��жϷ���//��ȷʱ����mid�����󷵻�-1
		Connection conn = open();
		int mid = 0;
		String sql = "select count(*) as count from member_info where name =? and pwd =?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ResultSet rs = (ResultSet) ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 0) {
				mid = -1;// �û��������벻��ȷ
			} else {// ��ȷ
				String sql2 = "select mid from member_info where name =? and pwd =?";
				PreparedStatement ps2 = (PreparedStatement) conn
						.prepareStatement(sql2);
				ps2.setString(1, vo.getName());
				ps2.setString(2, vo.getPwd());
				ResultSet rs2 = (ResultSet) ps2.executeQuery();
				while (rs2.next()) {
					mid = rs2.getInt("mid");
				}
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
		return mid;
	}
	 public static void main(String[] args){//test
	 
	 }

	// RegisterDAO sDAO=new RegisterDAO();
	// RegisterVO vo = sDAO.findByName("zxm");
	// System.out.println(vo.toString());
	// }

}