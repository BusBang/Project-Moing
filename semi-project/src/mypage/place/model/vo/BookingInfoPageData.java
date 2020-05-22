package mypage.place.model.vo;

import java.util.ArrayList;

public class BookingInfoPageData {
	private ArrayList<BookingInfoList> list;
	private String pageNavi;
	public BookingInfoPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingInfoPageData(ArrayList<BookingInfoList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<BookingInfoList> getList() {
		return list;
	}
	public void setList(ArrayList<BookingInfoList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
