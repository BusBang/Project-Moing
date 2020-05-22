package mypage.letter.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.letter.model.dao.LetterDao;
import mypage.letter.model.vo.Letter;
import mypage.letter.model.vo.LetterList;
import mypage.letter.model.vo.LetterPageData;

public class LetterService {

	public LetterPageData selectLetterGetList(int reqPage, String memberId) {//받은 쪽지함 리스트 가져오는 메소드
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalLetterGetCount = new LetterDao().totalLetterGetCount(conn, memberId);
		int totalPage = 0;
		if(totalLetterGetCount%numPerPage==0) {
			totalPage= totalLetterGetCount/numPerPage;
		}else {
			totalPage= totalLetterGetCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<LetterList> list = new LetterDao().selectLetterGetList(conn, memberId, start, end);
		
		String letterPageNavi = "";
		
		int pageNaviSize = 5;
		
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			letterPageNavi += "<span class='sideNavi'><a href='/letterList?num=0&reqPageSendLetter=1&reqPageGetLetter="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				letterPageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				letterPageNavi += "<span class='numNavi'><a href='/letterList?num=0&reqPageSendLetter=1&reqPageGetLetter="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			letterPageNavi += "<span class='sideNavi'><a href='/letterList?num=0&reqPageSendLetter=1&reqPageGetLetter="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		LetterPageData lpd = new LetterPageData(list, letterPageNavi);
		JDBCTemplate.close(conn);
		return lpd;
	}
	public LetterPageData selectLetterSendList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalLetterSendCount = new LetterDao().totalLetterSendCount(conn, memberId);
		int totalPage = 0;
		if(totalLetterSendCount%numPerPage==0) {
			totalPage= totalLetterSendCount/numPerPage;
		}else {
			totalPage= totalLetterSendCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<LetterList> list = new LetterDao().selectLetterSendList(conn, memberId, start, end);
		//페이지 네비 작성 시작
		String letterPageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			letterPageNavi += "<span class='sideNavi'><a class='sideNavi' href='/letterList?num=1&reqPageGetLetter=1&reqPageSendLetter="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				letterPageNavi += "<span class='numNavi'><span class='selNavi'>"+pageNo+"</span></span>";
			}else {
				letterPageNavi += "<span class='numNavi'><a class='numNavi' href='/letterList?num=1&reqPageGetLetter=1&reqPageSendLetter="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			letterPageNavi += "<div class='sideNavi'><a class='sideNavi' href='/letterList?num=1&reqPageGetLetter=1&reqPageSendLetter="+pageNo+"&memberId="+memberId+"'>다음</a></div>";//다음 네비 페이지로 이동.
		}
		LetterPageData lpd = new LetterPageData(list, letterPageNavi);
		JDBCTemplate.close(conn);
		return lpd;
	}

	public Letter selectOneLetter(int letterNo, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Letter l = new LetterDao().selectOneLetter(conn, letterNo);
		if(l.getLetterRead().equals("안읽음") && l.getLetterGetMemberId().equals(memberId)) {
			int result = new LetterDao().readNewLetter(conn, letterNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		JDBCTemplate.close(conn);
		return l;
	}
	public int sendLetter(ArrayList<Letter> arrLetter) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		for(int i=0; i<arrLetter.size(); i++) {
			result += new LetterDao().sendLetter(conn, arrLetter.get(i));
		}
		if(result==arrLetter.size()) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int deleteLetter(int letterNo, int num) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		if(num ==0) {
			//받은 쪽지
			//1. 보낸 사람이 삭제했는지 검사
			Letter l = new LetterDao().selectOneLetter(conn, letterNo);
			if(l.getLetterDelSend().equals("T")) {
				//2. 보낸 사람도 삭제한 쪽지인 경우 ->완전 삭제
				result = new LetterDao().deleteLetterComplete(conn, letterNo);
			}else {
				//3. 보낸 사람은 아직 삭제 안했음 ->del_get 컬럼값 바꿈
				result = new LetterDao().deleteGetLetter(conn, letterNo);
				
			}
		}else {
			//보낸 쪽지
			//1. 받은 사람이 삭제했는지 검사
			Letter l = new LetterDao().selectOneLetter(conn, letterNo);
			if(l.getLetterDelGet().equals("T")) {
				//2. 받은 사람도 삭제한 쪽지인 경우 ->완전 삭제
				result = new LetterDao().deleteLetterComplete(conn, letterNo);
			}else {
				//3. 받은 사람은 아직 삭제 안했음 ->del_get 컬럼값 바꿈
				result = new LetterDao().deleteSendLetter(conn, letterNo);
				
			}
		}
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
			
		return result;
	}
	public LetterPageData searchKeywordGet(String opt, String keyword, String memberId, int reqPage, int num) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalLetterGetCount = -1;
		System.out.println("!!"+keyword);
		System.out.println("!!"+memberId);
		if(num==0) {//받은 쪽지 검색시 총 쪽지 갯수 구하기
			if(opt.equals("letterSendMemberId")) {
				//받은쪽지 중 보낸 회원 아이디 검색시 총 쪽지 갯수 구하기
				int totalLetterGetCounts = new LetterDao().totalLetterGetCountSendMemberId(conn, memberId, keyword);
				totalLetterGetCount = totalLetterGetCounts;
				
			}else if(opt.equals("letterTitle")) {
				//받은쪽지 중 제목 검색시 총 쪽지 갯수 구하기

				totalLetterGetCount = new LetterDao().totalLetterGetCountLetterTitle(conn, memberId, keyword);
			}else {
				//받은쪽지 중 내용 검색시 총 쪽지 갯수 구하기

				totalLetterGetCount = new LetterDao().totalLetterGetCountLetterContent(conn, memberId, keyword);
			}
			
		}else {//보낸 쪽지 검색시 총 쪽지 갯수 구하기
			if(opt.equals("letterGetMemberId")) {
				//보낸쪽지 중 받은 회원 아이디 검색시 총 쪽지 갯수 구하기
				totalLetterGetCount = new LetterDao().totalLetterSendCountGetmemberId(conn, memberId, keyword);
				
			}else if(opt.equals("letterTitle")) {
				//보낸쪽지 중 제목 검색시 총 쪽지 갯수 구하기
				totalLetterGetCount = new LetterDao().totalLetterSendCountLetterTitle(conn, memberId, keyword);
			}else {
				//보낸쪽지 중 내용 검색시 총 쪽지 갯수 구하기
				totalLetterGetCount = new LetterDao().totalLetterSendCountLetterContent(conn, memberId, keyword);
			}
		}
		System.out.println(totalLetterGetCount);
		
		int totalPage = 0;
		if(totalLetterGetCount%numPerPage==0) {
			totalPage= totalLetterGetCount/numPerPage;
		}else {
			totalPage= totalLetterGetCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<LetterList> list = new ArrayList<LetterList>();
		if(num==0) {//받은 쪽지 검색 시 쪽지 리스트 구하기
			if(opt.equals("letterSendMemberId")) {
				//받은 쪽지중 보낸 회원 아이디 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterGetListSendMemberId(conn, memberId, start, end, keyword);
				
			}else if(opt.equals("letterTitle")) {
				//받은 쪽지중 제목 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterGetListLetterTitle(conn, memberId, start, end, keyword);
			}else {
				//받은 쪽지중 내용 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterGetListLetterContent(conn, memberId, start, end, keyword);
			}

		}else {//보낸 쪽지 검색 시 쪽지 리스트 구하기
			if(opt.equals("letterGetMemberId")) {
				//보낸 쪽지중 받은 회원 아이디 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterSendListGetMemberId(conn, memberId, start, end, keyword);
				
			}else if(opt.equals("letterTitle")) {
				//보낸 쪽지중 제목 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterSendListLetterTitle(conn, memberId, start, end, keyword);
			}else {
				//보낸 쪽지중 내용 검색 시 쪽지 리스트 구하기
				list = 	new LetterDao().selectLetterSendListLetterContent(conn, memberId, start, end, keyword);
			}
			
		}
		//페이지 네비 작성 시작
		String letterPageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			letterPageNavi += "<span class='sideNavi'><a href='/searchGetLetterKeyword?reqPage="+(pageNo-pageNaviSize)+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+num+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				letterPageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				letterPageNavi += "<span class='numNavi'><a href='/searchGetLetterKeyword?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+num+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			letterPageNavi += "<span class='sideNavi'><a href='/searchGetLetterKeyword?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+num+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		LetterPageData lpd = new LetterPageData(list, letterPageNavi);
		JDBCTemplate.close(conn);
		return lpd;
	}
	


}
