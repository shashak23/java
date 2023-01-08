package lecture.jdbc.service;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
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
		BookDAO dao = new BookDAO();
		ObservableList<BookVO> list = dao.select(text);
		
		return list;
	}

	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		// 로직처리해야해요!
		// DB처리만 있어요!
		BookDAO dao = new BookDAO();
		
		int count = dao.delete(deleteISBN);
		ObservableList<BookVO> list = dao.select(searchKeyword);
		
		return list;
	}

}






