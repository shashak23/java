package samlpeproj;


class ThreadEx_09_1 implements Runnable {
	
	    volatile boolean suspended = false;	  
	    volatile boolean stopped = false;
	    
	    Thread t;                           //나를 수행시킨 스레드가 무엇인지 인자로 갖고 있는 것.
	    
	    void setThread(Thread t) {
	    	this.t = t;                     //이렇게 만들었으니까 밑에 t1.start에 가서 
	    }
	    
	    @Override
	    public void run() {	    

			while(!stopped) {                
				                              
				if(!suspended) {               
					System.out.println(Thread.currentThread().getName());
		
					try {
						Thread.sleep(1000);
					} catch(Exception e) {
						
					} //이 코드가 수행이 되면은 체크해야하잖아요? 안멈췄으면 찍고? 그럼 계속 체크하겠쬬?
					  //그러 서스펜드를 때렸을 때 1초 잤다가 반복해서 계속 도는 거잖아? "1초 자고 깨고 체크" 이걸 반복 
					  //서스펜드를 하는 순간 인터렙트를 걸거야
					
					  //서스펜스가 끝나서 false로 멈췄다면 끝났으니까 빼내서 일드를 쓴다
				} else {
					Thread.yield();  //스케줄러가 알아서 처리함
					
				}
			}
			
		}
		
		public void suspend() { 
			suspended = true;     //아마도 최대 1초뒤에 상태를 확인해서 일시중지 작업을 시행
			//최대한 빨리 일시정지 상태에 돌입하려면 조금이라도 더 빨리 효율적으로 돌리려면
			//현재 스래드에 대해서 인터럽트를 걸어야 해
			//인터럽트를 호출해야되
			t.interrupt();        //여기서 this는 r1이 됨. 스레드가 아닌데 인터럽트를 못 거니까
			                      //인터럽트를 걸고 싶은데 걸 수 있는 방법!
			                      //서스펜들르 호출한 r1을 지칭할 걸 어떻게 써야하나?
			                      // => 레퍼런스를 챙겨와야해요.
			                      //r1이라는 러너블 인터페이스 객체를 스레드한 중요해요! 맨 위로 올라가! 
		
		} 
		public void resume() { suspended = false; }
		public void stop() { suspended = true; } 
		
	}
	


public class ThreadEx_09 {
	public static void main(String[] args) {
		
		
		ThreadEx_09_1 r1 = new ThreadEx_09_1();
		ThreadEx_09_1 r2 = new ThreadEx_09_1();
		ThreadEx_09_1 r3 = new ThreadEx_09_1();
		
		Thread t1 = new Thread(r1, "*");
		Thread t2 = new Thread(r2, "**");
		Thread t3 = new Thread(r3, "***");        //이름 입력하고 추출하자 
		
		r1.setThread(t1);
		r2.setThread(t2);
		r3.setThread(t3);
		
		t1.start();
		t2.start();
		t3.start();                            
		
		try {                                  
			Thread.sleep(2000);                
			r1.suspend();                      
			Thread.sleep(2000);                
			r2.suspend();                      
			Thread.sleep(2000);                
			r1.suspend();                      
			r1.stop();                      
			r2.stop();                        
			r3.stop();               //1,2,3을 따로 관리하기위해 뗴어둠           
			
		} catch (Exception e) {                
			
		}
		
		
	}

}