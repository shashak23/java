package src.lecture.jdbc.service;

import java.sql.Connection;

import javafx.collections.ObservableList;
import src.lecture.jdbc.dao.BookDAO;
import src.lecture.jdbc.dao.DBCPConnectionPool;
import src.lecture.jdbc.vo.BookVO;

//책에 대한 비즈니스 로직을 처리를 담당 클래스 = logic 담당 class

public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {

		BookDAO dao = new BookDAO();
		
		ObservableList<BookVO> list = dao.select(text); 

		return list;
	}
	
	//여기에서 데이터커넥션을 얻어와서 주입해야해요 ~! 
	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		
		Connection con = new 		// 로직처리해야해요! 그렇게 할 게 없어요? 왜? 지금은 db처리만 있어요
		Connection con = (DBCPConnectionPool.getDataSource()).getConnection();
		
		con.setAutoCommit(false);
		
		BookDAO dao = new BookDAO(con);
		
		int count = dao.delete(deleteISBN);
		 ObservableList<BookVO> list = dao.select(searchKeyword);
		// 여기서부터 Transaction 시작이에요
		// connection에 대해서 setAutocommin()을 false로 지정해야 transaction이 시작됨
		// transaction을 commit해야하고 그렇지 않으면 rollback 해야해요 
		if(count == 1&& list ! = null) {
			con.commit();
		} catch(SQLException e ) {
			e.printStackTrace();
		}
	} else {
		try {
			con.rollback();
			
		} catch() {
			
		}
		return list;
	}

}






