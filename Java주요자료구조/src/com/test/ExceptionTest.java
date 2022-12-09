package com.test;                                     //프로그램에 오류를 일부러 내봅시다?!

class Customer {                                      //class를 추가해서 예시를 들어볼까요?
	public String name;
	public long balance;                              //원래는 이 클래스에 인스턴스를 만들어야지, 쟤네들을 쓸 수 있는데?
}

public class ExceptionTest {
	public static void main(String[] args) {
		System.out.println("예외상황을 발생시켜요!");
		int result = 10 / 0;                          //문법적으로는 코드에러가 없지만? 0으로 나누면 무한대가 되니까? 이상하쥬?
		
		//Customer a = new Customer();                //이렇게가 정상인데> 아래와 같이 쓴다면?
//		Customer a = null;                            //a가 지칭하는 것이 null(객체를 가리키지 않는다)
//		a.name = "홍길동";                 
//		System.out.println("여기는 출력되나요?");
		try {
			int result = 10 / 0;
		
		} catch(NullPointException e) {               //내가 잡을 객체가 null타입의 객체니? 라고 위를 본다. 
			System.out.println("널포인트 익셉션!!");      //원래는 예외상황처리코드를 써야하는데 쓸 게 없으니까 걍 한글쓸게요
			
		} catch(Exception e) {
			System.out.println("수학연산이 잘못되었어요!");  //try에서 발생된 게 어떤 것이든지 exception에서 처리하겠다
			                                          //IS-A관계가 깔려있는 것이기에 소급법처럼 위에서부터 하나씩 처리하기때문에
			                                          //잡을 수 있는 것을 먼저 보기위해 맨 마지막에 exception class에서 다 처리하겠다는 입력해서 catch하는 것
		}  finally {                                  //실제로 업무를 할 때에는 찍는게 아니라 이 문제를 해결할 수 있는 로직들이 왕왕 들어가겠쥬?
		}  //finally는 무조건 해야해요~ 
     }
}


//main이 누군가에 의해 호출이 되면
//try-catch를 여기서 안쓸려고 하면 튕겨요?
//여기에서 main 옆에 throw를 입력하면 
//throw Exception을 함께 던진다.
//그렇지만 던지는 것뿐이기에 문제가 해결되는 것은 아니다
//이런 경우에 try와 catch가 나올 필요가 없어진다.
//아 그냥 외우자 진짜 이거 그냥 오늘꺼까지 내용을 다 정리해서 외워야겠다...
//throw exception 밑에서 발생한 exception처리를 안하겠다는 것이다?
//throw는 다른 것이에요?! throw exception을 main에 넣는 것은 여러개의 exception을 한꺼번에 처리하는 것
//자세한 throw는 뒤에서 다룰게요 T^T
