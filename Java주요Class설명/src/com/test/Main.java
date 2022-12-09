package com.test;

public class Main {
	public static void main(String[] args) {
		Student s1 = new Student("홍길동", 20);
		Student s2 = new Student("홍길동", 20);
		//두 객체가 같은 객체인지 알아보고 싶다? -> 같은 객체인가?
		//1. 두 객체의 instance가 같은 메모리 공간을 공유하고 있는가?
		//2. instance의 내용이 같은가? => 둘 중 하나가 해당되면 '같은 객체'이다.
		//이 경우는 실제로 s1과 s2가 값이 다른 경우
		// ex. s1 = "홍길동", 2  / s2 = "신사임당", 30
		
		//일반적으로 똑같은가? -> 이 연산자는 '==' => 비교연산자
        //같으면 true, 다르면 false 라는 논리값으로 결과가 리턴된다.
		System.out.println(s1 == s2); //false로 나오겠죠?
		System.out.println(s1.equals(s2));  //false
		//기본적으로 equals는 s1과 s2과 같은 것이다 비교하는 역할!!
	    System.out.println(s1.toString());  //s1이라는 값을 일단 문자열로 바꿔야겠다라고 해서 toString로 바꿈
	    //시작주소의 해쉬값(주소값)을 내가 어떻게 바꿀건지 설정하는 것.
			
	}

}
