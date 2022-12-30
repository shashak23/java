package javaexam1;

public class Test04 {
	public static void main(String[] args) { //1부터 100까지 합
       double sum=0; 
       int i =1; // =int sum=0, int i=1랑 같은 대신 한줄로! 
		//for(i = 1; i <=100; i++) { //for문을 사용하였을 때
			//sum += i;
		//}
		
		//while(i <= 100) { //while문을 사용하였을 때 
			//sum += i;
			//i++;
       
       do {
    	   sum += i;
    	   i++;
    	   
       //}	
		} while(i<=100);
  		System.out.println("합계 :" + sum);  //합계와 평균은 double형으로 계산한다
  		
  		System.out.println("평균 :" +sum / ((i-1)));  //합계와 평균은 double형으로 계산한다


       
	}

}
