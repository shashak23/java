package samlpeproj;

//01_2클래스는 스래드가 아닌 runnable이라는 특별한 인터페이스를 구현한 클래스 -> 그래서 스래드가 아님
class ThreadEx_01_2 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<5; i++) {
	    	  System.out.println(Thread.currentThread());
		}	
	}   //run을 사용해서 비슷하게 만들지만 인터페이스를 구현하는 방식이다.
	//하지만 사싱 override run이런거 못써요
	//getName같은 메소드를 이용하려면 current스래드를 발견 
	//이 메소드를 실행한다는 것은 밑에 러너블이 호출됐따는 것은 실행이 됐다는 것인데
	//그 실행의 역할을 하는 것이 currentThead이다.
}

//01_1클래스는 당연히 스래드임 why? IS-A관계에 있으니까(상속 중)
class ThreadEx_01_1 extends Thread {       //java.lang.Thread 자동변경 됨, public 지우고, 

	//새로운 독립적인 실행 흐름
	//마치 프로그램의 엔트리포인트에 준하는 무언가 시작 메소드가 있다는 의미
	//일단 여기에 main은 쓸 수 있으나, 독립적인 스래드를 만들기엔 부적합
	
    @Override
    public void run() {                  //스래드를 run으로 오버라이딩(재정의)함
    	//내가 실행하고자 하는 코드를 작성-run
    	//for문: 1.집합자료구조 이용하는 경우=for~each 구문 예)for(String s : list) {} 
    	//2.원하는 횟수만큼 반복할 경우=일반for문
    	for(int i=0; i<5; i++) {
    	  System.out.println(getName());  //this.getName에서 this는 생략가능
    	}
    	//메소드가 혼자 나온ㄴ 경우가 없고 앞에 인스턴스가 잇음
    
    }
}

// 예전 java thread_01 => 실행하면 JVM이 main method를 호출해서 시작한다고 설명함
// thread관점에서 설명: javathrea_01을 실행하면 메모리는 똑같고 jvm이 내부적으로 thread를 하나 생성함
//이 thread가 main method를 호출해서 생성하고 이걸 'main thread'라고 함
//main은 method이고 main을 호출해주는 main thread가 있다.

//process(=program)이 '시작하다', '끝난다'는 뜻
//main method가 시작하면 시작되는 거고
//main method가 종료되면 전체 프로그램이 종료되요 -> 이제는 틀린 말,
//시작자체는 main thread에서 시작되는 건 맞지만 종료는 의미가 달라요.

//정확하게는 process안에 있는 모든 thread가 종료되어야지 process가 종료되는 것임
public class ThreadEx_01 {
	public ThreadEx_01() {
		
	}
	
	public static void main(String[] args) {
		//이 main method는 당연히 하나의 thread에 의해서 실행되는 method
		//위에꺼를 생각하지 말아보자
		//예전에는 그냥 프로그램을 실행시키고 안제 메인이 잇으면 한줄 한줄 진행됩니다 입력했다면
		//실행하면 클래스를 읽어와 메모리 구조적으로 스래드를 만들어 스래드가 main을 호출하는검
		//그렇게 스래드가 기동을 하는 것 :)
	    //int result = 10 / 0;         //에러됨
		
		Runnable r2 = new ThreadEx_01_2();
		Thread t2 = new Thread(r2);
		//is-a관계에 의해서 runnable로 쓸 수 있다? 스래드는 아니다.
		//얘를 스래드로 만들려면 두번째줄처럼 해야해
		//러너블 인자로 놓고 쓴다면 run이라는 메소드가 실행이 됩니다? >>> 이게 무슨 말이야? 
		//직접 상속을 받던지, 러너블을 바꾸던지 두 가지 방법으로 스래드라는 걸 만들어 쓸 수 있어요
		
		ThreadEx_01_1 t1 = new ThreadEx_01_1();
		//t1은 thread instance가 되요
		//t1은 현재 객체일 뿐이야...아직 동작하지 않아요
		//이 thread는 독립적인 실행 흐름을 가지고 있고
		//run() 안에는 그 내용을 기술하고 있어요
		//이 thread를 실행시킬거에요
		//실수한다면 아래와 같이 작성됨
		//t1.run();  //이렇게 호출한다면 단순히 객체의 메소드를 호출하는 건지 스래드를 동작시킨건 아니다
		//인스턴스의 메소드를 호출한다는 거에요~ 
		//대부분의 메소드는 blocking method입니다
		//blocking은 메소드 수행이 끝나고 리턴 될때까지 기다린다는 거에요
		//run은 여기서 일단 대기한다는 것.
		
		//위의 코드는 실행이 되지만 실제로 스레드를 실행시킨 것은 아니고 객체의 호출을 하여 기능한 진행함
		//그러면 어떻게 하나요? 이렇게 하지 않고
		//다른 메소드를 이용한다
		t1.start();  //thread-0
		t2.start();  //thread-1
		
		//스래드를 실행하면 총 3개의 스래드가 된다. -> 어디에 모이는가? 러너블
		//메인은 러닝에 들어가서 실행이 되고 있지만......무슨 말이지 22 
		
		//이렇게 start를 이욯해서 스래드를 실행시킨다. 스래드가 내부에 있는 run을 찾아가 
		//위쪽의 override를 찾아가서 start를 이요해서 스래드를 실행시킨다.
		//start는 non blocking method임
		//여기에서 스래드가 시작하는 것임!
		//위의 방법을 두가지 다 알아두세요~ 
		System.out.println("main thread 종료!");
		
	}
	
}