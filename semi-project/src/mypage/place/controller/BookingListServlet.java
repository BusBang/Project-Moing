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
 * Servlet implementation class BookingListServlet
 */
@WebServlet(name = "BookingList", urlPatterns = { "/bookingList" })
public class BookingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int reqPagePlace= Integer.parseInt(request.getParameter("reqPagePlace"));//장소 제공자 리스트 요청페이지
		int reqPagePlaceBooking = Integer.parseInt(request.getParameter("reqPagePlaceBooking"));//장소 제공자 예약리스트 요청 페이지
		
		System.out.println("서블릿"+placeNo);
		System.out.println("ㅅㅂㄹ"+reqPagePlace);
		System.out.println("tqf"+reqPagePlaceBooking);
		
		
		BookingInfoPageData bipd = new PlaceService().bookingAdminList(reqPagePlace, reqPagePlaceBooking, placeNo);//장소 제공자 예약리스트페이지 데이터
		ArrayList<BookingAjaxData> arr = new ArrayList<BookingAjaxData>();
		System.out.println(bipd.getList().size()+"작동"+bipd.getPageNavi());

		for(int i=0; i<bipd.getList().size(); i++) {
			BookingAjaxData bad = new BookingAjaxData();
			
			bad.setBookingCancel(bipd.getList().get(i).getBi().getBookingCancel());
			
			bad.setBookingDate(bipd.getList().get(i).getBi().getBookingDate());
			
			bad.setBookingNo(bipd.getList().get(i).getBi().getBookingNo());
			
			bad.setMeetingName(bipd.getList().get(i).getBi().getMeetingName());
			
			bad.setMeetingNo(bipd.getList().get(i).getBi().getMeetingNo());
			bad.setPageNavi(bipd.getPageNavi());
			bad.setPlaceNo(bipd.getList().get(i).getBi().getPlaceNo());
			bad.setRnum(bipd.getList().get(i).getRnum());
			bad.setToday(bipd.getList().get(i).getBi().getToday());
			bad.setVisitDate(bipd.getList().get(i).getBi().getVisitDate());
			bad.setSit(bipd.getList().get(i).getBi().getSit());
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
