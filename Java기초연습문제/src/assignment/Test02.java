package assignment;

import java.util.Scanner; //정수형 데이터 입력받기 

public class Test02 {
	public static void main(String[] args) {		

		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt(); //숫자 nextInt(). 문자는 next()
		int b = sc.nextInt(); //두 수의 곱! int 정수형으로 입력받기 
		int result= a * b;
		
		if (1 <= result && result < 10) {
		
		System.out.println("한자리 수 입니다."); 
		
		} else if(10 <= result && result < 100) {
			
			System.out.println("두자리 수 입니다."); 

		}
		sc.close();
	}

}
