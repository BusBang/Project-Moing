package login.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import login.model.dao.LoginDao;
import login.model.vo.CategorySubjectList;
import login.model.vo.LoginMember;

public class LoginService {

	//로그인
	public LoginMember selectOneMember(String memberId, String memberPw) {
		Connection conn = JDBCTemplate.getConnection();
		LoginMember login = new LoginDao().selectOneMember(conn, memberId, memberPw);
		JDBCTemplate.close(conn);
		return login;
	}

	//회원가입
	public int insertMember(LoginMember m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().insertMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//메일인증 아이디, 아이디찾기
	public LoginMember idSearch(String memberEmail) {
		Connection conn = JDBCTemplate.getConnection();
		LoginMember m = new LoginDao().idSearch(conn, memberEmail);
		JDBCTemplate.close(conn);
		return m;
	}
	
	//비밀번호 재설정을 위한 메일아이디 확인
	public int pwSearch(String memberEmail) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().pwSearch(conn, memberEmail);
		JDBCTemplate.close(conn);
		return result;
	}

	//비밀번호 재설정
	public int updatePw(LoginMember m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().updatePw(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//회원정보 불러오기(1인) admin이랑 본인이랑 해당 아이디 정보확인
	public LoginMember selectOneMember(String memberId) {
		System.out.println("이거"+memberId);
		Connection conn = JDBCTemplate.getConnection();
		LoginMember m = new LoginDao().selectOneMember(conn, memberId);
		JDBCTemplate.close(conn);
		return m;
	}

	//마이페이지 정보수정
	public int update(LoginMember m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().update(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
	public ArrayList<LoginMember> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<LoginMember> list = new LoginDao().selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	//카테고리 가지고 오기
	public ArrayList<CategorySubjectList> categoryParam() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<CategorySubjectList> list = new LoginDao().selectAllCategory(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	//탈퇴하기, 회원탈퇴시키기
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().deleteMember(conn, memberId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//아이디 중복체크
	public int idConfirm(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().idConfirm(conn, memberId);
		JDBCTemplate.close(conn);
		return result;
	}

	public int emailConfirm(String memberEmail) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new LoginDao().emailConfirm(conn, memberEmail);
		JDBCTemplate.close(conn);
		return result;
	}


}
