package moim.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import meetingDetail.model.vo.MeetingPerson;
import meetingDetail.model.vo.Review;
import moim.model.vo.Meeting;

public class MoimDao {

	public int insertMoim(Connection conn, Meeting meet) {
		PreparedStatement pstmt =null;
		int result = 0;
		String query = "INSERT INTO MEETING VALUES (SEQ_MEETING.NEXTVAL, ?, ? , DEFAULT, ? ,? ,? ,? ,? ,?, ? ,DEFAULT ,?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query); //meetingno
			pstmt.setString(1, meet.getMeetingTitle());	//meeting title
			pstmt.setString(2, meet.getMeetingName());	//meeting name + now
			pstmt.setInt(3, meet.getMeetingMinPerson());	
			pstmt.setInt(4, meet.getMeetingMaxPerson());
			pstmt.setString(5, meet.getMeetingContent());
			pstmt.setString(6, meet.getMeetingFilename());
			pstmt.setString(7, meet.getMeetingFilepath());
			pstmt.setDate(8, meet.getMeetingDate());
			pstmt.setString(9, meet.getMeetingAddr());
			pstmt.setString(10, meet.getMemberId());
			pstmt.setInt(11, meet.getCategoryNo());
			pstmt.setInt(12, meet.getCountryNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Meeting> selectAllMeeting(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String query = "select * from meeting";
		Meeting m = null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				meetingList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return meetingList;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from meeting WHERE MEETING_DATE >= SYSDATE";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Meeting> selectMeeting(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE MEETING_DATE >= SYSDATE ORDER BY MEETING_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeeting(Connection conn, int start, int end, int categoryNo, int countryNo, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO=? AND COUNTRY_NO=? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, countryNo);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//카테고리 리스트 조회
	public ArrayList<Meeting> selectMeetingListCategory(Connection conn, int start, int end, int categoryNo, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeeting(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//장소 키워드 조회
	public ArrayList<Meeting> selectMeetingListCountry(Connection conn, int start, int end, int countryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE COUNTRY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, countryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//카테고리, 장소, 키워드
	public int totalCount(Connection conn, int categoryNo, int countryNo, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from meeting where category_no =? and country_no = ? and meeting_title like ? AND MEETING_DATE >= SYSDATE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, countryNo);
			pstmt.setString(3, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//카테고리, 키워드
	public int totalCountCategory(Connection conn, int categoryNo, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from meeting where category_no =? and meeting_title like ? AND MEETING_DATE >= SYSDATE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//키워드만
	public int totalCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from meeting where meeting_title like ? AND MEETING_DATE >= SYSDATE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//지역만/
	public int totalCountCountry(Connection conn, int countryNo, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from meeting where country_no =? and meeting_title like ? AND MEETING_DATE >= SYSDATE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, countryNo);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//인기순1
	public ArrayList<Meeting> selectMeetingLike(Connection conn, int start, int end, int categoryNo, int countryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO=? AND COUNTRY_NO=? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY LIKE_COUNT DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, countryNo);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeetingListCategoryLike(Connection conn, int start, int end, int categoryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY LIKE_COUNT DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeetingListCountryLike(Connection conn, int start, int end, int countryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE COUNTRY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY LIKE_COUNT DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, countryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//인기순 키워드만있을때
	public ArrayList<Meeting> selectMeetingNoNumberLike(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY LIKE_COUNT DESC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeetingName(Connection conn, int start, int end, int categoryNo, int countryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO=? AND COUNTRY_NO=? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_TITLE ASC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, countryNo);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Meeting> selectMeetingListCategoryName(Connection conn, int start, int end, int categoryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE CATEGORY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_TITLE ASC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeetingListCountryName(Connection conn, int start, int end, int countryNo,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE COUNTRY_NO = ? AND MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_TITLE ASC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, countryNo);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Meeting> selectMeetingNoNumberName(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM MEETING WHERE MEETING_TITLE LIKE ? AND MEETING_DATE >= SYSDATE ORDER BY MEETING_TITLE ASC) N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				list.add(m);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public Meeting selectOneMeeting(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from meeting where meeting_no = ?";
		Meeting m = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMemberId(rset.getString("member_id"));
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int MoimModify(Connection conn, Meeting meet) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update meeting set meeting_title=?, meeting_min_person=?, meeting_max_person=?, meeting_content=?, meeting_filename=?, meeting_filepath=?, meeting_date=?, meeting_addr=?, category_no=?, country_no=? where meeting_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, meet.getMeetingTitle());
			pstmt.setInt(2, meet.getMeetingMinPerson());
			pstmt.setInt(3, meet.getMeetingMaxPerson());
			pstmt.setString(4, meet.getMeetingContent());
			pstmt.setString(5, meet.getMeetingFilename());
			pstmt.setString(6, meet.getMeetingFilepath());
			pstmt.setDate(7, meet.getMeetingDate());
			pstmt.setString(8, meet.getMeetingAddr());
			pstmt.setInt(9, meet.getCategoryNo());
			pstmt.setInt(10, meet.getCountryNo());
			pstmt.setInt(11, meet.getMeetingNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<MeetingPerson> selectAllMeetingPerson(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MeetingPerson mp = new MeetingPerson();
		ArrayList<MeetingPerson> meetingPersonList = new ArrayList<MeetingPerson>();
		String query = "select * from meeting_person where meeting_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				mp = new MeetingPerson();
				mp.setMeetingNo(rset.getInt("meeting_no"));
				mp.setMemberId(rset.getString("member_id"));
				meetingPersonList.add(mp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return meetingPersonList;
	}

	public int MoimReopen(Connection conn, Meeting meet) {
		PreparedStatement pstmt =null;
		int result = 0;
		String query = "INSERT INTO MEETING VALUES (SEQ_MEETING.NEXTVAL, ?, ? , ?, ? ,? ,? ,? ,? ,?, ? ,? ,?, ?, ?)";
		//세번째칸은 현재 인원
		try {
			pstmt = conn.prepareStatement(query); //meetingno
			pstmt.setString(1, meet.getMeetingTitle());	//meeting title
			pstmt.setString(2, meet.getMeetingName());	//meeting name + now
			pstmt.setInt(3, meet.getMeetingNowPerson());
			pstmt.setInt(4, meet.getMeetingMinPerson());	
			pstmt.setInt(5, meet.getMeetingMaxPerson());
			pstmt.setString(6, meet.getMeetingContent());
			pstmt.setString(7, meet.getMeetingFilename());
			pstmt.setString(8, meet.getMeetingFilepath());
			pstmt.setDate(9, meet.getMeetingDate());
			pstmt.setString(10, meet.getMeetingAddr());
			pstmt.setInt(11, meet.getLikeCount());
			pstmt.setString(12, meet.getMemberId());
			pstmt.setInt(13, meet.getCategoryNo());
			pstmt.setInt(14, meet.getCountryNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Review> selectAllReview(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> rList = new ArrayList<Review>();		
		String query = "select * from review where meeting_no = ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Review r = new Review();
				r.setMeetingNo(rset.getInt("meeting_no"));
				r.setReviewNo(rset.getInt("review_no"));	//PK
				r.setReviewContent(rset.getString("review_content"));
				r.setMemberId(rset.getString("member_id"));
				rList.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;

	}

	public int MoimReopenSeqSelect(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select meeting_no from meeting order by meeting_no desc";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertMeetingPerson(Connection conn, int resultSeq, ArrayList<MeetingPerson> meetingPersonList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into meeting_person values (?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);

			for(int i = 0; i<meetingPersonList.size(); i++) {				
				pstmt.setInt(1, resultSeq);
				pstmt.setString(2, meetingPersonList.get(i).getMemberId());
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int insertReview(Connection conn, int resultSeq, ArrayList<Review> reviewList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into review values (?, seq_review.nextval, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<reviewList.size(); i++) {				
				pstmt.setInt(1, resultSeq);
				pstmt.setString(2, reviewList.get(i).getReviewContent());
				pstmt.setString(3, reviewList.get(i).getMemberId());
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int MoimModifyFileNull(Connection conn, Meeting meet) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update meeting set meeting_title=?, meeting_min_person=?, meeting_max_person=?, meeting_content=?, meeting_date=?, meeting_addr=?, category_no=?, country_no=? where meeting_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, meet.getMeetingTitle());
			pstmt.setInt(2, meet.getMeetingMinPerson());
			pstmt.setInt(3, meet.getMeetingMaxPerson());
			pstmt.setString(4, meet.getMeetingContent());
			pstmt.setDate(5, meet.getMeetingDate());
			pstmt.setString(6, meet.getMeetingAddr());
			pstmt.setInt(7, meet.getCategoryNo());
			pstmt.setInt(8, meet.getCountryNo());
			pstmt.setInt(9, meet.getMeetingNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


}
