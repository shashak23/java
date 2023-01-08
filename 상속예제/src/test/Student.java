package test;

// java test.Student => 실행
// java test.Student = 1. method area에 class정보를 올려요.
// 2. student class 안에서 main 메소드을 찾아라!
// 3. 왜? Student를 실행해야하니까
// 없으면? => Error 입니다.
// 있으면? = JVM에 의해 호출 됨. 메인은 메소드고 메소드를 사용하려면 객체가 있어야해. public static main void 가 고정으로 있어야함.
// 예외. 메소드가 없이도 호출되려면 스테틱을 쓴다. JV M 에 의해서 바로 실행이 된다.
// 비로소 method가 호출됐다면 stack에 메인메소드를 위한 공간을 할당함.

// 내가 student라는 클래스를 이용하니까 클래스에 대한 정보가 메소드 area에 올라가죠
// 그럼 올라가면 student 라는 class를 코딩을 보면? -> 어라 이거 상속받은거네?(컴퓨터가 확인)
// 확인이 됐다면 Student와 Person 클래스가 메소드 area에 확인되면서
// Person 내용이 메소드 area에 의해 Student에 올라간다.
// 비로소 Student에 대한 내용을 알 수 있는 것

// 객체지향의 상속을 이용해서 class를 다시 만들거야. 그냥 만드는게 아니고 확장해서 만들거야.
// java에서 extends라는 keyword를 제공한다.
// 기존 class명 뒤에 extends를 붙이고
// extends뒤에 class가 나올 수 있는데 java는 이때 단 하나의 class만 기입할 수 있어요.
// 이말인즉슨, extends를 기준으로 앞 뒤 중에 하나만 선택?
// 그래서 extends뒤에 ,를 지원하지 않는다. , 를 넣고 지원,지원 해주는 것이 있는데 자바에서는 지원하지 않아요~!!
// 자바는 단일상속을 지원한다.
// 단일상속이니까 복수가 아닌 하나가 하나를 연결하는 구조로 가지치기를 한다면 -> 상속트리 Inheritance Tree
public class Student extends Person {                //person을 이용해서 student를 정의(확장)하겠어!
	                                                 //person에 있는 모든 내용이 student에 포함된다.
	                                                 //필드와 메소드에 중복되는 내용을 없앤다.
	                                                 //class에 대한 정보가 method가 있어야한다.
	                                                 //student class를 보고 싶어도 person class가 있어야한다. 기입됨.                

	// 1. 생성자  // 리턴타입이 없으니까 void가 안되서 코드오류가 되니까 생성자에서는 상속이 되지 못한다.
	public Student() {
	//상위 클래스의 constructors 호출 ★★★★  => super();
		//생성자의 첫번째(바로 밑)는 무조건 super가 나와야 해요. -> 내가 안넣으면 자동으로 만들어져요.
		//super는 상위 생성자를 호출하는 코드.
		//person() super가 인자가 없는 코드니까 person도 인자가 없는 방식으로 똑같이 간다.
		//super()는 상위 클래스를 호출하기 위한 지칭코드이고 값을 넣으면 그에 따라 달라진다.
	
	//다시 person class의 생성자로 가요.

		//현재 객체의 field를 초기화하는 부분(별3개까지 배운 내용)
		
	}
	// 2. 필드
	//private라는 뜻이 그것이 쓰여지는 클래스 안에서'만' 바꿀 수 있다는 것인데, 다른 클래스에 따라가면 의미가 맞지 않아서 안됨.
	private String dept;           //데이터보호+문자형+학과
	
	
	// 3. 메소드   , 학생이 하는 행휘
	//public void eat() {
	//	System.out.println("밥을 먹어요.");  //중복되니까 삭제 가능
	//}
	
	public void eat() {                        //상속받은 상위 메소드를 다시 하위 메소드에 재정의하는 것
		super.eat();                           //상위 타입의 메소드를 호출할 수 있어요. why? 오버라이딩에 의해 감춰지기때문에
		System.out.println("밥을 신나게 먹어요."); //eat(){}이라는 코드로 heap에 새로 생김 : method overriding ☆☆
	}
	
	public void study() {
		System.out.println("공부를 해요.");
	} // 이렇게 반복적인 내용을 계속 쓴다면 효율성이 떨어지고 좀 더 쉽게 만들 수 있는 방법에 대해 -> 상속 Inheritance
	public static void main(String[] args) {
		// Person s = new Student();   // is a 관계에 의해 Student 를 Person으로 쓸 수 있다.
		// 우리 클래스에 student를 s라는 스첵이라는 참조변수가 가리키는 것이고 변수를 만들고 스텍에 저장 
		// new 라는 키워드에 스튜던트라는 인스턴스를 만드는 것이고 이걸 클래스 타입으로 지칭하는 것 이걸 가르키는 것이 뭔가? s가 가리키는 것이 student에요라고 하는 것임
		// student에 person을 쓸 수 있어.... 만들어진 객체(new student)를 명시해주기 사항이 클래스로 바꿀 수 있어.
		// is a 관계에 의해서 이게 되고, 이게 정확하게 어떤 의미를 가지는지 제엔장 ㅠ
		// 상속을 클래스를 정의 할 때 사용하는 방식인데
		// 왜? 처음부터 맨땅에서 클래스를 새로 만들지 않고 객체 모델을 했떤 것을 확장해서 재활용해서 내가 새로운 클래스를 디파잉한다면
		// 위의 내용을 객체지향으로 자바에서 한다면 상속이며, 단일지원만 한다.
		// 여러 개의 클래스로부터 동시에 못 상속하고 상속받고 단일로 계속 상속을 내려줄수가 있기 때문에
		// 계속적으로 확장을 시켜나가보면 상속트리(계층구조)가 만들어진다.
		// 계층구조가 너무 많이 나오면 넘 복잡하니까 위와 아래의 용어도 기억해야한다. 구분할 줄 알아야한다. -> 그림을 보고도 이해해야해서.
		// 이렇게 상속하고 상속받는 관계를 IS A 관계라고 하는데 
		// 의미론적으로 이해한다면 코딩을 할 때 '상속하는'클래스-> '상속받는' 크래스 = new 상속하는 이라고 코딩할 수 있다.
	    Student s = new Student();
	    //☆ person을 가리키면 heap에 가리키는 person(object가 포함된)만 쓸 수 있음, s.study(); 사용못함, s.eat();은 사용가능
	    //타입이 바뀌면 사용이 달라짐
	    //Object s = new Student(); 라면 object의 30인 값만 가리킴, s.eat이나 s.study 사용불가 => 데이터 타입에 따라서 값이 달라진다
	    //s가 고정이 되는데 어떻게 고정이 되느냐? -> 지금 우리가 하는 것처럼 고정이 되는데...
	    // stack에 있는 값을 고정하게 됨 근데 내가 사용하고 싶은게 eat이야? 근데 못 써 내가 가리키는게 object여서
	    // 나는 지금 object s 를 가리키고 있기때문에 그 외에는 사용하지 못하는데
	    // s로는 eat() method를 호출할 수 없다
	    // 형(type)변환을 이용하면 됩니다.
	    // 형변환은 casting 작업을 통해 사용할 수 있는데 -> 어떻게? () 괄호를 이용하여!
	//    ((Person)s).eat();
	    //☆☆원래는 오브젝트 타입인데 타입을 임시로 바꾸는 것이구만? 이 객체를 이용해서 eat을 찾았구나? 데이터타입이 바뀐다? 오키? 안오키?
	    
	//    s.eat();  // 바깥에서부터 eat을 찾으니까 overriding된 것부터 보고 없으면 점점 더 안에서 찾고 이런 식
	    s.eat();    //동적 바인딩이 일어나요. dynamic binding ☆☆☆
	    //원래는 person의 eat() method를 호출해야하지만 method overriding을 이용해 다시 작성한 경우
	    //해당 객체가 method overriding된 method를 대신 호출해요! , 앞에 무슨 타입이건 상관없어, 무조건 overriding 으로 감.
	    
	    // 이렇게 되면 결국 person에 있는 eat은 호출할 방법이 없게됨. why? overriding으로 이미 도출되었기 때문에 eat은 호출하지 않고 오버라이딩이로 출력됨
	    // 이러한 문제점을 보완하기 위해 만든게 super riding임
	    
	    // Student는 class이름이면서 data type(지정된 변수안에 들어올 값에 대한 제한)
	    // new하면 객체가 만들어짐
	    // new로 객체를 만들거야 그럼 Student()으로 이동해! 
	    // 어제 배운것과 다르게 실제로는 우리가 생각하는 것처럼 객체가 바로 빵! 만들어지지 않아요..
	    // 인스턴스를 만들 때 나를 만들기 이전에 나한테 상속을 주는 객체를 만들러가요. -> 쌓아서 만들어요.
	    // 그래서 결국 person(상위 클래스)으로 갑니다★
	    //상위 클래스가 완성되면, student의 인스턴스가 만들어지고 생성자가 호출된다. => 총 3개의 인스턴스
	    //★★객체(인스턴스)가 만들어지면 address가 호출되서 이게 object는 40시작, person은 50에 시작, student는 70에 시작
	    //★★★참조변수를 지칭해서 사용하려면 힙에 있는 인스턴스 중에서 고르면 됨
	    // 여기서 new 를 하고 생성자를 호출해서 프로세스를 진행해요
	    // 그러면 new Student에서 위에 생성자의 public Student로 넘어감 ★★
	}
}
