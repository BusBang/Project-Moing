package place.model.vo;

import java.util.ArrayList;

public class PlaceKindAndCountry {
	ArrayList<Country> cList;
	ArrayList<PlaceKind> pkList;
	
	
	
	public PlaceKindAndCountry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaceKindAndCountry(ArrayList<Country> cList, ArrayList<PlaceKind> pkList) {
		super();
		this.cList = cList;
		this.pkList = pkList;
	}
	public ArrayList<Country> getcList() {
		return cList;
	}
	public void setcList(ArrayList<Country> cList) {
		this.cList = cList;
	}
	public ArrayList<PlaceKind> getPkList() {
		return pkList;
	}
	public void setPkList(ArrayList<PlaceKind> pkList) {
		this.pkList = pkList;
	}
	
	
	
}
