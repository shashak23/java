package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class DeleteByISBNController {

	public ObservableList<BookVO> getResult(String deleteISBN, String searchKeyword) {
		
		// service를 이용해서 로직처리를 해야 해요!
		BookService service = new BookService();
		ObservableList<BookVO> list = 
				service.deleteByISBN(deleteISBN, searchKeyword);
		return list;
	}

}
