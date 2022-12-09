package samlpeproj;  //채팅할 때 똑같이 이 구조가 적용이 됨, 달라지는 건 키워드 정도? 

//채팅 네트워크가 비슷해요. 공유객체를 쓴다는!

//공유객체를 가지는 메소드를 호출하는 게 많아욧
//공유객체를 생성하기 위한 class
class Shared {
	
	public synchronized void printName() {  //syn넣고 동기화 시켜주기 
		
		try {
			
			for(int i=0; i<10; i++) {  //이름을 열번만 찍을 겁니다
				Thread.sleep(1000);  //1초 쉬고 이름 찍기
				System.out.println(Thread.currentThread().getName());  //thread가 가진 이름 찍어야 하는데 위의 printName을 호출한 애가 this
				notify(); //내가ㅓ wait으로 들어갈 때 notify를 해야 안에 있는 애가 기어 나와서 다시 웨잇이나 이런게 정상 작동? 
				wait();  //wait 상태가 됨 한번찍고 얘도 같이 들어가~ 둘다 wait상태가 됨, 계속 홀드가 됨
				
				
				//notify(); //먼저 웨이트가 되면 노티파이가 실행이 안됨.
				
			} //스레드가 가진 이름을 쓰기위해 커렌트를 씀??? 웨잇이 풀려야 진행 됨.
			
		} catch (Exception e) {
			
		}
		
	}//공유객체의 공유메소드, 이름을 찍을
	
}//따로 데이터를 가질 필요 없이 스래드가 가진 것을 반복적으로 찎어줘요

class ThreadEx_13_1 implements Runnable {
	public ThreadEx_13_1() {
		// TODO Auto-generated method stub
	}
	
    public ThreadEx_13_1(Shared shared) {
    	super();
    	this.shared = shared;

}
	//공유객체
	private Shared shared; //공유객체를 만들어줄거에요 
	
	
	@Override
	public void run() {
		shared.printName();
		
	}
	
} //러너블이 스레드라는 객체를 들고 있어야 그 안에 공유객체를 들고 있는 형태가 되잖아요~~~?

public class ThreadEx_13 {
	
	public static void main(String[] args) {
		
		//공유객체 하나 만들기
		Shared shared = new Shared();
		
		Thread t1 = new Thread(new ThreadEx_13_1(shared),"첫번째 쓰레드");   //쉐어드 객체에서 러너블 객체가 되고
		Thread t2 = new Thread(new ThreadEx_13_1(shared),"두번째 쓰레드");
		
		t1.start();
		t2.start();
		
	}

}
