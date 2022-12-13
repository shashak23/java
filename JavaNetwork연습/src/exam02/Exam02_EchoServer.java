package exam02;

import javafx.application.Application;
import javafx.application.Platform;
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

public class Exam02_EchoServer extends Application {  //창을 만들거니까 어플리케이션 상속, 어플리케이션은 추상메소드이고, 이걸 오버라이드를 해야지 추상이 안됨

	
	//field
	TextArea textarea; //인자 생성, 선언 //이거를 오버라이드 밑에 하면 지역변수가 되고 날라가요!
	Button startBtn;
	Button stopBtn;
	
	private void printMsg(String msg) {
    Platform.runLater(()->{  //이렇게 해서 별도의 Thread가 따로 처리를 해줘용
    	textarea.appendText(msg +"\n");  //스레드가 처리하도록, 그래서 문자가 문제없이 나오도록
    }); 
  }
	@Override
	public void start(Stage primaryStage) throws Exception {  //start는 창을 만들고 닫아주는 실행 역할 
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textarea = new TextArea();
		root.setCenter(textarea); //어디에 어떻게 잘 설정해야하는지 봅시다.
		
		startBtn = new Button("서버시작!");
		startBtn.setPrefSize(150,40);   //사이즈를 정해주고, 스타트 버튼을 눌렀을 때 
		//클릭은 액션이벤트라고 들어가요. 액션이벤트 1)마우스 클릭 2)입력창에 엔터
		//이벤트 처리 = 리스너 객체(Handler 객체)가 담당 -> delegation model
		//리스더=핸들러 객체가 와야해서 event 자동완성기능 써서 하면 바로 오버라이딩 됨
		startBtn.setOnAction(e -> { //이거 꼭 기억하세요

		//(new EventHandler<ActionEvent>() {
				
			//@Override
			//public void handle(ActionEvent e) {}
			
			textarea.appendText("서버가 시작되었어요!" + "\n");
		    for(long k=0; k<9000000000000L; k++); //버튼을 눌렀을 때 이만큼의 작업을 하게 됨
		    //append메소드가 blocking메소드인데 이걸 실행이 되는 동안 잠깐 멈춤...
		    //이렇게 되면 안되니까
		    //순차처리를 안하기 위해서 당연히 Thread를 사용해야해요.
		    printMsg("서버가 시작되었어요!");
		    //오버라이드를 해와야 러너블을 쓸 수 있는 건데
		    //근데 그럼 코드가 너무 길어지니까 -> 축약해서 바로 위와 같이 씀
		    //gui특성 때문에 이렇게 arrow function을 쓰는거임
		    //gui때문에 쓰는게 맞음, 구조상 연결하려면 쓰는 거쥬 
		    
		    //(new Runnable() { //러너블을 불러옴 -> 쓰레드! 
				
				//@Override
				//public void run() {
					// TODO Auto-generated method stub
					
				//}
            //static은 특수한 기능을 제공해주는 건데
		    //여기서 run later을 이용한 Thread를 이용해요. 여기서 run이 실행되고
		    //그래서 이걸 갖고 thread가 위의 것을 수행할 수 있도록.
	
		}); 
		//위와 같이 쓰는게 정석이지만? 
		//이벤트가 발생하면 이벤트 객체를 가지고 오버라이딩만큼 특정 작업을 진행 함
		//이렇게 나중에 생긴 축약기능을 arrow function 에로우 펑션 이라고 함
		
		//null은 버튼을 눌러도 아무일이 일어나지 않다는 것
		
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
		primaryStage.show();  //메인에 안 넣으면 빨간 밑줄, 복구하려면 premeter로! 괄호에 넣쟈 ! 
		
	} //해당 쓰레드가 바로 위를 호출해요~ 
	
	public static void main(String[] args) {
		launch(); //실제 코드를 실행시키면 우리 창이 실행되요
	}
	

}
