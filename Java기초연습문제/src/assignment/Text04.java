package assignment;

public class Text04 {
	public static void main(String[] args) {
	 //주사위 3개 
	int n1; //
	int n2; //
	int n3; //(%3==0)
	
	 //값마다 나올 수 있는 경우의 수를 다 찾아야하니까 반복시킴
	for(n1 = 1; n1 < 7; n1++) {
		for(n2 =1; n2 < 7; n2++) {
			for(n3 = 1; n3 < 7; n3++) {
				//3개를 곱하면 0이 된다
				if((n1*n2*n3) %3==0) {
					System.out.println(n1+"*" + n2+ "*" + n3+ "=" + (n1*n2*n3));
				} //출력과 같이 *도 넣고 =도 넣고 해야하니까 결과같에 따로 넣어줘야함
			} 
		}
	}

	}
}
