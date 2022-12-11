package sampleproj;

//공용객체 class로 정의 - 예금잔액
class Account {
	public Account() {
		
	}
	public Account(int balance) {
		super();
		this.balance = balance;
	}
	//field 예금잔액
	private int balance;

	
	//출금이라는 비즈니스 메소드
	//내가 원하는 만큼 출금하자구
	public void withdraw(int money) { //동기화 메소드가 됨 synchronized.
		//이 메소드를 실행한 Thread가 먼저 Lock(Monitor)획득함. 둘 중에 하나가 먼저 호출하면 다른 애는 block이 걸려요
        //첫번째 스래드가 메소드 호출을 끝낼 때까지, 그럼 다른 애의 block이 풀리고 이렇게 순차적으로 처리가 된다.
		//동기화 안됨
		
		
		synchronized (this) {                     //요고 하면 그 위와 그 밑은 동기화가 안되요 => 이걸 인계영역이라고 함 
		
		if(balance >= money)                      //잔액이 내가 출금하려는 돈보다 많아야지 출금이 가능!
			try {
				Thread.sleep(1000);               //출금처리를 제대로 하기 위해 눈에 보이게 하기 위해 시간 걸기
			} catch (Exception e) {
				
			}
	        balance -= money;                     //빼고 나면 잔액에서 출금 금액을 뺌, 마이너스가 될 수 있어요!
	
		}
	}
		
		
		//동기화 안됨

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}

class ThreadEx_12_1 implements Runnable {
	
	//field
	Account acc = new Account(1000);              //1000원으로 초기화 시켜줘야해요
	
	public void run() {
		
		while(acc.getBalance() > 0) {                //출금이 가능한 상태, 계속 차감을 하겠지, 위에서 볼 때 1초 쉬고 차감, 
			int money = (int)(Math.random() * 3 + 1) * 100;
					    //0과1사이에서 0과 3사이에서 1~4로, 출금할 금액을 알아둬
			acc.withdraw(money);                     //공용객체의 출금처리
			System.out.println("남은 금액은 : " + acc.getBalance());    //실제로 남은 금액이 얼마인지 출력
			
		}
	}
		
}

//두 개의 스래드를 줄게요
public class ThreadeEX_12 {
	public static void main(String[] args) {
		ThreadEx_12_1 r = new ThreadEx_12_1();
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);           //똑같은 러너블 객체를 공유, 위의 field를 공유함
		
		t1.start();
		t2.start();
		}
	}

}
