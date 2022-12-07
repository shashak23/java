package samlpeproj;
//run안에서 실행이 다르게 되는 2개의 클래스를 알아보자 
class ThreadEx_05_1 extends Thread {
	@Override
	public void run() {
		
	try {
		Thread.sleep(3000); 
	} catch (InterruptedException e) {
		System.out.println("소리없는 아우성");
	}
	
		for(int i=3; i<300; i++) {
			System.out.println("-");  //"-"이렇게 하면 옆으로 계속 --이거 씀
		}
		System.out.println("<< Thread 1 종료 >>");  //위에 다 찍으면 300찍고 종료됨 나옴
	}

}

class ThreadEx_05_2 extends Thread {
	@Override
	public void run() {
		for(int i=3; i<300; i++) {
			System.out.println("ㅣ");             //이거는 위로 찍는 거에요
	}
		System.out.println("<< Thread 2 종료 >>");
	
}


public class ThreadEx_05 {
	
}
	public static void main(String[] args) {
		Thread t1 = new ThreadEx_05_1();
		Thread t2 = new ThreadEx_05_2();
		
		t1.start();  //start=시작하세요
		t2.start();
		
		try {
			t1.sleep(2000);  //코드 이슈~~ 이게 정답: Thread.sleep(2000);
			//2초동안 t1을 재우고 싶었는데, Thread.sleep(2000); 라고 써야해요^^?
			//t1.sleep(2000); 단어가 누우면 static이라서 그대로 static을 이용해요
			//실제로 의미가 Thread.sleep(2000);이라는 의미, 여튼 고정역할이 있는 애라 t1이 안잠!!!!
			//그래서 노란밑줄로 경고를 주는 것임.......-> 올바른 코드가 아님
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("<< main thread 종료 >>");
		
	}

} //여기서 제일 먼저 출력이 되는 것은? 
//main이 진행되다가 t1을 만나고 2초 있다가 탈락되고 t2와 main이 남는데 둘 중에 먼저 출력되는 거?
//t2가 먼저 출력이 되지 않아요. 왜냐하면 sleep이 누웠는데 이건 static이라는 고정역할을 하는 것!!!
//t1을 재워야지 하지만 실제로 자진 않아요~~~ (함정 쩌네??)

//t1을 재우려면 try/catch를 지우고 위에 run에 Thread.sleep(2000); 을 해야함+try/catch추가

//어렵네? 
