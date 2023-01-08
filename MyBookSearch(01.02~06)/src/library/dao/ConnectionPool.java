package library.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
	private static BasicDataSource basicDS;

	static {
		// Connection Pool을 만들꺼예요!
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306"
				+ "/library?characterEncoding=UTF-8&server"
				+ "Timezone=UTC&useSSL=false&allowPublic"
				+ "KeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20);		
	}
	
	public static DataSource getDataSource() {
		return basicDS;
	}
	
}
