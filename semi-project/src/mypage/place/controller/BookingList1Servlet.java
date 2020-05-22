package mypage.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class BookingList1Servlet
 */
@WebServlet(name = "BookingList1", urlPatterns = { "/bookingList1" })
public class BookingList1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingList1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		String memberId = request.getParameter("memberId");
		System.out.println("서블릿 장소버노:"+placeNo);
		
		int reqPagePlace0= Integer.parseInt(request.getParameter("reqPagePlace1"));//장소 이용자 리스트 요청 페이지
		int reqPagePlace0Booking = Integer.parseInt(request.getParameter("reqPagePlace1Booking"));//장소 이용자 예약리스트 요청페이지
		
		BookingInfoPageData bipd1 = new PlaceService().booking1List(memberId, reqPagePlace0, reqPagePlace0Booking, placeNo);//장소 이용자 예약리스트페이지 데이터
		System.out.println("서블릿 bipd크기"+bipd1.getList().size());
		
		
		ArrayList<BookingAjaxData> arr = new ArrayList<BookingAjaxData>();
		System.out.println("작동");

		for(int i=0; i<bipd1.getList().size(); i++) {
			BookingAjaxData bad = new BookingAjaxData();
			
			bad.setBookingCancel(bipd1.getList().get(i).getBi().getBookingCancel());
			
			bad.setBookingDate(bipd1.getList().get(i).getBi().getBookingDate());
			
			bad.setBookingNo(bipd1.getList().get(i).getBi().getBookingNo());
			
			bad.setMeetingName(bipd1.getList().get(i).getBi().getMeetingName());
			
			bad.setMeetingNo(bipd1.getList().get(i).getBi().getMeetingNo());
			bad.setPageNavi(bipd1.getPageNavi());
			bad.setPlaceNo(bipd1.getList().get(i).getBi().getPlaceNo());
			bad.setRnum(bipd1.getList().get(i).getRnum());
			bad.setToday(bipd1.getList().get(i).getBi().getToday());
			bad.setVisitDate(bipd1.getList().get(i).getBi().getVisitDate());
			bad.setSit(bipd1.getList().get(i).getBi().getSit());
			arr.add(bad);
		}
		System.out.println(arr.size());
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
