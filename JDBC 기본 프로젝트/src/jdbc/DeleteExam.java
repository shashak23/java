package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteExam {
	public static void main(String[] args) {
		//특정 
		try {
			Class.forName("com.mysql.cj.jadbc,Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			// 2. db연결
			Connection con = DriverManager.getConnection(jdbc_url, id, pw);
			
			con.setAutoCommit(false);  // 해당 sql은 transactiondl이 실행됩니다!
			
			// 3. preparedStatement 실행
			String sql = "DELETE FROM book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%여행%");
			
			
			// 4. 쿼리 실행
			int count = pstmt.executeUpdate();
			//앗 리턴값은 정수값이 와요
			//영향받은 row의 수
			
			// 5. 결과처리
			System.out.println("삭제한 row의 수는 : " + count);
			// 커밋을 해주거나 롤백을 해주거나 둘 중에 하나는 나와야해요
			// con.commit(); // transaction을 종료하고 지금까지 실행한 sql문을 db에 적용함
			con.rollback(); // transaction을 종료하고 지금까지 실행한 sql문을 무효화!
			
			// 만약 transaction을 종료하지 않고 connection을 close끝내면?
			// transaction을 종료하고 지금까지 실행한 sql문을 실제로 적용
			// 명시적으로 잡아주기 위해 커밋이나 롤백이 나오는데 깜빡하고 안쓴다면 트랜잭션이 커밋되버버려요
			
			// 6. 사용한 자원 반납
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
