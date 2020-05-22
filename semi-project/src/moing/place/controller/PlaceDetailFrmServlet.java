package moing.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Meeting;
import member.model.service.MemberService;
import member.model.vo.Member;
import moing.bookinginfo.service.bookingInfoService;
import moing.bookinginfo.vo.BookingInfo;
//import moing.meeting.service.MeetingService;
//import moing.meeting.vo.Meeting;
//import moing.member.service.MemberService;
import moing.place.service.placeService;
//import moing.member.vo.Member;
import moing.place.vo.Place;

/**
 * Servlet implementation class PlaceDetailFrmServlet
 */
@WebServlet(name = "PlaceDetailFrm", urlPatterns = { "/placeDetailFrm" })
public class PlaceDetailFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceDetailFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		request.setCharacterEncoding("utf-8");
		//세션에 있는 아이디 값을 가져와서 모임 아이디와 비교후 맞을시에 모임번호를 전부 가져옴
		String memberId = request.getParameter("memberId"); //현재 로그인 되어있는 회원 아이디
		
		//세션과 동일한 아이디의 모임번호들을 리스트에 받아옴.
		//placeNo과 동일한 place 정보를 조회하여 place 에 저장
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		Place p = new placeService().placeDetail(placeNo);
		
		ArrayList<Meeting> list = new MeetingDetailService().selectOneMeeting(memberId); //로그인되어있는 사람의 모임글을 전부
		
		ArrayList<BookingInfo> booking = new bookingInfoService().bookingInfo(placeNo); //dsadsadsadasdas
		
//		String memberId = request.getParameter("memberId"); ㄴ
		Member m = new MemberService().selectOneMember(memberId); //세션에 있는 회원 정보(예약자)
//		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
//		Meeting mt = new MeetingService().selectOneMeeting(meetingNo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/place/placeDetail.jsp");
		request.setAttribute("p", p);
		request.setAttribute("b", booking);
		request.setAttribute("m", m);
//		request.setAttribute("mt", mt);
		request.setAttribute("list", list);
		System.out.println("test1");
		rd.forward(request, response);
		System.out.println("test2");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
