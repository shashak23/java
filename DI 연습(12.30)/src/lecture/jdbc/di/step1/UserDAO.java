package lecture.jdbc.di.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	// DAO는 단순히 db처리만 담당해요
	// 비즈니스 로직은 서비스가 하는 일이라~ 
	// business logic이 나올 수 없어요
	public void insert(User user) {
		//vo의 역할이 여기에서는 주고 받으며 하는 역할이라고 생각하면 됭!
		// 데이터를 전달하는 수단으로 활용이 되면서 다르게 부르는게 
		// 그걸 VO를 이용해서 서로 주고 받러다? 그래서 그걸 DTO
		
		//jdbc 코드가 나오겠도
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
		
			Connection con = DriverManager.getConnection(jdbc_url,id,pw);
		
			
			String sql = "INSERT INTO users VALUES (?, ?, ?)"; // 입력된 갑을 아직 명시하지 않은 것
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,  user.getId());
			pstmt.setString(1,  user.getName());
			pstmt.setString(1,  user.getPassword());
			
			//정수값이 나오는 게 정상이지만 내용이 없으니까 int count = 뺄게요
			pstmt.executeUpdate(); // 준비 다 됐으니까 실행만 해!
			
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public User select(String string) {
		User user = null;
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		String id = "root";
		String pw = "test1234";
		
	
		Connection con = DriverManager.getConnection(jdbc_url,id,pw);
	
		
		String sql = "SELECT * FROM users WHERE id = ?"; // ㅇ입력된 갑을 아직 명시하지 않은 것
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1,string);

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		user = new User(string,
				rs.getString("name"),
				rs.getString("password"));
		
		pstmt.close();
		con.close();
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
		return user;
}

}
