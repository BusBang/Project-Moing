package mypage.letter.model.vo;

public class LetterList {
	private Letter letter;
	private int letterRNum;
	public LetterList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LetterList(Letter letter, int letterRNum) {
		super();
		this.letter = letter;
		this.letterRNum = letterRNum;
	}
	public Letter getLetter() {
		return letter;
	}
	public void setLetter(Letter letter) {
		this.letter = letter;
	}
	public int getLetterRNum() {
		return letterRNum;
	}
	public void setLetterRNum(int letterRNum) {
		this.letterRNum = letterRNum;
	}
	
	
}
