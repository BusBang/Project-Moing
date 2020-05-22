package mypage.place.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.place.model.dao.PlaceDao;
import mypage.place.model.vo.BookingInfoList;
import mypage.place.model.vo.BookingInfoPageData;
import mypage.place.model.vo.PlaceInfoList;
import mypage.place.model.vo.PlaceInfoPageData;

public class PlaceService {

	public PlaceInfoPageData place0List(String memberId, int reqPagePlace0) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		//장소 이용자의 전체 장소 예약 갯수
		int totalPlace0Count = new PlaceDao().totalPlace0Count(conn, memberId);
		
		int totalPage=0;//전체 페이지
		if(totalPlace0Count%numPerPage==0) {//전체 장소 예약 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlace0Count/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlace0Count/numPerPage+1;
		}
		int start = (reqPagePlace0-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlace0*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 이용자의 장소 예약 리스트를 가져옴
		ArrayList<PlaceInfoList> list = new PlaceDao().selectPlace0List(conn, memberId, start, end);
		
		
		//페이지 네비 작성 시작
		String pageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPagePlace0-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			pageNavi += "<span class='sideNavi'><a href='/myPlaceList?num=0&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPagePlace0 == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				pageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				pageNavi += "<span class='numNavi'><a href='/myPlaceList?num=0&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			pageNavi += "<span class='sideNavi'><a href='/myPlaceList?num=0&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		PlaceInfoPageData pipd = new PlaceInfoPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return pipd;
	}

	public PlaceInfoPageData place1List(String memberId, int reqPagePlace1) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		//장소 제공자의 전체 장소 게시물 갯수
		int totalPlace0Count = new PlaceDao().totalPlace1Count(conn, memberId);
		int totalPage=0;//전체 페이지
		if(totalPlace0Count%numPerPage==0) {//전체 장소 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlace0Count/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlace0Count/numPerPage+1;
		}
		int start = (reqPagePlace1-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlace1*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 제공자의 장소 리스트를 가져옴
		ArrayList<PlaceInfoList> list = new PlaceDao().selectPlace1List(conn, memberId, start, end);
		
		//페이지 네비 작성 시작
		String PageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPagePlace1-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			PageNavi += "<span class='sideNavi'><a href='/myPlaceList?num=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace0=1&reqPagePlace1="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPagePlace1 == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				PageNavi += "<span class='numNavi'><a href='/myPlaceList?num=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace0=1&reqPagePlace1="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			PageNavi += "<span class='sideNavi'><a href='/myPlaceList?num=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&reqPagePlace0=1&reqPagePlace1="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		PlaceInfoPageData pipd = new PlaceInfoPageData(list, PageNavi);
		
		JDBCTemplate.close(conn);
		return pipd;
	}

	public BookingInfoPageData booking0List(String memberId, int reqPagePlace0, int reqPagePlace0Booking,int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 이용자의 장소 예약 리스트 글 수 
		int numPerPage = 10;
		//해당 장소의 이용자의 전체 장소 예약 갯수
		System.out.println("서비스 내 멤아: "+ memberId);
		int totalPlace0BookingCount = new PlaceDao().totalPlace0BookingCount(conn, memberId, placeNo);
		System.out.println("서비스 내 전체 예약 갯수"+totalPlace0BookingCount);
		
		int totalPage=0;//전체 페이지
		if(totalPlace0BookingCount%numPerPage==0) {//전체 장소 예약 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlace0BookingCount/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlace0BookingCount/numPerPage+1;
		}
		int start = (reqPagePlace0Booking-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlace0Booking*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 이용자의 해당 장소 예약 리스트를 가져옴
		ArrayList<BookingInfoList> list = new PlaceDao().selectPlace0BookingList(conn, memberId, start, end, placeNo);
		System.out.println("서비스 리스트크기"+list.size());
		
		//페이지 네비 작성 시작
		String pageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPagePlace0Booking-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			pageNavi += "<span class='sideNavi'><a href='/bookingList?num=0&reqPagePlace0="+reqPagePlace0+"&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0Booking="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPagePlace0Booking == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				pageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				pageNavi += "<span class='numNavi'><a href='/bookingList?num=0&reqPagePlace0="+reqPagePlace0+"&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0Booking="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			pageNavi += "<span class='sideNavi'><a href='/bookingList?num=0&reqPagePlace0="+reqPagePlace0+"&reqPagePlace1Booking=1&reqPagePlace1=1&reqPagePlace0Booking="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		BookingInfoPageData bipd = new BookingInfoPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return bipd;
	}

	public BookingInfoPageData booking1List(String memberId,  int reqPagePlace1, int reqPagePlace1Booking, int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		//해당 장소의 전체 예약 갯수
		int totalPlace1BookingCount = new PlaceDao().totalPlace1BookingCount(conn, placeNo);
		System.out.println("예약1서비스"+totalPlace1BookingCount);
		int totalPage=0;//전체 페이지
		if(totalPlace1BookingCount%numPerPage==0) {//전체 장소 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlace1BookingCount/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlace1BookingCount/numPerPage+1;
		}
		int start = (reqPagePlace1Booking-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlace1Booking*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 제공자의 장소 리스트를 가져옴
		ArrayList<BookingInfoList> list = new PlaceDao().selectPlace1BookingList(conn, start, end, placeNo);
		System.out.println("ㅇㅇ1"+list.size());
		
		//페이지 네비 작성 시작
		String PageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPagePlace1Booking-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			PageNavi += "<span class='sideNavi'><a href='/bookingList?num=1&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace1+"&reqPagePlace0=1&reqPagePlace1Booking="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPagePlace1Booking == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				PageNavi += "<span class='numNavi'><a href='/bookingList?num=1&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace1+"&reqPagePlace0=1&reqPagePlace1Booking=="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			PageNavi += "<span class='sideNavi'><a href='/bookingList?num=1&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace1+"&reqPagePlace0=1&reqPagePlace1Booking=="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		BookingInfoPageData bipd = new BookingInfoPageData(list, PageNavi);
		
		JDBCTemplate.close(conn);
		return bipd;
	}

	public BookingInfoPageData bookingList(String memberId, int reqPagePlace, int reqPagePlaceBooking, int placeNo,
			int num) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		int totalPlaceBookingCount=-1;
		//해당 장소의 전체 예약 갯수
		
		if(num==0) {
			//장소이용자
			totalPlaceBookingCount = new PlaceDao().totalPlace0BookingCount(conn,memberId, placeNo);
		}else {
			//장소제공자
			totalPlaceBookingCount = new PlaceDao().totalPlace1BookingCount(conn, placeNo);
		}
		
		
		int totalPage=0;//전체 페이지
		if(totalPlaceBookingCount%numPerPage==0) {//전체 장소 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlaceBookingCount/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlaceBookingCount/numPerPage+1;
		}
		int start = (reqPagePlaceBooking-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlaceBooking*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 제공자의 장소 리스트를 가져옴
		
		ArrayList<BookingInfoList> list = new ArrayList<BookingInfoList>();
		if(num==0) {
			//장소이용자
			list = new PlaceDao().selectPlace0BookingList(conn,memberId, start, end, placeNo);
			
		}else {
			//장소제공자
			list = new PlaceDao().selectPlace1BookingList(conn, start, end, placeNo);
		}
		
		
		
		//페이지 네비 작성 시작
		String PageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		
		int pageNo =((reqPagePlaceBooking-1)/pageNaviSize)*pageNaviSize+1;
		if(num==1) {
			if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList1?num="+num+"&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace+"&reqPagePlace0=1&reqPagePlace1Booking="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
			}
			for(int i=0; i<pageNaviSize;i++) {
				if(reqPagePlaceBooking == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
					PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
				}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
					PageNavi += "<span class='numNavi'><a href='/bookingList1?num="+num+"&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace+"&reqPagePlace0=1&reqPagePlace1Booking=="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
				}
				pageNo++;
				if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
					break;/*페이지 네비 생성 종료*/
				}
			}
			if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList1?num="+num+"&reqPagePlace0Booking=1&reqPagePlace1="+reqPagePlace+"&reqPagePlace0=1&reqPagePlace1Booking=="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
			}
			
		}else {
			if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList1?num="+num+"&reqPagePlace1Booking=1&reqPagePlace0="+reqPagePlace+"&reqPagePlace1=1&reqPagePlace0Booking="+(pageNo-pageNaviSize)+"&memberId="+memberId+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
			}
			for(int i=0; i<pageNaviSize;i++) {
				if(reqPagePlaceBooking == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
					PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
				}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
					PageNavi += "<span class='numNavi'><a href='/bookingList1?num="+num+"&reqPagePlace1Booking=1&reqPagePlace0="+reqPagePlace+"&reqPagePlace1=1&reqPagePlace0Booking=="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></span>";
				}
				pageNo++;
				if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
					break;/*페이지 네비 생성 종료*/
				}
			}
			if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList1?num="+num+"&reqPagePlace1Booking=1&reqPagePlace0="+reqPagePlace+"&reqPagePlace1=1&reqPagePlace0Booking=="+pageNo+"&memberId="+memberId+"'>다음</a></span>";//다음 네비 페이지로 이동.
			}
		}
		
		BookingInfoPageData bipd = new BookingInfoPageData(list, PageNavi);
		
		JDBCTemplate.close(conn);
		return bipd;
	}

	public PlaceInfoPageData adminPlaceList(int reqPagePlace) {
		Connection conn = JDBCTemplate.getConnection();
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		//장소 제공자의 전체 장소 게시물 갯수
		int totalPlaceCount = new PlaceDao().totaladminPlaceCount(conn);
		System.out.println(totalPlaceCount);
		int totalPage=0;//전체 페이지
		if(totalPlaceCount%numPerPage==0) {//전체 장소 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlaceCount/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlaceCount/numPerPage+1;
		}
		int start = (reqPagePlace-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlace*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 제공자의 장소 리스트를 가져옴
		ArrayList<PlaceInfoList> list = new PlaceDao().selectadminPlaceList(conn, start, end);
		System.out.println("ss"+list.size());
		//페이지 네비 작성 시작
		String PageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		int pageNo =((reqPagePlace-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
			PageNavi += "<span class='sideNavi'><a href='/adminPlaceList?reqPagePlace="+(pageNo-pageNaviSize)+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
		}
		for(int i=0; i<pageNaviSize;i++) {
			if(reqPagePlace == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
				PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
			}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
				PageNavi += "<span class='numNavi'><a href='/adminPlaceList?reqPagePlace="+pageNo+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
			if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
				break;/*페이지 네비 생성 종료*/
			}
		}
		if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
			PageNavi += "<span class='sideNavi'><a href='/adminPlaceList?reqPagePlace="+pageNo+"'>다음</a></span>";//다음 네비 페이지로 이동.
		}
		PlaceInfoPageData pipd = new PlaceInfoPageData(list, PageNavi);
		
		JDBCTemplate.close(conn);
		return pipd;
	}

	public BookingInfoPageData bookingAdminList(int reqPagePlace, int reqPagePlaceBooking, int placeNo) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println("ㅅㅄ"+placeNo);
		//한 페이지 당 장소 정보 리스트 글 수 
		int numPerPage = 10;
		int totalPlaceBookingCount=-1;
		//해당 장소의 전체 예약 갯수
		
		totalPlaceBookingCount = new PlaceDao().totalPlace1BookingCount(conn, placeNo);
		System.out.println("tt"+totalPlaceBookingCount);
		int totalPage=0;//전체 페이지
		
		if(totalPlaceBookingCount%numPerPage==0) {//전체 장소 갯수가 10의 배수일 때 -> 페이지 안에 리스트 10이 꽉 차서 들어감
			totalPage=totalPlaceBookingCount/numPerPage;
		}else {//아닐 때 -> 10보다 작은 수의 잔여 리스트 발생
			totalPage=totalPlaceBookingCount/numPerPage+1;
		}
		int start = (reqPagePlaceBooking-1)*numPerPage+1;//한 페이지의 리스트 시작 번호
		int end = reqPagePlaceBooking*numPerPage;//한 페이지의 리스트 끝 번호
		//한 페이지의 리스트 시작번호부터 리스트 끝 번호까지 장소 제공자의 장소 리스트를 가져옴
		
		ArrayList<BookingInfoList> list = new ArrayList<BookingInfoList>();
		list = new PlaceDao().selectPlace1BookingList(conn, start, end, placeNo);
		System.out.println("service"+list.size());
		
		//페이지 네비 작성 시작
		String PageNavi = "";
		//한개의 페이지 네비 길이
		int pageNaviSize = 5;
		 /*해당 페이지 네비의 시작 번호*/ 
		
		int pageNo =((reqPagePlaceBooking-1)/pageNaviSize)*pageNaviSize+1;
		
			if(pageNo !=1) {/*해당 페이지 네비의 시작번호가 1이 아닐 때 (6이상)*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList?placeNo="+placeNo+"&reqPagePlace="+reqPagePlace+"&reqPagePlaceBooking="+(pageNo-pageNaviSize)+"'> 이전</a></span>";//이전 네비페이지로 가는 버튼 a태그
			}
			for(int i=0; i<pageNaviSize;i++) {
				if(reqPagePlaceBooking == pageNo) {/*요청 페이지가 해당 페이지 네비의 첫번째일때 (시작번호일때: 1, 6, 11...)->클릭X*/
					PageNavi += "<span class='numNavi'><span>"+pageNo+"</span></span>";
				}else {/*요청 페이지가 해당 페이지 네비의 첫번째가 아닐 때 (1, 6, 11...제외)*/
					PageNavi += "<span class='numNavi'><a href='/bookingList?placeNo="+placeNo+"&reqPagePlace="+reqPagePlace+"&reqPagePlaceBooking="+pageNo+"'>"+pageNo+"</a></span>";
				}
				pageNo++;
				if(pageNo>totalPage) {/*페이지 시작 번호가 총 페이지 보다 클 때*/
					break;/*페이지 네비 생성 종료*/
				}
			}
			if(pageNo <= totalPage) {/*페이지 시작 번호가 총 페이지보다 작거나 같을 때*/
				PageNavi += "<span class='sideNavi'><a href='/bookingList?placeNo="+placeNo+"&reqPagePlace="+reqPagePlace+"&reqPagePlace1Booking="+pageNo+"'>다음</a></span>";//다음 네비 페이지로 이동.
			}
			
		BookingInfoPageData bipd = new BookingInfoPageData(list, PageNavi);
		
		JDBCTemplate.close(conn);
		return bipd;
	}

	public int bookingCancel(int bookingNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PlaceDao().bookingCancel(conn, bookingNo);
		if(result>0) {
				JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
