package exam02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam03_Client extends Application {

	//field 

	Button connBtn;
	TextField idField;
	TextField textField;
	PrintWriter pr;
	BufferedReader br;
	TextArea textarea;
	Socket s;

	//method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//마지막 root
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500); // Layout의 가로세로 크기
		
		textarea = new TextArea();
		root.setCenter(textarea);
		
		
		//연결버튼
		connBtn = new Button("서버접속!!");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> {
			//socket 값, 텍스트필드 넣기 
			try {
				s = new Socket("127.0.0.1", 5000);
				printMsg("서버에 연결이 성공했습니다."); //클라이언트에서 여기서부터 보임
				textField.setDisable(false);
				pr = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
		//아이디창
		idField = new TextField();
		idField.setPrefSize(200, 40);
		
		//채팅글 입력
		textField = new TextField();
		textField.setPrefSize(200, 40);
		textField.setDisable(true);
		textField.setOnAction(e -> {
			//텍스트창에는 메세지와 아이디를 반듯이 넣어야하고 이걸 붙인다
			String msg = textField.getText();
			String id = idField.getText();
			
			//pr,br 입력
			pr.println(id + ">" +msg); //출력되는 건 "아이디 > 메시지"
			pr.flush();
			
			if(!msg.equals("/exit")) {
			try {
				printMsg(br.readLine());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			} else {
				printMsg("클라이언트가 서버와 연결이 종료되었어요!");
			}
			  textField.clear(); //지우기하고 
			
		});
		
		//액션 값, 문구, exit 등 넣기 
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);  // 정렬
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(connBtn);
		flowPane.getChildren().add(idField);
		flowPane.getChildren().add(textField);
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		//s.넣기, primaryStage
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show();
				
		
		
	}
	private void printMsg(String string) {
        
		
	}

	public static void main(String[] args) {
		launch(); //창띄우는 코드
	} 
}
