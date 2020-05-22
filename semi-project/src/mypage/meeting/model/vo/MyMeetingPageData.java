package mypage.meeting.model.vo;

import java.util.ArrayList;

public class MyMeetingPageData {
	private ArrayList<MeetingInfoList> list;
	private String pageNavi;
	public MyMeetingPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyMeetingPageData(ArrayList<MeetingInfoList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<MeetingInfoList> getList() {
		return list;
	}
	public void setList(ArrayList<MeetingInfoList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	

}
