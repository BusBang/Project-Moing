package mypage.meeting.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.letter.model.dao.LetterDao;
import mypage.letter.model.vo.LetterList;
import mypage.letter.model.vo.LetterPageData;
import mypage.meeting.model.dao.MeetingDao;
import mypage.meeting.model.vo.MeetingInfoList;
import mypage.meeting.model.vo.MyMeetingPageData;

public class MeetingService {

	public MyMeetingPageData myMeetingList(int reqPage, String memberId) {
		System.out.println(memberId);
		System.out.println(reqPage);
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//로그인 아이디가 가입 된 모임 수 구해오는 dao호출
		int totalCount = new MeetingDao().totalCount(conn, memberId);
		
		System.out.println("서비스 : 모임 수 "+ totalCount);
		
		//총 페이지 수를 연산
		int totalPage=0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 모임정보 시작번호와 끝번호 연산
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		//해당 페이지의 모임 정보와 rownum 조회
		ArrayList<MeetingInfoList> list = new MeetingDao().selectMeetingList(conn, start, end, memberId);//between 사이에 start와 end를 넣어줄 것이다.
		
		System.out.println("서비스: 모임 정보 리스트 사이즈"+list.size());
		
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = /*한번에 표시 할 페이지의 수*/ 5;
		int pageNo = /*해당 페이지 네비의 시작 번호*/ ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+(pageNo-pageNaviSize)+"'> 이전</a>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+pageNo+"'>다음</a>";//다음 네비 페이지로 이동.
		}
		MyMeetingPageData mmpd = new MyMeetingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return mmpd;
	}

	public String meetingMember(int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		String letterGetMemberId = new MeetingDao().meetingMember(conn, meetingNo);
		JDBCTemplate.close(conn);
		return letterGetMemberId;
	}

	public MyMeetingPageData searchKeywordGet(String opt, String keyword, String memberId, int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//로그인 아이디가 가입 된 모임 수 구해오는 dao호출
		int totalCount = 0;
		
		if(opt.equals("memberId")) {
			totalCount = new MeetingDao().totalCountOptMemberId(conn, memberId, keyword);
		}else {
			totalCount = new MeetingDao().totalCountOptMeetingTitle(conn, memberId, keyword);
		}
		
		System.out.println("서비스 : 모임 수 "+ totalCount);
		
		//총 페이지 수를 연산
		int totalPage=0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 모임정보 시작번호와 끝번호 연산
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		//해당 페이지의 모임 정보와 rownum 조회
		ArrayList<MeetingInfoList> list = new ArrayList<MeetingInfoList>();
		if(opt.equals("memberId")) {
			list = new MeetingDao().selectMeetingListOptMI(conn, start, end, memberId, keyword);//between 사이에 start와 end를 넣어줄 것이다.
		}else {
			list = new MeetingDao().selectMeetingListOptMT(conn, start, end, memberId, keyword);
		}
		
		System.out.println("서비스: 모임 정보 리스트 사이즈"+list.size());
		
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = /*한번에 표시 할 페이지의 수*/ 5;
		int pageNo = /*해당 페이지 네비의 시작 번호*/ ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+(pageNo-pageNaviSize)+"'> 이전</a>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/myMoim?memberId="+memberId+"&reqPage="+pageNo+"'>다음</a>";//다음 네비 페이지로 이동.
		}
		MyMeetingPageData mmpd = new MyMeetingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return mmpd;
	}

	public MyMeetingPageData adminMeetingList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//로그인 아이디가 가입 된 모임 수 구해오는 dao호출
		int totalCount = new MeetingDao().totalCount(conn);
		
		System.out.println("서비스 : 모임 수 "+ totalCount);
		
		//총 페이지 수를 연산
		int totalPage=0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 모임정보 시작번호와 끝번호 연산
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		//해당 페이지의 모임 정보와 rownum 조회
		ArrayList<MeetingInfoList> list = new MeetingDao().selectMeetingList(conn, start, end);//between 사이에 start와 end를 넣어줄 것이다.
		
		System.out.println("서비스: 모임 정보 리스트 사이즈"+list.size());
		
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = /*한번에 표시 할 페이지의 수*/ 5;
		int pageNo = /*해당 페이지 네비의 시작 번호*/ ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/adminMoim?&reqPage="+(pageNo-pageNaviSize)+"'> 이전</a>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/adminMoim?&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/adminMoim?&reqPage="+pageNo+"'>다음</a>";//다음 네비 페이지로 이동.
		}
		MyMeetingPageData mmpd = new MyMeetingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return mmpd;
	}

}
