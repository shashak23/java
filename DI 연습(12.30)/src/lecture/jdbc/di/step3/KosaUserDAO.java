package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 상속 말고 다른 
public class KosaUserDAO extends UserDAO{
	@Override
	protected Connection getConnection() {
		// 자기 나름대로의 준비코드를 넣어주면 되요
		// connection을 리턴하는 코드를 작성하며 되요
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
		return null;
	}

	@Override
	protected void insert(User user) {
		// TODO Auto-generated method stub
		
	}

}
