package category.model.vo;

public class Category {
	private int categoryNo;
	private String categoryName;
	private int categoryLevel;
	private int categoryRef;
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryLevel() {
		return categoryLevel;
	}
	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	public int getCategoryRef() {
		return categoryRef;
	}
	public void setCategoryRef(int categoryRef) {
		this.categoryRef = categoryRef;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int categoryNo, String categoryName, int categoryLevel, int categoryRef) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.categoryLevel = categoryLevel;
		this.categoryRef = categoryRef;
	}
	
}
