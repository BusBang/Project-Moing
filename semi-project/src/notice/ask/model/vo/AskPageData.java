package notice.ask.model.vo;

import java.util.ArrayList;

public class AskPageData {
	private ArrayList<AskList> list;
	private String pageNavi;
	public AskPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AskPageData(ArrayList<AskList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<AskList> getList() {
		return list;
	}
	public void setList(ArrayList<AskList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
