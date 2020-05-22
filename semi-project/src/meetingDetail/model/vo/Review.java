package meetingDetail.model.vo;

public class Review {
	private int meetingNo;
	private int reviewNo;
	private String reviewContent;
	private String memberId;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int meetingNo, int reviewNo, String reviewContent, String memberId) {
		super();
		this.meetingNo = meetingNo;
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.memberId = memberId;
	}

	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
