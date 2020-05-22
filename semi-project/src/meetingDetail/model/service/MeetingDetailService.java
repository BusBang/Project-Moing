package meetingDetail.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import meetingDetail.model.dao.MeetingDetailDao;
import meetingDetail.model.vo.Meeting;
import meetingDetail.model.vo.MeetingDetail;
import meetingDetail.model.vo.MeetingDetailAllList;
import meetingDetail.model.vo.MeetingPerson;
import meetingDetail.model.vo.Review;

public class MeetingDetailService {



	public int likes(int meetingNo, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().likes(conn, meetingNo, memberId);
		int result2=0;   //미팅테이블 업뎃하는 결과 변수
		int count=0;
		if(result>0) {
			count = new MeetingDetailDao().countLike(conn, meetingNo);
			result2 = new MeetingDetailDao().updatelike(conn, meetingNo, memberId);
			if(result2>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return count;
	}

	public int dislikes(int meetingNo, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().dislikes(conn, meetingNo, memberId);
		int result2=0;   //미팅테이블 업뎃하는 결과 변수
		int count=0;
		if(result>0) {
			count = new MeetingDetailDao().countLike(conn, meetingNo);
			result2 = new MeetingDetailDao().updatelike(conn, meetingNo, memberId);
			if(result2>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return count;
	}

	public int joinMeeting(MeetingPerson mp) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().joinMeeting(conn, mp);
		int result2 = 0;
		if(result>0) {
			result2 = new MeetingDetailDao().updateMeeting(conn,mp);
			if(result2>0) {
			JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result2;
	}

	public int insertReview(Review r, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().insertReview(conn, r, memberId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int deleteReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().deleteReview(conn,r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().updateReview(conn,r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MeetingDetailAllList selectAllMD(int reqPage, int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		
		int totalCount = new MeetingDetailDao().totalCount(conn, meetingNo);
		
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int start = (reqPage-1)*numPerPage+1;	//1,6
		int end = reqPage*numPerPage;			//5,10
		
		//ArrayList<Review> list = new MeetingDetailDao().selectReviewPerPage(conn, start, end);
		MeetingDetail md = new MeetingDetailDao().selectAllMD(conn, start, end, meetingNo);
		ArrayList<MeetingPerson> mp = new MeetingDetailDao().MeetingPersonList(conn, meetingNo);
		
		String pageNavi="";
		int pageNaviSize=5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo != 1) {
			pageNavi += "<div><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(reqPage==pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			}else {
				pageNavi += "<div><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage="+pageNo+"'>다음</a>";
		}
		
		MeetingDetail allMD = new MeetingDetail(md.getMeet(),md.getReview(),pageNavi);
		MeetingDetailAllList all = new MeetingDetailAllList(allMD, mp);
		JDBCTemplate.close(conn);
		return all;
	}

	public int deleteMeeting(int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MeetingDetailDao().deleteMeeting(conn, meetingNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Meeting> likeList(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Meeting> likeList = new MeetingDetailDao().likeList(conn, memberId);
		JDBCTemplate.close(conn);
		return likeList;
	}
	
	public Meeting likeMeeting(Meeting m, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Meeting> likeList = new MeetingDetailDao().likeList(conn, memberId);
		Meeting meet = new Meeting();
		
		
		meet.setMeetingNo(likeList.get(m.getMeetingNo()).getMeetingNo());
		JDBCTemplate.close(conn);
		return meet;
	}

	public ArrayList<Meeting> likeLists(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Meeting> likeList = new MeetingDetailDao().likeList(conn, memberId);
		JDBCTemplate.close(conn);
		return likeList;
	}

	public ArrayList<Meeting> selectOneMeeting(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Meeting> list = new MeetingDetailDao().selectAllMeeting(conn, memberId);
		JDBCTemplate.close(conn);
		return list;
	}
	
	/*public MeetingDetail selectAllMD(int reqPage, int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int totalPage = 0;
		int totalCount = new MeetingDetailDao().totalCount(conn);
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		ArrayList<Review> list = new MeetingDetailDao().selectReviewPerPage(conn, start, end);
		MeetingDetail md = new MeetingDetailDao().selectAllMD(conn, start, end, meetingNo);
		
		String pageNavi="";
		int pageNaviSize=5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo != 1) {
			pageNavi += "<a href='/meetingDetail?reqPage="+(pageNo-pageNaviSize)+"'>이전</a>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}pageNo++;
			
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/meetingDetail?reqPage="+pageNo+"'>다음</a>";
		}
		
		MeetingDetail allMD = new MeetingDetail(md.getMeet(),md.getReview(),pageNavi);
		JDBCTemplate.close(conn);
		return allMD;
	}

	public int deleteMeeting(int meetingNo) {
		Connection conn = null;
		int result = new MeetingDetailDao().deleteMeeting(conn, meetingNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}*/

	/*public MeetingDetail selectAllReview(Review r){
		Connection conn = JDBCTemplate.getConnection();
		Meeting m = new MeetingDetailDao().selectOneMeeting(conn,r);
		ArrayList<Review> allReview = new MeetingDetailDao().selectAllReview(conn, r);
		MeetingDetail md = new MeetingDetail(m,allReview);
		JDBCTemplate.close(conn);
		return md;
	}*/
}
