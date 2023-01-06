package library.vo;

public class UserVO {
	//필드 생성 -- 일반유저, 관리자 구분
	private String id;
	private String name;
	private String pw;
	private String date;
	private String tier;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public UserVO(String id, String name, String pw, String date, String tier) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.date = date;
		this.tier = tier;
	}
	
}
	