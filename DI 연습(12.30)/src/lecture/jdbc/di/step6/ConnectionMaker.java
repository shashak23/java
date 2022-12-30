package lecture.jdbc.di.step6;

import java.sql.Connection;

public interface ConnectionMaker {
	
	public abstract Connection getConnection();
	

}
