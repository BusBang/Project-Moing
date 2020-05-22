package mypage.place.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import common.JDBCTemplate;
import mypage.place.model.vo.BookingInfo;
import mypage.place.model.vo.BookingInfoList;
import mypage.place.model.vo.PlaceInfo;
import mypage.place.model.vo.PlaceInfoList;

public class PlaceDao {

	public int totalPlace0Count(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query = "select distinct count(place_no) as cnt from booking_info b where meeting_no in (select meeting_no from meeting where member_id=?)";
		try {
			pstmt=conn.prepareStatement(query);//모임장 아이디가 memberId(이용자 아이디)인 모임의 모임번호들을 구하고, 그 모임번호로 예약된 장소번호의 중복 제외 갯수를 구한다.
			pstmt.setString(1, memberId);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}return result;
	}

	public ArrayList<PlaceInfoList> selectPlace0List(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PlaceInfoList> list = new ArrayList<PlaceInfoList>();
		String query = "select v.* from (select rownum as rrnum, y.* from (select rownum as rnum, e.* from(select distinct place_no as pno, place_title as ptitle, place_kind_name as pkn, p.member_id as pid, c.country_name as cn, bi.booking_no as bino from place p join country c using(country_no) join booking_info bi using(place_no) where meeting_no in(select distinct meeting_no from booking_info join meeting using(meeting_no) where booking_cancel='F' and member_id=?) and place_no in (select distinct place_no as cnt from booking_info b where booking_cancel='F' and meeting_no=(select meeting_no from meeting where member_id=?)) order by bino)e order by rnum desc)y)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);//현재 로그인 된 아이디가 모임장인 모임의 번호를 조회하여 그 번호로 이루어지고 취소되지 않은 예약을 조회한다. 그 예약의 장소번호를 통하여 장소 정보를 얻는다.
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				PlaceInfoList pil = new PlaceInfoList();
				PlaceInfo pi = new PlaceInfo();
				pi.setCountryName(rset.getString("cn"));
				pi.setHostId(rset.getString("pid"));
				pi.setPlaceKindName(rset.getString("pkn"));
				pi.setPlaceNo(rset.getInt("pno"));
				pi.setPlaceTitle(rset.getString("ptitle"));
				pi.setBookingNo(rset.getInt("bino"));
				pil.setPi(pi);
				pil.setRnum(rset.getInt("rnum"));
				list.add(pil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	
	public int totalPlace0BookingCount(Connection conn, String memberId, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=-1;
		System.out.println("dao"+memberId);
		System.out.println("dao"+placeNo);
		String query = "select count(booking_no) as cnt from booking_info where place_no=? and meeting_no in (select meeting_no from meeting where member_id=?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, placeNo);
			pstmt.setString(2, memberId);//장소 번호가 placeNo 이고 모임장이 사용자인 예약의 갯수 조회
			rset=pstmt.executeQuery();
			if(rset.next()) {
				System.out.println("rset"+rset.getInt("cnt"));
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		System.out.println("dao:"+result);
		return result;
	}

	public ArrayList<BookingInfoList> selectPlace0BookingList(Connection conn, String memberId, int start, int end, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookingInfoList> list = new ArrayList<BookingInfoList>();
		String query = "select v.* from (select rownum as rrnum, sysdate as today, y.* from (select rownum as rnum, e.* from(select booking_no as bno, floor(sysdate-visit_date) as day, place_no as pno, meeting_no as mno, booking_date as bd, visit_date as vd, booking_cancel as bc, meeting_name as mn from booking_info join meeting using(meeting_no) where place_no=? and booking_cancel='F' and meeting_no in (select meeting_no from meeting where member_id=?) order by bno)e order by rnum desc)y)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);//로그인 된 아이디(memberId)가 모임장인 모임 번호를 조회하여 그 모임 번호를 통한 예약들을 조회 후 그 예약들 중에서 해당 장소번호와 같은 예약에 대한 정보를 가져온다.
			pstmt.setInt(1, placeNo);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				BookingInfo bi = new BookingInfo();
				BookingInfoList bil = new BookingInfoList();
				bi.setBookingCancel(rset.getString("bc"));
				bi.setBookingDate(rset.getDate("bd"));
				bi.setBookingNo(rset.getInt("bno"));
				bi.setMeetingNo(rset.getInt("mno"));
				bi.setPlaceNo(rset.getInt("pno"));
				bi.setVisitDate(rset.getDate("vd"));
				bi.setToday(rset.getDate("today"));
				bi.setMeetingName(rset.getString("mn"));
				int day = rset.getInt("day");
				bi.setSit(day>0?"완료":"예약");
				bil.setBi(bi);
				bil.setRnum(rset.getInt("rnum"));
				list.add(bil);
				System.out.println("로우넘"+rset.getInt("rnum"));
				System.out.println("모임명"+rset.getString("mn"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}return list;
	}

	
	

	public int totalPlace1Count(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(place_no) as cnt from place where member_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}return result;
	}

	public ArrayList<PlaceInfoList> selectPlace1List(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PlaceInfoList> list = new ArrayList<PlaceInfoList>();
		String query = "select u.* from (select rownum as rrnum, v.* from (select rownum as rnum, y.* from (select place_no as pno, place_title as ptitle,  place_kind_name as pkn, p.member_id as pid, c.country_name as cn  from place p join country c using(country_no) where  p.member_id=? order by place_no)y order by rnum desc)v)u where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);//장소 제공자의 아이디가 사용자의 아이디와 같은 장소 정보를 모두 가져옴
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				PlaceInfoList pil = new PlaceInfoList();
				PlaceInfo pi = new PlaceInfo();
				pi.setCountryName(rset.getString("cn"));
				pi.setHostId(rset.getString("pid"));
				pi.setPlaceKindName(rset.getString("pkn"));
				pi.setPlaceNo(rset.getInt("pno"));
				System.out.println("내 제공 장소 번호 :"+ pi.getPlaceNo());
				pi.setPlaceTitle(rset.getString("ptitle"));
				pil.setPi(pi);
				pil.setRnum(rset.getInt("rnum"));
				list.add(pil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return list;
	}


	public int totalPlace1BookingCount(Connection conn, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(booking_no) as cnt from booking_info where place_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, placeNo);//장소 번호가 placeNo인 예약의 갯수 조회
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}return result;
	}

	public ArrayList<BookingInfoList> selectPlace1BookingList(Connection conn, int start, int end, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookingInfoList> list = new ArrayList<BookingInfoList>();
		String query = "select v.* from (select rownum as rrnum, y.* from (select rownum as rnum, e.* from(select booking_no as bno, floor(sysdate-visit_date) as day, place_no as pno, meeting_no as mno, booking_date as bd, visit_date as vd, booking_cancel as bc, meeting_name as mn from booking_info join meeting using(meeting_no) where place_no=? order by bno)e order by rnum desc)y)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);//장소 번호가 placeNo인 예약정보를 조회하여 리스트 작성
			pstmt.setInt(1, placeNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				BookingInfo bi = new BookingInfo();
				BookingInfoList bil = new BookingInfoList();
				bi.setBookingCancel(rset.getString("bc").equals("T")?"취소":"예약");
				bi.setBookingDate(rset.getDate("bd"));
				bi.setBookingNo(rset.getInt("bno"));
				bi.setMeetingNo(rset.getInt("mno"));
				bi.setVisitDate(rset.getDate("vd"));
				bi.setPlaceNo(rset.getInt("pno"));
				int day = rset.getInt("day");
				bi.setSit(day>0?"완료":"예약");
				bi.setMeetingName(rset.getString("mn"));
				bil.setBi(bi);
				bil.setRnum(rset.getInt("rnum"));
				list.add(bil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}return list;
	}

	public int totaladminPlaceCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(place_no) as cnt from place";
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}return result;
	}

	public ArrayList<PlaceInfoList> selectadminPlaceList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PlaceInfoList> list = new ArrayList<PlaceInfoList>();
		String query = "select u.* from (select rownum as rrnum, v.* from (select rownum as rnum, y.* from (select place_no as pno, place_title as ptitle,  place_kind_name as pkn, p.member_id as pid, c.country_name as cn  from place p join country c using(country_no) order by place_no)y order by rnum desc)v)u where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				PlaceInfoList pil = new PlaceInfoList();
				PlaceInfo pi = new PlaceInfo();
				pi.setCountryName(rset.getString("cn"));
				pi.setHostId(rset.getString("pid"));
				pi.setPlaceKindName(rset.getString("pkn"));
				pi.setPlaceNo(rset.getInt("pno"));
				System.out.println("내 제공 장소 번호 :"+ pi.getPlaceNo());
				pi.setPlaceTitle(rset.getString("ptitle"));
				pil.setPi(pi);
				pil.setRnum(rset.getInt("rnum"));
				list.add(pil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return list;
	}

	public int bookingCancel(Connection conn, int bookingNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update booking_info set booking_cancel='T' where booking_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookingNo);
				
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}



}
