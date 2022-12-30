package lecture.jdbc.vo;

public class BookVO {
	private String bisbn;
	private String btitle;
	private String bauthor;
	private Integer bprice;
	
	public BookVO() {
		
	}

	public BookVO(String bisbn, String btitle, String bauthor, Integer bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
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

	public Integer getBprice() {
		return bprice;
	}

	public void setBprice(Integer bprice) {
		this.bprice = bprice;
	}

}
