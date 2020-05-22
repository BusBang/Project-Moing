package moim.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import meetingDetail.model.dao.MeetingDetailDao;
import meetingDetail.model.vo.MeetingPerson;
import meetingDetail.model.vo.Review;
import member.model.vo.Member;
import moim.model.dao.MoimDao;
import moim.model.vo.Meeting;
import moim.model.vo.MoimPageData;

public class MoimService {

	public int insertMoim(Meeting meet) {
		Connection conn = JDBCTemplate.getConnection();
		int result2 = 0;
		int result = new MoimDao().insertMoim(conn, meet);
		if(result >0) {
			JDBCTemplate.commit(conn);
			System.out.println("result0:"+result);
			result2 = new MeetingDetailDao().insertMeetingPersonInsertMoim(conn, meet);
			if(result2 >0) {
				System.out.println("result1:"+result2);
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

	public ArrayList<Meeting> selectAllMeeting() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList <Meeting> meetingList = new MoimDao().selectAllMeeting(conn);
		JDBCTemplate.close(conn);
		return meetingList;
	}

	public MoimPageData selectMeetingList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeeting(conn, start, end);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//카테고리 선택, 지역선택
	public MoimPageData selectMeetingList(int reqPage, int categoryNo, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, categoryNo, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeeting(conn, start, end, categoryNo, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	
	//카테고리만 선택
	public MoimPageData selectMeetingListCategory(int reqPage, int categoryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCategory(conn, categoryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCategory(conn, start, end, categoryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//키워드만 조회
	public MoimPageData selectMeetingList(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeeting(conn, start, end, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//지역코드만 조회
	public MoimPageData selectMeetingListCountry(int reqPage, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCountry(conn, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCountry(conn, start, end, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//인기순정렬1
	public MoimPageData selectMeetingListLike(int reqPage, int categoryNo, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, categoryNo, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingLike(conn, start, end, categoryNo, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}

	public MoimPageData selectMeetingListCategoryLike(int reqPage, int categoryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCategory(conn, categoryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCategoryLike(conn, start, end, categoryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}

	public MoimPageData selectMeetingListCountryLike(int reqPage, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCountry(conn, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCountryLike(conn, start, end, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}

	public MoimPageData selectMeetingListNoNumberLike(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingNoNumberLike(conn, start, end, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//이름순 정렬
	public MoimPageData selectMeetingListName(int reqPage, int categoryNo, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, categoryNo, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingName(conn, start, end, categoryNo, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//이름순정렬 카테고리
	public MoimPageData selectMeetingListCategoryName(int reqPage, int categoryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCategory(conn, categoryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCategoryName(conn, start, end, categoryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}

	public MoimPageData selectMeetingListCountryName(int reqPage, int countryNo, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCountCountry(conn, countryNo, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingListCountryName(conn, start, end, countryNo, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//이름검색 4번째
	public MoimPageData selectMeetingListName(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지 당 게시물 수
		int numPerPage = 12;
		int totalCount = new MoimDao().totalCount(conn, keyword);
		//페이지 갯수 만들기.
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		//해당페이지 게시물들 조회
		ArrayList<Meeting> list = new MoimDao().selectMeetingNoNumberName(conn, start, end, keyword);
		
		//페이지 내비게이션 작성
		String pageNavi = "";
		//내비 길이
		int pageNaviSize = 3;
		//페이지 넘버
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo-pageNaviSize)+"'>&lt;</a></div>";
		}
		//중간버튼
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<div style='background-color : #204000; color:#f0E0C0; font-weight : bold; '><span>"+pageNo+"</span></div>";
			} else {
				pageNavi += "<div><a href='moimMain?reqPage="+pageNo+"'>"+pageNo+"</a></div>";
			}
			pageNo++;
			//페이지 수 > 전체페이지 수, break
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<div><a href='/moimMain?reqPage="+(pageNo)+"'>&gt;</a></div>";
		}
		MoimPageData md = new MoimPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return md;
	}
	//모임수정 (파일 있을 때)
	public int MoimModify(Meeting meet) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().MoimModify(conn, meet);
		if(result > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
		JDBCTemplate.close(conn);
		return result;
	}

	public Meeting SelectOneMeeting(int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		Meeting meeting = new MoimDao().selectOneMeeting(conn, meetingNo);
		JDBCTemplate.close(conn);
		return meeting;
	}

	public ArrayList<MeetingPerson> SelectAllMeetingPerson(int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MeetingPerson> meetingPersonList = new MoimDao().selectAllMeetingPerson(conn, meetingNo);
		JDBCTemplate.close(conn);
		return meetingPersonList;
	}
	//모임재개최
	public int MoimReopen(Meeting meet) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().MoimReopen(conn, meet);
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Review> SelectAllReview(int meetingNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> reviewList = new MoimDao().selectAllReview(conn, meetingNo);
		JDBCTemplate.close(conn);
		return reviewList;
	}
	
	public int MoimReopenSeqSelect() {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().MoimReopenSeqSelect(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertMeetingPerson(int resultSeq, ArrayList<MeetingPerson> meetingPersonList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().insertMeetingPerson(conn, resultSeq, meetingPersonList);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertReview(int resultSeq, ArrayList<Review> reviewList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().insertReview(conn, resultSeq, reviewList);
		JDBCTemplate.close(conn);
		return result;
	}
	//모임수정 (파일 널 일때)
	public int MoimModifyFileNull(Meeting meet) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoimDao().MoimModifyFileNull(conn, meet);
		if(result > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
		JDBCTemplate.close(conn);
		return result;
	}




}
