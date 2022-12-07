package samlpeproj;

class ThreadEx_02_1 extends Thread {
    
	public ThreadEx_02_1() {
		
	}
	
	public ThreadEx_02_1(String name) {   //스래드를 둘 때 이름하나 두겠단 얘기
		super();
		this.setName(name);               //스래드 이름을 설정하는 메소드
	} //해당 문자열로 스래드의 이름을 설정
	
	@Override
    public void run() {
    	// TODO Auto-generated method stub
    	for(int i=0; i<5; i++) {
    		System.out.println(this.getName());  //이름 얻어서 넣기
    	}
    }	
}



public class ThreadEx_02 {
	public static void main(String[] args) {
		Thread t1 = new ThreadEx_02_1("My-Thread");
		
		//1번  t1.start();
		t1.run();        //2번 
		System.out.println("main thread 종료!");
		
	}

} //아까 한거랑 똑같은 거라는데, 이게 stack의 변화를 보기위해 만든 클래스
//스래드별 스택이 따로 있어요.
