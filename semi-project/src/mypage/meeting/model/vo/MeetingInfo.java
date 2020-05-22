package mypage.meeting.model.vo;

public class MeetingInfo {
	int meetingNo;
	String meetingTitle;
	String meetingName;
	String bossId;
	String categoryName;
	String countryName;
	
	
	
	public MeetingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingInfo(int meetingNo, String meetingTitle, String meetingName, String bossId, String categoryName,
			String countryName) {
		super();
		this.meetingNo = meetingNo;
		this.meetingTitle = meetingTitle;
		this.meetingName = meetingName;
		this.bossId = bossId;
		this.categoryName = categoryName;
		this.countryName = countryName;
	}
	public int getMeetingNo() {
		return meetingNo;
	}
	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	public String getMeetingTitle() {
		return meetingTitle;
	}
	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getBossId() {
		return bossId;
	}
	public void setBossId(String bossId) {
		this.bossId = bossId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
