package assignment;

public class Test01 {
	public static void main(String[] args) { // arguments에 원하는 숫자를 넣고 2 4  (쉽표없이)
		double a;
		double b;
		
		a = Double.parseDouble(args[0]);   //문자에서 숫자라고 바꿀 때, 형마다 다르다, 이거는 더블형
		b = Double.parseDouble(args[1]);
		
		if(a % b > 1) { 
			System.out.println("나머지가 1보다 크다!");
		} else { // 1보다 작거나 같은 경우
			System.out.println("나머지가 1보다 작거나 같다!");
		}

	}

}
