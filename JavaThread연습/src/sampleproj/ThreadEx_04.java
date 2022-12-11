package sampleproj;

public class ThreadEx_04 implements Runnable {
	//run을 오버라이딩 하지 않음ㄴ 추사이 되니깡 꼭 합시당
	
	//field
	static boolean autoSave = false;
	  //class의 이름 가지고도 바로 access 할 수 있어서 -> 이게 무슨 말이지 
	public static void main(String[] args) {
		  //우리 클래스에 인스턴스를 만들어주자
		Thread t = new Thread(new ThreadEx_04());  //스레드가 만들어지고 start하면 실행
		t.setDaemon(true);  //이걸 빼면 처음에는 안나오다가 시간이 지나면 나와요. 언제까지? 내가 강제종료할 때까지
		  //true가 되면 자기를 만든 메인 스래드의 보조 역할이 된다
		  //바로 위의 스래드는 main이 만듦, main method가 호출했으니까 main을 수행하고 있는 jvm의 main method가 생성해줌!
		t.start();
		
		for(int i=0; i<10; i++) {
			try {
				Thread.sleep(1000);             //슬립은 특정 스래드를 지정해서 재울 수 없고?
			} catch (InterruptedException e) {  //슬립은 현재 실행되는 스래드를 재울 때 사용해요
				e.printStackTrace();            //여기서 실행이 되는 코드도 메인 스래드에 의해 실행이 되쥬?
			}                                   //메인이 실행되면 코드가 실행되고 슬립이 현재 실행 중인 스래드를 재울 때 씀당
            if(i==5) {
            	autoSave = true;
            }   //static으로 하면 autoSave 못 써요 
            //5초 지난 다음에 특정 순간이 되면 내용을 찍기 시작해요?! 
            //메인 메소드가 1초 자다 깨다 자다깨다 하다가 어느 순간에는 i값이 10을 넘어가서
            //죽게되고 데몬메소드가 같이 죽게되요
		}

	}    //코드는 똑같지만 어디에서 코드를 쓰냐에 따라 재우는 대상이 달라짐
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(autoSave) {
				System.out.println("자동저장 되었어요!");
			}
			//데몬스래드가 실행됐어, 3초 자, 이프가서 자동저장 찍고, 3초 돌고 다시 자동저장 찍어요
			//sleep있지만 에러가 발생해요? 3000 = 3초
			//그냥 Thread.sleep();은 쓸 수 없어서 try/catch를 명시해줘야해요
			//thread를 일정시간동안 재운다, 시간을 정해줄 수 있다.정확히 얘기하면 sleep,
		}   //while은 안끝나서 y를 true로 잡아도 상관없어요
		
	}

}
