package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



//상속이 안될 것 같고요
//그렇다고 DAO안에 데이터베이스 연결정보를 넣자니 이건 말이 안되고
//여기에만 안넣음ㄴ 된다는 생각에 class단위로 분리하는 건 어때? 
//이제 분리를 하나씩 하고 연결을 하는 거죠?

public class UserDAO {
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDAO() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	protected void insert(User user) {
		// TODO Auto-generated method stub
		Connection con = simpleConnectionMaker.getConnection();
		String sql = "INSERT INTO users VALUES (?, ?, ?)"; // ㅇ입력된 갑을 아직 명시하지 않은 것
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  user.getId());
			pstmt.setString(2,  user.getName());
			pstmt.setString(3,  user.getPassword());
			
			//정수값이 나오는 게 정상이지만 내용이 없으니까 int count = 뺄게요
			int count = pstmt.executeUpdate(); // 준비 다 됐으니까 실행만 해!
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
