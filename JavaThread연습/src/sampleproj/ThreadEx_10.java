package sampleproj;  //yield 설명


class ThreadEx_10_1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("-");
		}
	}
}

class ThreadEx_10_2 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("|");  //shift+\=|
		
	}
}


public class ThreadEx_10 {
	public static void main(String[] args) {
		long startTime = 0;                    //시간 잡는 변수 하나 적음
	
	    Thread t1 = new ThreadEx_10_1();
	    Thread t2 = new ThreadEx_10_2();
	    
	    t1.start();
	    t2.start();
	    
	    startTime = System.currentTimeMillis();  //숫자로 현재시간을 표현할 때 사용하는 것, 스탑워치 한 번
	    
	    try {           //현재 실행 중인 스래드에 t1과 t2를 쪼인한거에요! 스탑워치 한번 더 
	    	
	    	t1.join();  //그냥 쓰면 빨간색 밑줄, try/catch로 처리해야해요 //메인스래드가 진행 중이었는데 t1을 join하면 메인이 멈춰요
	    	t2.join();  //t1을 작동중인 스래드를 멈춰요. t1이 끝날 때까지. //t2가 멈출때까지 메인이 멈춰
	    	
		} catch (Exception e) {
	      e.printStackTrace();
			
		} 
	    
	    System.out.println("소요시간 : " 
	    + (System.currentTimeMillis()- startTime));  //스탑워치 2개의 시간차를 계산
	}
		
	} //문제는 뭐냐면 이 일드는 어디다가 써요? 

}
