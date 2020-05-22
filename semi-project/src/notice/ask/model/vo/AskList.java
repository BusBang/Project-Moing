package notice.ask.model.vo;

import java.util.ArrayList;

public class AskList {
	private Ask ask;
	private int rnum;
	public AskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AskList(Ask ask, int rnum) {
		super();
		this.ask = ask;
		this.rnum = rnum;
	}
	public Ask getAsk() {
		return ask;
	}
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
}
