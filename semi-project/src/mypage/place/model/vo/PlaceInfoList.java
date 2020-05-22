package mypage.place.model.vo;

public class PlaceInfoList {
	private PlaceInfo pi;
	private int rnum;
	public PlaceInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaceInfoList(PlaceInfo pi, int rnum) {
		super();
		this.pi = pi;
		this.rnum = rnum;
	}
	public PlaceInfo getPi() {
		return pi;
	}
	public void setPi(PlaceInfo pi) {
		this.pi = pi;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
}
