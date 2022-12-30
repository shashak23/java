package jdbc.vo;

// vo는 데이터를 표현하는 객체, 비즈니스 로직이 나오면 안되고요!
// 값을 저장하고 정리하기 위한 공간!
// 결국은 database의 테이블을 보고 만듦

public class Book {
	private String bisbn;
	private String btitle;
	private String bauthor; //필드에 있는 것을 다 쌓기 도메인 객체를 만드는 도메인 필드
    private int bprice; // 내용을 쌓을 때에는 반드시 데이터베이스의 필드와 일치하는 이름으로 해야한다
    
    public Book() {
    	
    }
    
    public Book(String bisbn, String btitle, String bauthor, int bprice) {  
    	
    }
    	

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;	
	
	
    }

	@Override
	public String toString() {
		return "Book [bisbn=" + bisbn + ", btitle=" + btitle + ", bauthor=" + bauthor + ", bprice=" + bprice + "]";
	}

	
 }
