package lecture.jdbc.service;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.vo.BookVO;

//책에 대한 비즈니스 로직을 처리를 담당 클래스 = logic 담당 class

public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		// 검색 키워드를 받아서 ObservableList<BookVO>을 리턴하는 하나의 작업을 하기 위해서 로직처리가 필요함(더하고 뺴고, for, of) 
		// 당연히 데이터 베이스 처리가 필요함
		// 지금으로써는 로직이란게 없어서 현재로써는 쓸 게 없음...
		// 우리문제는 워낙 간단한 것이라 단순히 데이터베이스 테이블 뒤져서
		// 결과를 가져오면 끝나고
		// 데이터 베이스를 처리하는데 여기에서 처리하면 안되고
		// DAO한테 시켜요 ! dao는 데이터베이스를 전문적으로 처리해요 
		BookDAO dao = new BookDAO();
		
		ObservableList<BookVO> list = dao.select(text); // dao는 비즈니스 관련된 내용이 나와서는 안되고, 코드들이 있어도 모으는 건 select가 해요!
		//dad가 text키워드를 가지고 검색해서 list를 가져올거에요 
		
		return list;
	}
    // service는 메소드를 추가시켜나가는 겁니다.
	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		// 로직처리해야해요! 그렇게 할 게 없어요? 왜? 지금은 db처리만 있어요
		BookDAO dao = new BookDAO();
		int count = dao.delete(deleteISBN); // 중복될 일 없이 해보기 
		ObservableList<BookVO> list = dao.select(deleteISBN);
		return list;
	}

}






