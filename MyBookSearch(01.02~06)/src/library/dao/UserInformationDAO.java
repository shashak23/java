package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.vo.UserVO;

public class UserInformationDAO {
	

	Connection con;
	
	public UserInformationDAO() {	
	}
	
	
	public UserInformationDAO(Connection con) {
		super();
		this.con = con;
	}
	

	public ObservableList<UserInformationDAO> select(String text) {

		ObservableList<UserInformationDAO> list = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *");
		sql.append("FROM users");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			
			// 5. 결과처리!
			while(rs.next()) {
				UserVO ui = new UserVO(rs.getString("id"),
						rs.getString("name"),
						rs.getString("pw"),
						rs.getString("date"),
						rs.getString("tier"));
//				list.add(ui);
			}
			
			// 6. 사용한 리소스 반납
			rs.close();
			pstmt.close();
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return list;
	}

}
