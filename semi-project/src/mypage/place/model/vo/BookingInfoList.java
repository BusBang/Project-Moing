package mypage.place.model.vo;

public class BookingInfoList {
	private BookingInfo bi;
	private int rnum;
	public BookingInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingInfoList(BookingInfo bi, int rnum) {
		super();
		this.bi = bi;
		this.rnum = rnum;
	}
	public BookingInfo getBi() {
		return bi;
	}
	public void setBi(BookingInfo bi) {
		this.bi = bi;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	

}
