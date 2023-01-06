package lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.jdbc.vo.BookVO;

//데이터베이스 처리 전문객체를 만들기위한 class

public class BookDAO {

	Connection con;
	
	public BookDAO() {	
	}
	
	
	public BookDAO(Connection con) {
		super();
		this.con = con;
	}


	public ObservableList<BookVO> select(String text) {

		ObservableList<BookVO> list = null;
		
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
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return list;
	}

	public int delete(String deleteISBN) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ?");

		int count = 0;
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, deleteISBN);
			
			count = pstmt.executeUpdate();
			
			// 6. 사용한 자원 반납
			pstmt.close();
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return count;
	}

	
}
