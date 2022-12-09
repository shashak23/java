package com.test;

import java.util.ArrayList;

public class ArrayListTest {
	public static void main(String[] args)
	{
		//ArrayList 하나를 생성해보자.
		ArrayList<Object> a = new ArrayList();  //ctrl+shift+o(영어) 하면 자동으로 생성됨.
		a.add("Hello");                 //첫번째 객체 추가
		a.add(new Student("홍길동",20));  //저 객체가 두번째로 추가, Heap에 Student instance의 공간이 생기고 홍길동, 20 삽입
		a.add(new Integer(100));        //숫자인 100을 넣고 싶다면, 그냥 100이 아니고 Error는 아니지만
		                                //int 100에 대응하는Integer Class의 100을 넣는다. Integer(100)
		              //객체화를 시켜야하는데 그러기위해서는 Integer(100)이라는 객체화를 시켜야한다
		              //결국 a.add(100) => a.add(new Integer(100)) 자동으로 바뀜 => Auto Boxing
		a.add(100);   //auto boxing이 발생하는 경우
		a.add(3.14);  //auto boxing을 사용하고 double class를 사용
		//빨간색 밑줄은 코드 에러
		//노란색 밑줄은 warning ^^........제대로 쓰자?!
		
		//위의 코드들의 int를 없애려면 어떻게 해야할까? type - Object로 잡아야하고 <>사용 : <Object>
		//정수는 Object로부터 상속받았으니까 type도 Object에서 받음 why? IS - A 관계니까~!
		
		ArrayList b = new ArrayList();  //노란색 밑줄은 여기서 더 정석대로 하라는 말....
		ArrayList<String> b = new ArrayList(); // 문자열만 집어 넣는다?! -> <> 사용
		b.add("Hello");
		b.add("홍길동");                    //Heap에 String pool에서 사용
		b.add(new String("소리없는 아우성!"));  //Heap에 ArrayList에서 사용
		b.add(100); //int가 들어올 수 없기에 error로 됨
		//이런 케이스가 많아요~ 나중에는 싹다 이런 것만 써요~ 
	
	}
	

}
