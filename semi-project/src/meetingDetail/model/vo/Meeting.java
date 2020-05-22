package meetingDetail.model.vo;

import java.sql.Date;

public class Meeting {
	private int meetingNo;
	   private String meetingTitle;
	   private String meetingName;
	   private int meetingNowPerson;
	   private int meetingMinPerson;
	   private int meetingMaxPerson;
	   private String meetingContent;
	   private String meetingFilename;
	   private String meetingFilepath;
	   private Date meetingDate;
	   private String meetingAddr;
	   private int likeCount;
	   private String memberId;
	   private int categoryNo;
	   private int countryNo;
	   
	   
	   public Meeting(int meetingNo, String meetingTitle, String meetingName, int meetingNowPerson, int meetingMinPerson,
			int meetingMaxPerson, String meetingContent, String meetingFilename, String meetingFilepath,
			Date meetingDate, String meetingAddr, int likeCount, String memberId, int categoryNo, int countryNo) {
		super();
		this.meetingNo = meetingNo;
		this.meetingTitle = meetingTitle;
		this.meetingName = meetingName;
		this.meetingNowPerson = meetingNowPerson;
		this.meetingMinPerson = meetingMinPerson;
		this.meetingMaxPerson = meetingMaxPerson;
		this.meetingContent = meetingContent;
		this.meetingFilename = meetingFilename;
		this.meetingFilepath = meetingFilepath;
		this.meetingDate = meetingDate;
		this.meetingAddr = meetingAddr;
		this.likeCount = likeCount;
		this.memberId = memberId;
		this.categoryNo = categoryNo;
		this.countryNo = countryNo;
	}

	   public String getMeetingAddr() {
		return meetingAddr;
	}

	public void setMeetingAddr(String meetingAddr) {
		this.meetingAddr = meetingAddr;
	}

	public Meeting() {
	      super();
	      // TODO Auto-generated constructor stub
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
	   public int getMeetingNowPerson() {
	      return meetingNowPerson;
	   }
	   public void setMeetingNowPerson(int meetingNowPerson) {
	      this.meetingNowPerson = meetingNowPerson;
	   }
	   public int getMeetingMinPerson() {
	      return meetingMinPerson;
	   }
	   public void setMeetingMinPerson(int meetingMinPerson) {
	      this.meetingMinPerson = meetingMinPerson;
	   }
	   public int getMeetingMaxPerson() {
	      return meetingMaxPerson;
	   }
	   public void setMeetingMaxPerson(int meetingMaxPerson) {
	      this.meetingMaxPerson = meetingMaxPerson;
	   }
	   public String getMeetingContent() {
	      return meetingContent;
	   }
	   public void setMeetingContent(String meetingContent) {
	      this.meetingContent = meetingContent;
	   }
	   public String getMeetingFilename() {
	      return meetingFilename;
	   }
	   public void setMeetingFilename(String meetingFilename) {
	      this.meetingFilename = meetingFilename;
	   }
	   public String getMeetingFilepath() {
	      return meetingFilepath;
	   }
	   public void setMeetingFilepath(String meetingFilepath) {
	      this.meetingFilepath = meetingFilepath;
	   }
	   public Date getMeetingDate() {
	      return meetingDate;
	   }
	   public void setMeetingDate(Date meetingDate) {
	      this.meetingDate = meetingDate;
	   }
	   public int getLikeCount() {
	      return likeCount;
	   }
	   public void setLikeCount(int likeCount) {
	      this.likeCount = likeCount;
	   }
	   public String getMemberId() {
	      return memberId;
	   }
	   public void setMemberId(String memberId) {
	      this.memberId = memberId;
	   }
	   public int getCategoryNo() {
	      return categoryNo;
	   }
	   public void setCategoryNo(int categoryNo) {
	      this.categoryNo = categoryNo;
	   }
	   public int getCountryNo() {
	      return countryNo;
	   }
	   public void setCountryNo(int countryNo) {
	      this.countryNo = countryNo;
	   }
}
