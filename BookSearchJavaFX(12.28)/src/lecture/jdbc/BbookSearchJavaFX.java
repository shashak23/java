package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class BbookSearchJavaFX extends Application {
	
	TableView<BookVO> tableView;
	TextField textField;
	Button deleteBtn;
	String deleteISBN;
	
	public BbookSearchJavaFX( ) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 1. 레이아웃 설정하기
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. flowpane 설정하기
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setHgap(10);
		
		// 3. 각각의 component를 생성해서 붙이기
		textField = new TextField();
		textField.setPrefSize(250, 40);
		textField.setOnAction(e -> {
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			Connection con = null; // con을 널로 잡고
			
			// 5. 연결하고
			try {
				con = DriverManager.getConnection(jdbc_url, id, pw);
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT bisbn, btitle, bauthor, bprice ");
				sql.append("FROM book ");
				sql.append("WHERE btitle like ? ");
				sql.append("ORDER BY bprice DESC");
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + textField.getText() + "%"); // ?의 첫번째를 가리킴
				ResultSet rs = pstmt.executeQuery();
				ObservableList<BookVO> list = FXCollections.observableArrayList();
				while(rs.next()) {
					BookVO book = new BookVO(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice"));
					list.add(book);
					
				}
				
				System.out.println("실행");
				tableView.setItems(list);
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			// 6. sql 문법 넣고
			
			
			
			
			// 7.결과처리!
			
			// 8. 리스트 보여주기
			
			// 9. 사용한 리소스 반납
	  // try/catch 잡기 
			
		});
		
		// 4. 삭제버튼 만들고 붙이기
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			// delete연결되는 driver manager, prepareStatement pstmt, executeUpdate, commit, rollback 
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			Connection con = null;
			
			// 10. 연결해주기
			try {
			con = DriverManager.getConnection(jdbc_url, id, pw);
			
			// 11. sql 문법 넣기
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE ");
			sql.append("FROM book ");
			sql.append("WHERE bisbn = ?");
			
			// 12. 결과처리 - 재진st
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, deleteISBN);
			int count = pstmt.executeUpdate();
			
			// 13. 사용한 리소스 종료 
			pstmt.close();
			con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			// 13. 결과처리 - 교수님st
			// if(count == 1) {
			// con.commit();
			// } else {
			// con.rollback();
			
		});
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		
		// column 객체를 생성해요
		TableColumn<BookVO, String> isbnColumn = 
				new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
		isbnColumn.setMinWidth(150);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));

		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple JavaFX TableView");
		primaryStage.show();
		
	}
	
	
	public static void main(String[] primaryStage) {
		launch();
	}

}
