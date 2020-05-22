package moing.place.controller;

import java.io.Console;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import moim.model.vo.Meeting;
//import moing.meeting.vo.Meeting;
import moing.place.service.placeService;
import moing.place.vo.Place;

/**
 * Servlet implementation class PlacePaymentServlet
 */
@WebServlet(name = "PlacePayment", urlPatterns = { "/placePayment" })
public class PlacePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlacePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		//넘어온 값 저장
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo")); //미팅번호		
		//장소placeNo값 을받아서 저장
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));		
		//datepicker 에서 선택된 날짜값을 가져옴.
		java.sql.Date visitDate = java.sql.Date.valueOf(request.getParameter("visitDate"));
		System.out.println(visitDate);
		String memberId = request.getParameter("memberId");
		
		int result = new placeService().payment(placeNo,meetingNo, visitDate);
		
		//int result= new placeService().payment(m, p, visitDate);		
		//int meetingNo =Integer.parseInt(request.getParameter("meetingNo"));
		//int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		//String visitDate = request.getParameter("visitDate");
		//int result = new placeService().payment(placeNo,meetingNo,visitDate);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("loc", "/index.jsp");
			request.setAttribute("msg", "성공");
		}else {
			HttpSession session = request.getSession(false);
			request.setAttribute("loc", "/placeDetailFrm?placeNo="+placeNo+"&memberId=" + memberId);
			request.setAttribute("msg", "실패");
		
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
