package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
	private static BasicDataSource basicDS; // 포함시킨 dbcp에 있는 거에요!
	
	//main이 호출되기 전에 특정 코드를 실행시키고 싶어요
	//일반적으로 프로그램에서 사용하는 여러가지 자원 같은 거 로딩할 때 사용해요
	
	static { // resource를 로딩하고 싶을 때 하는 거죠
		// connection pool을 만들거에요 
		// database 연결하는 것 
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");  // sql 드라이버 연결
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");  
		// 연결한게 뭔지는 url에 적기
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		basicDS.setInitialSize(10);  //10개의 커넥션을 만들겠다는 초기 설정값 코드, 이거 시작되면 커넥션이 최대 10개가 와다다 만들어지죠 >< 
		basicDS.setMaxTotal(20); // 전체 커넥션의 최대값은 얼마로 할거니?, 최대로 만들겠다는 설정값
		// 그러니까 basicDS가 큰 커넥션이고 그거에 대한 정보는 static 박스안에 넣고 그거를 끄집어서 main에 다가 getdatasource로 만들어서 쓰는구나! 

	}
	
	public static DataSource getDataSource() {
		return basicDS;
		
	}
	
	public static void main(String[] args) {
		// DBCP 사용에 대해 알아보기
		// 만들고 사용하는 과정만 살짝 알아보아요
		// 커넥션이 있어야지 풀을 가져와요
        DataSource ds = getDataSource();
        Connection con = null;
        
        try {
			con = ds.getConnection(); // 커넥션 풀에서 커넥션을 빌려와요
			String sql = "SELECT btitle, bauthor FROM book"; // sql 조건문 만들기	
            PreparedStatement pstmt = con.prepareStatement(sql); // sql 연결하고 
            ResultSet rs = pstmt.executeQuery(); // sql 쿼리에 쓰도록 rs 맞추기
            while(rs.next()) {
            	System.out.println(rs.getString("btitle"));
            	
            }
            // 반납처리 
            rs.close();
            pstmt.close();
            con.close();
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}
        
		
	}

}
