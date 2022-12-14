package assignment;

import java.util.Scanner; //입력받기 

public class Test02 {
	public static void main(String[] args) {		

		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt(); //숫자 nextInt(). 문자는 next()
		int b = sc.nextInt();
		
		System.out.println(a*b);
		
		sc.close();
	}

}
