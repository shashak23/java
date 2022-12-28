package lecture.jdbc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class SsimpleJavaFXTableView extends Application {
	TextField textField;
	TableView<BookVO> tableView;
	TextArea textArea;
 
	@Override
	public void start(Stage primaryStage) throws Exception {
        // 1. layout 생성
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. borderpane에 붙일 판자 생성
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(250, 40);
		flowpane.setHgap(10);		
		
		// 3. component 생성
		textField = new TextField();
		textField.setPrefSize(250, 40);
		flowpane.getChildren().add(textField);
		
		// 5. 책 내용 기반 출력 내용 작성하기
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(150);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
		TableColumn<BookVO, String> titleColumn = 
				new TableColumn<>("TITLE");
		titleColumn.setMinWidth(150);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<BookVO, String> authorColumn = 
				new TableColumn<>("AUTHOR");
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> PriceColumn = 
				new TableColumn<>("PRICE");
		PriceColumn.setMinWidth(150);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		// 6. 내용 리스트업하기
		ObservableList<BookVO> list = FXCollections.observableArrayList();
		list.add(new BookVO("123","자바 30일 완성","홍길동",2000)); // 어레이 리스트를 이용해서 책을 차곡차곡 모아두기를 하기 위한 코드 
		list.add(new BookVO("456","자바 눈누난나","신사임당",2000));
		list.add(new BookVO("789","자바 뽕뽑기","강감찬",2000));
		
		tableView = new TableView<BookVO>();
		tableView.getColumns().addAll(isbnColumn,titleColumn,authorColumn,PriceColumn);
		// 7. 작성한 내용 붙이기
		tableView.setItems(list);
		
		// 4. root , primaryStage 생성 및 붙이기
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("ssimple javafx table view");
		
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch();
	}

}
