package mypage.place.model.vo;

import java.util.ArrayList;

public class PlaceInfoPageData {
	private ArrayList<PlaceInfoList> list;
	private String pageNavi;
	public PlaceInfoPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaceInfoPageData(ArrayList<PlaceInfoList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<PlaceInfoList> getList() {
		return list;
	}
	public void setList(ArrayList<PlaceInfoList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	

}
