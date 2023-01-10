package library.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.service.BookSearchService;
import library.vo.BookVO;


public class BookSearchController {
	public ObservableList<BookVO> getResult(String search) {
		BookSearchService service = new BookSearchService();
		
		ObservableList<BookVO> list = FXCollections.observableArrayList();
		ObservableList<BookVO> book = service.selectBookByBookVO(search);
		//		List<BookVO> list_ = 
//				service.selectBookByISBNBookVO(search);
//		
//		for (BookVO book : list) {
//			list.add(book);
//		}
		return book;
	}


}
