package login.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import common.JDBCTemplate;
import login.model.vo.CategorySubjectList;
import login.model.vo.LoginMember;

public class LoginDao {
	
	//로그인
	public LoginMember selectOneMember(Connection conn, String memberId, String memberPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LoginMember login = null;
		String query = "select * from member where member_id=? and member_pw=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				login = new LoginMember();
				login.setMemberId(rset.getString("member_id"));
				login.setMemberPw(rset.getString("member_pw"));
				login.setMemberAddr(rset.getString("member_addr"));
				login.setMemberEmail(rset.getString("member_email"));
				login.setMemberName(rset.getString("member_name"));
				login.setMemberPhone(rset.getString("member_phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return login;
	}
	
	//회원가입
	public int insertMember(Connection conn, LoginMember m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, m.getMemberId());
			pstmt.setString(index++, m.getMemberPw());
			pstmt.setString(index++, m.getMemberName());
			pstmt.setString(index++, m.getMemberAddr());
			pstmt.setString(index++, m.getMemberEmail());
			pstmt.setString(index++, m.getMemberPhone());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//메일인증을 위한 아이디 있는지 확인
	public LoginMember idSearch(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LoginMember m = null;
		String query = "select * from member where member_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new LoginMember();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
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
	
	//비밀번호 재설정
	public int updatePw(Connection conn, LoginMember m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw = ? where member_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//회원정보 보기
	public LoginMember selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LoginMember m = null;
		System.out.println(memberId);
		String query = "select * from member where member_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new LoginMember();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
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
	
	//이메일정보가 있는지 확인
	public int pwSearch(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberEmail);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//마이페이지 정보수정
	public int update(Connection conn, LoginMember m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?,member_name=?,member_email=?,member_phone=?,member_addr=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setString(5, m.getMemberAddr());
			pstmt.setString(6, m.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//
	public ArrayList<LoginMember> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LoginMember> list = new ArrayList<LoginMember>();
		String query = "select * from member";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				LoginMember m = new LoginMember();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAddr(rset.getString("member_addr"));
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

	//카테고리 불러오기
	public ArrayList<CategorySubjectList> selectAllCategory(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CategorySubjectList> list = new ArrayList<CategorySubjectList>();
		String query = "select * from category";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CategorySubjectList cl = new CategorySubjectList();
				cl.setCategoryLevel(rset.getInt("category_level"));
				cl.setCategoryName(rset.getString("category_name"));
				cl.setCategoryNo(rset.getInt("category_no"));
				cl.setCategoryRef(rset.getInt("category_ref"));
				list.add(cl);
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

	//admin이 회원 탈퇴시키기
	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//아이디 중복체크
	public int idConfirm(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int emailConfirm(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberEmail);
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
