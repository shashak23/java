package library.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignupMVC {
	// 버튼 1개, 텍스트 5개
	Button signupBtn; // 맨 아래 회원가입 완료 버튼
	TextField nametextField;
	TextField idtextField;
	PasswordField passwordtextField;
	TextField birthtextField;
	Text signputext; // 맨위 회원가입 텍스트
	Text nametext;
	Text idtext;
	Text pwtext;
	Text birtext; // 생년월일 텍스트
	Text managetext; // 관리자 모드 텍스트 
	Scene scene = null;
	BorderPane logmvc = null;
	Stage primaryStage = null;


	public SignupMVC(Scene scene, BorderPane logmvc, Stage primaryStage) {
		super();
		this.scene = scene;
		this.logmvc = logmvc;
		this.primaryStage = primaryStage;
	}



	public BorderPane getRoot(Stage primaryStage) {
		// layout -- text field를 세로 정렬, text 세로 정렬, 체크포인트1개(관리자구분), 
		BorderPane root = new BorderPane();
//		root.setPrefSize(900, 600);
		
		// 판만들기
		FlowPane flowPane = new FlowPane();  // 메인 글씨 넣을 판 
		VBox VBox = new VBox();  // 가운데 정렬 넣을 판
		VBox VBox2 = new VBox();  // 체크박스 넣을 판
		FlowPane flowPane2 = new FlowPane();  // 회원가입 버튼 넣을 판
		FlowPane fp = new FlowPane();
		FlowPane fp2 = new FlowPane();
		FlowPane fp3 = new FlowPane();
		FlowPane fp4 = new FlowPane();

		// 회원가입 버튼 넣을 판
//		flowPane.setPrefSize(700, 100);
		flowPane.setHgap(30);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setColumnHalignment(HPos.CENTER);
		// 회원가입 텍스트 판
		flowPane2.setPrefSize(200,100);
		flowPane2.setAlignment(Pos.CENTER);
		
		// 회원가입 버튼 넣는 버튼
		signupBtn = new Button("회원가입 완료");
		signupBtn.setPrefSize(130, 70);
		signupBtn.setOnAction(e -> {
			// 다이어로그로 하기
			Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("회원가입 성공");
            ButtonType type = new ButtonType("로그인 이동", ButtonData.OK_DONE);
            String str = "회원가입이 완료됐습니다";
            dialog.setContentText(str);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.getDialogPane().setMinHeight(300);
            dialog.showAndWait(); // 닫기할 때까지 기다리는 거
            
            // 화면전환 설정하기
            scene.setRoot(logmvc);
            primaryStage.setScene(scene);
            
            
		
		});
		
		nametextField = new TextField();
		nametextField.setPrefSize(150, 100);
		
		idtextField = new TextField();
		idtextField.setPrefSize(150, 100);
		
		passwordtextField = new PasswordField();
		passwordtextField.setPrefSize(150, 100);
		
		birthtextField = new TextField();
		birthtextField.setPrefSize(150, 100);
		
		// 회원가입 상단 텍스트 넣기
		signputext = new Text("회원가입"); 
		signputext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		// 회원가입 정보 기입란 v box
		//줄4개 판 4개
		Label l1 = new Label("이름");
		Label l2 = new Label("ID");
		Label l3 = new Label("PW");
		Label l4 = new Label("생년월일");
		l1.setPrefSize(80, 100);
		l2.setPrefSize(80, 100);
		l3.setPrefSize(80, 100);
		l4.setPrefSize(80, 100);
		l1.setAlignment(Pos.CENTER_RIGHT);
		l2.setAlignment(Pos.CENTER_RIGHT);
		l3.setAlignment(Pos.CENTER_RIGHT);
		l4.setAlignment(Pos.CENTER_RIGHT);
		// v box에 판 붙이고, 라벨붙이기 
		fp.getChildren().add(l1);
		fp2.getChildren().add(l2);
		fp3.getChildren().add(l3);
		fp4.getChildren().add(l4);
		// 판에다가 텍스트필드 넣기 
		fp.getChildren().add(nametextField);
		fp2.getChildren().add(idtextField);
		fp3.getChildren().add(passwordtextField);
		fp4.getChildren().add(birthtextField);
		// 판 중앙으로
		fp.setAlignment(Pos.CENTER);
		fp2.setAlignment(Pos.CENTER);
		fp3.setAlignment(Pos.CENTER);
		fp4.setAlignment(Pos.CENTER);
		// 판 간격두기
		fp.setHgap(10);
		fp2.setHgap(10);
		fp3.setHgap(10);
		fp4.setHgap(10);
		// v box 설정
		VBox.getChildren().add(fp);
		VBox.getChildren().add(fp2);
		VBox.getChildren().add(fp3);
		VBox.getChildren().add(fp4);
		//v box 중앙에 두기 
		VBox.setAlignment(Pos.CENTER);
		VBox.setSpacing(10);
		// 판에 텍스트랑 버픈 붙이기 
		flowPane.getChildren().add(signupBtn);
		flowPane2.getChildren().add(signputext);
		// 판을 보더판에 붙이기 
		root.setTop(flowPane2);
		root.setCenter(VBox);
		root.setBottom(flowPane);
		
		return root;

		
	}	
	//팝업 만들기 회원가입완료되었습니다 하고 바로 로그인 (다이어로그 이용)
//    public class popup {
//    	public static void main(String[] args) {
//			// 팝업 만들기
//    		JOptionPane.showMessageDialog(null, "회원가입이 완료됐습니다.");
//    		// 버튼을 넣어야하나 ? 
//    		
//		}
//    }
//	
	
	
}
