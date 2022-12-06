package com.test5;

public class Sales extends Employee implements Bonus {
	//생성자들
	public Sales() {
		
	}
	public Sales(String name, int number, String department, int salary) {
		super();
	}
    //필드들
	
	//인센티브를 지급한다
	@Override
	public void incentive(int pay) {
		plusIncentive = getSalary() + (pay*1.3);
		
	}
	
	//메소드들
	
	//세금을 리턴한다
	
}
