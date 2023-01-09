package library.view;

import java.awt.print.Book;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.controller.BookSearchController;
import library.vo.BookVO;


public class MainMVC {
	// 생성, 텍스트(point, id, u i, library, search)
	Text maintext;
	Text idtext;
	Text uitext; // 회원정보 텍스트 user information
	String searchKeyword;
	// 버튼 필드 생성, 버튼(검색, 회원정보, 포인트)
	Button uiBtn;
	Button pointBtn;
	Button bloanBtn;
	Button bllBtn;
	Button breturnBtn;
	// 검색버튼, 검색텍스트필드
	TextField searchtextField;
	Button searchBtn;
	// 다음 신 필드 생성
    Scene scene = null;
	PointListMVC plmvc = null;
	BookReturn br = null;
	BookLoan bl = null;
	UserInformationMVC uimvc = null;
	//테이블 뷰
	TableView<BookVO> tableView; // 검색결과
	Parent logmvc;
	MainMVC mainmvc;


	public MainMVC(Scene scene, PointListMVC plmvc, BookReturn br, BookLoan bl, UserInformationMVC uimvc) {
		super();
		this.scene = scene;
		this.plmvc = plmvc;
		this.br = br;
		this.bl = bl;
		this.uimvc = uimvc;
	}


	public BorderPane getRoot(Stage primaryStage) {

		// 레이아웃 구성, 판 총 3개, 맨위(), 왼쪽(), 맨 위 아래
		BorderPane root = new BorderPane();
		//root.setPrefSize(900, 600);
        // 판 만들기 
		FlowPane flowPane = new FlowPane();  // 제일 큰 판
		FlowPane flowPane2 = new FlowPane();  // library를 넣을 판
		VBox VBox3 = new VBox();  // 왼쪽 정렬 넣을 판
		FlowPane flowPane3 = new FlowPane(); // 검색결과 넣을 판 
		FlowPane flowPane4 = new FlowPane();  // 맨 아래 정렬

		
		//제일 큰 판
		flowPane.setPrefSize(900, 700);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setColumnHalignment(HPos.CENTER);
		// library 판
		flowPane2.setPrefSize(200, 100);   
		flowPane2.setAlignment(Pos.CENTER);
		flowPane2.setVgap(30);
		// 왼쪽 정렬 넣을 판
		VBox3.setPrefSize(200, 100);
		//검색 결과 넣을 판
		flowPane3.setPrefSize(400, 300);
		// 맨아래 정렬 넣을 판 
		flowPane4.setPrefSize(700, 100);
		flowPane4.setAlignment(Pos.CENTER);
		flowPane4.setVgap(10);
		
		// library text 넣기
		maintext = new Text("Library");
		maintext.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		// 검색 텍스트필드 // 처음부터 표를 구현해두는 것 
		searchtextField = new TextField();
		searchtextField.setPrefSize(450,50);
		searchtextField.setOnAction(e -> {
			BookSearchController controller = 
					new BookSearchController();
			
			ObservableList<BookVO> list = 
					controller.getResult(searchtextField.getText());
			
			tableView.setItems(list);
			
			searchKeyword = searchtextField.getText();
			searchtextField.clear();
		});
		
		// 검색 입력, 검색 버튼
		searchBtn = new Button("검색");
		searchBtn.setPrefSize(100, 50);
		searchBtn.setOnAction(e -> {
			BookSearchController controller = 
					new BookSearchController();
			
			ObservableList<BookVO> list = 
					controller.getResult(searchtextField.getText());
			
			tableView.setItems(list);
			
			searchKeyword = searchtextField.getText();
			searchtextField.clear();
		});
		
		bloanBtn = new Button("대출하기");
		bloanBtn.setPrefSize(100, 50);
		bloanBtn.setOnAction( e -> {
			// 다이어로그로 하기 -- 책(row)을 선택하고 대출하기 버튼을 누르면 대출이 완료되었습니다. 다이어로그 뜨기 
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("대출 성공");
			ButtonType type = new ButtonType("뒤로가기", ButtonData.OK_DONE);
			ButtonType ty= new ButtonType("대출현황", ButtonData.OK_DONE);
			String str = "대출처리가 완료됐습니다";
			dialog.setContentText(str);
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.getDialogPane().getButtonTypes().add(ty);
			dialog.getDialogPane().setMinHeight(300);
			dialog.showAndWait(); // 닫기할 때까지 기다리는 거
			
			//대출현황 버튼을 누르면 창이 넘어가는 거
			
			dialog.initOwner(primaryStage);
			
		});
		

		// id text 넣기
		idtext = new Text("내 아이디 : " ); // id 넣어야s해요
		idtext.setWrappingWidth(90);

		// 회원정보 버튼 넣기
		uiBtn = new Button("회원정보");
		uiBtn.setPrefSize(100 , 30);
		uiBtn.setOnAction(e -> {
			// 액션 이벤트 넣기
			uimvc = new UserInformationMVC(scene, br, plmvc, bl, mainmvc, uimvc);
			scene = new Scene(uimvc.getRoot(primaryStage));
			primaryStage.setScene(scene);
			
		});
		
		bllBtn = new Button("대출현황");
		bllBtn.setPrefSize(100, 30);
		bllBtn.setOnAction( e -> {
			bl = new BookLoan(scene, br, bl, plmvc, uimvc, mainmvc);
			scene.setRoot(logmvc);
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
		
		
		// b return 버튼 넣기
		breturnBtn = new Button("반납내역");
		breturnBtn.setPrefSize(100,30);
		breturnBtn.setOnAction( e-> {
			// 액션 이벤트 만들기
			br = new BookReturn(scene, plmvc, uimvc, mainmvc, br, bl);
			scene = new Scene(br.getRoot(primaryStage));
			primaryStage.setScene(scene);
			
		});
		// column 생성하고 
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = 
				new TableColumn<>("서명");  
		titleColumn.setMinWidth(150);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));

		TableColumn<BookVO, String> authorColumn = 
				new TableColumn<>("저자");  
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> pageColumn = 
				new TableColumn<>("페이지"); 
		pageColumn.setMinWidth(150);
		pageColumn.setCellValueFactory(new PropertyValueFactory<>("bpage"));
		
		TableColumn<BookVO, String> dateColumn = 
				new TableColumn<>("출판년도"); 
		dateColumn.setMinWidth(150);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("bdate"));
		
		TableColumn<BookVO, Integer> priceColumn = 
				new TableColumn<>("가격");  
		priceColumn.setMinWidth(150);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));

		// tableView 검색결과 book list 
		tableView = new TableView<BookVO>();
		
		// 위에서 만들어진 컬럼객체를 TableView에 붙여요!
		tableView.getColumns().addAll(isbnColumn, 
				titleColumn, authorColumn, priceColumn);
		
		//이어 붙이기
		flowPane2.getChildren().add(maintext);
		flowPane3.getChildren().add(tableView);
		
		flowPane4.getChildren().add(searchtextField);
		flowPane4.getChildren().add(searchBtn);
		flowPane4.getChildren().add(bloanBtn);

		VBox3.getChildren().addAll(idtext, uiBtn, pointBtn, bllBtn, breturnBtn);
		VBox3.setAlignment(Pos.CENTER);
		VBox3.setSpacing(10);
		
		// scene에 붙이기
		root.setTop(flowPane2);
		root.setLeft(VBox3);
		root.setCenter(flowPane3);
		root.setBottom(flowPane4);
		
		return root; // 신을 따로 생성하지 말고 root(border pane)을 반환하기 
	}

}
