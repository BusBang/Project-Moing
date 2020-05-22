package mypage.letter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.letter.model.vo.Letter;
import mypage.letter.model.vo.LetterList;

public class LetterDao {

	public int totalLetterGetCount(Connection conn, String memberId) {//해당 아이디의 받은 쪽지 갯수 구하는 쿼리 실행하는 메소드
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query="select count(*) as lcnt from letter where letter_get_member_id=? and letter_del_get='F'";
		try {
			pstmt = conn.prepareStatement(query);//쪽지 수신 아이디가 해당 아이디이고 수신자가 삭제하지 않은 쪽지의 갯수
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("lcnt");
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
	public int totalLetterSendCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query="select count(*) as lcnt from letter where letter_send_member_id=? and letter_del_send='F'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("lcnt");
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

	public ArrayList<LetterList> selectLetterGetList(Connection conn, String memberId, int start, int end) {//해당 아이디가 받은 쪽지의 정보를 모두 가져오는 쿼리를 실행하는 메소드
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_del_get='F' and letter_get_member_id=? order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);//수신아이디가 해당 아이디이고 수신자가 삭제하지 않은 쪽지의 정보를 구함
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterSendList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_del_send='F' and letter_send_member_id=? order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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

	public Letter selectOneLetter(Connection conn, int letterNo) {
		PreparedStatement pstmt = null;
		Letter l = null;
		ResultSet rset = null;
		String query = "select * from letter where letter_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, letterNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				l=new Letter();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return l;
	}

	public int readNewLetter(Connection conn, int letterNo) {
		PreparedStatement pstmt = null;
		Letter l = null;
		int result = 0;
		String query = "update letter set letter_read='T' where letter_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, letterNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int sendLetter(Connection conn, Letter letter) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into letter values (seq_letter.nextval,?,?,?,sysdate,?,'F','F','F')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, letter.getLetterSendMemberId());
			pstmt.setString(2, letter.getLetterTitle());
			pstmt.setString(3, letter.getLetterContent());
			pstmt.setString(4, letter.getLetterGetMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int deleteGetLetter(Connection conn, int letterNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update letter set letter_del_get='T' where letter_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, letterNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int deleteLetterComplete(Connection conn, int letterNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from letter where letter_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, letterNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int deleteSendLetter(Connection conn, int letterNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update letter set letter_del_send='T' where letter_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, letterNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int totalLetterGetCountSendMemberId(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = -1;
		ResultSet rset = null;
		System.out.println(memberId+"!!");
		System.out.println(keyword+"!!");
		String query = "select count(*) as cnt from letter where letter_get_member_id=? and letter_send_member_id like ? and letter_del_get='F'";
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
		System.out.println(result);
		return result;
	}
	public int totalLetterGetCountLetterTitle(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from letter where letter_get_member_id=? and letter_title like ? and letter_del_get='F'";
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
	public int totalLetterGetCountLetterContent(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from letter where letter_get_member_id=? and letter_content like ? and letter_del_get='F'";
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
	public int totalLetterSendCountGetmemberId(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from letter where letter_send_member_id=? and letter_get_member_id like ? and letter_del_send='F'";
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
	public int totalLetterSendCountLetterTitle(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from letter where letter_send_member_id=? and letter_title like ? and letter_del_send='F'";
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
	public int totalLetterSendCountLetterContent(Connection conn, String memberId, String keyword) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from letter where letter_send_member_id=? and letter_content like ? and letter_del_send='F'";
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
	public ArrayList<LetterList> selectLetterGetListSendMemberId(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_get_member_id=? and letter_send_member_id like ? and letter_del_get='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterGetListLetterTitle(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_get_member_id=? and letter_title like ? and letter_del_get='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterGetListLetterContent(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_get_member_id=? and letter_Content like ? and letter_del_get='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterSendListGetMemberId(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_send_member_id=? and letter_get_member_id like ? and letter_del_send='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterSendListLetterTitle(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_send_member_id=? and letter_title like ? and letter_del_send='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
	public ArrayList<LetterList> selectLetterSendListLetterContent(Connection conn, String memberId, int start, int end,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select j.* from (select rownum as rrnum, k.* from (select rownum as rnum, l.* from (select * from letter where letter_send_member_id=? and letter_Content like ? and letter_del_send='F' order by letter_read desc, letter_no asc)l order by rnum desc)k)j where rrnum between ? and ?";
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Letter l = new Letter();
				LetterList ll = new LetterList();
				l.setLetterContent(rset.getString("letter_content"));
				l.setLetterGetMemberId(rset.getString("letter_get_member_id"));
				l.setLetterNo(rset.getInt("letter_no"));
				l.setLetterSendDate(rset.getDate("letter_send_date"));
				l.setLetterSendMemberId(rset.getString("letter_send_member_id"));
				l.setLetterTitle(rset.getString("letter_title"));
				l.setLetterRead(rset.getString("letter_read").equals("T")?"읽음":"안읽음");
				l.setLetterDelGet(rset.getString("letter_del_get"));
				l.setLetterDelSend(rset.getString("letter_del_send"));
				ll.setLetter(l);
				ll.setLetterRNum(rset.getInt("rnum"));
				list.add(ll);
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
