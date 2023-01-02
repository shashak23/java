package test;

public class ThisTest extends Object {

	//생성자
	//default 생성자는 항상 써주세요.
	public ThisTest() {
		super();                 //super대신에 this(); = 현재 클래스에 다른 생성자를 호출할 때 씀 3)this();
		                        //그래서 밑에 생성자를 보게 됩니다. 내가 가지고 있는 또 다른 생성자를 보자~
	}
	
	//생성자 overloading으로 생성자를 하나 더 만들어요.
	public ThisTest(int k) {
		super();                                //여기까지가 2) 정상적인 클래스 형태
		System.out.println("인자가 하나 있는 생성");
		
	}
	
	//필드와 메소드는 없는 유형
	public static void main(String[] args) {
		ThisTest a = new ThisTest();
	}
	//위의 생성자 둘 중에 하나를 사용해서 밑에를 쓰자. 여기까지 1)

}
