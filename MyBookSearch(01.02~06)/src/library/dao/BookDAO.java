package library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import library.vo.BookVO;


public class BookDAO {
	
	private SqlSessionFactory factory;

	public BookDAO(SqlSessionFactory factory) {
		this.factory = factory;
		
	}
	// 뷰에서 컨트롤러가 나오고 컨트롤러에서 서비스로 가고 서비스에서 dao로 가는데
	// 컨트롤러에 있는 getresult에다가 값응ㄹ 넣어주면 순차적으로 전달되는데
	// view에 들어오는 값을 여기에 적어주는 것
	public List<BookVO> selectBookByISBNBookVO(String isbn) {

		List<BookVO> list = null;

		SqlSession session = factory.openSession();

		list = session.selectList("library.Book.selectBookByISBNBookVO", isbn);

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
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		return list;
	}
}
