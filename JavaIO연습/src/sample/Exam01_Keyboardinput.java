package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam01_Keyboardinput {
	
	public static void main(String[] args) {
	   
		System.out.println("키보드로 한줄을 입력하세요!");
		
		//키보드로부터 입력을 받으려면 연결 통로가 있어야겠죠?
		//System.in이 제공되는데, InputStream class의 객체에요. -> 사용하기에는 불편함
		//문자기반의 데이터를 받기를 ㅜ언하니 Reader를 하나 만들어야 해요
		//new InputStreamReader(System.in) 이렇게 스트림을 결합해서 조금 더 편한 
		//문자기반의 통로를 열었어요~ -> 그럼에도 불구하고 불편함 -> 한 라인을 그대로 읽는 거를 원해요 - 그게 편해요
		//조금 더 편한 문자 기반의 데이터 입력 연결통로를 만들어 볼꺼에요~!
		BufferedReader br =    //br은 Sytem.in을 기반으로 확장된 통로로 키보드와 연결된 것
				new BufferedReader(new InputStreamReader(System.in)); //문자열 기반 통로인데 버퍼가 있는 개선된 통로
		//통로를 좀 더 좋은 걸로 발전시킴 - 결합!
		//통로의 메소드를 이용해요. 대표적인 메소드가 readLin
		
		
		try {
		  String s = br.readLine(); 
		  System.out.println("입력받은 데이터는 : " + s);
		} catch (IOException e) {
			
		}
		
		
	}

}
