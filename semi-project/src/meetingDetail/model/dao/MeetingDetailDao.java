package meetingDetail.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import common.JDBCTemplate;
import meetingDetail.model.vo.Meeting;
import meetingDetail.model.vo.MeetingDetail;
import meetingDetail.model.vo.MeetingDetailAllList;
import meetingDetail.model.vo.MeetingPerson;
import meetingDetail.model.vo.Review;

public class MeetingDetailDao {
	
	public Meeting selectOneMeeting(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		Meeting m = new Meeting();
		ResultSet rset = null;
		String query = "select * from meeting where meeting_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setInt(1, 1);	//가데이터
			pstmt.setInt(1, r.getMeetingNo());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMemberId(rset.getString("member_id"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int likes(Connection conn, int meetingNo, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into like_meeting values(?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int dislikes(Connection conn, int meetingNo, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from like_meeting where meeting_no = ? and my_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int joinMeeting(Connection conn, MeetingPerson mp) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into meeting_person values(?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mp.getMeetingNo());
			pstmt.setString(2, mp.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateMeeting(Connection conn, MeetingPerson mp) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update meeting set meeting_now_person = (select count(*) from meeting_person where meeting_no = ?) where meeting_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mp.getMeetingNo());
			pstmt.setInt(2, mp.getMeetingNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	public int insertReview(Connection conn, Review r, String memberId) {		//리뷰 등록
		PreparedStatement pstmt = null;
		String query = "insert into review values(?,seq_review.nextval,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getMeetingNo());
			pstmt.setString(2, r.getReviewContent());
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		String query = "delete from review where meeting_no = ? and review_no=?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getMeetingNo());
			pstmt.setInt(2, r.getReviewNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update review set review_content = ? where review_no = ? and meeting_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReviewContent());
			pstmt.setInt(2, r.getReviewNo());
			pstmt.setInt(3, r.getMeetingNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
	public int totalCount(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt_review from review where meeting_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt_review");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Review> selectAllReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> allReview = new ArrayList<Review>();
		String query = "select * from review where meeting_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getMeetingNo());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				r.setMeetingNo(rset.getInt("meeting_no"));
				r.setMemberId(rset.getString("member_id"));
				r.setReviewContent(rset.getString("review_content"));
				r.setReviewNo(rset.getInt("review_no"));
				allReview.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return allReview;
	}
	public MeetingDetail selectAllMD(Connection conn, int start, int end, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Meeting m = new Meeting();
		ArrayList<Review> review = new ArrayList<Review>();
		MeetingDetail md = new MeetingDetail(m, review);
		String query = "select * from (select rownum as rnum, md.* from (select * from review right join meeting using (meeting_no) where meeting_no = ? order by review_no desc) md) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Review r = new Review();
				MeetingPerson mp = new MeetingPerson();
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMemberId(rset.getString(17));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				r.setMeetingNo(rset.getInt("meeting_no"));
				r.setMemberId(rset.getString("member_id"));
				r.setReviewContent(rset.getString("review_content"));
				r.setReviewNo(rset.getInt("review_no"));
				review.add(r);
				md.setReview(review);
				md.setMeet(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return md;
	}
	
	public int deleteMeeting(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from meeting where meeting_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<MeetingPerson> MeetingPersonList(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Meeting m = new Meeting();
		ArrayList<MeetingPerson> list = new ArrayList<MeetingPerson>();
		MeetingDetailAllList all = new MeetingDetailAllList();
		String query = "select * from (select rownum as rnum, md.* from (select * from meeting_person join meeting using (meeting_no) where meeting_no = ?) md)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MeetingPerson mp = new MeetingPerson();
				/*m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMemberId(rset.getString(17));
				m.setMeetingAddr(rset.getString("meeting_addr"));*/
				mp.setMeetingNo(rset.getInt("meeting_no"));
				mp.setMemberId(rset.getString(3));
				list.add(mp);
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
	

	public ArrayList<Meeting> likeList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Meeting> likeList = new ArrayList<Meeting>();
		String query = "select * from like_meeting join meeting using (meeting_no) where like_meeting.MY_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				likeList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return likeList;
	}

	public int countLike(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "select like_count from meeting where meeting_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				count = rset.getInt("like_count");
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

	public int updatelike(Connection conn, int meetingNo, String memberId) {
	      PreparedStatement pstmt = null;
	      int result = 0;
	      String query = "update meeting set like_count = (select count(*) from like_meeting where meeting_no = ?) where meeting_no = ?";
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, meetingNo);
	         pstmt.setInt(2, meetingNo);
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(pstmt);
	      }
	      return result;
	   }

	public int insertMeetingPersonInsertMoim(Connection conn, moim.model.vo.Meeting meet) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into meeting_person values (SEQ_MEETING.CURRVAL,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, meet.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public ArrayList<Meeting> selectAllMeeting(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		ResultSet rset = null;
		String query = "select * from meeting where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setInt(1, 1);	//가데이터
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Meeting m = new Meeting();
				m.setCategoryNo(rset.getInt("category_no"));
				m.setCountryNo(rset.getInt("country_no"));
				m.setLikeCount(rset.getInt("like_count"));
				m.setMeetingContent(rset.getString("meeting_content"));
				m.setMeetingDate(rset.getDate("meeting_date"));
				m.setMeetingFilename(rset.getString("meeting_filename"));
				m.setMeetingFilepath(rset.getString("meeting_filepath"));
				m.setMeetingMaxPerson(rset.getInt("meeting_max_person"));
				m.setMeetingMinPerson(rset.getInt("meeting_min_person"));
				m.setMeetingNowPerson(rset.getInt("meeting_now_person"));
				m.setMeetingName(rset.getString("meeting_name"));
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setMeetingTitle(rset.getString("meeting_title"));
				m.setMemberId(rset.getString("member_id"));
				m.setMeetingAddr(rset.getString("meeting_addr"));
				list.add(m);
				
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