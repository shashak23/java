package com.test4;

public class Rectangle extends Shape implements Movable{
	public Rectangle() {
		
	}
	public Rectangle(int width, int x, int y) {
	    super(new Point(x,y));
		this.width = width;
		
	}
	//field
	private int width;
	
	@Override  //삼각형과 사각형이 형태가 다르니까 값도 따로 하는 것임
	public void move(int x, int y) {
		Point p = getpoint();           
		p.setX(p.getX() + x + 2);       
		p.setY(p.getY() + y + 2);
		setPoint(p); 
		
	}
	@Override
	public double getArea() {
		return Math.round(Math.pow(width, 2));
	}
	@Override
	public double getCircumference() {
		return Math.round(4 * width);  //삼각형, 사각형읠 둘레
	}
	//여기까지도 어렵지가 않다라...........도대체 이과생을 무슨 삶을 사는 걸까
	//와 나 진짜 하나도 모르겠는데? ㅠㅠ 큰일났네 실화인가...현실부정하고 싶다

    //getter & setter 
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "   ";
		   + width + "   "
		   + getPoint().getX() + "    "
		   + getPoint().getY() + "    "
		   + getArea() + "   "
		   + getCircumference();
	}	
	
	

}
