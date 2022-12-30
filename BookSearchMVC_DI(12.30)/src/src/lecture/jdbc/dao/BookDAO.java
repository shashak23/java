package src.lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.lecture.jdbc.vo.BookVO;


//데이터베이스 처리 전문객체를 만들기위한 CLASS 
public class BookDAO {
	
	Connection con;
	
	public BookDAO() {
		
	}
	
	public ObservableList<BookVO> select(String text) {
		
		ObservableList<BookVO> list = null;
		
		Connection con = null;
		
		try {
			con = ds.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bisbn, btitle, bauthor, bprice ");
		sql.append("FROM book ");
		sql.append("WHERE btitle like ?");
		sql.append("ORDER BY bprice DESC");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, "%" + text + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			
			// 5. 결과처리!
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bauthor"),
						rs.getInt("bprice"));
				list.add(book);
			}
			
			// 6. 사용한 리소스 반납
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return list; // 채운 결과를 리턴 
	}

	public int delete(String deleteISBN) {

		try {
			con.setAutoCommit(false);  // transaction 시작 
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, deleteISBN);
			
			int count = pstmt.executeUpdate();
	
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return 0;
		
	}
}
