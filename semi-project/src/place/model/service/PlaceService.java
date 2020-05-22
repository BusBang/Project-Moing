package place.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import common.JDBCTemplate;
import place.model.dao.PlaceDao;
import place.model.vo.Country;
import place.model.vo.Place;
import place.model.vo.PlaceKind;
import place.model.vo.PlaceKindAndCountry;
import place.model.vo.PlacePageData;

public class PlaceService {

	//장소글 등록 시 db에 저장되어 있는 지역테이블의 정보와 장소형식 테이블의 정보가 필요하기 때문에 구해오는 작업
	public PlaceKindAndCountry selectCountryAndPlaceKind() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Country> cList = new PlaceDao().selectCountry(conn);//지역 정보 전체를 불러옴
		ArrayList<PlaceKind> pkList = new PlaceDao().selectPlaceKind(conn);//장소형식 정보 전체를 불러옴
		
		PlaceKindAndCountry pkAc = new PlaceKindAndCountry(); //두개의 정보를 담을 객체 생성
		pkAc.setPkList(pkList);
		pkAc.setcList(cList);
		
		JDBCTemplate.close(conn);
		
		return pkAc;
	}

	//장소글 등록 시 2차지역명에 대한 no(외래키)로 등록하기 위해 Name에 대한 No를 구해오는 작업
	public int selectCountryNo(String countryName) {
		Connection conn = JDBCTemplate.getConnection();
		int countryNo = new PlaceDao().selectCountryNo(conn, countryName);
		JDBCTemplate.close(conn);
		return countryNo;
	}

	//장소글을 등록하는 작업
	public int insertPlace(Place p) { 
		Connection conn = JDBCTemplate.getConnection();
		int result =  new PlaceDao().insertPlace(conn, p);		
		
		if(result>0) {			
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//장소글 등록 시 1차 지역명을 선택하면 2차지역명이 필요하기 때문에 값을 구해오는 작업
	public ArrayList<String> selectCountryNames(String countryName) { 
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> countryNames = new PlaceDao().selectCountryNames(conn, countryName);
		JDBCTemplate.close(conn);
		return countryNames;
	}

	//처음 장소찾기 눌렀을 시 장소게시글 페이징 및 게시글 불러오는 작업
	public PlacePageData selectListPlace(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();

		int totalCount = new PlaceDao().totalCount(conn); //총 게시물의 갯수를 구해옴(총 페이지 수를 구하기 위함)
		int numPerPage = 12; //한 페이지당 게시물의 수!
		int totalPage = 0; //총 페이지 수
		
		//총 페이지수를 연산하는 작업
		if(totalCount%numPerPage == 0) { //게시물 수가 9개는 1페이지, 18개는 2페이지, 27개는 3페이지...
			totalPage = totalCount/numPerPage;
		}else { //게시물 수가 10개는 2페이지, 23개는 3페이지 , 35개는 4페이지.... 
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 게시물의 페이지의 시작번호와 끝번호를 구하는 작업
		int start = (reqPage-1)*numPerPage+1; //해당 페이지의 시작번호
		int end = reqPage*numPerPage; //해당 페이지의 마지막 번호
		
		//해당 페이지의 게시물 조회해온 리스트 ( 처음엔 1번 ~ 9번 게시물을 가져옴)
		ArrayList<Place> list = new PlaceDao().selectListPlace(conn, start, end);
		
		//페이지 네비게이션 작성 시작(1,2,3,4,5)
		String pageNavi = "";
		int pageNaviSize = 5; //한번에 표시하는 페이지 수 (1,2,3,4,5) 즉, 길이!
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1; //페이지 네비게이션이 ()
		
		if(pageNo != 1) { //2부터 이전버튼이 생기도록
			pageNavi += "<a class='btn' href='/placeListMain?reqPage=" + (pageNo-pageNaviSize) + "'>이전</a>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) { //현재 페이지 버튼은 누르지 않음!
				pageNavi += "<span class='selectPage'>" + pageNo + "</span>";
			}else { //첫번째 pageNo는 내가 누른 번호를 요청한 페이지 번호로 넘겨주기 위함이고, 두번째는 그냥 출력용!
				pageNavi += "<a class='btn' href='/placeListMain?reqPage=" + pageNo + "'>" + pageNo + "</a>";
			}
			pageNo++;
			if(pageNo>totalPage) { //전체 페이지 수보다 크면 break;
				break;
			}
		}
		
		if(pageNo <= totalPage) {// 현재 페이지번호가 총 페이지수와 같거나 작을때 다음 버튼이 생기도록
			pageNavi += "<a class='btn' href='/placeListMain?reqPage=" + pageNo + "'>다음</a>";
		}
		
		PlacePageData pd = new PlacePageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return pd;
	}

//	//검색 필터에 맞는 장소제공글의 리스트(페이징 X)
//	public ArrayList<Place> searchPlace(String keyword, String check, String sel2, String placeKind, String useDate) {
//		Connection conn = JDBCTemplate.getConnection();
//		ArrayList<Place> list = new PlaceDao().searchPlace(conn, keyword, check, sel2, placeKind, useDate);
//		
//		JDBCTemplate.close(conn);
//		return list;
//		
//	}

	//검색 필터 적용 후 검색 조건에 맞는 게시물 수와  게시물 받아오기
	public PlacePageData selectSearchPlaceList(int reqPage, String keyword, String check, String sel2, String placeKind,
			String useDate) {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new PlaceDao().searchTotalCount(conn, keyword, check, sel2, placeKind, useDate);
		int numPerPage = 12; //한 페이지당 게시물의 수!
		int totalPage = 0; //총 페이지 수
		
		//총 페이지수를 연산하는 작업
		if(totalCount%numPerPage == 0) { //게시물 수가 9개는 1페이지, 18개는 2페이지, 27개는 3페이지...
			totalPage = totalCount/numPerPage;
		}else { //게시물 수가 10개는 2페이지, 23개는 3페이지 , 35개는 4페이지.... 
			totalPage = totalCount/numPerPage+1;
		}
		
		//조회해 올 게시물의 페이지의 시작번호와 끝번호를 구하는 작업
		int start = (reqPage-1)*numPerPage+1; //해당 페이지의 시작번호
		int end = reqPage*numPerPage; //해당 페이지의 마지막 번호
		
		//해당 페이지의 게시물 조회해온 리스트 ( 처음엔 1번 ~ 9번 게시물을 가져옴)
		ArrayList<Place> list = new PlaceDao().searchSelectListPlace(conn, start, end, keyword, check, sel2, placeKind, useDate);
		
		//페이지 네비게이션 작성 시작(1,2,3,4,5)
		String pageNavi = "";
		int pageNaviSize = 5; //한번에 표시하는 페이지 수 (1,2,3,4,5) 즉, 길이!
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1; //페이지 네비게이션이 ()
		
		if(pageNo != 1) { //2부터 이전버튼이 생기도록
			pageNavi += "<a class='btn' href='/searchPlace?reqPage=" + (pageNo-pageNaviSize) + "&keyword="+ keyword + "&check_option2="+check+"&countryName="+sel2+"&placeKindName="+placeKind+"&placeUseDate="+useDate+"'>이전</a>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) { //현재 페이지 버튼은 누르지 않음!
				pageNavi += "<span class='selectPage'>" + pageNo + "</span>";
			}else { //첫번째 pageNo는 내가 누른 번호를 요청한 페이지 번호로 넘겨주기 위함이고, 두번째는 그냥 출력용!
				pageNavi += "<a class='btn' href='/searchPlace?reqPage=" + pageNo + "&keyword="+ keyword + "&check_option2="+check+"&countryName="+sel2+"&placeKindName="+placeKind+"&placeUseDate="+useDate+"'>" + pageNo + "</a>";
			}
			pageNo++;
			if(pageNo>totalPage) { //전체 페이지 수보다 크면 break;
				break;
			}
		}
		
		if(pageNo <= totalPage) {// 현재 페이지번호가 총 페이지수와 같거나 작을때 다음 버튼이 생기도록
			pageNavi += "<a class='btn' href='/searchPlace?reqPage=" + pageNo + "&keyword="+ keyword + "&check_option2="+check+"&countryName="+sel2+"&placeKindName="+placeKind+"&placeUseDate="+useDate+"'>다음</a>";
		}
		
		PlacePageData pd = new PlacePageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return pd;
	}

	public Place selectOnePlace(int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Place p  = new PlaceDao().selectOnePlace(conn, placeNo);
		JDBCTemplate.close(conn);
		return p;
	}

	public int updatePlace(Place p) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PlaceDao().updatePlace(conn, p);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deletePlace(int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PlaceDao().deletePlace(conn, placeNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	
}
