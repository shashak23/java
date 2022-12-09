package com.test;

import java.util.HashMap;

public class MapTest {
	public static void main(String[] args) {
		
		HashMap<String,String> map = new HashMap<String,String>();  
		//key와 value가 같이 들어가야하니까 둘다 스트링에 넣어요
		//Map에 데이터를 저장할 때
		map.put("123", "Hello");   //key값에는 123, 벨류에는 hello
		//실제 위치는 위가 아니어도 상관없어요
		map.put("4", "안녕!!"); //간단해욧 -> 이거는 맵에 데이터를 저장할 때
		map.put("kk", "홍길동");
		map.put("aa", "신사임당");
		map.put("123", "소리없는 아우성!");  //->에러인가? 열쇠가 똑같은 게 두개가 생겨버림?
		//그래서 밑에 쓴 걸로 대체가 됨!!!
		
		//Map에서 데이터를 추출할 때
		//key를 알아야지 할 수 있으니까
		System.out.println(map.get("123"));   //이걸로 확인해보자구요?
		map.get("123");
		
		//Key만 갖고 있는 Set을 이용해서 Key값만 나오게 하는 코드, 첫 번째
		Set s = map.keySet();    //map이 갖고 있는 데이터로 set을 만드는 겁니다
		//여기서 Map과 Set은 상속관계여서 Set과는 달라요~~ 
		for(String a : s) {
			System.out.println(a);   //key값만 나오게 하는 코드, 두 번째
		}
 	}

}
