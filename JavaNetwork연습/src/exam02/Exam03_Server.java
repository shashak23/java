package exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

public class Exam03_Server extends Application {
	
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	Socket s;
	TextArea textarea;
	
	PrintWriter pr;
	BufferedReader br; //인자 생성, import 하기 
	Thread Runnable;
		
	private void printMsg(String msg) {
		Platform.runLater(()-> {  //new Runnable을 생성하는 경우도 있지만 교수님은 안하심
		    textarea.appendText(msg + "\n");	
		
		});  //멀티스레드를 가져오기 위한 코드? -> 수요일에 확인하기 
		}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500); // Layout의 가로세로 크기
		
		textarea = new TextArea();
		root.setCenter(textarea);
		
		startBtn = new Button("서버시작!"); //시작 버튼, 글귀
		startBtn.setPrefSize(150, 40);  //시작버튼 사이즈
		startBtn.setOnAction(e -> { //blocking 메소드, 잠시 중지 
			printMsg("서버가 시작되었어요!");
			try {
				server = new ServerSocket(5000);  //서버 인자 생성하고(주소값)
				printMsg("클라이언트 접속대기중!");  // 클라이언트 대기 상태 안내
				
				(new Thread(() -> {
					try { //얘는 반복적으로 계속 받아야해ㅇ
						s = server.accept();
						printMsg("클라이언트 접속 성공!!");
						pr = new PrintWriter(s.getOutputStream());
						br = new BufferedReader(new InputStreamReader(s.getInputStream()));
						
						while(true) { //false가 나올 땎가지 반복시키기
						String msg = br.readLine();
						printMsg("클라이언트의 메시지 : " + msg);
						
						if(msg.equals("/exit")) {
							break; //채팅창에 /exit를 치면 나간다는 것을 설정함!
						}
						pr.println(msg); //메세지 찍고 클라이언트 돌려주고 또 다시 클라이언트가 뭐 보내줄까? 싶어서 대기탐
						pr.flush();
						}
						//break가 걸리고 exit되면 여기로 와요~!
						printMsg("클라이언트 종료! & 서버 Process 종료");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				})).start();				
				
				
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			
		});
		
	
		stopBtn = new Button("서버중지!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(null); //stop버튼 완료 
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);  // 정렬
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}

