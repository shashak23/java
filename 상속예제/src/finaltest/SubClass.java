package finaltest;

//java finaltest.SubClass => 실행합니다?!
//제일 먼저 Class에 대한 정보가 method area에 올라갑니다?!  1)
//method area에 class의 정보가 잘 저장되면 그 다음에는 당연히 프로그램 시작 포인트에서
//main을 찾습니다?! main이 시작되니 10번 코드가 출력되고 main에 있는 코드가 한줄씩 처리를 해야죠? 
//

public class SubClass extends SuperClass {   //subclass에 대한 걸 올리려고 했는데 먼저 superclass를 기반으로 만들어지니까 걔를 정보를 올려요~?!
	
	//생성자
	public SubClass( ) {
		super(100);                 // 4)상위클래스의 객체가 먼저 만들어져야 하니까 생성자가 먼저 노출되어야 해요?! 이게 안붙으면 자동으로 super(100)가 붙어요 생성자릐 첫 줄은 this 아니면 super가 나온다. 둘 중에 하나가 나옴.
		staticCall("8번 문장입니다.");
		super.myFunc();  //현재 작업하는 객체의 상위 클래스(타입), 여기서 상위 타입=super class
		                 //super가 가지고 있는 myFunc를 표출해야해요?!
		                 //그건 super에 myFunc에 5번이라고 되어 있어요!
	}
	
	//필드
	int c = staticCall("6번 문장입니다.");   //staticCall은 상위클래스에 있음
	static int d = staticCall("7번 문장입니다.");
	//static이라는 공간이 만들어져서 int d를 넣어요.
	//요 값을 넣어주기 위해서 실행이 된 결과가 7번임.
	
	//메소드
	//overriding을 할 거에요 메소드 상속받을 것을 다시 작성할거에요.
	//overriding을 한다는 게 내용을 복사해서 밑에 하는 행동을 바꾸는 거잖아요? 복붙은 안좋은 습관이니 가능한한
	//source > override > myFunc를 선택하세요. 
	//super.myFync(); 를 지우세요
	@Override //annotation 이라고 해요
	public void myFunc() {
		System.out.println("9번 문장입니다.");
		//아래의 Override 된 마지막 myFunc을 따라가니? 9번이 나옵니다?!!
	}
	public static void main(String[] args) {
		System.out.println("10번 문장입니다.");
		SuperClass obj = new SubClass();       //하위클래스의 인스턴스를 구하는 거니까. 인스턴스를 만드는 것. 2)
		//obj 객체를 참조하는 참조 변수 -> 결국 해쉬값이 들어온다. 
		//지시에 따라서 main 공간이 생긴 stack에서 main내부에 obj 공간이 생김
		//여기서 객체를 만들고 초기화 해! 그 작업을 같이 하기 위해 생성자로 움직여요 3)
		//myFunc까지 답을 찾았다면 여기까지 작업이 끝난거에요?!! 
		obj.myFunc();         //오브젝트가 마이펑크를 호출했어요? 근데 코드를 보니? 
		//내 코드가 override되어 있어요? subclass에도 포함되면서? 
		//heap의 subclass에 myFunc가 생기는 거에요?!! => 동적 마인딩
	}
	
 // 객체가 이런 과정을 통해 만들어진다는 것을 다시 해보고 기억해두세요~!
}
