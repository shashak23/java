package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam03_ChatClient extends Application{
	TextArea textArea;
	TextField ipTextField;
	Button connBtn;
	TextField chatTextField;
	TextField idTextField;
	
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br; 

	//UI 메세지 출력되는 거 쓰기 (이거는 서버랑 클라이언트 다 써야해요)
	private void printMsg(String msg) {
	Platform.runLater(()-> {
	textArea.appendText(msg + "\n");
	});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//보더판 붙이고 
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		//text area 붙이기
		textArea = new TextArea();
		root.setCenter(textArea);
		
		//flowpane붙이기
		FlowPane upFlowPane = new FlowPane(); //플로우 팬 붙이기
		upFlowPane.setPadding(new Insets(10,10,10,10)); //여백
		upFlowPane.setColumnHalignment(HPos.CENTER); //정렬
		upFlowPane.setPrefSize(700, 40);
		upFlowPane.setHgap(10);
		
		//iptextfield 붙이기
		ipTextField = new TextField();
		ipTextField.setPrefSize(200, 40);		
		
		//connBtn 붙이기
		connBtn = new Button();
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(e -> {
		try {
			//소켓, buffered 
			socket = new Socket(); //여기에 iptext랑 gettext넣기
			pr = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(a
					new InputStreamReader(socket.getInputStream()));
					
			
			//익명스레드 넣기
			
			
		}catch(IOException e1){	
			e1.printStackTrace();
		}	
		});
		
	}
	
	public static void main(String[] args) {
		launch();
		
	}

}
