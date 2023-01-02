package com.test;

class MyClass extends Exception {              //exception이라고 불리는 모든 상황의 최상위 크래스
	
}

public class CustomExceptionTest {
	public static void main(String[] args)throws Exception {
		System.out.println("시작");
			throw new MyClass(); 
		//System.out.println("끝");   //실행이 안됐다는 표시에요            
		//이렇게 하면 시작과 끝이 그냥 나와요~
		//객체를 만드는 것과 exception을 발생시키는 것은 다르다
		//이런 exception 객체를 던져야 해요? -> 던지는 키워드가 던지는 용어 throw라고해서....
		//객체만 있다고 해서 exception이 발생하는 것이 아닌
		//내부적으로 throw가 있어야하는데 결국 이게 exception이니까 고치기 위해 try와 catch를 넣고
		
		//과연 수많은 exception을 다 try catch를 많이 해서 걍 try catch가 천지에요
		//try catch를 안쓰면 문제가 생기는 강제코드이다보니까
		//내 java에는 try catch를 만들다가 끝나는 경우도 많아요....?
				
	}

}
