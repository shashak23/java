package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.vo.CATEGORY;

public class Example0101 {
	
	public static void main(String[] args) {
		
		try {
			//1. driver loading
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			// 2. database 연결
			Connection con = DriverManager.getConnection(jdbc_url,id,pw);
			
			// 3. statement 생성
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CATEGORY , DEPARTMENT_NAME , CAPACITY");
			sql.append("FROM tb_department");
			sql.append("WHERE CATEGORY = '공학' AND CAPACITY BETWEEN 20 AND 30");
			sql.append("ORDER BY DEPARTMENT_NAME ASC");
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			// 4.실행
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<CATEGORY> list = new ArrayList<CATEGORY>();
			
			// 5. 결과처리
			while(rs.next()) {
				CATEGORY category = new CATEGORY(rs.getString("CATEGORY"),
						rs.getString("DEPARTMENT_NAME"),
						rs.getInt("CAPACITY"));
				list.add(category);
			}
			for(CATEGORY category : list) {
				System.out.println(category);
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
