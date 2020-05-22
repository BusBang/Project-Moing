package mypage.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.place.model.service.PlaceService;
import mypage.place.model.vo.BookingAjaxData;
import mypage.place.model.vo.BookingInfoPageData;

/**
 * Servlet implementation class BookingList0Servlet
 */
@WebServlet(name = "BookingList0", urlPatterns = { "/bookingList0" })
public class BookingList0Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingList0Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		String memberId = request.getParameter("memberId");//장소 제공자의 아이디(로그인 되어있는 아이디)
		System.out.println("서블릿 장소버노:"+placeNo);
		
		int reqPagePlace0= Integer.parseInt(request.getParameter("reqPagePlace0"));//장소 이용자 리스트 요청 페이지
		int reqPagePlace0Booking = Integer.parseInt(request.getParameter("reqPagePlace0Booking"));//장소 이용자 예약리스트 요청페이지
		
		BookingInfoPageData bipd0 = new PlaceService().booking0List(memberId, reqPagePlace0, reqPagePlace0Booking, placeNo);//장소 이용자 예약리스트페이지 데이터
		System.out.println("서블릿 bipd크기"+bipd0.getList().size());
		
		
		ArrayList<BookingAjaxData> arr = new ArrayList<BookingAjaxData>();
		System.out.println("작동");

		for(int i=0; i<bipd0.getList().size(); i++) {
			BookingAjaxData bad = new BookingAjaxData();
			
			bad.setBookingCancel(bipd0.getList().get(i).getBi().getBookingCancel());
			
			bad.setBookingDate(bipd0.getList().get(i).getBi().getBookingDate());
			
			bad.setBookingNo(bipd0.getList().get(i).getBi().getBookingNo());
			
			bad.setMeetingName(bipd0.getList().get(i).getBi().getMeetingName());
			
			bad.setMeetingNo(bipd0.getList().get(i).getBi().getMeetingNo());
			bad.setPageNavi(bipd0.getPageNavi());
			bad.setPlaceNo(bipd0.getList().get(i).getBi().getPlaceNo());
			bad.setRnum(bipd0.getList().get(i).getRnum());
			bad.setToday(bipd0.getList().get(i).getBi().getToday());
			bad.setVisitDate(bipd0.getList().get(i).getBi().getVisitDate());
			bad.setSit(bipd0.getList().get(i).getBi().getSit());
			arr.add(bad);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(arr, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
