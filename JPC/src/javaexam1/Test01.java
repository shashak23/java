package javaexam1;

import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(); 
		//next()메서드는 공백이전까지의 문자열을 입력받기
		//nextln()메서드는 int 정수를 입력받기 
		
		if(a %2!=0) {
		
		System.out.println("2의 배수가 아닙니다.");
	} else if(a % 2== 0) {
		System.out.println("2의 배수입니다.");
	}
	sc.close();
}
}
