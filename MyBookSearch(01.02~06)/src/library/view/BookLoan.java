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
import library.vo.BookVO;
import library.vo.UserVO;

public class BookLoan {
	// 필드 생성
	 TableView<UserVO> tableView; // 검색결과
	 Text idtext;
	 Text bloantext; // 대출조회 메인 위치 텍스트
	 Button uiBtn;
	 Button pointBtn;
	 Button breturnBtn; // 반납하기 버튼
	 Button mainhBtn; // 메인홈페이지 버튼 
	 Scene scene = null;
	 BookReturn br = null;
	 PointListMVC plmvc = null;
	 UserInformationMVC uimvc = null;
	 MainMVC mmvc = null;
		 
	public BookLoan() {
		
	}
	
	public BorderPane getRoot(Stage primaryStage) {
		// layout -- 전체판(900.500), 두 번째 판(main 글씨판), V box3판(아이디, 포인트, 반납내역, 메인홈페이지), 3번째판(대출조회 결과)
		BorderPane root = new BorderPane();
		root.setPrefSize(900, 600);
		
		FlowPane flowPane = new FlowPane();  // 
		FlowPane flowPane2 = new FlowPane();  // 대출조회 텍스트를 넣을 판
		VBox VBox3 = new VBox();  // 왼쪽 정렬 넣을 판
		FlowPane flowPane4 = new FlowPane(); // 대출조회 결과 나올 판
		// 전체 창
		flowPane.setPrefSize(900, 400);
		flowPane.setAlignment(Pos.CENTER);
		// 대출현황 판
		flowPane2.setPrefSize(500, 100);
		flowPane2.setAlignment(Pos.CENTER);
		// 왼쪽 정렬 판
		VBox3.setPrefSize(200, 250);
		VBox3.setAlignment(Pos.CENTER);
		// 대출조회 결과 넣을 판
		flowPane4.setPrefSize(700, 100);
		flowPane4.setAlignment(Pos.CENTER);
		
		// id text 넣기
		idtext = new Text("내 아이디 : " );
		idtext.setWrappingWidth(90);
    	// 회원정보 버튼 넣기
		uiBtn = new Button("회원정보");
		uiBtn.setPrefSize(100 , 30);
		uiBtn.setOnAction( e-> {
		     uimvc = new UserInformationMVC();
		     scene = new Scene(uimvc.getRoot(primaryStage));
		     primaryStage.setScene(scene);
		
	    });
		
		// point 버튼 넣기
		pointBtn = new Button("내 포인트");
		pointBtn.setPrefSize(100, 30);
		pointBtn.setOnAction(e -> {
		    plmvc = new PointListMVC();
	        scene = new Scene(plmvc.getRoot(primaryStage));
	        primaryStage.setScene(scene);
         });
		// 반납조회 버튼 넣기 
		breturnBtn = new Button("반납내역");
		breturnBtn.setPrefSize(100, 30);
		breturnBtn.setOnAction( e-> {
			br = new BookReturn();
			scene = new Scene(br.getRoot(primaryStage));
			primaryStage.setScene(scene);
			
		});
		// 메인홈페이지 이동 버튼 넣기
		mainhBtn = new Button("메인 홈페이지 이동");
		mainhBtn.setPrefSize(150, 30);
		mainhBtn.setOnAction( e-> {
			mmvc = new MainMVC();
			scene.setRoot(mmvc.getRoot(primaryStage));
			primaryStage.setScene(scene);
			
		});


		// loan list text 넣기sssss
		bloantext = new Text("대출 조회");
		bloantext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		//이어 붙이기
		flowPane2.getChildren().add(bloantext);

		VBox3.getChildren().addAll(idtext, uiBtn, pointBtn, breturnBtn, mainhBtn);
		VBox3.setAlignment(Pos.CENTER);
		VBox3.setSpacing(10);
		
		//table view 넣기 
		
		root.setTop(flowPane2);
		root.setLeft(VBox3);
		root.setCenter(flowPane4);
		
		return root;
		
		
	}
}
