package samlpeproj;

class ThreadEx_03_1 extends Thread{
	public ThreadEx_03_1() {
		
	}
	
	public ThreadEx_03_1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(this.getName());
		}

	}
}
public class ThreadEx_03 {
	public static void main(String[] args) {
		Thread t1 = new ThreadEx_03_1("Thread-01");
		Thread t2 = new ThreadEx_03_1("Thread-02");
		
		//실행시키지 전에 우선순위를 매겨보기
		t1.setPriority(1);
		t2.setPriority(9);
		
		t1.start();
		t2.start();
		//사실 뭐가 먼저 실행될지 모르는 상황에서 t1을 1을 주고 t2을 9를 준다면
		//1~10까지에서 보다 높은 t2(9)이 진행되어야하는데 그럼에도불구하고 설정된대로 순서가 안됨
		//우선순위 의미가 없다 ^-^.....?
		
	}

}
