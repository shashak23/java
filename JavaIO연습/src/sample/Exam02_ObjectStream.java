package sample;

import java.io.File;                 //import 안들어가 있으면 밑에 file 쓸 떄 빨간 밑줄생김
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Exam02_ObjectStream {
	
	public static void main(String[] args) {
		
		//먼저 스트림을 통해 내보낼 해쉬맵을 간단하게 만들기
		HashMap<String, String> map 
		= new HashMap<String , String>();
		
		map.put("1", "홍길동");
		map.put("2", "신사임당");
		map.put("3", "강감찬");
		
		//handling을 해야죠
		//실제 파일을 생성하려면 당연히 자바쪽에서 file객체를 만들어야 해요
		//자바는 객체로 도니까~ OS에 있는 파일을 이용하려면 파일을 객체로 만들어야해요
		File file = new File("readme.txt");   //파일을 객체로 만들어서 결국 연결시킴
		
		try {
			FileOutputStream fis = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			
			oos.writeObject(map);                  //해쉬맵 객체를 내보내는 거얌 이걸 성공하려면
			                                       //첫 번째 이 객체는 반드시 Serializable Interface를 구현하고 있어야해요.
			                                       //두 번째 이 객체는 변환된가 실제 공간에는 다르게 변형이 되어 저장되요.
			
			
		} catch (Exception e) {
			
			
		} 
		
	}

}

