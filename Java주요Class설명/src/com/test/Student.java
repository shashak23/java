package com.test;
import java.lang.*; //밑에 오브젝트에 대한 중복을 피하겠다는 "기본으로 삽입되어야 하는 표현"

public class Student {            
	//constructors
	public Student() {  //인자가 없는 생성자는 항상 필수.

	}
	//field
	private String Name;
	private int Age;
	public String getName() {
		return Name;
	}
	
	//getter&setter
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}

	public Student(String name, int age) {  //overloading, 초기화하는 일반적인 생산자
		super();
		Name = name;
		Age = age;
	}	
	//method

	@Override
	public boolean equals(Object obj) {
		//s1과 s2가 따로 있지만 실제 내용이 같은 경우 ex. s1 = "홍길동", 20 / s2 = "홍길동", 20
		
		
		
		//equals method의 기능을 재정의 할 수 있음!
		//객체의 instance에 이름과 나이가 같으면 같은 것이라고 새로 만들거에요.
		//equals를 호출하는 클래스는 Student 클래스이고, Object가 상위 타입이고
		//여기서 obj는 field에 있는 인자?값을 갖지 않아용? -> obj을 먼저 변경해야함
		//Student가 상속하는 Object의 obj를 성격을 바꿔서 아래와 같이 씀
		// **같다라는 의미를 내가 원하는 대로 바꾸고 싶다면~!
		Student target = (Student)obj;       //원하는 값을 해당 클래스로 끌고 오고
		boolean result = false;              //불리언을 써서 결과가 false가 되도록
		if((this.Name.equals(target.Name))&&      //if문을 사용하여 Name 과 Age가 같은 값이라고 지정
		   (this.Age == target.Age)) {       //결과 같이 true가 되도록 유인
			     result = true;              //실행시키면 s1과 s2가 true가 됨.
			                                 //String은 불변이자 내용의 변화 없이 추가 또는 약간의 변형이 되는 하는 것이기에
			                              // ==은 숫자를 비교할 때 하는 것이고, String에는 equals를 쓴다.
			
		}
		return result;
		
	}

	@Override
	public String toString() {
		return this.Name + " , "+this.Age; //비교가 끝났으면 overriding의 string을 해서 값을 표출
		//위와 같이 넣어서 표출한다~!
	}
	
	

}
