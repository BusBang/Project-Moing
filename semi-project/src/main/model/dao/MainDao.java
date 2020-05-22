package main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import meetingDetail.model.vo.Meeting;

public class MainDao {

	public ArrayList<Meeting> selectPopularMeeting(Connection conn, Meeting m) {		//인기모임
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "select * from (select rownum rnum, m.* from (select * from meeting order by like_count desc) m) where rnum between 1 and 3";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Meeting meet = new Meeting();
				meet.setCategoryNo(rset.getInt("category_no"));
				meet.setCountryNo(rset.getInt("country_no"));
				meet.setLikeCount(rset.getInt("like_count"));
				meet.setMeetingAddr(rset.getString("meeting_addr"));
				meet.setMeetingContent(rset.getString("meeting_content"));
				meet.setMeetingDate(rset.getDate("meeting_date"));
				meet.setMeetingFilename(rset.getString("meeting_filename"));
				meet.setMeetingFilepath(rset.getString("meeting_filepath"));
				meet.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				meet.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				meet.setMeetingName(rset.getString("meeting_name"));
				meet.setMeetingNo(rset.getInt("meeting_No"));
				meet.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				meet.setMeetingTitle(rset.getString("meeting_title"));
				meet.setMemberId(rset.getString("member_id"));
				
				list.add(meet);
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

	
	//광고 랜덤수 추출 위해 컬럼개수 세기
	public int countCol(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "select count (meeting_no) cnt from meeting";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				count = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	//광고모임
	public ArrayList<Meeting> selectAdMeeting(Connection conn, Meeting m, ArrayList<Integer> nums) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "select * from meeting where meeting_no = ? or meeting_no = ? or meeting_no = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, nums.get(0));
			pstmt.setInt(2, nums.get(1));
			pstmt.setInt(3, nums.get(2));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Meeting meet = new Meeting();
				meet.setCategoryNo(rset.getInt("category_no"));
				meet.setCountryNo(rset.getInt("country_no"));
				meet.setLikeCount(rset.getInt("like_count"));
				meet.setMeetingAddr(rset.getString("meeting_addr"));
				meet.setMeetingContent(rset.getString("meeting_content"));
				meet.setMeetingDate(rset.getDate("meeting_date"));
				meet.setMeetingFilename(rset.getString("meeting_filename"));
				meet.setMeetingFilepath(rset.getString("meeting_filepath"));
				meet.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				meet.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				meet.setMeetingName(rset.getString("meeting_name"));
				meet.setMeetingNo(rset.getInt("meeting_No"));
				meet.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				meet.setMeetingTitle(rset.getString("meeting_title"));
				meet.setMemberId(rset.getString("member_id"));
				
				list.add(meet);
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
