package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class UserDAO {
	protected abstract Connection getConnection(); // 패키지가 달라도 상속관계에 있으면 사용 가능하도록 한 게 protected	
	
	public User select(String string) {
		User user = null;
		
		try {
		
			Connection con = getConnection();
		
			
			String sql = "SELECT * FROM users WHERE id = ?"; // ㅇ입력된 갑을 아직 명시하지 않은 것
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,string);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			user = new User(string, rs.getString("name"), rs.getString("password"));
	
			pstmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
			return user;
	}

	protected abstract void insert(User user);
}



