package moim.model.vo;

import java.util.ArrayList;

public class MoimPageData {
	private ArrayList<Meeting> list;
	private String pageNavi;
	public ArrayList<Meeting> getList() {
		return list;
	}
	public void setList(ArrayList<Meeting> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public MoimPageData(ArrayList<Meeting> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public MoimPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
