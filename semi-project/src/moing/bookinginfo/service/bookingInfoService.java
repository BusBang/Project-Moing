package moing.bookinginfo.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import moing.bookinginfo.dao.BookingInfoDao;
import moing.bookinginfo.vo.BookingInfo;
import moing.place.vo.Place;

public class bookingInfoService {

	public ArrayList<BookingInfo> bookingInfo(int placeNo) {
		Connection conn =JDBCTemplate.getConnection();
		ArrayList<BookingInfo>booking = new BookingInfoDao().bookingInfo(conn,placeNo);
		JDBCTemplate.close(conn);
		return booking;
	}

	public BookingInfo visitDate(int placeNo) {
		Connection conn =JDBCTemplate.getConnection();
		BookingInfo b = new BookingInfo().visitDate(placeNo,conn);
		JDBCTemplate.close(conn);
		return b;
	}

}
