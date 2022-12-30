package lecture.jdbc.di.step6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lecture.jdbc.di.step1.User;


public class UserDAO { // class에 connection을 넣어줘야지 사용할 수 있도록 해주는!
    private ConnectionMaker connectionMaker;
	
	public UserDAO(ConnectionMaker connectionMaker) {
//		connectionMaker = new KosaConnectionMaker();
	}
	
	public void insert(User user) {
		
		// 일반 JDBC Code가 나오겠죠.
		try {
			
			Connection con = connectionMaker.getConnection();
			
			String sql = "INSERT INTO users VALUES (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			
			pstmt.executeUpdate();  // 사용자 등록!!
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User select(String string) {
		// 일반 JDBC Code가 나오겠죠.
		User user = null;
		
		try {
			
			Connection con = connectionMaker.getConnection();
			
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, string);
			
			ResultSet rs = pstmt.executeQuery();  // 사용자 검색			
			rs.next();
			
			user = new User(string, 
					rs.getString("name"),
					rs.getString("password"));
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
}

