package library.view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.vo.UserVO;


public class LoginMVC extends Application {
	//필드 입력하기
	Text maintext;
	Text idtext;
	Text pwtext;
	TextField maintextField;
	TextField idtextField;
	PasswordField pwtextField; // 패스워드는 passwordField 자동완성기능으로 하면 됨!
	Button loginBtn;
	Button signupBtn;
	String searchKeyword;
	Scene scene = null;
	BorderPane root = null;
	Stage primaryStage = null;
	MainMVC mainmvc = null;
	UserInformationMVC uimvc;
	BookLoan bl;
	BookReturn br;
	PointListMVC plmvc;

	
	public LoginMVC() {
		
	}
	
//	id,pw 치면 맞는 아이디, 패스워드인지 확인하고 정확하면 들어가지는 것
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	    // 1. layout 입력란 2개, 버튼 2개
		// 2. BorderPane 판자를 하나 생성해서 버튼을 두 개를 붙이기
		BorderPane root = new BorderPane();
		root.setPrefSize(900, 600);
				
		// 3. flow pane 총 4개를 만들어서 원하는 디자인대로 flow pane마다 넣기
		FlowPane flowPane = new FlowPane();  // 얘는 로그인, 회원가입버튼을 붙이는 판
		FlowPane flowPane2 = new FlowPane();  // 제일 큰 판 
		FlowPane flowPane3 = new FlowPane();  // 도서관 이름 넣을 판
		// id 와 pw를 판을 나눠서 넣기!
		FlowPane fp = new FlowPane(); // id을 넣을 판
		FlowPane fp2 = new FlowPane(); // pw을 넣을 판
	
		flowPane2.setPrefSize(700, 400); // 가장 큰 애
		flowPane2.setVgap(10);
		flowPane2.setOrientation(Orientation.VERTICAL);
		flowPane2.setAlignment(Pos.CENTER);
		flowPane2.setColumnHalignment(HPos.CENTER);

		flowPane3.setPrefSize(500, 100);      // library main
		flowPane3.setAlignment(Pos.CENTER);
		flowPane3.setVgap(30);
		
		flowPane.setPrefSize(700, 100);
		flowPane.setHgap(30);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setColumnHalignment(HPos.CENTER);
		
		fp.setPrefSize(700, 100);
		fp.setHgap(10);
		fp.setAlignment(Pos.CENTER);
		fp.setColumnHalignment(HPos.CENTER);
		
		fp2.setPrefSize(700, 100);
		fp2.setHgap(10);
		fp2.setAlignment(Pos.CENTER);
		fp2.setColumnHalignment(HPos.CENTER);

		
		// 4. textField 생성, 사이즈, 이벤트 -- id, pw 2개
		maintext = new Text("Library");
		maintext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		
		idtext = new Text("ID");
		idtext.setWrappingWidth(50); // text가 들어갈 공간
		idtextField = new TextField();
		idtextField.setPrefSize(350, 70);
		idtextField.setOnAction(e -> {
			//입력하기 아이디 그대로 보이기
		});
		 
		pwtext = new Text("PW");
		pwtext.setWrappingWidth(50);
		pwtextField = new PasswordField();
		pwtextField.setPrefSize(350, 70);
		pwtextField.setOnAction(e -> {
			// 입력하기 점으로 표시하기 
		});
		
		// 5. button 생성, 사이즈, 이벤트 -- login, sign in
		loginBtn = new Button("로그인");
		loginBtn.setPrefSize(130, 70);
		loginBtn.setOnAction(e -> {
			mainmvc = new MainMVC(scene, plmvc, br, bl, uimvc);
			scene = new Scene(mainmvc.getRoot(primaryStage));
			primaryStage.setScene(scene);
		});
		
		signupBtn = new Button("회원가입");
		signupBtn.setPrefSize(130, 70);
		signupBtn.setOnAction(e -> {
			// 버튼을 클릭하면 회원가입 페이지로 넘어가기 
			SignupMVC smvc = new SignupMVC(scene, root, primaryStage); // 순서대로!
			Scene scene = new Scene(smvc.getRoot(primaryStage)); // 신에다가 넣기 
			primaryStage.setScene(scene); // primaryStage 신 연결
		});
		
		// 6. 밑에 판자에다가 loginBtn이랑 signinBtn 붙이기
		flowPane.getChildren().add(loginBtn);
		flowPane.getChildren().add(signupBtn);
		
		// 7. root.setcenter 하고 scene 하고 primary Stage(파라미터)하고 show하고
		flowPane3.getChildren().add(maintext);
		
		fp.getChildren().add(idtext);
		fp.getChildren().add(idtextField);
		
		fp2.getChildren().add(pwtext);
		fp2.getChildren().add(pwtextField);
		
		flowPane2.getChildren().add(fp);
		flowPane2.getChildren().add(fp2);
		
		scene = new Scene(root);
		
		root.setTop(flowPane3);
		root.setCenter(flowPane2);
		root.setBottom(flowPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("LoginMVC");
		primaryStage.setOnCloseRequest(e-> {
			
		});
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}


}
