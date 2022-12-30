package lecture.jdbc.di.step2;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

import lecture.jdbc.di.step1.User;

// vo와 
	public class UserDAO {
		Connection con = null;
		
		private Connection getConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
				String id = "root";
				String pw = "test1234";

				Connection con = DriverManager.getConnection(jdbc_url,id,pw);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return con;
			
		}


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
		}}
