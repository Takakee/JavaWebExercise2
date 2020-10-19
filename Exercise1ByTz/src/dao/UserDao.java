package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.User;

public class UserDao {

	// 1.设置数据库连接对象和准备sql语句
	private Connection conn;
	private PreparedStatement pstmt;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	// 查询单个用户
	public User getByUserName(String inputUserName) {
		String sql = "select * from t_user where userName=?";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, inputUserName);
			// 执行SQL语句
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
		// 执行SQL语句
		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}

	}
}
