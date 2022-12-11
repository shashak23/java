package sampleproj;

//공유객체
class Shared {  //처리해야하는 메소드
	public void printName() {  //싱크로나이즈드로 코드를 블럭을 다시 만들어
		synchronized(this) {   //싱크로나이즈 기본
			try {
				for(int i=0; i<10; i++) {
				Thread.sleep(1000);
				notify();
				System.out.println(Thread.currentThread().getName());
				wait();
				}
			} catch (Exception e) {
				
			}			
		}

	}
	
}

//스레드 이름
class ThreadEx_13_1 implements Runnable {   //쓰레드는 러너블을 주로 사용하고요
	public ThreadEx_13_1() {
		
	}
	
	public ThreadEx_13_1(Shared shared) {
		this.shared = shared;                  //쉐어드 연결
		
	}
	
	@Override
		public void run() {      //main이 run에 함수가 실행이 됨, 내용이 없어요, 코드=중괄호, 초괄호=메소드
			 shared.printName(); //혜화역.교육센터. 위의 메소드를 통째로 불러올거고, 소괄호로 해야지 실행이 되요
			 
		}
	private Shared shared;
}

public class ThreadEx_13 {                   //쓰레드 이름!
	public static void main(String[] args) {  //자바를 실행하는 메소드
		Shared shared = new Shared();          //노란색-선언해주세요
		
		Thread t1 = new Thread(new ThreadEx_13_1(shared), "첫번째 쓰레드"); // 첫번째 쓰레드 이름
		Thread t2 = new Thread(new ThreadEx_13_1(shared), "두번째 쓰레드"); // 두번째 쓰레드 이름, (shared)를 선언해야 연결해서 사용됩니다.
		//러너블의 런 메소드 안에서 공용객체를 사용해야하기때문에 스레드13_1생성할 때 매개 변수로 공용객체를 넘겨줬다 -> 그래야 클래스 안에서 공용객체를 사용할 수 있다.
		
		t1.start();
		t2.start();
	}
}
