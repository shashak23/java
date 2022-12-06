package com.test5;

public class Secretary extends Employee implements Bonus {
	//생성자
	public Secretary() {
		
	}
	public Secretary(String name, int number, String department, int salary) {
		super();
	}
    //필드
	//인센티느 지급, 지급되는 pay에 80%가 기존 salary에 더해진다.
	@Override
	public void incentive(int pay) {
		plusIncentive = getSalary() + (pay*0.8);
		
	}
	
	
	//메소드
	
	//세금을 리턴한다
	//인센티브를 지급한다
}
