package meetingDetail.model.vo;

import java.util.ArrayList;

public class ReviewPage {
	private ArrayList<Review> list;
	private String pageNavi;
	public ReviewPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewPage(ArrayList<Review> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Review> getList() {
		return list;
	}
	public void setList(ArrayList<Review> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
