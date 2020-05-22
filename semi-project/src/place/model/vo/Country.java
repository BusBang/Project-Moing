package place.model.vo;

public class Country {
	private int countryNo;
	private String countryName;
	private int countryLevel;
	private int countryRef;
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Country(int countryNo, String countryName, int countryLevel, int countryRef) {
		super();
		this.countryNo = countryNo;
		this.countryName = countryName;
		this.countryLevel = countryLevel;
		this.countryRef = countryRef;
	}


	public int getCountryNo() {
		return countryNo;
	}


	public void setCountryNo(int countryNo) {
		this.countryNo = countryNo;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public int getCountryLevel() {
		return countryLevel;
	}


	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}


	public int getCountryRef() {
		return countryRef;
	}


	public void setCountryRef(int countryRef) {
		this.countryRef = countryRef;
	}




	
	
	
}
