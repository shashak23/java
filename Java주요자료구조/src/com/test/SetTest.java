package com.test;

import java.util.HashSet;  //항상 넣어줘야해요!

public class SetTest {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();         //안에 들어가는 데이터 타입이 무엇이냐에 따라
		
		//set안에 저장하기
		set.add("123");
		set.add("홍길동");
		set.add("신사임당");
		set.add("Hello");
		
		//set안에 저장된 데이터를 꺼내려면 어떻게 해야하나요?
		//문제점1. set은 순서가 없음 -> 순서대로 찾을 수 없음
		//문제점2. set은 key가 없음 -> key를 이용해서 데이터를 찾을 수도 없음
		//결론. 하나씩 꺼내야 함
		//for를 이용해서 주머니에서 하나씩 꺼내야함
		for(String s : set) {    //이 집합구조에서 하나씩 꺼내세요 라는 코드
			System.out.println(s);  //s = set인 데이터를 추출할 수 있어요~
			//set은 중복이 배제되어 있어 확실히 유니크하당!
			//결과가 뜨는 표현도 순서가 없음!
		}
	}

}
