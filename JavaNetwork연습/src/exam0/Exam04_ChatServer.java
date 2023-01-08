package exam0;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class Shared {
	
	ArrayList<Socket> list = new ArrayList<Socket>();  // field
	HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	// method
	// 서버에 새로운 클라이언트가 접속하면.. 
	// 해당 클라이언트에 대한 소켓이 서버쪽에 만들어지고
	// 이 소켓을 공유객체에 저장해야 해요!
	// 이 작업을 아래쪽에 있는 메소드가 수행할꺼예요!
	public synchronized void addClinet(Socket socket) {
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public synchronized void broadcast(String msg) {
		// 전달받은 문자열을 모든 클라이언트 PrinterWriter를 통해
		// 데이터를 내 보내요! --> 동기화를 시키는 거에요 
		for(Socket s: list) {
			map.get(s).println(msg); //해쉬맵에서 하나씩 메세지 뽑아내기
			map.get(s).flush(); //하나씩 빼서 하나씩 보내기!
		}
	}
}

class MyRunnable implements Runnable {

	Socket socket;
	BufferedReader br;
	Shared shared;
	
	public MyRunnable() {		
	}
	
	public MyRunnable(Socket socket, Shared shared) {
		super();
		this.socket = socket;
		this.shared = shared; //인자 생성 ->0에 삽입 -> this에 연결까지
		try {
			
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				String msg = br.readLine();
				
				// 공유객체를 통해서 모든 클라이언트에게
				// 데이터를 내보내요!
				//pr.flush();
				shared.broadcast(msg);
				//공유 객체가 가지고 있는 메서드를 통해서 데이터를 싹ㄷ 다 뿌려줘요. 스트림 하나에서 보내는 게 아니고 다 보내는 거에요!
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

public class Exam04_ChatServer extends Application { 

	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	
	Shared shared;   // 공유객체를 필드로 설정해요!

	
	private void printMsg(String msg) {
		
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		}); //UI thread를 이용해서 출력할 수 있도록 
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { //
		// 화면 구성을 해요!
		// primaryStage => 이놈이 실제 window예요!
		
		// 화면을 구성할 Layout을 먼저 만들어요!
		// 우리는 화면 구성을 BorderPane을 이용해서 구성할 꺼예요!(동서남북중앙 화면 5등분)
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 실제 화면구성을 해 보아요!
		textArea = new TextArea();
		root.setCenter(textArea);
		//text area에 넣어 필드로 올리는 이유: 살아있는 동안 다른 void나 이런데에서 쓸 수가 있어서 
		
		startBtn = new Button("서버기동!!");
		startBtn.setPrefSize(150, 40);
		// 버튼의 이벤트 처리코드를 일단 먼저 작성
		startBtn.setOnAction(e -> {
			// 이벤트 처리 코드가 나와요! Java Lambda
			// 여기가 실행될 동안 window는 잠시 block되요!
			// 하필이면..클라이언트의 접속을 무한정 기다리는 코드가 나와야되!
			// 이문제를 해결하기 위해.. Thread를 생성해서 사용해요!	
			
			// 공유객체를 생성해요! 스레드를 필두로 박혀놓아야 해요.
			shared = new Shared();
			
			// 익명 class의 Thread생성 및 실행
			new Thread(() -> {
				// 서버소켓을 하나 생성해야 해요! 클라이언트의 접속을 기다리는 놈이죠!!
				try {
					// port가 다른 프로그램에서 사용되고 있는 중일 수 있기때문에
					// 예외상황이 발생할 수 있어요. 그래서 이 코드는 예외처리가 강제되요!
					server = new ServerSocket(7777);
					
					while(true) {
						Socket socket = server.accept(); // 클라이언트의 접속을 기다려요!
						// 대기하고 있다가 클라이언트가 접속하면 해당 클라이언트와 
						// 연결된 Socket 객체를 하나 생성해요!
						
						// socket을 이용해서 직접 클라이언트와 통신하는 기능을 수행하는
						// Thread를 하나 생성해야 해요!
						// Thread를 만들기위한 클래스가 있어야 겠죠. 클래스를 작성해 보아요!
						
						printMsg("새로운 클라이언트 접속!!");
						
						// 공유객체에 클라이언트 소켓을 저장해요!
						shared.addClinet(socket);
						
						MyRunnable r = new MyRunnable(socket, shared); //제어할 수 있는 부분은 여기니까 여기에 socket이랑 shared를 같이 넣어줘요
						Thread t = new Thread(r);
						t.start();
					}
					
					
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}).start();
			
		});
		
		stopBtn = new Button("서버중지!!");
		stopBtn.setPrefSize(150, 40);
		// 버튼의 이벤트 처리코드를 일단 먼저 작성
		stopBtn.setOnAction(e -> {
			// 이벤트 처리 코드가 나와요! Java Lambda
			
		});
		
		// BorderPane의 아래부분(Bottom)에 버튼을 부착시키고 싶어요!
		// 공간은 하나인데 버튼은 2개예요! 붙일수가 없네...
		// 일단 판자(FlowPane)을 하나 만들어서 버튼 2개를 차례대로 붙이고
		// 이 판자를 BorderPane의 아래부분(Bottom)에 붙여요!
		FlowPane flowPane = new FlowPane();
		// 이 판자에 버튼을 2개 붙일꺼예요. 그런데 설정(Padding, 정렬)을 안하면
		// 안예뻐요! 보기가 좋지 않아요. 설정이 좀 들어가야 되요!
		flowPane.setPadding(new Insets(10,10,10,10)); // padding(여백)설정
		flowPane.setColumnHalignment(HPos.CENTER); // 정렬
		flowPane.setPrefSize(700, 40);
		flowPane.setHgap(10);
		// 판자 설정이 끝났으니 이제 판자에 버튼을 붙어야 해요!
		flowPane.getChildren().add(startBtn);		
		flowPane.getChildren().add(stopBtn);  //버튼 붙이기 끝
		
		// 판자를 BorderPane의 아래부분(Bottom)에 붙여요!
		root.setBottom(flowPane); //전체구성 끝
		
		// 이런 Layout을 이용해서 장면(Scene)을 만들어요!
		Scene scene = new Scene(root); //scene은 장면임
		
		// 이제 만들어진 장면을 window에 넣어요!
		primaryStage.setScene(scene); //stage에 scene을 넣어요 
		
		primaryStage.setTitle("Echo Server Program");
		
		primaryStage.show();		
	}

	public static void main(String[] args) {
		// main thread에 의해서 최초로 실행되는 method
		// entry point
		// GUI Thread를 하나 생성할꺼예요!
		launch();
	}
}
