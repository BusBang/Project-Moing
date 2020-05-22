package meetingDetail.model.vo;

import java.util.ArrayList;

public class MeetingDetailAllList {
	private MeetingDetail md;
	private ArrayList<MeetingPerson> mp;
	public MeetingDetailAllList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingDetailAllList(MeetingDetail md, ArrayList<MeetingPerson> mp) {
		super();
		this.md = md;
		this.mp = mp;
	}
	public MeetingDetail getMd() {
		return md;
	}
	public void setMd(MeetingDetail md) {
		this.md = md;
	}
	public ArrayList<MeetingPerson> getMp() {
		return mp;
	}
	public void setMp(ArrayList<MeetingPerson> mp) {
		this.mp = mp;
	}

}
