package mypage.letter.model.vo;

import java.util.ArrayList;

public class LetterPageData {
	private ArrayList<LetterList> list;
	private String letterPageNavi;
	public LetterPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LetterPageData(ArrayList<LetterList> list, String letterPageNavi) {
		super();
		this.list = list;
		this.letterPageNavi = letterPageNavi;
	}
	public ArrayList<LetterList> getList() {
		return list;
	}
	public void setList(ArrayList<LetterList> list) {
		this.list = list;
	}
	public String getLetterPageNavi() {
		return letterPageNavi;
	}
	public void setLetterPageNavi(String letterPageNavi) {
		this.letterPageNavi = letterPageNavi;
	}
	
	
	
}
