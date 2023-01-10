package library.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javafx.collections.ObservableList;
import library.dao.BookDAO;
import library.vo.BookVO;


public class BookSearchService {

	public ObservableList<BookVO> selectBookByBookVO(String text) {
		
		ObservableList<BookVO> list = null;

		// session은 Service에 있어야 해요
		// 왜냐면 DAO가 실행될 때
		// select
		// udate 실행 되면
		// 연결은 한번만 해야 효율적이니까
		// 조회는 데이터의 변경이 일어나는게 아니라서
		// 딱히 커밋이 필요 없어요
		// update나 insert가 실행될때는 commit이 무조건 있어야 해요
		    SqlSessionFactory factory = 
		    		MyBatisConnectionFactory.getSqlSessionFactory();
			SqlSession session = factory.openSession();
			session.close();
			// con 의 역할을 session이 해주고 있어요
		    BookDAO dao = new BookDAO(session); // dao한테 factory를 주입하기 
			list = dao.selectList(text); // list는 text를 selectOne해라
			// 그리고 반환해라 
	    
		return list;
	
	}

}






