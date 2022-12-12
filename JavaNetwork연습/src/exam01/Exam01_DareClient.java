package exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam01_DareClient extends Application  {
	
	
	//field
	TextArea textarea;  //컨트롤 + shift + o + sense--
	Button connBtn;
	
	public void start(Stage primaryStage) throws Exception {
		//화면 구성을 해봅시다
		//일단 에이아웃부터 하나 만들어 보아요
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);  //원하는 크기 설정
		//밑에 버튼 만듭시다
		//먼저 text area부터 만듭시다
		
		//component 생성
		textarea = new TextArea();
		root.setCenter(textarea);
		
		connBtn = new Button("Naver 서버 접속"); // 버튼을 만들어서 지정 서버가 네이버고 클라이언트가 나이고
		connBtn.setPrefSize(150,40);
		connBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			
			@Override
			public void handle(ActionEvent arg0) {
	            // click이벤트
	            textarea.clear(); //text area안의 내용을 싹 다 지운다.
	            //서버에 접속 -> Socket객체 생성 시도
	            //객체 생성 성공시 접속 성공
	            //IP와 Port알아야함
	            try {
	               Socket s = new Socket("127.0.0.1",3000); //127.0.0.1 -> 내가 사용하는 computer 지칭 (local host)
	               System.out.println("서버에 접속 성공!");  //접속 될 때에만 성공이 한번만 나와요.
	               BufferedReader br = 
	            		   new BufferedReader(
	            				   new InputStreamReader(s.getInputStream()));  //통로(br)만들어요
	               
	               String msg = br.readLine(); //통로로부터 데이터를 읽겠다
	               //서버와 클라이언트가 같이 돌아? 시간상 서버가 데이터를 쏴주기전에 클라이언트가 읽을 준비가 안돼?
	               //그럼 데이터가 없어지는 건가? -> 시간상 동시에 일어난다는 건 없어요. 결국 약간의 차이로 엇갈린단 말이쥬?
	               //두개가 만들어지고 관을 서로 연결해서 데이터를 주고 받는데, 받는 측에서 아직 안보냈는데 먼저 받으려고 해?
	               //그럼 blocking이 되요. 통로를 통해 들어올 때까지 기다려요.
	               //일단은 도스창에 찍고? 
	               System.out.println(msg);
	               
	               br.close();
	               s.close();
	               
	               System.out.println("서버와 연결 종료!");  //이렇게 끝~! 
	                
	               
	            } catch (UnknownHostException e) {
	               e.printStackTrace();
	            } catch (IOException e) {
	               
	            }  
	         }
	      }); //괄호가 많아도 처리가 제대로 안돼요 ㅜㅜ

		    //접속 버튼을 클릭하면 하는 일을 여기에 작성
			//서버와 접속하는 코드를 만들어 주면 되요
			//밑에 버튼 부분에 공간을 확보해서 글을 써야하니까.. ->>> 채팅 할 때 

		FlowPane flowpane = new FlowPane(); //아래쪽 영역에 붙ㄴㄴ layout
		//여백을 좀 잡아서 그나마 좀 보기 좋게 만들어보아요.
		  //위아래 여백잡는거 이따가 붙인 버튼의 공간이 만들어짐 10픽셀만큼
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);  //버튼이 버튼 레이아웃에 고정되는 여백
		flowpane.setHgap(10);
		
		//버튼을 flow pane에 붙여요
		flowpane.getChildren().add(connBtn);
		
		//이렇게 만든 맨 밑에 연결하는 겁니다?! 
	    root.setBottom(flowpane);
	    
	    Scene scene = new Scene(root); //장면을 만들고
	    //윈도우 겁데기에 이걸 넣어요
	    primaryStage.setScene(scene);
	    
	    primaryStage.show(); //장면 만들고 윈도우에 붙이고 다시 띄우고 
	    //서버에 접속하는 거 까지 
	    
		
	}
	
	public static void main(String[] arg0) {
		launch();
		
		
	}

}
