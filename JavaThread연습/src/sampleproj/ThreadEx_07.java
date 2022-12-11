package sampleproj;

class ThreadEx_07_1 implements Runnable {     //얘는 스래드가 아니고 러너블 인터페이스를 구현해주는 클래스
	

	public void run() {
		while(true) {
		//현재 수행중인 Thread를 찾아 Thread의 이름을 출력해요
			System.out.println(Thread.currentThread().getName());         //getName은 Thread가 갖고 있어요
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
			
		}			
	}
}




public class ThreadEx_07 {
	
	public static void main(String[] args) {
		//러너블 interface를 구현한 class의 instance를 생성
		Runnable r = new ThreadEx_07_1();
		
		Thread t1 = new Thread(r,"*");      //첫번째 스래드 이름은 별표입니다
		Thread t2 = new Thread(r,"**");
		Thread t3 = new Thread(r,"***");    //"" 인 이름을 다르게 해줘야 셋이 겹치지 않고 검색이 되겠죠?
		
		t1.start();
		t2.start();
		t3.start();                        //실행되는 준비를 마침
		
		try {                              
			Thread.sleep(2000);            //main Thread가 졸아요, 홀드 되면, 일단 멈춘다는 얘기, 스레드3개가 대기중
			t1.suspend();                  //t1이 멈춰요
			Thread.sleep(2000);            //어느 순간 2개씩 찍히다가
			t2.suspend();                  //t2이 멈추면서 어느 순간 t3만 나와요   => 나중에 이런거를 로직으로 바꾸는 연습을 해야해요
		} catch (Exception e) {

		}

	} //run해서 실행만 되는 거고 스래드를 만들고 공용으로 사용해도 실행코드를 갖고 올 수 있는 거에요
	
}
