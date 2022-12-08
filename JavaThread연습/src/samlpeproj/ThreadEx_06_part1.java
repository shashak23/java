package samlpeproj;

import javax.swing.JOptionPane;

class ThreadEx_06_1_part1 extends Thread {         //순서2
	@Override
	public void run() {                            //순서3
		
		int i = 10;                                //순서4
		
		while(i != 0 && !this.isInterrupted()) {                   //첫번째 조건. (어떻게 반복할 것인가)동안, i가 0이 아닐동안 //방해를 안받을동안
			                                                 //isInterrupted 메소드만 덜렁 나왔다면? 
			                                                 //메소드나 필드는 자바에서 제공받은 거든 만든거든 무조건 앞에 Class명이 나오던가 new에서 만든 인스턴스가 있어야해
			                                                 //메소드나 필드가 앞에 Class나 new instance가 생략되서 나오는 경우가 빈번. (This.)메소드 생량 가능
			                                                 //여기서 생략된 this는 밑에 t(instance)이다.
			System.out.println(i--); 
			try{
				//--는 1씩 감소, 10출력 후 i값을 1감소

			  Thread.sleep(2000);                            //순서8 i-- 찍고 2초 쉬고 
			
			} catch (InterruptedException e) {               //잡을 오류 코드 넣고
				//e.printStackTrace();                       //순서8-1 액면으로 생각하기엔 스래드가 2초를 잠시 쉬는구나가 라고 생각할 수 있지만 그게 아님 -> why? 밑에 interrupt때문에
				//System.out.println("자는 중에 interrupt가 걸렸어요!");  //순서8-2
				interrupt();                                 //순서10 초기화 된 거를 다시 interrupt를 다시 걸어서 기록을 남기고 더이상 카운트가 증가하지 않아요.
				                                             //
			}
		}
		System.out.println("카운트가 종료되었습니다!");             //10부터 1씩 감소하면서 카운트값을 찍은 스래드를 종료하자고 출력!
	}
}


public class ThreadEx_06_part1 {
	
	public static void main(String[] args) {       //순서1
		Thread t = new ThreadEx_06_1_part1();                    //상위타입 스래드는 우리 타입의 스래드 인스턴스 생성
		
		t.start();  //순서5  thread를 Runnable 상태로 전이시킴, 실행 가능한 상태, 언젠가는 스케줄러에 의해서 실행되겠죠?
		
		String data = JOptionPane.showInputDialog("값을 입력하세요!");      //순서5  대화창을 띄우는 것, 구소-클래스.static 메소드(); 
		                                                                //JO 이걸로 블로킹을 해봄, 값을 입력하면 블로킹이 풀리고 
		
		System.out.println(data);                  //순서6 그러고 실행을 시키면 값을 넣으라는 화면이 나온다 -> hold가 된다.
		                                           //순서7 "거기에 소리없는 아우성"이런 필요 문구를 넣으면 콘솔에 출력이 완료.
		t.interrupt();                             //순서9 thread사 중지되거나 방해받지 않고 인터렙트스택트라는 변수(상태값)을 재정리해주는 것.
		                                           //interrupt를 걸었는데 이때 해당 Thread가 하필 sleep(join,wait 등)상태일 때.......(순서8의 영향)
		                                           //자는 와중에 순서8의 영향을 받아서 exception이 발생해서 try/catch가 꼭 필요해요 그렇게 깬 상태가 되고
		                                           //그러면서 위의 try/catch를 돌고 누가 나를 interrupt된 거를 초기화시켜버려. -> thread의 흔적을 지움
		                                           //그럼 다시 돌아가면서 interrupt 걸렸었니? 그 사실을 초기화 시켜서? -> 아니요? => 결국 계속 돈다.
		//t.suspend();                             //스래드를 직관적으로 일시정지 시킴 
		//t.stop();                                //스래드를 중단 
	}

}
