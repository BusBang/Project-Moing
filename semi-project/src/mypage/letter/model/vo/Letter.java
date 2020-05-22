package mypage.letter.model.vo;

import java.util.Date;

public class Letter {
	
	private int letterNo;
	private String letterSendMemberId;
	private String letterTitle;
	private String letterContent;
	private Date letterSendDate;
	private String letterGetMemberId;
	private String letterRead;
	private String letterDelGet;
	private String letterDelSend;
	
	
	public String getLetterDelGet() {
		return letterDelGet;
	}
	public void setLetterDelGet(String letterDelGet) {
		this.letterDelGet = letterDelGet;
	}
	public String getLetterDelSend() {
		return letterDelSend;
	}
	public void setLetterDelSend(String letterDelSend) {
		this.letterDelSend = letterDelSend;
	}
	public Letter(int letterNo, String letterSendMemberId, String letterTitle, String letterContent,
			Date letterSendDate, String letterGetMemberId, String letterRead, String letterDelGet,
			String letterDelSend) {
		super();
		this.letterNo = letterNo;
		this.letterSendMemberId = letterSendMemberId;
		this.letterTitle = letterTitle;
		this.letterContent = letterContent;
		this.letterSendDate = letterSendDate;
		this.letterGetMemberId = letterGetMemberId;
		this.letterRead = letterRead;
		this.letterDelGet = letterDelGet;
		this.letterDelSend = letterDelSend;
	}
	public Letter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContentBr(){
		return letterContent.replaceAll("\r\n", "<br>");
	}
	public int getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(int letterNo) {
		this.letterNo = letterNo;
	}
	public String getLetterSendMemberId() {
		return letterSendMemberId;
	}
	public void setLetterSendMemberId(String letterSendMemberId) {
		this.letterSendMemberId = letterSendMemberId;
	}
	public String getLetterTitle() {
		return letterTitle;
	}
	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}
	public String getLetterContent() {
		return letterContent;
	}
	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}
	public Date getLetterSendDate() {
		return letterSendDate;
	}
	public void setLetterSendDate(Date letterSendDate) {
		this.letterSendDate = letterSendDate;
	}
	public String getLetterGetMemberId() {
		return letterGetMemberId;
	}
	public void setLetterGetMemberId(String letterGetMemberId) {
		this.letterGetMemberId = letterGetMemberId;
	}
	public String getLetterRead() {
		return letterRead;
	}
	public void setLetterRead(String letterRead) {
		this.letterRead = letterRead;
	}
	
	
	
}