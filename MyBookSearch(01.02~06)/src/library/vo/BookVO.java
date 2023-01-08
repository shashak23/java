package library.vo;

public class BookVO {
	
	// 필드 생성
	 String bisbn;
	 String btitle;
	 String bauthor;
	 int bpage;
	 String bdate;
	 int bprice;
	 
	// 메소드 생성
	public BookVO() {
 	}

	public BookVO(String bisbn, String btitle, String bauthor, int bpage, String bdate, int bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bpage = bpage;
		this.bdate = bdate;
		this.bprice = bprice;
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

	public int getBpage() {
		return bpage;
	}

	public void setBpage(int bpage) {
		this.bpage = bpage;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}

	
}
