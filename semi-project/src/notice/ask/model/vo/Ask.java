package notice.ask.model.vo;

import java.util.Date;

public class Ask {
	private int askNo;
	private String memberId;
	private String askTitle;
	private String askContent;
	private Date askSendDate;
	private String askFilepath;
	private String askFilename;
	private String askReply;
	private String replyContent;
	
	
	
	
	
	public Ask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ask(int askNo, String memberId, String askTitle, String askContent, Date askSendDate, String askFilepath,
			String askFilename, String askReply, String replyContent) {
		super();
		this.askNo = askNo;
		this.memberId = memberId;
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askSendDate = askSendDate;
		this.askFilepath = askFilepath;
		this.askFilename = askFilename;
		this.askReply = askReply;
		this.replyContent = replyContent;
	}
	
	public String getReplyBr() {
		return replyContent.replaceAll("\r\n","<br>");
	}
	
	public String getContentBr(){
		return askContent.replaceAll("\r\n", "<br>");
	}
	
	public int getAskNo() {
		return askNo;
	}
	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAskTitle() {
		return askTitle;
	}
	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}
	public String getAskContent() {
		return askContent;
	}
	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}
	public Date getAskSendDate() {
		return askSendDate;
	}
	public void setAskSendDate(Date askSendDate) {
		this.askSendDate = askSendDate;
	}
	public String getAskFilepath() {
		return askFilepath;
	}
	public void setAskFilepath(String askFilepath) {
		this.askFilepath = askFilepath;
	}
	public String getAskFilename() {
		return askFilename;
	}
	public void setAskFilename(String askFilename) {
		this.askFilename = askFilename;
	}
	public String getAskReply() {
		return askReply;
	}
	public void setAskReply(String askReply) {
		this.askReply = askReply;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
}
