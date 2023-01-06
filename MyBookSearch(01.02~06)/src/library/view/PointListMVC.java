package library.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.vo.BookVO;

public class PointListMVC {
	// 테이블 뷰 1개, 텍스트 
	Text maintext;
	Text idtext;
	Text uitext; // 회원정보 텍스트 user information
	Button uiBtn;
	Button pointBtn;
	Button bloanBtn;
	Button breturnBtn;
    Scene scene = null; // 필요에 따라 null 하기 
	
	public PointListMVC() {
		
	}

	public Parent getRoot(Stage primaryStage) {
		// layout 설정
		BorderPane root = new BorderPane();
		// Flow Pane판 만들기 
		FlowPane flowPane = new FlowPane();  // 제일 큰 판
		FlowPane flowPane2 = new FlowPane();  // library를 넣을 판
		VBox VBox3 = new VBox(); 
		FlowPane flowPane3 = new FlowPane(); // 포인트 조회 결과 붙일 판
		// component 생성하기 
		flowPane.setPrefSize(900, 400);
		flowPane.setAlignment(Pos.CENTER);
		
		flowPane2.setPrefSize(500, 100);
		flowPane2.setAlignment(Pos.CENTER);
		flowPane2.setVgap(30);
		
		VBox3.setPrefSize(200, 250);
		// 포인트 조회 결과 붙일 판
		flowPane3.setPrefSize(600, 300);
		// 텍스트 넣기
		maintext = new Text("포인트 내역");
		maintext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		idtext = new Text("내 아이디 : "); 
		idtext.setWrappingWidth(90);
		// 버튼 만들기
		uiBtn = new Button("회원정보");
		uiBtn.setPrefSize(100 , 30);

		bloanBtn = new Button("대출조회");
		bloanBtn.setPrefSize(100, 30);

		breturnBtn = new Button("반납내역");
		breturnBtn.setPrefSize(100,30);
		
		// tableView 만들기
		//tableView = new TableView<PointVO>();

		
		
		return root;
	}

}
