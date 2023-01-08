package library.controller;

import javafx.collections.ObservableList;
import library.service.BookSearchService;
import library.vo.BookVO;


public class BookSearchController {
	public ObservableList<BookVO> getResult(String text) {
		BookSearchService service = new BookSearchService();
		ObservableList<BookVO> list = 
				service.selectBooksByKeyword(text);
		
		return list;
	}


}
