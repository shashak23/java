package library.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.vo.UserVO;

public class UserInformationMVC {
	// 텍스트 
	Text maintext;
	Text idtext;
    // v 박스 버튼 -- 3개
	Button bloanBtn;
	Button breturnBtn;
	Button pointBtn;
	Button mainBtn; // 메인 홈페로 돌아갈 버튼 
	// 가운데 정렬 판에 들어갈 버튼 -- 3개
	Button blBtn;
	Button brBtn;
	TableView<UserVO> tableView;
	Scene scene=null;
	BookReturn br=null;
	PointListMVC plmvc=null;
	BookLoan bl = null;
	MainMVC mainmvc = null;
	UserInformationMVC uimvc;

	


	public UserInformationMVC(Scene scene, BookReturn br, PointListMVC plmvc, BookLoan bl, MainMVC mainmvc,
			UserInformationMVC uimvc) {
		super();
		this.scene = scene;
		this.br = br;
		this.plmvc = plmvc;
		this.bl = bl;
		this.mainmvc = mainmvc;
		this.uimvc = uimvc;
	}




	public BorderPane getRoot(Stage primaryStage) {
		// 보더 펜 만들기
		BorderPane root = new BorderPane();
		// 플로우 펜 만들기
		FlowPane flowPane = new FlowPane(); // 전체 판
		FlowPane flowPane2 = new FlowPane(); // 메인 판
		VBox VBox = new VBox();  // 왼쪽 정렬 넣을 판
		VBox VBox2 = new VBox();  // 가운데 정렬 넣을 판
		
		flowPane.setPrefSize(700, 300);
		flowPane.setAlignment(Pos.CENTER);

		flowPane2.setPrefSize(500, 100);
		flowPane2.setAlignment(Pos.CENTER);
		
		VBox.setPrefSize(200, 250);
		
		VBox2.setPrefSize(600, 300);
		
		// component 만들기
		maintext = new Text("회원 정보");
		maintext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		// 왼족 정렬 판에 버튼
		mainBtn = new Button("메인 홈페이지 이동");
		mainBtn.setPrefSize(200, 30);
		mainBtn.setOnAction( e-> {
			mainmvc = new MainMVC(scene, plmvc, br, bl, uimvc);
			scene.setRoot(mainmvc.getRoot(primaryStage));
			primaryStage.setScene(scene);
		});

		
		// 가운데 정렬 판에 텍스트랑 버튼
		idtext = new Text("내 아이디 : " );
		
		pointBtn = new Button("내 포인트");
		pointBtn.setPrefSize(100, 30);
		pointBtn.setOnAction(e -> {
			plmvc = new PointListMVC();
			scene = new Scene(plmvc.getRoot(primaryStage));
			primaryStage.setScene(scene);
		});
		
		bloanBtn = new Button("대출조회");
		bloanBtn.setPrefSize(100, 30);
		bloanBtn.setOnAction(e -> {
			bl = new BookLoan(scene, br, bl, plmvc, uimvc, mainmvc); // 생성해서 
			scene = new Scene(bl.getRoot(primaryStage)); // 신에다가 넣기 1
			primaryStage.setScene(scene); // primaryStage 신 연결
		});
		
		breturnBtn = new Button("반납내역");
		breturnBtn.setPrefSize(100,30);
		breturnBtn.setOnAction( e-> {
			br = new BookReturn(scene, plmvc, uimvc, mainmvc, br, bl);
			scene = new Scene(br.getRoot(primaryStage));
			primaryStage.setScene(scene);
			
		});
		
		// table view <UserVO> 연결하기
		
		TableColumn<UserVO, String> idColumn = 
				new TableColumn<>("아이디");  
		idColumn.setMinWidth(150);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<UserVO, String> nameColumn = 
				new TableColumn<>("이름");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<UserVO, String> passwordColumn = 
				new TableColumn<>("비밀번호"); 
		passwordColumn.setMinWidth(150);
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("pw"));
		
		TableColumn<UserVO, String> dateColumn = 
				new TableColumn<>("생년월일");  
		dateColumn.setMinWidth(150);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<UserVO, String> tierColumn = 
				new TableColumn<>("등급");  
		tierColumn.setMinWidth(150);
		tierColumn.setCellValueFactory(new PropertyValueFactory<>("tier"));

		tableView = new TableView<UserVO>();
		tableView.getColumns().addAll(idColumn, nameColumn, passwordColumn, 
				dateColumn, tierColumn);
		
		// 붙이기!
		flowPane2.getChildren().add(maintext);
		VBox.getChildren().add(mainBtn);
		VBox2.getChildren().addAll(idtext, pointBtn, bloanBtn, breturnBtn);
		VBox2.setSpacing(10);
		
		// root 연결하기 
		root.setTop(flowPane2);
		root.setLeft(VBox);
		root.setCenter(VBox2);
		root.setBottom(flowPane);
		
		return root;
	}

}
