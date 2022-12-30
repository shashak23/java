package javaexam1; 

public class Test02 {
	public static void main(String[] args) {
		
		int a = Integer.parseInt(args[0]); //두 개 중 하나의 값을 0로 지정 
		int b = Integer.parseInt(args[1]); //두 개 중 하나의 값을 1로 지정 
		int result = a*b;
		
		if(1<= result && result < 10) { //결과가 1부터 10사이 일 때 한자리 수 입니다
			System.out.println("한자리 수 입니다.");
			
		} else if (10 <= result && result < 100) {
			System.out.println("두자리 수 입니다."); // 결과가 10부터 100사이 일 때 두자리 수 입니다.
		}
		
		
	}
} 
