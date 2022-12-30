package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Maiin {
	public static void main(String[] args) {
		// 1. driver loading 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver 연결 성공!");
			
			// 나의 포트번호, 아이디, 패스워드 넣어서 값 생성
	        String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			// 2. database 연결
			// 연결 문법 넣고 =  연결할 거 지정하고(세부 값 넣어주고)
			Connection con = DriverManager.getConnection(jdbc_url, id, pw);
			System.out.println("database 접속 성공1!");
			
			// 3. sql문장을 가지고 있는 statement생성
			// 일반 statement 생성
			Statement stmt = con.createStatement();
			
			// 4. 생성한 statements 실행하기
			// 실행하기 위한 방법으로 SELECT 구문을 가져온다. 어디서? DB에서
			String sql = "SELECT bisbn, btitle, bauthor, bprice rn"
					+ "FROM bookrn"
					+ "WHERE btitle LIKE %여행%";
			// 마우스 커서 역할을 하는 rs를 생성
			ResultSet rs = stmt.executeQuery(sql);
			// rs를 조절하기 
			rs.next();
			
			String title = rs.getString("btitle");
			System.out.println("책 제목은 :" + title);
			//int title = rs.getInt("bprice");
			int price = rs.getInt(4); //3번째에 있는 값, 배열에서는 0부터, db에서는 1부터 시작 
			// 이 코드들을 실행하기 전에 해야하는 것!
			//pstmt.setString();
			String keyword = "자바";
			String sql2 = "SELECT bisbn, btitle, bauthor, bprice "
			         + "FROM BOOK "
			         + "WHERE btitle like ?";
	        PreparedStatement pstmt = con.prepareStatement(sql2); //keyword를 쓸 수 있는 statement
			ResultSet rs2 = pstmt.executeQuery();		
			
			// 5. 결과처리하기
			while(rs2.next()) {
				String title2 = rs2.getString("btitle");
				System.out.println("책 제목은:" + title2);
			}
			
			// 6. 사용한 리소스 해제하기
			rs.close();
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

}
