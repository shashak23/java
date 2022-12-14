package exam03_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

public class Exam03_MultiEchoClient extends Application {
	
	TextArea textArea;
	TextField ipTextField;
	Button connBtn;
	TextField idTextField;
	TextField chatTextField;
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br; //선언만 하는게 아니고 만들어야지 쓸 수 있겠쬬? 통로 뚫기 가자 
	
	private void printMsg(String msg) {
		Platform.runLater(()-> {
			textArea.appendText(msg + "\n"); //UI Tread 이용해서 출력할 수 있또록 
		});
		}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
		//보더펜 생성
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		FlowPane upFlowPane = new FlowPane();
		upFlowPane.setPadding(new Insets(10,10,10,10));
		upFlowPane.setColumnHalignment(HPos.CENTER);
		upFlowPane.setPrefSize(700, 40);
		upFlowPane.setHgap(10);
		
		ipTextField = new TextField();
		ipTextField.setPrefSize(200, 40);
		
		connBtn = new Button("서버에 접속!!");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> {  //이벤트 처리
			//특정 IP와 특정 port번호를 이용해서 socket 객체를 생성시도한다 => 이게 서버에 접속한다는 겁니다? ^^!
			
			try {
				socket = new Socket(ipTextField.getText(), 7777); //만약 성공하면 서버와 연결된 socket객체를 얻어요 => 연결 2개 완료! =>서버가쟈! 
				//소켓을 얻어오는 순간 통로 뚫어!
				pr = new PrintWriter(socket.getOutputStream()); //소켓을 통해 내보내기!
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream())); //소켓을 통해 받기~! 
				
				
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} //IP와 포트번호를 이용해서 접속을 시도하기~! 오예!, 익셉션 처리가 강조됨 // 자동완성기능을 이용합시다 ?
			
			
			
		});   
		
		upFlowPane.getChildren().add(ipTextField);
		upFlowPane.getChildren().add(connBtn);
		
		FlowPane bottomFlowPane = new FlowPane();
		bottomFlowPane.setPadding(new Insets(10,10,10,10));
		bottomFlowPane.setColumnHalignment(HPos.CENTER);
		bottomFlowPane.setPrefSize(700, 40);
		bottomFlowPane.setHgap(10);
		
		idTextField = new TextField();
		idTextField.setPrefSize(150, 40);
		
		chatTextField = new TextField();
		chatTextField.setPrefSize(150, 40);
		chatTextField.setOnAction(e -> {
			//채팅ㅇ입력장에서 enter를 치면 action event가 발생해서 이 코드가 시행
			String id = idTextField.getText();
			String msg = chatTextField.getText(); // 가지고 와서 보내쥬?
			
			pr.println(id + ">" + msg); //소켓을 통해 가지고 온 정보를 괄호와 같은 구조로 보내기! ※여기서는 항상 println()을 써야해요
			pr.flush(); //여기까지가 그림안에서 1번임 ^^;;;;;;; 소오름
			
			try {
				String receive = br.readLine();
				printMsg(receive);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		bottomFlowPane.getChildren().add(idTextField);
		bottomFlowPane.getChildren().add(chatTextField);
		
		root.setTop(upFlowPane);
		root.setBottom(bottomFlowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show(); //화면 띄우기
	}

	public static void main(String[] args) {
		launch();
	}

}
