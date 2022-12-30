package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.vo.PracticeJDBC_test02_Book;

public class Example01 {

	public static <PracticeJDBC_test02_Book> void main(String[] args) {
		// 교수님 코드
		// JDBC를 이용해서 Database에서 데이터를 추출해서 출력하자.
		try {
			// 1. Driver Loading
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 데이터베이스 연결
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			Connection con = DriverManager.getConnection(jdbc_url, id, pw);
			
			// 3. 실행할 SQL문장을 가지고 있는 Statement 생성하기
			StringBuffer sql = new StringBuffer();
			sql.append("select category, department_name, capacity ");
			sql.append("from tb_department ");
			sql.append("where (category = '공학') and (capacity between 20 and 30) ");
		    sql.append("order by department_name asc");
		    
		    PreparedStatement pstmt = con.prepareStatement(sql.toString());
		    
		    // 4. 실행
		    ResultSet rs = pstmt.executeQuery();
		    
		    // 5. 결과처리
		    ArrayList<PracticeJDBC_test02_Book> list = new ArrayList<PracticeJDBC_test02_Book>();
		    
		    while (rs.next()) {
		    	// VO를 생성해야 한다.
		    	PracticeJDBC_test02_Book dept = new PracticeJDBC_test02_Book(rs.getString("category"),
		    			rs.getString("department_name"),
		    			rs.getInt("capacity"));
		    	// arraylist에 추가하기
		    	list.add(dept);
		    }
		    
		    for (PracticeJDBC_test02_Book d : list) {
		    	System.out.println(d.getDepartment_name());
		    }
		    
		    // 6. 사용한 리소스 해제
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
