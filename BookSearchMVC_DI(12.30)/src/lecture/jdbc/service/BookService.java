package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.vo.BookVO;

// 이놈이 로직담당하는 class
public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		// 검색 키워드를 받아서 ObservableList<BookVO>를 리턴하는 하나의
		// 작업(transaction)을 처리.
		// 이 작업을 하기 위해서.. 로직처리가 필요하고 (더하고 빼고, for, if)
		// 당연히 데이터베이스 처리가 필요!
		// 그런데 우리 문제는 워낙 간단해요. 그냥 단순히 데이터베이스 테이블 뒤져서..
		// 결과 가져오면 끝나요!
		// 그럼..데이터베이스 처리를 해 보아요!
		// 여기에서 데이터베이스 처리하면 안되요!
		// 그럼 누구한테 시키나요? => DAO(Data Access Object)한테 시켜요!
		Connection con = null;
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
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

	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		// 로직처리해야해요!
		// DB처리만 있어요!
		
		// 여기서부터 Transaction 시작이예요!!!
		// Connection에 대해서 setAutocommit()을 false로 지정해야 Transaction이 시작!
		
		// 여기에서 Database Connection을 얻어와야해요!
		Connection con = null;
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
			con.setAutoCommit(false);  // transaction 시작!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
		
		int count = dao.delete(deleteISBN);
		ObservableList<BookVO> list = dao.select(searchKeyword);
		
		// 여기가 Transaction의 끝이예요!!
		// 서비스의 메소드 맨 마지막에 이 메소드가 잘 처리되었으면
		// 이 로직코드는 당연히 우리가 처리해 줘야 해요!
		// transaction을 commit해야 하고 그렇지 않으면 rollback해야 해요!
		
		if(count == 1 && list != null) {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}






