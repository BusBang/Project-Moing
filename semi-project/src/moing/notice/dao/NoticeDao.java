package moing.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import moing.notice.vo.Notice;

public class NoticeDao {

	public int noticeInsert(Connection conn, Notice n) {
		//글쓰기용
		PreparedStatement pstmt =null;
		int result = 0;
		String query = "insert into notice values(seq_notice.nextval,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Notice seleteoneNotice(Connection conn, int noticeNo) {
		//noticeMainFrm에서 사용
		PreparedStatement pstmt = null;
		Notice n = null;
		ResultSet rset = null;
		String query = "select * from notice where notice_no=?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}

	public int noticeModify(Notice n, Connection conn) {
		//게시글 수정
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="update notice set notice_title=?,notice_content=? where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int noticeDelete(Connection conn, int noticeNo) {
		//삭제용
		PreparedStatement pstmt = null;
		int result=0;
		String query = "delete from notice where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Notice noticeView(Connection conn, int noticeNo) {
		//notice view  상세보기용 dao
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String query = "select * from notice where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}

	public ArrayList<Notice> searchNoticeTitle(Connection conn, String keyword) {
		//noticeTitle search 용 Dao
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<Notice>list = new ArrayList<Notice>();
		Notice n = null;
		String query = "select * from notice where notice_title like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				list.add(n);
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

	public ArrayList<Notice> searchNoticeContent(Connection conn, String keyword) {
		//noticeContent search 용 Dao 
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<Notice>list = new ArrayList<Notice>();
		Notice n = null;
		String query = "select * from notice where notice_content like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				list.add(n);
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
		//메인페이지 total count dao
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int result = 0;
		String query = "select count(*) as cnt from notice";
		try {
			pstmt = conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			if(rset.next()) {
				result =rset.getInt("cnt");
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

	public ArrayList<Notice> selectList(Connection conn, int start, int end) {
		//main에서 사용하는 페이징 dao
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice>list = new ArrayList<Notice>();
		String query ="SELECT * FROM(SELECT ROWNUM AS RNUM,N.* FROM (SELECT * FROM NOTICE ORDER BY NOTICE_NO DESC)N)WHERE RNUM BETWEEN ? AND ? ";
			try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				list.add(n);
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
//타이틀로 검색
	public ArrayList<Notice> searchNoticeTitle(int start,int end,int reqPage, Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		ArrayList<Notice>list = new ArrayList<Notice>();
		String query = "SELECT * FROM(SELECT ROWNUM AS RNUM,N.* FROM (select * from notice where notice_title like ? ORDER BY NOTICE_NO DESC)N)WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n  =  new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				list.add(n);
		
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

	public ArrayList<Notice> searchNoticeContent(int start, int end, int reqPage, Connection conn, String keyword) {
		//noticeContent로 검색
		PreparedStatement pstmt = null;
		System.out.println("dao keyword : "+keyword);
		ResultSet rset = null;
		ArrayList<Notice>list = new ArrayList<Notice>();
		String query = "SELECT * FROM(SELECT ROWNUM AS RNUM,N.* FROM (select * from notice where notice_content like ? ORDER BY NOTICE_NO DESC)N)WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n  =  new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				list.add(n);
		
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

	public int totalCount2(String keyword, String type, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int result = 0;
		String query = "select count(*) as cnt from notice where "+type+" like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
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
		}
		
		return result;
	}


}
