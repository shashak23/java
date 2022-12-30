package lecture.jdbc.di.step5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lecture.jdbc.di.step1.User;

// 이전에는 클래스와 메소드를 반듯 써야하는 조건이었다면
// 지금은 인터페이스 연결만 하시면 되요로 바뀜
// 클래스에 지금 인스턴스가 없는 상황이에요 그래서 dao에서 나올 수 밖에 없으니까
// 결국에는 다시 강결해지는 거에요 tightly 되죠....

public class UserDAO {
private ConnectionMaker connectionMaker;
	
	public UserDAO() {
		connectionMaker = new KosaConnectionMaker();
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

