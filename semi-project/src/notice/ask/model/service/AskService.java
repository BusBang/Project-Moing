package notice.ask.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.letter.model.dao.LetterDao;
import mypage.letter.model.vo.LetterList;
import mypage.letter.model.vo.LetterPageData;
import notice.ask.model.dao.AskDao;
import notice.ask.model.vo.Ask;
import notice.ask.model.vo.AskList;
import notice.ask.model.vo.AskPageData;

public class AskService {

	public AskPageData askList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//로그인 아이디가 작성한 문의글 수 구해오는 dao호출
		int totalCount = new AskDao().totalCount(conn, memberId);
		
		//총 페이지 수를 연산
		int totalPage=0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 문의글 시작번호와 끝번호 연산
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		//해당 페이지의 문의글과 rownum 조회
		ArrayList<AskList> list = new AskDao().selectAskList(conn, start, end, memberId);//between 사이에 start와 end를 넣어줄 것이다.
		System.out.println("서비스"+list.size());
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = /*한번에 표시 할 페이지의 수*/ 5;
		int pageNo = /*해당 페이지 네비의 시작 번호*/ ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/askFrm?memberId="+memberId+"&reqPage="+(pageNo-pageNaviSize)+"'> 이전</a>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/askFrm?memberId="+memberId+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/askFrm?memberId="+memberId+"&reqPage="+pageNo+"'>다음</a>";//다음 네비 페이지로 이동.
		}
		AskPageData apd = new AskPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return apd;
	}

	public int askSend(Ask ask) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AskDao().sendAsk(conn, ask);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Ask askView(int askNo) {
		Connection conn = JDBCTemplate.getConnection();
		Ask ask = new AskDao().askView(conn, askNo);
		System.out.println(ask.getReplyContent());
		JDBCTemplate.close(conn);
		return ask;
	}

	public int askDelete(int askNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AskDao().deleteAsk(conn, askNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public AskPageData askAdminList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//로그인 아이디가 작성한 문의글 수 구해오는 dao호출
		int totalCount = new AskDao().totalCount(conn);
		
		//총 페이지 수를 연산
		int totalPage=0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 문의글 시작번호와 끝번호 연산
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		//해당 페이지의 문의글과 rownum 조회
		ArrayList<AskList> list = new AskDao().selectAskAdminList(conn, start, end);//between 사이에 start와 end를 넣어줄 것이다.
		System.out.println("서비스"+list.size());
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = /*한번에 표시 할 페이지의 수*/ 5;
		int pageNo = /*해당 페이지 네비의 시작 번호*/ ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/askAdmin?reqPage="+(pageNo-pageNaviSize)+"'> 이전</a>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/askAdmin?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/askAdmin?reqPage="+pageNo+"'>다음</a>";//다음 네비 페이지로 이동.
		}
		AskPageData apd = new AskPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return apd;
	}

	public int sendReply(int askNo, String replyContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AskDao().sendReply(conn, askNo, replyContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public AskPageData searchKeyword(String opt, String keyword, String memberId, int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalCount = 0;
		System.out.println("!!"+keyword);
		System.out.println("!!"+memberId);
		if(memberId.equals("admin")) {//관리자
			if(opt.equals("askTitle")) {
				
				totalCount = new AskDao().totalCountAT(conn, keyword);
			}else if(opt.equals("askContent")) {
				totalCount = new AskDao().totalCountAC(conn,keyword);
			}else {
				totalCount = new AskDao().totalCountRC(conn,keyword);
			}
			
		}else {//일반 회원
			if(opt.equals("askTitle")) {
				
				totalCount = new AskDao().totalCountAT(conn, memberId, keyword);
			}else if(opt.equals("askContent")) {
				totalCount = new AskDao().totalCountAC(conn, memberId, keyword);
			}else {
				totalCount = new AskDao().totalCountRC(conn, memberId, keyword);
			}
		}
		
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage= totalCount/numPerPage;
		}else {
			totalPage= totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<AskList> list = new ArrayList<AskList>();
		
		if(memberId.equals("admin")) {//관리자
			if(opt.equals("askTitle")) {
				list = new AskDao().getListAT(conn, keyword, start, end);
			}else if(opt.equals("askContent")) {
				list = new AskDao().getListAC(conn, keyword, start, end);
			}else {
				list = new AskDao().getListRC(conn, keyword, start, end);
			}
			
		}else {//일반 회원
			if(opt.equals("askTitle")) {
				list = new AskDao().getListAT(conn, memberId, keyword, start, end);
			}else if(opt.equals("askContent")) {
				list = new AskDao().getListAC(conn, memberId, keyword, start, end);
			}else {
				list = new AskDao().getListRC(conn, memberId, keyword, start, end);
			}
		}
		//페이지 네비 작성 시작
		String pageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(memberId.equals("admin")) {
			if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
				pageNavi += "<span class='sideNavi'><a href='/askAdmin?reqPage="+(pageNo-pageNaviSize)+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
			}
			for(int i=0; i<pageNaviSize;i++) {
				if(reqPage == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
					pageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
				}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
					pageNavi += "<span class='numNavi'><a href='/askAdmin?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+"'>"+pageNo+"</a></span>";
				}
				pageNo++;
				if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
					break;/*페이지 네비 생성 종료*/
				}
			}
			if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
				pageNavi += "<span class='sideNavi'><a href='/askAdmin?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+"'>다음</a></span>";//다음 네비 페이지로 이동.
			}
		}else {
			if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
				pageNavi += "<span class='sideNavi'><a href='/askAdmin?reqPage="+(pageNo-pageNaviSize)+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
			}
			for(int i=0; i<pageNaviSize;i++) {
				if(reqPage == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
					pageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
				}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
					pageNavi += "<span class='numNavi'><a href='/askAdmin?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+"'>"+pageNo+"</a></span>";
				}
				pageNo++;
				if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
					break;/*페이지 네비 생성 종료*/
				}
			}
			if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
				pageNavi += "<span class='sideNavi'><a href='/askFrm?reqPage="+pageNo+"&memberId="+memberId+"&opt="+opt+"&keyword="+keyword+"&num="+"'>다음</a></span>";//다음 네비 페이지로 이동.
			}
			
		}
		AskPageData apd = new AskPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return apd;
	}

}
