package exam03_1;

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

	//클래스로 스레드를 명시적으로 만들어 놓고 아래에 적용
	//상속으로 할수도 있지만? 바로 스레드를 만들도록 Runnable을 이용함 
	
class MyRunnable implements Runnable{   //여기서 요점은 상속으로는 이렇게 클래스를 만들수가 없고? 러너블은 가능함
	
	Socket socket; //필드를 잡으면 저장할 수 있으니까 , 이걸 이용해서 통신해야하니까?! 
	PrintWriter pr;
	BufferedReader br;

	
	public MyRunnable() {
			
		}
	public MyRunnable(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.pr = new PrintWriter(socket.getOutputStream());
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		
		try {
			while(true) {
				String msg = br.readLine();
				
				pr.println(msg);
				pr.flush();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
}

public class Exam03_MultiEchoServer extends Application{
	TextArea textArea; //필드로 잡을지 지역변수로 잡을지 걱정 -> 대부분 필드로 잡는게 편해요. 그래야 다른 곳에서도 많이 제어가 되쥬
	Button startBtn;
	Button stopBtn;
	
	ServerSocket server;
	
	private void printMsg(String msg) {

		textArea.appendText(msg + "\n"); //UI Tread 이용해서 출력할 수 있도록 

	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//arg0가 창이에요~ 안바꿔도 되지만 primaryStage -> 실제 windows 창
		
		//layout부터 만들어야해요~
		//BorderPane(동서남북으로 화면을 5분할하는) layout 이용할거에요
		BorderPane root = new BorderPane(); //보더펜말고 다른 이름 써도 됩니당
		//여기까지는 창만 있고 내용이 없는 상태 
		
		//사이즈 정하기
		root.setPrefSize(700, 500); //가로세로 사이즈 
		
		startBtn = new Button("서버기동");
		startBtn.setPrefSize(150, 40);
		//버튼의 이벤트 처리코드를 일단 먼저 작성
		// 이게 복잡해요 startBtn.setOnAction(new EventHandler<ActionEvent>() {  //여기서 eventHandler로 자동완성으로 설정함
		//MyServerRunnable r = new MyServerRunnable();
		//Thread t = new Thread();
		//t.start(); //3줄은 또 너무 많다? 
		//여기서 Runnable은 interface여서 override을 할수가 없어요
		

			//@Override
			//public void handle(ActionEvent arg0) {
				
			//}
		//}); //리스너객체가 들어가야해요, 근데 사실은 얘가 인터페이스입니다
		startBtn.setOnAction(e -> {
			(new Thread(() -> {
				//액션이 생기면 해야하는 내용을 작성하자, accept만 여기서 잡아도 되지만 서버소켓도 여기서 잡읍시다
				try {
					server = new ServerSocket(7777); //7777으로 임시로 했지만 이미 다른 사람이 썼다면 에러로 떠요, try/catch로 예외상황을 해둬야해요
					//port번호가 다른 곳에서 사용줄 일 수 있기때문에 -> try/catch로 강제 상황을 만들기
					Socket socket = server.accept(); //버튼과는 별도로 혼자 돌게 되고, 클라이언트의 접속을 기다려요!
					while(true) { 
						
					//한번 끝나면 바로 윗줄로 다시 돌아와요 그래서 반복시키게 만들어야지 멀티가 됩니다.
				    //주고 받으려면 양단에 스트림이 있어야지? 뚫어줘야지? 통로가 있어야지? 
					
					//대기하고 있다가 클라이언트가 접속하면 해당 클라이언트와 연결된 Socket 객체를 하나 생성해요!
					//클라이언트와 소켓을 이용해서 직접 클라이언트와 통신하는 기능을 수행하는 스레드가 만들어져야 하니까, 직접 주관할 서버쪽 쓰레드를 만듦. 
					//위의 new Thread했던 것처럼 또 하면 안돼요! why?쓰레드가 소켓을 받아서 들고 있으니까 곤란해져요
					//Thread를 만들기 위한 클래스가 있어야겠죠. 클래스를 작성해 봅시다!
					MyRunnable r = new MyRunnable(socket); //제어할 수 있는 애는 얘니까
					Thread t = new Thread(r);
					t.start(); //thread할 때 많이 했던 3줄이에요~
					//스레드가 소켓을 들고 있어야해요. t안에 소켓이 있어야해요.
					//스레드까면 러너블 있고 러너블안에 소켓있는 구조가 완성 따란 
					}
				} catch (Exception e1) {
					
				}//서버가 원래 다 했던 일을 클랑언트한테 하나씩 던져주고 스레드를 생성, 러너블을 생성, 그 안에 소켓을 짜잔.
				
				
			})).start();  //쨋든 레퍼런스인 t가 있어야지 축약까지 가능한데, 굳이 만들필요는 없어요
			//왜 이렇게 쓸 수 있는지 "이해를 하세요"

			//코드작성하고 읽기가 쉬워짐
			// 여기가 실행되는 동안 windows응 잠시 block이 됩니다?! <-> 이게 끝나야 활성화가 된다.
			//block이 너무 오래되면 안되니까~~~ 하필이면 클라이언트의 접속을 무한정 기다리는 코드가 들어가야해요
			//이벤트 처리를 위한 Thread를 하기로~! 
			//스레드의 가장 큰 목적 1) block이 되지 않도록 2) 효율성을 높이기 위함
			//위에 클래스로 스레드를 만들자 
			
		});
		
		stopBtn = new Button("서버정지");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
		 // 이벤트 처리 Lambda가 나와요.
		 
		 
			
	
	    
	    });
		
		//보더펜의 아래부분에 버튼을 부착시키고 싶어요~!
		//공간은 하나인데 버튼은 2개에요. 붙일수가 없네요
		//일단판잘 FlowPane을 하나 만들어서 버튼 2개를 차례대로 붙이고
		//이 판자를 보더 pane의 아랫부분에 붙여요
		FlowPane flowPane = new FlowPane(); //클래스 타입의 인스턴스를 만들 떄 소문자로 바꿔서 변수를 많이 잡아요~ 단, 인스턴스 하나만 만들 때! why? 식별자는 소문자로 관용적으로 시작하거든요?!
		//이 판자에 버튼ㅇㄹ 2개 붙일거에요 그런데 설정(Padding, 정렬)을 안하면 안예뻐요! 보기가 좋지 않아요 ㅠㅠ
		//바로 설정이 들어가야 보기도 편하고 이뻐요~
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);
		flowPane.setPrefSize(700, 40);
		flowPane.setHgap(10);
		//판자 설정이 끝났으니 이제 판자에 버트을 붙어야 해요~
		
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn); //버튼 붙이기 완성 
		
		//판자를 보더 아랫부분에 붙여요
		root.setBottom(flowPane); // 전체구성 끝, 
		
		//실제 화면 구성
	    textArea = new TextArea(); // 선언을 뺴세요 맨 앞에 TextArea! 
		root.setCenter(textArea); //text area에 넣어, 필드로 올리는 이유: 살아있는 동안 다른 void나 이런데에서 쓸 수가 있어서
		
		//이런 레이아웃을 이용해서 내가 보여주고 싶은 장면(scene)을 만듦
		Scene scene = new Scene(root); //scene은 장면임
		
		primaryStage.setScene(scene);  //stage에 scene을 넣기		
		primaryStage.setTitle("Echo Server Program");
		
		primaryStage.show();  //창 띄우자!는 코드 
		
	}

	public static void main(String[] args) {
		//최초로 실행되는 메소드
		//entry point
		//GUI Thread를 하아 생성할거에요, Thread를 만드는 거니까 new Thread해야하는데
		//위와 같이 하지 않고 화면에 창을 띄우고 창이 독자적으로 돌아갈 수 있도록
		launch(); //메인쓰레드만 끝나고 GUI Thread만 남을 거에요
		
	}

}
