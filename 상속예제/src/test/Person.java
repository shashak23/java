package test;                       // 안드로이드나 웹 프로젝트 내용
//import java.lang.*;                 // 기본적으로 생략이 가능한데 내가 안쓰면 자바가 알아서 넣음

public class Person {
	//★★★★ 현재 class인 상위 class인 Object class를 호출하는 것이야 
	//Object()가 실행이 됩니다?!
	//호출을 끝냈으면 자신의 초기화를 진행해야해요~?!
	//그래서 자신의 공간을 만들고
	//자신의 호출을 Student에서 받아서 person의 초기화를 해요!
		
	//★Student 메인에서 찾기 위해 왔는데 펄슨이 오브젝트를 이용해서 확장했데?!extends Object 그럼 오브젝트 가야지
	//그럼 instance에 Object클래스가 heap에 만들어 짐. 
	//객체 만들려면 생성자를 만들어서 초기화해야하니까
	//원래는 펄슨을 만들러 왔다가 오브젝트가 있으니까 오브젝트를 먼저 만들었으
	//오브젝트를 만들었으면 펄슨을 만들겠쥬?
	//이때 person이 object를 감싼 형태로 heap에 생겨요.
	//감싼 형태에서 name공간, age공간, gender공간이 생긴다.
	//만들어지면 뭐를 호출해? 생성자를 호출을 해야지 인스턴스를 만들어지고 초기화를 한다.
	//그러면 상위 클래스는 완성.
//extends java.long.Object : 무조건 붙는 것         
//테스트 네임을 잘 지어야해요, 대전제{java의 모든 class는 Object를 상속하고 있다.}
	// 1. 생성자
	public Person() {        //인자도 받아들이지 않고 하는 일도 없는 constructor, ()안에 내용이 없으니까
		super();
		//★★★★ 상위 Object class를 호출하는 것이야 
		//Object()가 실행이 됩니다?!
		//호출을 끝냈으면 자신의 초기화를 진행해야해요~?!
		//그래서 자신의 공간을 만들고
		//자신의 호출을 Student에서 받아서 person의 초기화를 해요!
				System.out.println("Person의 생성자가 호출!!");
	}
	// 2. 필드  //사람이니까 일반적인 사람의 특성들을 변수화해서 넣어주기
	private String name;         //데이터를 보호+참조 변수+이름
	private String gender;       //데이터를 보호+참조 변수+성별
	private int age;             //데이터를 보호+기본,원시 변수+나이
	
	
	//데이터를 함부로 바꾸거나 맘대로 헨들링을 하는 일은 사전에 막아야하기 때문에 특별한 경우가 아니고서는 private
	
	// 3. 메소드  // business logic method , 하는 일이 필드를 핸들링 하는 역할
	public void eat() {                   //외부에서도 메소드를 쓸 수 있도록, 여기서 외부는 같은 사내
		System.out.println("밥을 먹어요."); 
	}

}

//별도로 만들 수 있고, 아니면 아예 다른 class를 만들 수 있다.

