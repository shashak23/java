package exam02;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam02_EchoServer extends Application {  //창을 만들거니까 어플리케이션 상속, 어플리케이션은 추상메소드이고, 이걸 오버라이드를 해야지 추상이 안됨

	
	//field
	TextArea textarea; //인자 생성, 선언 //이거를 오버라이드 밑에 하면 지역변수가 되고 날라가요!
	Button startBtn;
	Button stopBtn;
	
	@Override
	public void start(Stage arg0) throws Exception {  //start는 창을 만들고 닫아주는 실행 역할 
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textarea = new TextArea();
		root.setCenter(textarea); //어디에 어떻게 잘 설정해야하는지 봅시다.
		
		startBtn = new Button("서버시작!");
		startBtn.setPrefSize(150,40);   //사이즈를 정해주고, 스타트 버튼을 눌렀을 때 
		//클릭은 액션이벤트라고 들어가요. 액션이벤트 1)마우스 클릭 2)입력창에 엔터
		startBtn.setOnAction(null); //null은 버튼을 눌러도 아무일이 일어나지 않다는 것
		
		stopBtn = new Button("서버중지!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(null); //종료버튼을 누르고 생기는 일을 넣기 
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10));  //flow pane에 padding을 잡으면 나중에 웹에서 신센스랑 비슷해요, 픽셀로 나눔
		flowPane.setColumnHalignment(HPos.CENTER);  //가운데로 정렬!
		flowPane.setHgap(10);  //h gap은 버튼사이 간격

		flowPane.getChildren().add(startBtn); //이건 정해진 것임 걍 외우셈
		flowPane.getChildren().add(stopBtn); 
		
		root.setBottom(flowPane);  //이거 없음 안되용
		
		Scene scene = new Scene(root); //인자 생성 선언
		primaryStage.setScene(scene); //창을 띄워요 

		primaryStage.setTitle("Echo Server Program");
		primaryStage.show();
		
	} //해당 쓰레드가 바로 위를 호출해요~ 
	
	public static void main(String[] args) {
		launch(); //실제 코드를 실행시키면 우리 창이 실행되요
	}
	

}
