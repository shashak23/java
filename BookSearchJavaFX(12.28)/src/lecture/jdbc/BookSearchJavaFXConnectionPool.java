package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import lecture.jdbc.vo.BookVO;



public class BookSearchJavaFXConnectionPool extends Application { // 커넥션이랑 서치랑 같이 해서 해보세요!
	TableView<BookVO> tableView;
	TextField textField;
	Button deleteBtn;
	String deleteISBN;
	
	public BookSearchJavaFXConnectionPool( ) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private static BasicDataSource basicDS;
	
	static {
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("\"jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20);
	}
	
	
	// datasource public 하나 만들고 
	public static DataSource getDataSource() {
		return basicDS;
		
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
				});
				
				deleteBtn = new Button("선택된 책 삭제");
				deleteBtn.setPrefSize(150, 40);
				deleteBtn.setDisable(true);
				deleteBtn.setOnAction(e -> {
					// delete연결되는 driver manager, prepareStatement pstmt, executeUpdate, commit, rollback 
					String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
					String id = "root";
					String pw = "test1234";
					
					DataSource ds = getDataSource();
					
					
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
					
//					if(count == 1) {
//						con.commit();
//						// 다시 검색해서 결과 가져와서 화면에 찍어야 해요!
//					} else {
//						con.rollback();
//					}
					
					
					// 13. 사용한 리소스 종료 
					pstmt.close();
					con.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				});
				flowpane.getChildren().add(textField);
				flowpane.getChildren().add(deleteBtn);
				
				// column 객체를 생성해요
				TableColumn<BookVO, String> isbnColumn = 
						new TableColumn<>("ISBN");  // ISBN은 화면에 보여지는 컬럼의 이름
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
				
				tableView = new TableView<BookVO>();  
				
				tableView.getColumns().addAll(isbnColumn, titleColumn, authorColumn, PriceColumn);  // tableView에 꼭 인자 넣기 
				
				tableView.setRowFactory(e -> {  // tableView 실행시키는 거 넣기! 
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
				primaryStage.setTitle("Simple JavaFX TableView");
				primaryStage.setOnCloseRequest(e -> {
					// 프로그램이 종료가 될 때 어떤 동작으로 처리되는 가를 적기 
					try {
						((BasicDataSource)getDataSource()).close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				
			}

	
	
	
	public static void main(String[] args) {
		launch();
		
	}

}
