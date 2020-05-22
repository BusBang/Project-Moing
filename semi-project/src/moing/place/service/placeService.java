package moing.place.service;

import java.net.StandardSocketOptions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import common.JDBCTemplate;
//import moing.meeting.dao.MeetingDao;
//import moing.meeting.vo.Meeting;
import moing.place.dao.placeDao;
import moing.place.vo.Place;

public class placeService {

	public Place placeDetail(int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Place p = new placeDao().placeDetail(conn,placeNo);
		JDBCTemplate.close(conn);
		return p;
	}

	public int payment(int placeNo, int meetingNo, Date visitDate) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new placeDao().payment(placeNo,meetingNo,conn,visitDate);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

//	public int payment(Meeting m, Place p, String visitDate) {
//		Connection conn =JDBCTemplate.getConnection();
//		System.out.println(p);
//		int result= new placeDao().payment(m, p, conn, visitDate); 
//		return result;
//	}
//
//	public int payment(Place p, Meeting m) {
//		Connection conn =JDBCTemplate.getConnection();
//		System.out.println(p);
//		int result= new placeDao().payment(m, p, conn); 
//		if(result>0) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
//		JDBCTemplate.close(conn);
//		return result;
//	}


}
