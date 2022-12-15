package assignment;

import java.util.Scanner;

public class Text05 {
	public static void main(String[] args) {	 //방법이 두 가지 	
	//3개의 정수값을 입력하고 최대값을 구한다
		Scanner stdIn = new Scanner(System.in);
		
		int a = stdIn.nextInt();
		int b = stdIn.nextInt();
		int c = stdIn.nextInt();
		
		/*
		 * int max = a; if(b > max) max = b; if(c > max) max = c;
		 */ //최대값 비교 

		//int형 배열을 만들어서 abc를 넣고 반복문을 비교하는게 좋은 방법!
		int array [] = {a,b,c};
		int max = array[0];
		int min = array[0];
		
		for(int i=0; i<array.length; i++) { //array.length 어레이 길이 
			if(max < array[i])
			{
				//비교하고 맥스값을 바꿔주는
			  max = array[i]; 
			} System.out.println("최대값 : " + max);
			if(min > array[i])
			{
			  min = array[i];
			} System.out.println("최소값 : " + min);
			
		}
		 //배열을 자동 정렬 (맨앞이 최소, 맨뒤가 최대)
		//Arrays.sort(array);
		//int M = array[array.length-1];
		//int m = array[0];
		
	}

}
