package meetingDetail.model.vo;

import java.util.ArrayList;

public class LikeList {
	private ArrayList<Meeting> meeting;
	private Meeting meet;
	private String myId;
	public LikeList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeList(ArrayList<Meeting> meeting, String myId) {
		super();
		this.meeting = meeting;
		this.myId = myId;
	}
	public LikeList(Meeting meet, String myId) {
		super();
		this.meet = meet;
		this.myId = myId;
	}
	public ArrayList<Meeting> getMeeting() {
		return meeting;
	}
	public void setMeeting(ArrayList<Meeting> meeting) {
		this.meeting = meeting;
	}
	public String getMyId() {
		return myId;
	}
	public void setMyId(String myId) {
		this.myId = myId;
	}
	public Meeting getMeet() {
		return meet;
	}
	public void setMeet(Meeting meet) {
		this.meet = meet;
	}
	
	
}
