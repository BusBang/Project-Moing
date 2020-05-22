package moing.place.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import common.JDBCTemplate;
//import moing.meeting.vo.Meeting;
import moing.place.vo.Place;

public class placeDao {

	public Place placeDetail(Connection conn, int placeNo) {
		PreparedStatement pstmt = null;
		Place p = null;
		ResultSet rset = null;
		String query = "select * from place where place_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, placeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Place();
				p.setPlaceNo(rset.getInt("place_no"));
				p.setPlaceTitle(rset.getString("place_title"));
				p.setPlaceContent(rset.getString("place_content"));
				p.setPlaceAddr(rset.getString("place_addr"));
				p.setPlaceStartDate(rset.getDate("place_start_date"));
				p.setPlaceEndDate(rset.getDate("place_end_date"));
				p.setPlaceDeposit(rset.getInt("place_deposit"));
				p.setPlaceAccountNumber(rset.getString("place_account_number"));
				p.setPlaceFilename(rset.getString("place_filename"));
				p.setPlaceFilepath(rset.getString("place_filepath"));
				p.setMemberId(rset.getString("member_id"));
				p.setCountryNo(rset.getInt("country_no"));
				p.setPlaceKindName(rset.getString("place_kind_name"));
				p.setCheckOption(rset.getString("check_option"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return p;
	}

	public int payment(int placeNo, int meetingNo, Connection conn, Date visitDate) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into booking_info values(seq_booking.nextval,?,?,sysdate,?,'F')";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, placeNo);
			pstmt.setInt(2, meetingNo);
			pstmt.setDate(3, visitDate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

//	public int payment(Meeting m, Place p, Connection conn, String visitDate) {
//		PreparedStatement pstmt = null;
//		int result= 0;
//		String query = "insert into booking_info values(seq_booking_info.nextval,?,'1',sysdate,?,'F')";
//		try {
//			pstmt=conn.prepareStatement(query);
//			pstmt.setInt(1, p.getPlaceNo());
//			pstmt.setString(2, visitDate);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}

//	public int payment(Meeting m, Place p, Connection conn) {
//		PreparedStatement pstmt = null;
//		int result= 0;
//		String query = "insert into booking_info values(seq_booking_info.nextval,?,'1',sysdate,?,'F')";
//		try {
//			pstmt=conn.prepareStatement(query);
//			pstmt.setInt(1, p.getPlaceNo());
//			pstmt.setDate(2, p.getPlaceStartDate());
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}



}
