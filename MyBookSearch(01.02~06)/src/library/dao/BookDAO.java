package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.vo.BookVO;

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
		sql.append("SELECT bisbn, btitle, bauthor, bpage, bdate, bprice ");
		sql.append("FROM book ");
		sql.append("WHERE btitle like ?");
		sql.append("ORDER BY bprice DESC"); // java라고 검색했을 때 나오는 것
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, "%" + text + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bauthor"),
						rs.getInt("bpage"),
						rs.getString("bdate"),
						rs.getInt("bprice"));
				list.add(book);
				
			}

			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return list;
	}

}
