package lecture.jdbc.di.step6;

// 객체를 만들어서 객체간의 주고 받을 수 있도록 하는 것 
// 여기서 클래스가 시작이 되도록 생성한 더미 클래스 
public class Main {
	public static void main(String[] args) {
		User user = new User("hong","홍길동","1234"); //간단하게 vo만들수 
		
		ConnectionMaker cm = new KosaConnectionMaker();
		//db처리하기 위해서 
		UserDAO dao = new UserDAO(cm);  // 추상클래스 = 실제클래스 // 클래스간에 의존관계를 사라지지만 객체의존성을 주입해요!!!!!! DI개념!
		
		dao.insert(user); //dao한테 vo를 넘겨서 처리를 하도록
		
		System.out.println("새로운 사용자 등록!");
		
		User user2 = dao.select("hong");
		
		System.out.println(user2.getName() + ", " + user2.getPassword());
		System.out.println("조회 성공!");
		
	}

}
