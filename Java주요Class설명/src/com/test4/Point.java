package com.test4;

public class Point {
	//constructor
	public Point() {
		
	}
	public Point(int x, int y) { //constructor 넣기 
		super();
		this.x = x;
		this.y = y;
	}

	//field
	private int x;
	private int y;
	
	//getter & setter 
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
