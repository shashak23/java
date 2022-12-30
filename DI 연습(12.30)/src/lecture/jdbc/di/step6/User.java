package lecture.jdbc.di.step6;
// 사용자 입력하고 정보 조회하는 용도로 사용할거라서
public class User {
	//사용자에 관련된 데이터베이스를 처리하는 클래스를 생성해야해요 --> UserDAO
	
	
	//sql에서 생성한 스키마의 users테이블의 데이터를 구분하기 위해 여기도 입력
	private String id;
	private String name;
	private String password;
	
	public User() {
		
	}

	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
