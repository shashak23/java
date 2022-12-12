package exam01;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

import javafx.scene.input.DataFormat;

public class Exam01_DataServer {    //바로 프로그램 호출하도록
	
	public static void main(String[] args) {
		
		//제일 먼저 서버 소켓을 만들어야해요 -> why? 클라이언트 연결을 대기하는 애라서
		try {
			ServerSocket server = new ServerSocket(3000); //밑줄? -> 언핸들링 exception 
			System.out.println("서버소켓이 생성되었어요 = 포트번호 3000");
			// 일단 해당 객체를 담은 걸 만듦? 기다리는 상태가 되어야지 접속이 가능함
			
			//터미네이티트 없으면 실행 중인 상황
			
			//다시 실행시켜서 3000포트번호 잡고, 서버쪽 클라이언트 접속 대기 상태
			
			//제대로 작동하면 지울 수 있어요 
			
			//이 서버소켓을 통해서 클라이언트의 접속을 기다려야함 -> 이럴 때 쓰는 메솓 
			 Socket s = server.accept(); // 대기해-> 클라이언트의 접속이 들어올 때 까지
		    System.out.println("클라이언트의 접속이 들어왔어요!");
		    
		    //이게 맞나...? 
			//날짜를 구해서 통로를 만들어야해요 - 데이터를 주기 위한
		    //소켓을 뽑아냅니다. 나가는 스트림을! // 이걸 가지고 만든게 print writer임.
		    //클라이언트와 연결된 output stream(데이터를 내보내기 위한 스트림)을
		    //사용하기 편한 PrintWriter로 만들어요.
		    PrintWriter out = new PrintWriter(s.getOutputStream()); 
		     //s.getOutputStream()이거는 좋은 스트림이 아니에요
		    
		    //현재시간을 보내줄거에요, DateFormat으로 쉽게 구할 수 있어요
		    DateFormat dateFormat = DateFormat.getInstance();  //아직 날짜를 구한건 아니에요
		    //dateFormat.format(new Data(null, 0)); 
		    String currentDate = dateFormat.format(new Date());  //java.util.데이터로 잡아주세요 
		    
		    out.println(currentDate); //보냈다고 생각하지만 실제로는 안가요 재진씨 진짜 미안하다 ㅠ 흑 
		    out.flush(); //보내주는 거에요! 
		    
		    //서버소켓만들거 - 화면 찍고 - 일단 홀드(대기) - 클라이언트가 접속할 때까지
		    //인데 홀드상태로 딱 걸려서 재생버튼으로 돌려도 녹음버튼 마냥 일시정지가 안꺼짐
		    
			
		    //스트림을 닫아요.
		    out.close(); //통로를 닫습니다 
		    s.close(); // 소켓을 닫아라  -> 논리적으로 묶여있던 소켓이 사라짐 -> 둘이 동시에 exception 생겨서 사라짐
		    //이러한 과정이 거쳐야지 제대로 종료가 될 수 있어요
		    //여기서 close를 해줘야하는 이유 -> 내가 쓰는 프로그램과 타 컴퓨터의 프로그램이 서로 쓰는데 나한테 문제가 생기면 타한테도 생길 수 있어요
		    //이전에는 close를 안하는 이유 -> 나혼자 쓰는 프로그램이었고
		    server.close();
		    
		    System.out.println("서버프로그램 종료!!");  //여기까지가 서버와 클라이언트의 일련의 과정이에요 ^^ 
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

} //여기가 가장 기본적인 네트워크 소켓입니다, 아직 메세지 주고 받는건 하지도 못했어요 ^^
