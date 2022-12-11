package sampleproj;

class ThreadEx_11_1 extends Thread {        //final+상수(고정되고 변하지 않는 필드)만들기 
	final static int MAX_MEMORY = 1000;     //상수를 쓸 때에는 static 많이 쓰고 관용적으로 대문자로 쓰고 snake case _을 사용함
	                                        //이 Thread의 instance를 생성해서 실행하면 10초마다 일정량의 메모리사용향을 감소시켜요
	                                       
	int usedMemory = 0;                     //변수를 하나 잡고
	
	@Override
		public void run() {                 //run으로 오버라이딩을 해야하고
			while(true) {                   //break 만나서 끝날 때까지 안나가요
				try {                       //계속 반복하는 while(true)
					Thread.sleep(10000);    //10초 마다 자다 깨다 
				} catch (Exception e) {     //스레드 슬립은 입섹션이 발생할 수 있기떄문에 catch로 잡고 
					
				}                           //위에께 끝나면
				gc();                       //method를 조절하는 걸 불러요, gc()=메모리를 차감시키는 역할
				System.out.println("남은 메모리량 :" + freeMemory());  //10초마다 얼만지 알려주는 거에요.
			}  
			
		}
	
	public void gc() {                       //현재 사용된 메모리량이 얼마인지는 모르겟으나 그걸 조절하기 위해
		usedMemory = usedMemory - 300;       //임시적으로 300씩 마이너스 할래, 메모리량은 양수값
		if(usedMemory < 0) {                 
			usedMemory = 0;                  //메모리가 0보다 작은 음수이면 0으로 하겠다.
		}                                    //(usedMemory -= 300;) = (usedMemory = usedMemory - 300;) if말고 그 전에 쓰기.
		
	}
	public int totalMemory() {               //메소드 1, 최대 메모리 리턴시켜주기 
	     return MAX_MEMORY - usedMemory;     //총량을 알려주는  public int totalMemory

	}
	public int freeMemory() {                //전체 메모리에서 사용된 메모리량을 뺴서
	     return MAX_MEMORY - usedMemory;     //현재 이용한 메모리량을 알려주는

	}
} //main Thread가 만든 것!

public class ThreadEx_11 {
	public static void main(String[] args) {
		
		ThreadEx_11_1 t = new ThreadEx_11_1();
		t.setDaemon(true);              //데몬스레드를 만들어주면 파생된 스레드가 종료될 때 같이 종료; //얘는 보고스레드
		t.start();                      //위에 run(){} 진행 - 잠 - 300뺴래 - 마이너스300 - 0보다 적으면 0으로 하래 - print에 전체에서 남은 거 뺴 - 출력
		
		int requiredMemory = 0;
		
		for(int i=0; i<20; i++) {       //20번 반복하자
			requiredMemory = (int)(Math.random() * 10) * 20;              //math.랜덤은 난수값을 도출. 0과 1사이인데 0은 포함하고 1은 포함하지 않는, 실수형
			//0와 10사이, 이렇게 나온 걸 실수가 아닌 정수도 만들거에요? - 뒤에 실수를 날려버리고 결국 0~9사이가 됨. - 내가 원하는 정수값을 도출하기!!!
			//0, 20, 40 ... 180
			
			//욘상해보자.
			//위에서 구한 필요한 메모리량이 사용할 수 있는 (가용한)메모리량보다 크면 메모리를 확보할 떄 gc를 깨울거에요.
			//가비지 컬렉션이 생겨나서 메모리량을 조절해서 메모리를 확보해야해요~~~
			if((t.freeMemory() < requiredMemory) ||     //사용한 량보다 사용하려는 양이 더 커지는 구간
					t.freeMemory() < t.totalMemory() * 0.4) {   //전체 메모리의 0.4보다 free메모리가 없어요. - 현재 60%를 사용중
				t.interrupt();
				try {
					t.join(100);                                //t.join을 실행시켜서 100정도의 제한?을 주는 거임, 이거 안주면 무한루프에 빠짐, 안끝남.
				} catch (Exception e) {                         //0.1초동안 진행이 됨. 그래서 결구 마이너스(-)까지 떨어지지 않아요~! - 섬세한 처리...ㄷㄷ
					
				} 
			}
			
			t.usedMemory =+ requiredMemory;  //사용된 메모리량을 누적
			//리퀘얼메모리하고 유즈드메모리하고 반복해서 
			System.out.println("남은 메모리량 :" + t.freeMemory());
		}
	} //결론적으로 얘를 실행하면 0밑으로 떨어질 수 없고 1000이상으로 올라가지 않아요, 메모리량이 
      //실행 후 -20 막 이런거 가끔 찍혀요 왜? 허점... 데몬스래드에서 if가 진행이 될 때 순차적으로 처리될거라고 생각해서 -20 이런게 없을거라고 잘될거라고 보지만
	  //사실 데몬스래드와 메인스래드가 동시에 진행 중이니까 문제가 가끔 생길 수 있어요. => 이걸 보완하기 위해 메인스래드를 멈추고 순차처리가 되도록 해야함.
	  //이 때 쓰는게 join() ^^ 
}
