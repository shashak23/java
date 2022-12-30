package javaexam1;

public class Test06 {
	
	public static void main(String[] args) {
		
		int array [] = {1,2,3,4,5,6,7,8,9,10}; //데이터 배열 활용하기
        double sum = 0; 
        int i = array[i];
        
        for(int j=0; j < array.length; j++) { //반복문에 배열 넣어서 조건문 넣기
        	if(array[i] %2 == 0); //홀수 조건문
        	continue; //컨티뉴하기
        	
        }
        System.out.println(array[i]); //출력하기
        sum += array[i]; 

		System.out.println("합계 :"+ sum); //합계 출력하기

	} 
}