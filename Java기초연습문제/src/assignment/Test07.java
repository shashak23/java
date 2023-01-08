package assignment;

public class Test07 {
	public static void main(String[] args) {

		// n 값은 총점
		// N 값은 클래스value
	    for(int n = 0; n < args.length; n++) {
	    	int checkValue = 0;
	    	checkValue = Integer.parseInt(args[n]); // args의 n을 숫자로 변환해서 int 값을 checkvalue에 넣기
	    	
	    	// 사용 데이터에 10~99까지의 값을 받는다고 했으니까 if 조건으로 넣고
	    	if (!(checkValue >= 10 && checkValue <= 99)) { 
	    		System.out.println("다시 입력 하세요"); // 원하는 값이 나올 때 까지 글자 출력
 	    		System.exit(0); // 0일 때 시스템 접속 종료 
	    	}
	    	
	    }
	    
	    int m1 = Integer.parseInt(args[0]);
	    int m2 = Integer.parseInt(args[1]);
	    int m3 = Integer.parseInt(args[2]);
	    int m4 = Integer.parseInt(args[3]);
	    int m5 = Integer.parseInt(args[4]);
	    
	    //합계를 array배열의 길이로 나눈 값을 double로 변환	
	    double a,d,b,c,e = 0;
	    double totalValue = 0;
	    String classValue = "";
		
	    a = m1;
	    b = m2;
	    c = m3;
	    d = m4;
	    e = m5; // 변환해서 읽을 수 있도록 설정해주고 
	    
		//평가기준 계산을 이용하고 (A+B)/2: 60%  (C+D)/2: 20%   E : 20%
		totalValue = (a + b / 2)* 0.6 + ((c + d)/ 2)* 0.2 + e * 0.2 ;
		
		if(totalValue >= 90) { // if 조건문으로 Gold Class 세부설정하고
			classValue = "Gold Class"; // 90이하면 골드 클래스
		} else if(totalValue >= 80) { // 80이하면 조건
			classValue = "Siver Class";
		} else if(totalValue >= 70) {
			classValue = "Bronze Class";
		} else if(totalValue > 70) {
			classValue = "Normal Class";
		}
		
		//Normal Class값을 출력함
		System.out.println("평가점수 : " + totalValue);
		System.out.println("Class : " + classValue);
	} 

}


