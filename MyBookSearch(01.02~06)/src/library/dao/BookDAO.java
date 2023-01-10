package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javafx.collections.ObservableList;
import library.vo.BookVO;


public class BookDAO {
	
	private SqlSession session;

	public BookDAO(SqlSession session) {
		this.session = session;
		
	}
	// 뷰에서 컨트롤러가 나오고 컨트롤러에서 서비스로 가고 서비스에서 dao로 가는데
	// 컨트롤러에 있는 get result에다가 값응 넣어주면 순차적으로 전달되는데
	// view에 들어오는 값을 여기에 적어주는 것
	public List<BookVO> selectBookByISBNBookVO(String text) {

		List<BookVO> list = null;

	
System.out.println("이거 실행 되야 합니다");
		List<Object> book = session.selectList("library.BookVO.selectBookByISBNBookVO", text);
		System.out.println(book);
		System.out.println(list);
		session.close();
		session.commit();
//		con.close();
//		con.commit();
		return list;

				
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT bisbn, btitle, bauthor, bpage, bdate, bprice ");
//		sql.append("FROM book ");
//		sql.append("WHERE btitle like ?");
//		sql.append("ORDER BY bprice DESC"); // java라고 검색했을 때 나오는 것
//		
//		try {
//			PreparedStatement pstmt = con.prepareStatement(sql.toString());
//			
//			pstmt.setString(1, "%" + text + "%");
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			list = FXCollections.observableArrayList();
//			
//			while(rs.next()) {
//				BookVO book = new BookVO(rs.getString("bisbn"),
//						rs.getString("btitle"),
//						rs.getString("bauthor"),
//						rs.getInt("bpage"),
//						rs.getString("bdate"),
//						rs.getInt("bprice"));
//				list.add(book);
//				
//			}
//
//			rs.close();
//			pstmt.close();
//			 ㄱ
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		return list;
	}
	public ObservableList<BookVO> selectList(String text) {
		List<BookVO> list = null;
		ObservableList<BookVO> result=null;
		
		System.out.println("이거 실행 되야 합니다");
				List<Object> book = session.selectList("library.BookVO.selectBookByISBNBookVO", text);
				System.out.println(book);
				System.out.println(list);
//				session.close();
//				session.commit();
//				con.close();
//				con.commit();
				return result;
	}


}
