package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 별도의 클래스이고 여기에 메소드 연결 정보를 가지고 커넥션을 생성하는 메소드를 
// 이 클래스 안에 작성하자는 거쥬
// 결국엔 문제가 있는 게 userdao안에 connection이 있기때문에 생기는 문제가 많아?
// 그걸 빼내서 심플커넥션메이커에 넣는 게 어때? 이렇게 쪼개서 쓰자 ! 
public class SimpleConnectionMaker {
	
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";

			con = DriverManager.getConnection(jdbc_url,id,pw);

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