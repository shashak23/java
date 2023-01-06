package library.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import library.dao.BookDAO;
import library.dao.ConnectionPool;
import library.vo.BookVO;


public class BookSearchService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		Connection con = null;
		
		try {
			con=(ConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookDAO dao = new BookDAO(con);
		
		ObservableList<BookVO> list = dao.select(text);
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		
	}

}






