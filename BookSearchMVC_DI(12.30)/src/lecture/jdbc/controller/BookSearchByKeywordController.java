package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookSearchByKeywordController {

	public ObservableList<BookVO> getResult(String text) {
		// controller의 역할은 View와 service와의 연결
		// service객체가 있어야지 일을 시키겠죠??
		BookService service = new BookService();
		ObservableList<BookVO> list= 
				service.selectBooksByKeyword(text);
		
		return list;
	}

}
