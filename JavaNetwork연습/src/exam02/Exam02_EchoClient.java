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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam02_EchoClient extends Application {

	TextArea textarea;
	Button connBtn;
	TextField textField;
	TextField idField;
	
	Socket s;
	PrintWriter pr;
	BufferedReader br;
	
	
	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Layout //한번 갔다 오고 끝나는 로직임. -> 내가 특정 메세지로 끝내거나, 닫기를 누를 때까지 반복되야해요~! =>  나중에 채팅만들때는 ㅠ 
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500);
		
		textarea = new TextArea();
		root.setCenter(textarea);
		
		connBtn = new Button("");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> {			
			try {
				s = new Socket("127.0.0.1", 5000);
				printMsg("연결이 성공했습니다"); //소켓이 나온거임
				
				pr = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
			
		idField = new TextField(); 
		idField.setPrefSize(200, 40); //사이드 잡기

		
		
		textField = new TextField();
		textField.setPrefSize(200, 40);
		textField.setDisable(true); //사용할 수 없도록 세팅할까요?
		textField.setOnAction(e -> {	
			String msg = textField.getText();  //엔터치면 보내는 부분
			String id = idField.getText();   //id field가져오기 
			pr.println(id + ">" + msg); //>는 사람이름 구분하기 위해 넣음
			pr.flush();
			
			try {
				printMsg(br.readLine());
				//하 진짜 너무 빨라서 진짜 빡친다 ^^;
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
			
			textField.clear();
		});
		
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(connBtn);
		flowPane.getChildren().add(idField); //id field 위치 잡기
		flowPane.getChildren().add(textField);
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

} //=> 여기까지는 단발성이고 반복적으로 하는 걸 오후에 해봅시다.
