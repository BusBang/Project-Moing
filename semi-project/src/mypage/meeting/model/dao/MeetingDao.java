package mypage.meeting.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.meeting.model.vo.MeetingInfo;
import mypage.meeting.model.vo.MeetingInfoList;

public class MeetingDao {

	public int totalCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(meeting_no) as cnt from meeting_person where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<MeetingInfoList> selectMeetingList(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MeetingInfoList> list = new ArrayList<MeetingInfoList>();
		String query = "select m.* from(select rownum as rrnum, j.* from(select rownum as rnum, k.* from(select distinct meeting_no as mmn, meeting_title as mmt, meeting_name as mmnm, m.member_id as mmi, c.category_name as cn, t.country_name as tn from meeting m left join meeting_person p using(meeting_no) left join category c using(category_no) left join country t using(country_no) where p.MEMBER_ID =? or m.member_id=?)k order by rnum desc)j)m where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				MeetingInfo mi = new MeetingInfo();
				MeetingInfoList mil= new MeetingInfoList();
				mi.setBossId(rset.getString("mmi"));
				mi.setCategoryName(rset.getString("cn"));
				mi.setCountryName(rset.getString("tn"));
				mi.setMeetingName(rset.getString("mmnm"));
				mi.setMeetingNo(rset.getInt("mmn"));
				mi.setMeetingTitle(rset.getString("mmt"));
				mil.setMeetingInfo(mi);
				mil.setMeetingRNum(rset.getInt("rnum"));
				list.add(mil);
				System.out.println(mi.getBossId());
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

	public String meetingMember(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String letterGetMemberId = "";
		String query = "select distinct p.member_id from meeting_person p left join meeting m using(meeting_no) where meeting_no=? and p.member_id!=m.member_id";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				letterGetMemberId += rset.getString("member_id")+",";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return letterGetMemberId;
	}

	public int totalCountOptMemberId(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(meeting_no) as cnt from meeting_person p left join meeting m using(meeting_no) where p.member_id=? and m.member_id like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public int totalCountOptMeetingTitle(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(meeting_no)as cnt from meeting_person p left join meeting m using(meeting_no) where p.member_id=? and m.meeting_title like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<MeetingInfoList> selectMeetingListOptMI(Connection conn, int start, int end, String memberId,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MeetingInfoList> list = new ArrayList<MeetingInfoList>();
		String query = "select m.* from(select rownum as rrnum, j.* from(select rownum as rnum, k.* from(select distinct meeting_no as mmn, meeting_title as mmt, meeting_name as mmnm, m.member_id as mmi, c.category_name as cn, t.country_name as tn from meeting m left join meeting_person p using(meeting_no) left join category c using(category_no) left join country t using(country_no) where (p.MEMBER_ID =? or m.member_id=?) and m.member_id like ?)k order by rnum desc)j)m where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				MeetingInfo mi = new MeetingInfo();
				MeetingInfoList mil= new MeetingInfoList();
				mi.setBossId(rset.getString("mmi"));
				mi.setCategoryName(rset.getString("cn"));
				mi.setCountryName(rset.getString("tn"));
				mi.setMeetingName(rset.getString("mmnm"));
				mi.setMeetingNo(rset.getInt("mmn"));
				mi.setMeetingTitle(rset.getString("mmt"));
				mil.setMeetingInfo(mi);
				mil.setMeetingRNum(rset.getInt("rnum"));
				list.add(mil);
				System.out.println(mi.getBossId());
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

	public ArrayList<MeetingInfoList> selectMeetingListOptMT(Connection conn, int start, int end, String memberId,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MeetingInfoList> list = new ArrayList<MeetingInfoList>();
		String query = "select m.* from(select rownum as rrnum, j.* from(select rownum as rnum, k.* from(select distinct meeting_no as mmn, meeting_title as mmt, meeting_name as mmnm, m.member_id as mmi, c.category_name as cn, t.country_name as tn from meeting m left join meeting_person p using(meeting_no) left join category c using(category_no) left join country t using(country_no) where (p.MEMBER_ID =? or m.member_id=?) and m.meeting_title like ?)k order by rnum desc)j)m where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				MeetingInfo mi = new MeetingInfo();
				MeetingInfoList mil= new MeetingInfoList();
				mi.setBossId(rset.getString("mmi"));
				mi.setCategoryName(rset.getString("cn"));
				mi.setCountryName(rset.getString("tn"));
				mi.setMeetingName(rset.getString("mmnm"));
				mi.setMeetingNo(rset.getInt("mmn"));
				mi.setMeetingTitle(rset.getString("mmt"));
				mil.setMeetingInfo(mi);
				mil.setMeetingRNum(rset.getInt("rnum"));
				list.add(mil);
				System.out.println(mi.getBossId());
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

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(meeting_no) as cnt from meeting";
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<MeetingInfoList> selectMeetingList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MeetingInfoList> list = new ArrayList<MeetingInfoList>();
		String query = "select m.* from(select rownum as rrnum, j.* from(select rownum as rnum, k.* from(select distinct meeting_no as mmn, meeting_title as mmt, meeting_name as mmnm, m.member_id as mmi, c.category_name as cn, t.country_name as tn from meeting m left join meeting_person p using(meeting_no) left join category c using(category_no) left join country t using(country_no))k order by rnum desc)j)m where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				MeetingInfo mi = new MeetingInfo();
				MeetingInfoList mil= new MeetingInfoList();
				mi.setBossId(rset.getString("mmi"));
				mi.setCategoryName(rset.getString("cn"));
				mi.setCountryName(rset.getString("tn"));
				mi.setMeetingName(rset.getString("mmnm"));
				mi.setMeetingNo(rset.getInt("mmn"));
				mi.setMeetingTitle(rset.getString("mmt"));
				mil.setMeetingInfo(mi);
				mil.setMeetingRNum(rset.getInt("rnum"));
				list.add(mil);
				System.out.println(mi.getBossId());
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


	

}
