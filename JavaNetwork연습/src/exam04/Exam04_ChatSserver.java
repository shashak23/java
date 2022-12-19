package exam04;

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

//쉐어 클래스 만들기
class Sharred{
	ArrayList<Socket> list = new ArrayList<Socket>();
	HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	public synchronized void addClient(Socket socket) {
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void broadcast(String msg) {
		for(Socket s: list) {
			map.get(s).println(msg); //하나씩 메세지 뽑기
			map.get(s).flush(); //하나씩 정해서 보내기
			
			
		}
	}
}

//러너블 클래스 만들기
class MyyRunnable implements Runnable {
	Socket socket;
	BufferedReader br;
	Shared shared;
	
	public MyyRunnable() {
		
	}
	
	public MyyRunnable(Socket socket, Shared shared) {
		super();
		this.socket = socket;
		this.shared = shared;
		
		try {
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
						
		} catch (Exception e) {
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



public class Exam04_ChatSserver extends Application {
	Button startBtn;
	Button stopBtn;
	TextArea textArea;
	Shared shared;
	ServerSocket server;
	
	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n"); //권장하지 않아요~ 
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		startBtn = new Button("서버기동!!!");
		startBtn.setPrefSize(150, 40);
		startBtn.setOnAction(e -> { //문법이라 상관없어요 
			
			//공유객체 생성
			shared = new Shared();
			
			//익명스레드 생성
			(new Thread(() -> {
				try {
					server = new ServerSocket(1223);
					
					while(true) {
						Socket socket = server.accept(); //접속기다림
						
						printMsg("새로운 클라이언트 접속!!!");
						
						shared.addClinet(socket);
						
						MyRunnable r = new MyRunnable(socket, shared); //제어할 수 있는 부분에 소켓이랑 쉐어 같이!
						Thread t = new Thread(r);
						t.start(); //thread
						
					}
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				
			})).start(); //
			
			
		});
		
		stopBtn = new Button("서버중지!!!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
			
		});
		
		FlowPane flowPane = new FlowPane(); //판자 버튼
		flowPane.setPadding(new Insets(10,10,10,10)); //여백
		flowPane.setColumnHalignment(HPos.CENTER); //정렬
		flowPane.setPrefSize(700, 40); 
		flowPane.setHgap(10); //바닥에서 여백
		
		//버튼 붙이기
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		//판자 바텀에 붙이지
		root.setBottom(flowPane); //여기까지 전체 구성 끝
		
		Scene scene = new Scene(root); //장면에 넣을게요
		
		primaryStage.setScene(scene); //stage에 scene을 넣어요
		primaryStage.setTitle("Echo Server Program");
		primaryStage.show();
		
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}
