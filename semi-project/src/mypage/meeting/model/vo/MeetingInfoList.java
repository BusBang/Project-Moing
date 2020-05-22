package mypage.meeting.model.vo;

public class MeetingInfoList {
	private MeetingInfo meetingInfo;
	private int meetingRNum;
	public MeetingInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingInfoList(MeetingInfo meetingInfo, int meetingRNum) {
		super();
		this.meetingInfo = meetingInfo;
		this.meetingRNum = meetingRNum;
	}
	public MeetingInfo getMeetingInfo() {
		return meetingInfo;
	}
	public void setMeetingInfo(MeetingInfo meetingInfo) {
		this.meetingInfo = meetingInfo;
	}
	public int getMeetingRNum() {
		return meetingRNum;
	}
	public void setMeetingRNum(int meetingRNum) {
		this.meetingRNum = meetingRNum;
	}
	
}
