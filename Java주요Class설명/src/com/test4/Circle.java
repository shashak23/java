package com.test4;

public class Circle extends Shape implements Movable {  //Shape를 이용해 확장, Movable을 이용해서 실행
	//constructor
	public Circle( ) {
		
	}
	
	public Cirlce(int radius, int x, int y) {  //x,y 값은 Movable class에서 끌어와야하는 것?! 
	    super(new Point(x,y));
		this.radius = radius;
		//radius는 여기서 본다는 걸로 남기고
		//shape을 살펴보는데? shape을 사용하려면 포인트 객체가 있어야해
		//그래서 super에 있는 것 중에서 상위 포인트의 객체를 상세하게 적어야 함
	
	}
	// 여기서 받은 x,y 좌표는 Point에서 봐야함

	//field
	private int radius;

	//override
	@Override
	public void move(int x, int y) {    //x와 y의 자표값을 변경하는 거에요.
	                                    //x,y 좌표값을 가지는 것을 댕기면 되요?
		Point p = getPoint();           //내 상위 클래스의 getter가 존재하는데 그걸 가져오는 거에요.
		p.setX(p.getX() + x + 1);       //x,y 좌표에서 1만큼 더 이동해요
		                                //더 해주는 건데 원이다보니까 x값을 1만큼 더 이동하는 거에요.
		                                //포인트 객체에 대한 값을 변경
		p.setY(p.getY() + y + 1);
		setPoint(p);                 //얻어온 객체를 다시 세팅해주기!		
	}
	
	@Override
	public double getArea() {
		return Math.round(Math.pow(radius,2) * Math.PI);  //원주율 값(로 제공 대한 상수값을 제공하는데, 그걸 java가 Math Class로 제공해줌)
		//상수값은 대문자로 표현됨, PI=3.14, *Math.PI해도 되지만 위의 문장이 더 널리 쓰임
		//pi의 제곱이니까 '2' , pow는 제곱하는 기능을 가진 method, Math를 가져오면 구현되어있는 method를 활용한다는 거고 
		//소수점 첫째자리까지 반올림해서 표현하라구 했죠?
		//그래서 Math.round가 추가되어요
		
	}
	@Override
	public double getCircumference() {
		return Math.round(2 * radius * Math.PI);
	} 
	//기본적으로 요 3개만 해요?! get~move 오버라이딩으로 구현함
	//으악 머리 터질 것 같아 이거 다시 보여달라고 해야겠다 ㅠㅠㅠ

	//getter&setter
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
		
		
		
	}

	@Override   //하위클래스에서 가져오는 방법
	public String toString() {  //인스턴스를 파생시킨 클래스 정보안에
		
		return this.getClass().getSimpleName() + "    "
		//getSimpleName은 클래스 이름만 가져와요.
		//이렇게 정해진 코드를 기억해두세요~~ 다 알아두셔야 합니다~~ 얏호 
		   + radius + "   "
		   + getPoint().getX() + "    "
		   + getPoint().getY() + "    "
		   + getArea() + "   "
		   + getCircumference();
		   
		
	}
}

