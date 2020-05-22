package meetingDetail.model.vo;

import java.util.ArrayList;

public class MeetingDetail {
	private Meeting meet;
	private ArrayList<Review> review;
	private String pageNavi;
	private ArrayList<MeetingPerson> mpList;
	
	



	public ArrayList<MeetingPerson> getMpList() {
		return mpList;
	}



	public void setMpList(ArrayList<MeetingPerson> mpList) {
		this.mpList = mpList;
	}



	public MeetingDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MeetingDetail(Meeting meet, ArrayList<Review> review) {
		super();
		this.meet = meet;
		this.review = review;
	}



	public MeetingDetail(Meeting meet, ArrayList<Review> review, String pageNavi) {
		super();
		this.meet = meet;
		this.review = review;
		this.pageNavi = pageNavi;
	}



	


	public Meeting getMeet() {
		return meet;
	}



	public void setMeet(Meeting meet) {
		this.meet = meet;
	}



	public ArrayList<Review> getReview() {
		return review;
	}



	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}



	public String getPageNavi() {
		return pageNavi;
	}



	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}



	public MeetingDetail(Meeting meet, ArrayList<Review> review, String pageNavi, ArrayList<MeetingPerson> mpList) {
		super();
		this.meet = meet;
		this.review = review;
		this.pageNavi = pageNavi;
		this.mpList = mpList;
	}
	

}