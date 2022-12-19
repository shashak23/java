package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class MyRunnable implements Runnable {

	Socket socket;
	Shared shared;
	BufferedReader br;

	public MyRunnable () {
	}
	
	public MyRunnable(Socket socket,Shared shared) {
	//여기에 소켓이랑 쉐어랑 BufferedReader하기 
	super();
	this.socket = socket;
	this.shared = shared;
	//try/catch로 묶어야해요
	try {
	this.br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //송출, 소켓 연결
	} catch(Exception e) {
		e.printStackTrace();

	}
}

	@Override
	public void run() {
		String msg;
		try {
			while(true) {
			msg = br.readLine();
			shared.broadcast(msg);
			
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
}

class Shared {
	//여기에다가는 ArrayList, HashMap 넣고 
	ArrayList<Socket> list = new ArrayList<Socket>();
	HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	public synchronized void addClient(Socket socket) { //파라미터로 잡고 동기화하기 
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}

public class Exam03_ChatServer extends Application {
    //field
	Button startBtn;
	Button stopBtn;
	TextArea textArea;
	
	Shared shared;
	ServerSocket server;
	
	
	//글 출력하는 거 하기
	private void printMsg(String msg) {
	textArea.appendText(msg + "\n");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//borderPand 붙이기
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		//startBtn
		startBtn = new Button("서버시작");
		startBtn.setPrefSize(150, 40);
		startBtn.setOnAction((e -> {
			//공유 객체 생성
			shared = new Shared();
			//익명 스레드 생성
			(new Thread(() -> {
				try { //socket 접속 대기시키고 while문 만들고	
					server = new ServerSocket(1111);
					
					while(true) {
						Socket socket = server.accept(); //클라이언트에서 서버 접속이 되기를 기다림
						
					//접속되면 문장 출력
					printMsg("새로운 클라이언트가 서버 접속됐습니다.");
					
					shared.addClinet(socket); //클라이언트 추가시 소켓 공유 확인
					
					//스레드 연결
					MyRunnable r = new MyRunnable(socket, shared); //스레드가 갈 수 있는 곳에 소켓이랑 공용객체를 넣고
					//new Thread로 잡지 말고 new MyRunnable 로 잡으세요
					Thread t = new Thread(r);
					t.start(); //스레드 실행시키기
					
					}
				} catch (IOException e1) {
					
				}
				
			})).start(); //thread 실행시키기 
			
			
			
		}));
		
		//stopBtn
		stopBtn = new Button("서버정지");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction((e -> {
			
		}));
		
		//flowPane 넣기
		FlowPane flowPane = new FlowPane(); //판자버튼
		flowPane.setPadding(new Insets(10,10,10,10)); //여백
		flowPane.setColumnHalignment(HPos.CENTER); //정렬
		flowPane.setPrefSize(700, 40); //사이즈
 		flowPane.setHgap(10);  //바닥에서 여백
 		
 		//각 버튼 붙이기
 		flowPane.getChildren().add(startBtn);
 		flowPane.getChildren().add(stopBtn);
 		
 		//붙인 판자를 바닥에 붙이기
 	    root.setBottom(flowPane);
 	    
 	    //scene꺼내기
 	    Scene scene = new Scene(root);
 	    
		//primaryStage 붙이기
 	   primaryStage.setScene(scene);
 	   primaryStage.setTitle("에코 서버 프로그램");
 	   primaryStage.show(); //보여주기
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
}
