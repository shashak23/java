package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookSearchByKeywordController {

	public ObservableList<BookVO> getResult(String text) {
		// controller의 역할은 view와 service를 연결
		// service 객체가 잇어야지 일을 시키겠죠?
		BookService service = new BookService(); // 로직처리하는 애한테 시키는 거얌
		ObservableList<BookVO> list = 
				service.selectBooksByKeyword(text);
		// 서비스 객체를 만든 다음에 지금 매소드 객체를 실행했어요. 트랜젝션이 일의 최소단위인데 그걸 설정하는 개념적인 단위가 서비스 메소드임!
		// 그렇게 우리는 이 메소드를 설정해야하고 이름 자체가 트랜젝션을 지칭하는 이름이 되어야한다
		// 컨트롤러가 서비스한테 일을 시켜서 그 결과를 얘가 받아는데
        //운하던 것이 나오면 화면에 나오고 동시에 null로 돌아간다 ? 컨트롤러가 중간 다리 역할을 한다?
		
		return list;
		// 가져와서 다 끝나면 리스트 돌려보내요~!
		
	}

	

}
