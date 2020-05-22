package main.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import main.model.dao.MainDao;
import meetingDetail.model.vo.Meeting;

public class MainService {

	public ArrayList<Meeting> selectPopularMeeting() {
		Connection conn = JDBCTemplate.getConnection();
		Meeting m = new Meeting();
		ArrayList<Meeting> poplist = new MainDao().selectPopularMeeting(conn, m);
		JDBCTemplate.close(conn);
		return poplist;
	}

	
	//랜덤넘버가 모임의 개수 내에서 뽑힐 수 있게 모임의 개수 세기
	public int countCol() {
		Connection conn = JDBCTemplate.getConnection();
		int count = new MainDao().countCol(conn);
		JDBCTemplate.close(conn);
		return count;
	}

	public ArrayList<Meeting> selectAdMeeting(ArrayList<Integer> nums) {
		Connection conn = JDBCTemplate.getConnection();
		Meeting m = new Meeting();
		ArrayList<Meeting> adlist = new MainDao().selectAdMeeting(conn, m, nums);
		JDBCTemplate.close(conn);
		return adlist;
	}


}
