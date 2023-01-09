package library.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.service.BookSearchService;
import library.vo.BookVO;


public class BookSearchController {
	public ObservableList<BookVO> getResult(String isbn) {
		BookSearchService service = new BookSearchService();
		
		ObservableList<BookVO> list = FXCollections.observableArrayList();
		List<BookVO> list_ = 
				service.selectBookByISBNBookVO(isbn);
		
		for (BookVO book : list_) {
			list.add(book);
		}
		return list;
	}


}
