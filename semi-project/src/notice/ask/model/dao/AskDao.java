package notice.ask.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.ask.model.vo.Ask;
import notice.ask.model.vo.AskList;

public class AskDao {

	public int totalCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query="select count(ask_no) as cnt from ask where member_id=?";
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
		}
		return result;
	}

	public ArrayList<AskList> selectAskList(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where member_id=? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public int sendAsk(Connection conn, Ask ask) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into ask values (seq_ask.nextval, ?,?,?,sysdate,?,?,'n',null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ask.getMemberId());
			pstmt.setString(2, ask.getAskTitle());
			pstmt.setString(3, ask.getAskContent());
			pstmt.setString(4, ask.getAskFilename());
			pstmt.setString(5, ask.getAskFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Ask askView(Connection conn, int askNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Ask a = null;
		String query = "select ask_no, ask_title, ask_content, member_id, NVL(ask_filename,'no') as fn, nvl(ask_filepath,'no') as fp, ask_send_date, ask_reply, NVL(reply_content,'답변 대기중') as rc from ask where ask_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, askNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				a = new Ask();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("fn"));
				a.setAskFilepath(rset.getString("fp"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply"));
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("rc"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return a;
	}

	public int deleteAsk(Connection conn, int askNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from ask where ask_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, askNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query="select count(ask_no) as cnt from ask";
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
		}
		return result;
	}

	public ArrayList<AskList> selectAskAdminList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public int sendReply(Connection conn, int askNo, String replyContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update ask set reply_content=?, ask_reply='y' where ask_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, replyContent);
			pstmt.setInt(2, askNo);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}return result;
	}

	public int totalCountAT(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where ask_title like ?";
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
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int totalCountAC(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where ask_Content like ?";
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
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int totalCountRC(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where reply_content like ?";
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
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int totalCountAT(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where member_id=? and ask_title like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
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

	public int totalCountAC(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where member_id=? and ask_content like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
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

	public int totalCountRC(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from ask where member_id=? and reply_title like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
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

	public ArrayList<AskList> getListAT(Connection conn, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where ask_title like ? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public ArrayList<AskList> getListAC(Connection conn, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where ask_content like ? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public ArrayList<AskList> getListRC(Connection conn, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where reply_content like ? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public ArrayList<AskList> getListAT(Connection conn, String memberId, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where ask_title like ? and member_id=? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public ArrayList<AskList> getListAC(Connection conn, String memberId, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where ask_Content like ? and member_id=? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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

	public ArrayList<AskList> getListRC(Connection conn, String memberId, String keyword, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AskList> list = new ArrayList<AskList>();
		String query = "select v.* from (select rownum as rrnum, p.* from (select rownum as rnum, q.* from(select * from ask where reply_Content like ? and member_id=? order by ask_reply desc, ask_no asc)q order by rnum desc)p)v where rrnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Ask a = new Ask();
				AskList al = new AskList();
				a.setAskContent(rset.getString("ask_content"));
				a.setAskFilename(rset.getString("ask_filename"));
				a.setAskFilepath(rset.getString("ask_filepath"));
				a.setAskNo(rset.getInt("ask_no"));
				a.setAskReply(rset.getString("ask_reply").equals("y")?"답변완료":"답변대기중");
				a.setAskSendDate(rset.getDate("ask_send_date"));
				a.setAskTitle(rset.getString("ask_title"));
				a.setMemberId(rset.getString("member_id"));
				a.setReplyContent(rset.getString("reply_content"));
				al.setAsk(a);
				al.setRnum(rset.getInt("rnum"));
				list.add(al);
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