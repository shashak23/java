package test;

public abstract class SuperClass {

	//생성자
	public SuperClass() {
		
	}
	//필드
	
	//메소드
	// 일반 메소드(메소드 정의)
	public void sayHello() {
		System.out.println("안녕하세요!");
	}
	
	//메소드 선언 => 완전한 형태의 메소드가 아니에요 -> abstract keyword를 이용해서 표시
	public abstract void eat();             //추상메소드가 됨, 추상메소드가 하나라도 클래스에 들어온다면? 전체가 추상이 됨
	//메소드부분에 abstract가 있다면 public과 class 사이에 abstract를 기입
}

//또다른 클래스의 묶음을 만들 수 있습니다?
abstract class SubClass extends SuperClass {

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		//짜잔, 오버라딩이을 한다고 해주는 지시어얌.
		//이제서야 하위 클래스의 인스턴스를 만들 수 잇음
		//반드시 
		
	}
	//추상클래스를 상속시켜 확장하여 만들거임
	//SubClass안에 abstract가 있다는 거임
	//이 클래스로부터도 객체를 만들 수 없음 -> 결국 소용 없는 짓 ^^;
	//완전한 형태의 클래스를 만들려면 상속받은 애를 주는 애한테서 오버라이딩을 해야함
	
}