package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.User;

public class UserDao {

	// 1.�������ݿ����Ӷ����׼��sql���
	private Connection conn;
	private PreparedStatement pstmt;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	// ��ѯ�����û�
	public User getByUserName(String inputUserName) {
		String sql = "select * from t_user where userName=?";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, inputUserName);
			// ִ��SQL���
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				User u = new User();

				u.setUserName(rs.getString("userName"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("role"));
				u.setChrName(rs.getString("chrName"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean insertUser(User user) throws Exception {
		String sql = "insert into t_user values (?,?,?,?)";

		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUserName());
		this.pstmt.setString(2, user.getPassword());
		this.pstmt.setString(3, user.getChrName());
		this.pstmt.setString(4, user.getRole());
		// ִ��SQL���
		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}

	}
}
