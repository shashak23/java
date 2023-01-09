package library.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import library.dao.BookDAO;
import library.vo.BookVO;


public class BookSearchService {

	public List<BookVO> selectBookByISBNBookVO(String isbn) {
		
		 List<BookVO> list = null;

		
		    SqlSessionFactory factory = 
		    		MyBatisConnectionFactory.getSqlSessionFactory();
		    BookDAO dao = new BookDAO(factory); // dao한테 factory를 주입하기 
			list = dao.selectBookByISBNBookVO(isbn); // list는 text를 selectOne해라
			// 그리고 반환해라 
	
		return list;
	
	}

}






