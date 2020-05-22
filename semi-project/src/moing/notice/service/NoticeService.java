package moing.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import moing.notice.vo.NoticePageData;
import moing.notice.dao.NoticeDao;
import moing.notice.vo.Notice;

public class NoticeService {


	public int noticeInsert(Notice n) {
		//글추가용
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().noticeInsert(conn,n);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice selectOneNotice(int noticeNo) {
		//noticeMainFrm에서 사용
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().seleteoneNotice(conn,noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public int noticeModify(Notice n) {
		//수정용
		Connection conn =JDBCTemplate.getConnection();
		int result = new NoticeDao().noticeModify(n,conn);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn); 
		return result;
	}

	public int noticeDelete(int noticeNo) {
		//게시글 삭제용
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().noticeDelete(conn,noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice noticeView(int noticeNo) {
		//게시글 상세보기용
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().noticeView(conn,noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}



	public NoticePageData selectList(int reqPage) {
		//notice main에서 사용하는 페이징 service
		Connection conn =JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//총 게시물 수를 구해오는 dao 호출
		int totalCount = new NoticeDao().totalCount(conn);
		//총 페이지 수 를 연산.
		int totalPage =0;
		if(totalCount%numPerPage==0) {
			totalPage= totalCount/numPerPage;
		}else {
			totalPage= totalCount/numPerPage+1;
		}
		//조회해올 게시물 시작번호와 끝번호 연산.
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Notice>list = new NoticeDao().selectList(conn,start,end);
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = 5;
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1; 
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/noticeMain?reqPage="+(pageNo-pageNaviSize)+"'>이전</a>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/noticeMain?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/noticeMain?reqPage="+pageNo+"'>다음</a>";
		}
		NoticePageData pd =new NoticePageData(list,pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}
	//서치 서블릿
	public NoticePageData searchPageNotice(int reqPage, String type, String keyword) {
		Connection conn =JDBCTemplate.getConnection();
		int numPerPage = 10;//한 페이지당 게시물 수
		//총 게시물 수를 구해오는 dao 호출
		int totalCount = new NoticeDao().totalCount2(keyword, type, conn);
		System.out.println(totalCount);
		
		//총 페이지 수 를 연산.
		int totalPage =0;
		if(totalCount%numPerPage==0) {
			totalPage= totalCount/numPerPage;
		}else {
			totalPage= totalCount/numPerPage+1;
		}
		//조회해올 게시물 시작번호와 끝번호 연산.
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Notice>list = new ArrayList<Notice>();
		if(type.equals("notice_Title")) {
			list = new NoticeDao().searchNoticeTitle(start,end,reqPage,conn,keyword);
		}else {
			list = new NoticeDao().searchNoticeContent(start,end,reqPage,conn,keyword);
		}
		//페이지 네비게이션 작성 시작
		String pageNavi = "";
		//페이지 네비게이션 길이
		int pageNaviSize = 5;
		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1; 
		if(pageNo != 1) {
			if(type.equals("notice_Title")) {
			pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+(pageNo-pageNaviSize)+"&type=notice_Title&keyword="+keyword+"'>이전</a>";
			}else {
			pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+(pageNo-pageNaviSize)+"&type=notice_Content&keyword="+keyword+"'>이전</a>";
			}
			}
		for(int i=0;i<pageNaviSize;i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				if(type.equals("notice_Title")) {
				pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+pageNo+"&type=notice_Title&keyword="+keyword+"'>"+pageNo+"</a>";
				}else {
				pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+pageNo+"&type=notice_Content&keyword="+keyword+"'>"+pageNo+"</a>";
				}
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			if(type.equals("notice_Title")) {
				pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+pageNo+"&type=notice_Title&keyword="+keyword+"'>다음</a>";
				}else {
				pageNavi += "<a class='btn' href='/noticeSearch?reqPage="+pageNo+"&type=notice_Title&keyword="+keyword+"'>다음</a>";
				}
		}
		NoticePageData pd =new NoticePageData(list,pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}

}
