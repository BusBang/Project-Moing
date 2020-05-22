package mypage.place.model.vo;

import java.util.Date;

public class BookingInfo {
	private int bookingNo;
	private int placeNo;
	private int meetingNo;
	private Date bookingDate;
	private Date visitDate;
	private String bookingCancel;
	private Date today;
	private String meetingName;
	private String sit;
	
	
	
	
	public BookingInfo(int bookingNo, int placeNo, int meetingNo, Date bookingDate, Date visitDate,
			String bookingCancel, Date today, String meetingName, String sit) {
		super();
		this.bookingNo = bookingNo;
		this.placeNo = placeNo;
		this.meetingNo = meetingNo;
		this.bookingDate = bookingDate;
		this.visitDate = visitDate;
		this.bookingCancel = bookingCancel;
		this.today = today;
		this.meetingName = meetingName;
		this.sit = sit;
	}
	public BookingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSit() {
		return sit;
	}
	public void setSit(String sit) {
		this.sit = sit;
	}
	
	
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public int getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}
	public int getPlaceNo() {
		return placeNo;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public int getMeetingNo() {
		return meetingNo;
	}
	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getBookingCancel() {
		return bookingCancel;
	}
	public void setBookingCancel(String bookingCancel) {
		this.bookingCancel = bookingCancel;
	}
	
	
}
