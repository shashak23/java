package samlpeproj;

class ThreadEx_11_1 extends Thread {        //final+상수(고정되고 변하지 않는 필드)만들기 
	final static int MAX_MEMORY = 1000;     //상수를 쓸 때에는 관용적으로 대문자로 쓰고 snake case _을 사용함
	                                       
	int usedMemory = 0;
	
	@Override
		public void run() {
			while(true) {                   //break 만나서 끝날 때까지 안나가요
				try {
					Thread.sleep(10000);
				} catch (Exception e) {
					
				}                           //위에꼐 끝나면
				gc();                       //memory 청소해서 memory 용량을 다시 확보하는 method
				System.out.println("남은 메모리량 :" + freeMemory());  //10초마다 얼만지 알려주는 거에요.
			}  
			
		}
	
	public void gc() {                       //현재 사용된 메모리량을 조절하기 위해
		usedMemory = usedMemory - 300;       //임시적으로 300씩 마이너스 할래, 메모리량은 양수값
		if(usedMemory < 0) {
			usedMemory = 0;                  //메모리가 0보다 작은 음수이면 0으로 하겠다.
		}
		
	}
	public int totalMemory() {               
	     return MAX_MEMORY - usedMemory;     //리턴해요

	}
	public int freeMemory() {                //전체 메모리에서 사용된 메모리량을 뺴서
	     return MAX_MEMORY - usedMemory;     //현재 아용한 메모리량을 알아내요

	}
}

public class ThreadEx_11 {
	public static void main(String[] args) {
		
	}

}
