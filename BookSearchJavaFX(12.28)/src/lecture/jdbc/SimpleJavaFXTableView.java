package lecture.jdbc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class SimpleJavaFXTableView extends Application {
	// textarea
	// tableview 안에 데이터를 표현할 때 vo를 가져다가 한 줄 식 표현하게
	// 그때 어떤 vo를 사용할 지 class이름을 
	TableView<BookVO> tableView;
	TextField textField;
	TextArea textArea;


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		// 1. layout 설정
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500);
		
		// 2. border pane 아래쪽에 붙일 판자를 하나 생성, 속성 설정
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(250,40);
		flowpane.setHgap(10);
		
		// 3. 각각의 component를 생성해서 좋아요
		textField = new TextField();
		textField.setPrefSize(250,40);
		flowpane.getChildren().add(textField);
		
		// 눈에 보이기 위해 textarea를 했지만
		// textArea = new TextArea();
		
		// 컬럼 객체를 생성해요!
		// TableColumn<해당 컬럼에 나오는 데이터를 어떤 vo에서 가져오는지 그 vo의 클래스 명시
		// , VO에서 값을 가져올 때 사용하는 데이터 타입> 
		// isbn column 은 사이즈를 지정하는 코드 
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN"); // ()괄호안에 인자는 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150); // 이 밑으로는 작게 할 수 없다
		// 해당 컬럼의 데이터는 VO의 어떤 field에서 값을 가져올지를 설정
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = 
				new TableColumn<>("TITLE"); // ()괄호안에 인자는 화면에 보여지는 컬럼의 이름
		titleColumn.setMinWidth(150); // 이 밑으로는 작게 할 수 없다
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<BookVO, String> authorColumn = 
				new TableColumn<>("AUTHOR"); // ()괄호안에 인자는 화면에 보여지는 컬럼의 이름
		authorColumn.setMinWidth(150); // 이 밑으로는 작게 할 수 없다
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, String> PriceColumn = //타입이 int여서 얘만 다름
				new TableColumn<>("PRICE");
		PriceColumn.setMinWidth(150); 
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		
		// table view에 포현할 데이터를 만들어 보아요
		// table view에 데이터를 미얼넢을 때는 array list와 유사한 객체를 사용
		// 아래의 두 list는 살짝 달라요. 그와 유사한 역할을 하는 옵저버에이블 리스트를 씁니다
		// 사용하는 건 어레이리스트랑 똑같네? 
		ObservableList<BookVO> list = FXCollections.observableArrayList();
		
		
		// ArrayList<BookVO> list = new ArrayList<BookVO>();
		list.add(new BookVO("123","자바 30일 완성","홍길동",2000)); // 어레이 리스트를 이용해서 책을 차곡차곡 모아두기를 하기 위한 코드 
		list.add(new BookVO("456","자바 눈누난나","신사임당",2000));
		list.add(new BookVO("789","자바 뽕뽑기","강감찬",2000));
		
		
		tableView = new TableView<BookVO>();
		
		// 여기에다가 만든 객체를 붙일거에요 
		tableView.getColumns().addAll(isbnColumn, titleColumn, authorColumn, PriceColumn);
		
		//table 세팅하기 결과에 리스트가 나오도록
		tableView.setTitle(list);
				
		
		root.setCenter(textArea);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple JavaFX TableView");
		
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch();
	
	}

}
