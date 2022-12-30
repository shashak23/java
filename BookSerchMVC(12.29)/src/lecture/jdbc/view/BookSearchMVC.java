package lecture.jdbc.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.controller.BookSearchByKeywordController;
import lecture.jdbc.controller.DeleteByISBNController;
import lecture.jdbc.vo.BookVO;

//view 역할을 하는 클래스! // 화면 구성은 똑같으니까 복붙해도 여기서 나오면 안되는 건 지우자! 
public class BookSearchMVC extends Application {
	TableView<BookVO> tableView;	
	TextField textField;
	Button deleteBtn;
	String deleteISBN;
	String searchKeyword;
	
//	생성자(준비단계, 초기화단계.. 앗..여기에서 JDBC 준비작업을 하면 되겠네요!!)
//	public BookSearchMVC() {		
//		try {
//			// 1. JDBC Driver Loading 단계
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		// 1. layout부터 설정해야 해요!
		// BorderPane을 이용할 꺼예요!(동서남북중앙)
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. BorderPane 아래쪽에 붙일 판자(FlowPane)를 하나 생성, 속성 설정
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		textField.setOnAction(e -> { 
		    // 이벤트 처리 들어와야 해요 
			// 우리는 지금 뷰를 이용하고 있는데 연결시켜주는 역할을 하는 controller를 이용해서 service에 로직 실행 요청을 해야해요!
			// 1. controller 객체를 만들겁니다 (클래스가 있어야지 객체를 만들겠죠?)
			BookSearchByKeywordController controller = 
					new BookSearchByKeywordController();  // 컨트롤러를 생성해서 동작을 시켜요
			
			ObservableList<BookVO> list = 
					controller.getResult(textField.getText()); // 컨트롤러야 너가 가진 결과 좀 갖구와봐요
			// 너가 텍스트필드에 있는 거 중에서 옵저버리스트를 갖고 오면 되쥬! 테이블에 셋하면 넣는 거구나! 
			// 뷰의 입장에서 넘겨줄 것과 받아줄 것을 생각해야해요 ^^? 
			tableView.setItems(list); // view 입장에서 리스트를 넣어야지 리스트가 나한테 보이겠죠?
			// 뷰가 컨트롤러를 통해서 리스트를 델꾸왔꾸 여기서 getResult에서 create method 를 하면 자동으로 짜잔! 
			searchKeyword = textField.getText();
			textField.clear();
		
		});
				
		
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			DeleteByISBNController controller = 
					new DeleteByISBNController();
			
			ObservableList<BookVO> list = 
					controller.getResult(deleteISBN, searchKeyword);
			
			tableView.setItems(list);
		});
		
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		
        //table 표현하는 거니까 두고 
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = 
				new TableColumn<>("TITLE");  // ISBN은 화면에 보여지는 컬럼의 이름
		titleColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));

		TableColumn<BookVO, String> authorColumn = 
				new TableColumn<>("AUTHOR");  // ISBN은 화면에 보여지는 컬럼의 이름
		authorColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> priceColumn = 
				new TableColumn<>("PRICE");  // ISBN은 화면에 보여지는 컬럼의 이름
		priceColumn.setMinWidth(150);
		// 해당컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정!
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		
		// TableView에 표현할 데이터를 만들어 보아요!
		// TableView에 데이터를 밀어넣을때는.. ArrayList와 유사한 List를 사용 
		
		tableView = new TableView<BookVO>();
		
		// 위에서 만들어진 컬럼객체를 TableView에 붙여요!
		tableView.getColumns().addAll(isbnColumn, 
				titleColumn, authorColumn, priceColumn);
		
		// 나중에 TableView에 데이터가 표현될꺼예요! 
		// 이때 각 행들에 대해서 이벤트를 설정할 수 있어요.
		// 정확하게 얘기하자면 각 행들에 대한 특정 설정을 할 수 있어요!
		tableView.setRowFactory(e -> {
			// TableRow(테이블의 각 행)을 만들어서
			TableRow<BookVO> row = new TableRow<>();			
			// 해당 행에 이벤트 처리를 설정한 다음
			row.setOnMouseClicked(e1 -> {
				deleteBtn.setDisable(false); // 삭제버튼 활성화
				// 내가 어떤 행을 클릭했는지 확인을 해야 하니..
				BookVO book = row.getItem();
				// 삭제할 책의 ISBN을 버튼이 클리되었을때 알아내야 해요!
				deleteISBN = book.getBisbn();
			});
			// 해당 행을 리턴하는 방식
			return row;
		});
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book Search MVC");
		primaryStage.setOnCloseRequest(e -> {
			
			
		});			
		primaryStage.show();
		
	}
public static void main(String[] args) {
	launch();
}

}
