package com.test4;
import java.util.ArrayList;

public class ShapeTest {
	public static void main(String[] args) {

		//ArrayList
		ArrayList<Shape> list = new ArrayList<Shape>();   //<>안에 들어갈 것!
		//Shape 으로 해야 모든 도형이 들어오겠죠? Shape으로 타입을 때려 넣어야해요.
		list.add(new Rectangle(4, 7, 4));  //순서가 width - x - y 
		list.add(new Rectangle(5, 4, 6));
		//list.add(new Circle(6, 7, 6));
		//list.add(new Circle(9, 10, 9));
		
		//for문을 사용하여 정리 -> result가 찍히도록 만들기
		//상위 클래스 또는 하위 클래스에서 override하는 방법 중 '하위(Circle, Rectangle)'사용
		//요런거 판단하는 게 어려워요~
		for(Shape s : list) {
			System.out.println(s);  //동적바인딩에 의해 만들어진 하위에서 오버라이딩 한 것을 호출합니다
		}
		
		for(Shape s : list) {
			((Movable)s).move(10,10);   //일단 movable로 바꾼 다음에 s를 실행해라
			                            //괄호가 s에서 빠지면 실행 먼저하고 movable로 바꾸세요 가 되서 괄호유무가 중요함
		} //동적바인딩이 일어나서 move까지 호출할 수 잇음
		for(Shape s : list) {
			System.out.println(s);  //동적바인딩에 의해 만들어진 하위에서 오버라이딩 한 것을 호출합니다
		}
		//출력까지 다시 한 번 얏호 무슨 말인지 모르겠어 ^-^

		//move 함수 이용, 타입 변환해서 move 하는 거 하기...? 
		
		// 맵구조, set, get 꼭 알아둬야해요!
		// 이셉션???? 까지 내일 얘기하면 자바 기본입니다.
		
		
	}
	
}