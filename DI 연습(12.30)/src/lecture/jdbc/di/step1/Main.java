package lecture.jdbc.di.step1;


// 여기서 클래스가 시작이 되도록 생성한 더미 클래스 
public class Main {
	public static void main(String[] args) {
		User user = new User("hong","홍길동","1234"); //간단하게 vo만들수 o
		
		//db처리하기 위해서 
		UserDAO dao = new UserDAO();
		
		dao.insert(user); //dao한테 vo를 넘겨서 처리를 하도록
		
		System.out.println("새로운 사용자 등록");
		
		//이 id를 가진 사람을 검색하세요! 
		User user2 = dao.select("hong");
		//나한테 리턴되는데 홍길동의 
 		System.out.println(user2.getName() + " , " + user2.getPassword());
 		System.out.println("조회 성공");
 		
	}

}
