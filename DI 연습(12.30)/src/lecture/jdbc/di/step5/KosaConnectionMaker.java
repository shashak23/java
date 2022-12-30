package lecture.jdbc.di.step5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KosaConnectionMaker implements ConnectionMaker {

	@Override
	public Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";			
			con = DriverManager.getConnection(jdbc_url, id, pw);						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
