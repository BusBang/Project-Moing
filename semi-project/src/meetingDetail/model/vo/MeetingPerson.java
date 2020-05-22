package meetingDetail.model.vo;

public class MeetingPerson {
	private int meetingNo;
	private String memberId;
	public MeetingPerson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingPerson(int meetingNo, String memberId) {
		super();
		this.meetingNo = meetingNo;
		this.memberId = memberId;
	}
	public int getMeetingNo() {
		return meetingNo;
	}
	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
