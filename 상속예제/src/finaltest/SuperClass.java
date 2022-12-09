package finaltest;

public class SuperClass extends Object{
	//생성자
	public SuperClass() {               //인자가 없는 생성자는 꼭 만들어두세요
		//super(); 눈에는 안보이지만 super가 들어간 것임. 나는 안썼지만 자동으로 들어왔고.
		//상위 클래스의 instance 생성부분
		//나를 만들기 이전에 object로 먼저 가는 것임?!
		//우리 객체의 초기화 진행 (먼저 공간이 만들어져야해요?!!) 그걸 가지고 변수의 값을 갖고 초기화가 진행될 수 있당.
		//인스턴스가 메모리에 공간을 확보(필드를 위한 공간을 만들어져요. int a 는 인스턴스가 만들어져야 생기는 공간이니까 heap의 super에 등록)
		//공간이 만들어지기때문에 1번이 출력이 됩니다?! 6)
		staticCall("3번 문장입니다.");
	}
	//생성자 overloading으로 또 다른 생성자를 하나 정의해요
	public SuperClass(int i) {                        //위와 무언가 조금이라도 달라야 쓸 수 있겠쬬?
		this();                                       //현재 클래스에 다른 생성자 중에서 ()인자가 없는 것을 호출한다. -> public SuperClass()
		staticCall("4번 문장입니다.");
		//5) 숫자를 받아들이는 생성자로 int i를 봐야하는데?!
		//생성자의 첫 줄에 this아니면 super가 나와야하는데? this()가 잇어요?!
		//우리 클래스의 객체를 만들려면 object의 개체를 만들고 와야하는데?
		//this : 내가 가진 클래스의 this에 가봐? -> 인자가 없는 SuperClass로 가야해요?!!!
		
	}
	//this(); 의 문제가 발생함. 이전에는 super(100);이라는 subclass가 있었는데 그게 super에서 보니 int i 라고 있긴한데
	//코드가 this(); 라고 인자값이 또 없어...
	//근데 생성자라는 건 공간이 미리 만들어져야지 초기화(처음값으로 돌아가는)를 할 수 있다는 거니까
	//필드를 만들 수 있으니까 필드의 int a 1번문장을 따라가서 10번 다음에 1번이 오는 거임!!!!!
	
	
	//필드
	int a = staticCall("1번 문장입니다.");  //메소드 중 staticCall을 호출하는 것, 실행하세요 근데 밑에서 return 100이라고 했으니까
	//int a = 100;                       //return의 의미, 결국 위 문장과 아래는 같은 것이다. 둘 중 하나 쓰면 되요.
	//여기서 a라는 필드는 인스턴스가 생성되어야 공간이 실제로 만들어지고 사용할 수 있어요.
	//그래서 a는 인스턴스가 있어야지 사용할 수 있는 변수라 해서 'instance variable', 만들어지는 공간은 heap
	static int b = staticCall("2번 문장입니다.");
	        //static이 공간이 만들어지니까 int b가 실행이 됨
			//b라는 필드는 instance가 없어도 사용이 가능 why? b의 공간이 method area에 만들어지기 때문에
			//이러한 필드를 우리는 class variable이라고 불러요. 만들어 지는 공간은 method area입니다.
	
	//메소드
	//method는 일반적으로 public을 지정해요
	//method의 결과값을 치런한다는 의미는 메소드를 호출한 곳으로 값을 대치한다는 것
	public static int staticCall(String msg) {  //객체가 없어도 쓸 수 있습니다~ 인스턴스 없이도, 뭘 갖고? 클래스 이름을 갖고
		System.out.println(msg);
		return 100;                //이 메소드 전체가 돌아가는 값이 있는 int(인티지)여서 <-> void면 return을 안해도 됨
	}
	public void myFunc() {
		System.out.println("5번 문장입니다.");
	}
 // private 나 getter, setter가 없는 문제입니다...넘 복잡해지니까....
}
