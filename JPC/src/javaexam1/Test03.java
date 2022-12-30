package javaexam1;

public class Test03 {
	
	public static void main(String[] args) {

		for(int i=2; i<6; i++ ) { //2단부터 시작하고 5단까지 실행하며 반복문이 한번 돌때마ㅏ 1씩 증가시킴
			
			System.out.println();
			
			for(int j=1; j<10; j++ ) { //구구단이니까 9까지 곱하는 걸 넣어주기 
				int result = i*j;
				 
			if(result %2!=0) { //홀수만 출력하기 만들기 
				System.out.println(i + "x" + j + "=" + result); //출력 넣기

			}
			}
		}

	}

} //홀수 만들기

