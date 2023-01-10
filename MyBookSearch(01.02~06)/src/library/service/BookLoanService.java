package library.service;

import javafx.collections.ObservableList;
import library.dao.BookDAO;
import library.vo.BookVO;

public class BookLoanService {
	public ObservableList<BookVO> selectBooksByKeyword(String text) {
//		System.out.println("@check");
		BookDAO dao = new BookDAO();
		ObservableList<HashMap<BookVO>> list = dao.selectAllHashMap();
		
		return list;
	}

}
