package mypage.place.model.vo;

public class PlaceInfo {
	private int placeNo;
	private String placeTitle;
	private String countryName;
	private String placeKindName;
	private String hostId;
	private int bookingNo;
	public PlaceInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaceInfo(int placeNo, String placeTitle, String countryName, String placeKindName, String hostId,
			int bookingNo) {
		super();
		this.placeNo = placeNo;
		this.placeTitle = placeTitle;
		this.countryName = countryName;
		this.placeKindName = placeKindName;
		this.hostId = hostId;
		this.bookingNo = bookingNo;
	}
	public int getPlaceNo() {
		return placeNo;
	}
	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}
	public String getPlaceTitle() {
		return placeTitle;
	}
	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getPlaceKindName() {
		return placeKindName;
	}
	public void setPlaceKindName(String placeKindName) {
		this.placeKindName = placeKindName;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public int getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}
	
	
	
	
	
}
