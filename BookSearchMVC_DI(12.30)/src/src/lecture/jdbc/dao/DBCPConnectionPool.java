package src.lecture.jdbc.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPConnectionPool {
	// 실행되도록 만들었던 이 부분을 따로 뺄거에요~~ 
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
	
	public static DataSource getDataSource() {
		return basicDS;
	}
	
}
