package library.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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


public class BookReturn {
	// 필드 생성
	 TableView<UserVO> tableView; // 검색결과
	 Text bretruntext; // 반납내역 
	 Text idtext;
	 Button uiBtn;
	 Button pointBtn;
	 Button bloanBtn;
	 Button mainBtn; // 메인홈페이지 버튼 
	 Scene scene = null;
	 PointListMVC plmvc= null;
	 UserInformationMVC uimvc= null;
	 
	 public BookReturn( ) {
		 
	 }
	 
		public BorderPane getRoot(Stage primaryStage) {
			// layout -- 
					BorderPane root = new BorderPane();
					root.setPrefSize(900, 600);
					
					FlowPane flowPane = new FlowPane();  // 제일 큰 판
					FlowPane flowPane2 = new FlowPane();  // library를 넣을 판
					VBox VBox3 = new VBox();  // 왼쪽 정렬 넣을 판
					FlowPane flowPane3 = new FlowPane(); // 검색결과 넣을 판 
				    // 전체 창 
					flowPane.setPrefSize(900, 400);
					flowPane.setAlignment(Pos.CENTER);
					flowPane.setColumnHalignment(HPos.CENTER);
					// library 판
					flowPane2.setPrefSize(500, 100);   
					flowPane2.setAlignment(Pos.CENTER);
					flowPane2.setVgap(30);
					// 왼쪽 정렬 넣을 판
					VBox3.setPrefSize(200, 250);
					// 검색결과 넣을 판
					flowPane3.setPrefSize(600, 300);
					// 반납내역 텍스트 넣기
					bretruntext = new Text("반납 내역");
					bretruntext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
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
					// bloan 버튼 넣기
					bloanBtn = new Button("대출조회");
					bloanBtn.setPrefSize(100, 30);
					bloanBtn.setOnAction(e -> {
						BookLoan bl = new BookLoan(); // 생성해서 
						scene = new Scene(bl.getRoot(primaryStage)); // 신에다가 넣기 1
						primaryStage.setScene(scene); // primaryStage 신 연결
					});
					// 메인홈페이지 이동 버튼 넣기
					mainBtn = new Button("메인 홈페이지 이동");
					mainBtn.setPrefSize(200, 30);
					mainBtn.setOnAction( e-> {
						MainMVC mmvc = new MainMVC();
						scene.setRoot(mmvc.getRoot(primaryStage));
						primaryStage.setScene(scene);
						
					});

					
					// column 생성하고 
					TableColumn<UserVO, String> isbnColumn = 
							new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
					isbnColumn.setMinWidth(150);
					isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
					
					TableColumn<UserVO, String> titleColumn = 
							new TableColumn<>("서명");  // ISBN은 화면에 보여지는 컬럼의 이름
					titleColumn.setMinWidth(150);
					titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));

					TableColumn<UserVO, String> authorColumn = 
							new TableColumn<>("저자");  // ISBN은 화면에 보여지는 컬럼의 이름
					authorColumn.setMinWidth(150);
					authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
					
					TableColumn<UserVO, Integer> priceColumn = 
							new TableColumn<>("가격");  // ISBN은 화면에 보여지는 컬럼의 이름
					priceColumn.setMinWidth(150);
					priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));

					// tableView 검색결과 user list 
					tableView = new TableView<UserVO>();
					
					// 위에서 만들어진 컬럼객체를 TableView에 붙여요!
					tableView.getColumns().addAll(isbnColumn, 
							titleColumn, authorColumn, priceColumn);
	
					//이어 붙이기
					flowPane2.getChildren().add(bretruntext);
					flowPane3.getChildren().add(tableView);
					VBox3.getChildren().addAll(idtext, uiBtn, pointBtn, bloanBtn, mainBtn);
					VBox3.setAlignment(Pos.CENTER);
					VBox3.setSpacing(10);
					
				    scene = new Scene(root);
				    
					root.setTop(flowPane2);
					root.setLeft(VBox3);
					root.setCenter(flowPane3);
					
					return root;		
		}
	

}
