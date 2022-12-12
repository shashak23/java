package javafxexam;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {  //순서1 어플리케이션은 창만 뜨는 애에요
	
	@Override
	public void start(Stage primaryStage) throws Exception { //순서2 start() override, 내부적으로 콜함
		//화면 구성하는 작업을 여기서 함당
		//간단한 코드 만들어보기
		Button btn = new Button();  //순서4 버튼은 사실 여러가지임당
		btn.setText("안녕!");   //순서5 버튼 설정을 해도 얘는 반응을 안해요 
		//버튼에 이벤트 처리를 하는곤데 이게 모든 사용자의 행위를 지칭해요~~ 
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			//순서 10
			@Override 
			public void handle(ActionEvent arg0) {
				//이벤트가 발생하면 자동으로 호출되는 군요 
				System.out.println("안녕하세요!"); //버튼을 누를 때마다 액션이 발생하고 액션발생하면 리서드가 시작되면서
				//결과로 넣은 안녕하세요가 나옴? 맞아?

				
			}
		}); //마감처리 주의 
		
		{
			// 순서9 버튼에 리스너를 붙이려고해, 버튼에 액션이라는 이벤트를 처리할 수 잇는 리스너 객체를 붙여요
		}
		
		
		StackPane root = new StackPane();
		//스텍펜이란? 안녕!을 어떻게 붙일건지....배고파...위치 잡아 주는 것을 layout을 한다.
		//레이아웃이 위치나 설정을 조정해주는 것~~~적절하게 화면이 이뻐 보이게 하는 것! 일단  stackpane을 layout에 붙어야해요!
		//StackPane => layout을 중 추가되는 순서대로 덧붙여서 기가화하는 layout! // layout칸을 root라고 했어요
		
		root.getChildren().add(btn);
		//순서 4 여기서 루트는 layout이고 겟칠드런은 객체이고 btn이 안녕이고 추가는 그냥 쓸 수가 없어서 .에 붙임
		
		//한 화면을 지칭하는 레이아웃을 만들기 위해 그걸 포함하는 객체를 만들어야하는데 그게 :Scene이얌
				
		//순서 6 Scene 객체 생성
		Scene scene = new Scene(root, 300, 150); //여기에 레이아웃, 가로세로길이까지 넣으면 되용
		//장면을 어디에 담아야 하나? 나는 윈도우 그 작업을 하는 게 Stage임
		//그렇게 Scene을 감싸는 stage가 생김 - 실제 윈도우의 역할을 하는 것. stage > scene > layout > button > "안녕!"컴포넌트
		//작업중인 프로그램명와 닫기버튼 이런거 갖고 잇는데 레이아웃이에요
		
		//순서8 
		primaryStage.setTitle("연습입니다.");  //set타입은 작업 중인 프로그램명으로 들어감
		primaryStage.setScene(scene);
		primaryStage.show(); //위에 ()제시가 되어있는 것을 활용해서 동작을 순서대로 기입
		
		
	}
	
	//메인 메소드
	private static void main(String[] args) {
		launch();   //순서3 누웠으니까 static, 화면에는 창이 뜨는데 그게 Thread이고 메인이 스레드가 중지되고 다른 스레드가 호출이 됐고
		            //gui스레드가 자동으로 하나 만들어지면서 start();라는 메소드를 콜함다 -> 직접적으로 호출을 하지 않아요
		            //스레드가 별도로 만들어지진 않아요~ launch하면 창이 뜹니다?! 
		//gui스레드가 실행되고 화면에 창이 계속 뜬다 
		//Java는 delegation model을 이용해요 -> web javaScript도 이 방식을 이용해요
		//클라이언트 화면은 이렇게 만들어져요
		//Layout을 설정해요(컴포넌트가 붙는 방식을 결정하는 객체)

		
	}

}
