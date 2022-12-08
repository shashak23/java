package samlpeproj;

import javax.swing.JOptionPane;

class ThreadEx_06_1 extends Thread {               //순서2
	@Override
	public void run() {                            //순서3
		
		int i = 10;                                //순서4
		
		while(i != 0 && !this.isInterrupted()) {                   //첫번째 조건. (어떻게 반복할 것인가)동안, i가 0이 아닐동안
			                                                 //isInterrupted 메소드만 덜렁 나왔다면? 
			                                                 //메소드나 필드는 자바에서 제공받은 거든 만든거든 무조건 앞에 Class명이 나오던가 new에서 만든 인스턴스가 있어야해
			                                                 //메소드나 필드가 앞에 Class나 new instance가 생략되서 나오는 경우가 빈번. (This.)메소드 생량 가능
			                                                 //여기서 생략된 this는 밑에 t(instance)이다.
			System.out.println(i--);                         //--는 1씩 감소, 10출력 후 i값을 1감소
			//busy-waiting, 하는 일도 없이 시간 끌 때 표현해요 :) (바쁘게 기다려볼까?)
			for(long k=0; k<10000000000000000L; k++);   //순서8  코어가 실제로 250억번을 돌리는데, 순식간에 찍을걸 10찍고 쉬었다가 9찍고...이런 식?
				
		}
		System.out.println("카운트가 종료되었습니다!");             //10부터 1씩 감소하면서 카운트값을 찍은 스래드를 종료하자고 출력!
	}
}


public class ThreadEx_06 {
	
	public static void main(String[] args) {            //순서1
		Thread t = new ThreadEx_06_1();                       //상위타입 스래드는 우리 타입의 스래드 인스턴스 생성
		
		t.start();  //순서5  thread를 Runnable 상태로 전이시킴, 실행 가능한 상태, 언젠가는 스케줄러에 의해서 실행되겠죠?
		
		String data = JOptionPane.showInputDialog("값을 입력하세요!");      //순서6  대화창을 띄우는 것, 구소-클래스.static 메소드(); 
		
		System.out.println(data);                  //순서6 그러고 실행을 시키면 값을 넣으라는 화면이 나온다 -> hold가 된다.
		                                           //순서7 "거기에 소리없는 아우성"이런 필요 문구를 넣으면 콘솔에 출력이 완료.
		t.interrupt();                             //순서9 thread가 중지되거나 방해받지 않고 인터렙트스택트라는 변수(상태값)을 재정리해주는 것.
		t.suspend();                                          //스래드를 직관적으로 일시정지 시킴 
		t.stop();                                             //스래드를 중단 
	}

}
