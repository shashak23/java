package src.lecture.jdbc.controller;

import javafx.collections.ObservableList;
import src.lecture.jdbc.service.BookService;
import src.lecture.jdbc.vo.BookVO;

public class DeleteByISBNController {

	public ObservableList<BookVO> getResult(String deleteISBN, String searchKeyword) {
		BookService service = new BookService();
		ObservableList<BookVO> list = 
				service.deleteByISBN(deleteISBN, searchKeyword);
		return list;
	}
}
