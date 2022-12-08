package samlpeproj;


class ThreadEx_08_1 implements Runnable {
	
	    volatile boolean suspended = false;	  //불리언은 true or false니까 //볼리타일 불리언? 캐쉬 무시하고 저 변수는 메모리가서 확인해 
	    volatile boolean stopped = false;     //이거는 객체필드니까 HEAP에 있어요 
		public void run() {
			//로직을 이용해서 동작을 만드는 걸 아래와 같이 만듦.
			while(!stopped) {                 //계속 반복되는 while에 stopped를 주고 !(not)을 붙여
				                              //위의 변수를 반영해서 while을 튕겨나가도록 만들어서 종료가 되게끔 함
				if(!suspended) {              //suspend가 안멈춰지고 제대로 수행이 되면 로직을 수행하겠따는 것 
					System.out.println(Thread.currentThread().getName());
					//06에서 했떤 거를 stop이런거 안쓰고 로직으로 바꾸는 주잉에요
					try {
						Thread.sleep(3000);
					} catch(Exception e) {
						
					} //변수 두 개를 이용해서 위와 같이 간.단.한. 로직은 만드는 것
					
					
				} //아 진짜 뭔소리인지 하나로 모르겠네 머리 터진다 이해하도록 노력해보자 
				  //08번은 서스펜드하면 첫번쨰 스래드가 진짜 멈춘게 아니고 로직으로 중지된 것처럼 보일뿐(효과를 준다)
				  //07번에 걔는 진짜 멈춘거야, 진짜 모든 걸 멈추세요 하는 건데 이건 아님 
				  //건너뛰면 중지가 되어 있는 그런 처리를 원하니까 더이상 cpu타임을 묶어두지 않고 빠져두면 되는데?
				  //나는 더이상 이거 안할거거든? 그럼 양보를 할 수 있어요!!! yield() 합니다. 다른 스레드를 쓰도록 줌
				  //와 진짜 이런것까지 하는게 말이 되겠찜 그러니까 다 하겠찌 아악! 
			}
			
		}
		
		public void suspend() { suspended = true; } // 로직은 만드는 걸 멈추는 효과.  //변수가 메모리에 있어
		public void resume() { suspended = false; } // 로직을 연결하고 고안해서 어떻게 쓸수 있는지를 연력해야해요
		public void stop() { suspended = true; } //어떻게 하면 고안행서 어떻게 하면 아까와 같은 효과를 낼까?
		//스레드에서만 쓰이는 게 아니고.....변수를 true, false로 바꾸기 위해서 메소드를 따로 만들어 둔 것.
	}
	


public class ThreadEx_08 {
	public static void main(String[] args) {
		
		//Runnable 객체를 공유하면 안돼요 그래서 반드시 값마다 따로 만들어줘야해요! 
		ThreadEx_08_1 r1 = new ThreadEx_08_1();
		ThreadEx_08_1 r2 = new ThreadEx_08_1();
		ThreadEx_08_1 r3 = new ThreadEx_08_1();
		
		Thread t1 = new Thread(r1, "*");
		Thread t2 = new Thread(r2, "**");
		Thread t3 = new Thread(r3, "***"); 
		
		t1.start();
		t2.start();
		t3.start();                            //로직으로 해당 변수들이 따로 행동하기 시작해요 
		
		try {                                  //코어가 메모리에 있는 서스펜스 등을 가져와서 해야해요,, -> 단점)시간이 걸림
			Thread.sleep(2000);                //main 잠깐 대기하세요 //일단 멈추면 위의 스레드 3개가 돌아가고(2초동안)
			r1.suspend();                      //첫번쨰 thread를 일시정지 할거에요, 이 때 로직을 하려면? // 2초 뒤에 r1에 멈추게 만들어요-> *이 안찍힘
			Thread.sleep(2000);                //스래드를 조절하는 메소드를 이용해서 스래드의 동작 방식을 바꿔주는 것, 직접이 아닌 돌려서,
			r2.suspend();                      //필드값을 조절해서 러너블 객체가 가지고 있는 run() method의 로직을 변화시키는 것. //r2까지 멈추고
			Thread.sleep(2000);                //필드값을 조절한다는게 그럼 동작객체를 변경한다는 건가? -> 물어봐야지 
			r1.suspend();                      //위에 while을 작업하지 말아야해요. 그럼 true가 되면 안하겠죠?
			r1.stop();                      //첫번째 스래드인 suspended를 바꿔주면서 마치 아무것도 안하는 것처럼 바꿔줄 수가 있음 //다시 스레드를 깨워서 r1이 나타나요?
			r2.stop();                         //위의 suspended 그거 호출하면 되요. //근데 r1이 다시 나타날건데 안나타나요?? -> 이거 왜 안되나요? 로직은 문제가 없음...
			r3.stop();                         //러너블 객체에 대한 거를 가져와서 호출하면 됩니다.
			
		} catch (Exception e) {                //^-^ 이게 로직상의 문제는 없지만 결과가 코딩대로 나오지 않는 예시....
			
		}
		
		
	}

} //Runnable은 자체적으로 객체를 갖고 있고 안에 내용의 stopped이런 거를 공유하게 되요 스래드 3개가 
