package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

   public static void main(String[] args) {
      
      try {
         // 1. JDBC Driver Loading 단계
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("Driver 로딩 성공!!");
         //jdbc는 my sql을 사용할거고, 나의 포트번호 , 내가 무슨 db를 사용할거냐!
         String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
         String id = "root";
         String pw = "test1234";
         
         // 2.Database 접속
         Connection con = DriverManager.getConnection(jdbc_url,id,pw); 
         System.out.println("데이터베이스 접속 성공~");
         //일반 Statement 사용
         // 3. Statement 생성
         //Statement stmt = con.createStatement();
         
         // 4.Query를 작성해서 시킨다 (SELECT 구문을 실행시켰다)
         
//         String sql = "SELECT bisbn, btitle, bauthor, bprice \r\n"
//               + "FROM BOOK\r\n"
//               + "WHERE btitle like '%" + keyword + "%'";
//         ResultSet rs = stmt.executeQuery(sql);
//         
      
//         rs.next(); //결과는 내려갈 수 있으면, true 없으면 false
//         String title = rs.getString("btitle");
//         System.out.println("책 제목은: " + title);
         //int title = rs.getInt("bprice");
         //String price = rs.getString("bprice");
         //몇번째 컬럼인지 써도 되긴ㅎ
         //String price = rs.getString(3);
         
         //만약 가격을 가지고 오는 거면 Int title = rs.getInt("bprice");
         
         //PrepareStatement로 사용해야함!
         //PreparedStatement는 SQL을 가지고 생성한다
         //preparedStatement는 IN Parameter를 이용할 수 있어요 => ?로 표현해요!
         //주의해야 하는 점은 keyword 부분에는 ?(In Parameter)를 쓸 수 없떠용! 조건 값을 대입할 때는 쓸 수 있어요 컬럼명 안대...
         String keyword = "자바";
         String sql = "SELECT bisbn, btitle, bauthor, bprice "
         + "FROM BOOK "
         + "WHERE btitle like ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         
         //실행하기 전에 ...? 를 채워야해요!
         pstmt.setString(1,"%" + keyword +"%");
         ResultSet rs = pstmt.executeQuery();
         // 5.결과 처리! 
         while(rs.next()) {
            String title = rs.getString("btitle");
            System.out.println("책 제목은:" + title);
         } //값이 없응ㄹ 때까지 반복
         
         // 6.사용한 자원 해제
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