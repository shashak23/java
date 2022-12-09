package com.test4;

public abstract class Shape {  // 추상클래스 표시
	//constructor
	public Shape() {
		
	}
	public Shape(Point point) {  //엄...get constructor 넣기
	super();
	this.point = point;
}

	//field 
	protected Point point;  //프로텍트 클래스 타입의 필드, 포인트 객체

	//getter & setter 
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	//method
	public abstract double getArea();           //추상클래스 메소드 표시하기
	public abstract double getCircumference();  //지시대로 get부분 넣기

}
