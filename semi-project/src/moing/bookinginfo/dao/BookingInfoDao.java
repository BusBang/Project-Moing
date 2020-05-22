package moing.bookinginfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import moing.bookinginfo.vo.BookingInfo;

public class BookingInfoDao {

	public ArrayList<BookingInfo> bookingInfo(Connection conn, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookingInfo>booking =new ArrayList<BookingInfo>();
		String query = "select place_no,meeting_no,visit_date, booking_cancel from booking_info where place_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, placeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookingInfo b = new BookingInfo();
				b.setPlaceNo(rset.getInt("place_no"));
				b.setMeetingNo(rset.getInt("meeting_no"));
				b.setVisitDate(rset.getDate("visit_date"));
				b.setBookingCancel(rset.getString("booking_cancel"));
				booking.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return booking;
	}

}
