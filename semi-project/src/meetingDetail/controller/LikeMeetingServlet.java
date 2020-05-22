package meetingDetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.vo.LoginMember;
import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.LikeList;
import meetingDetail.model.vo.Meeting;

/**
 * Servlet implementation class LikeMeetingServlet
 */
@WebServlet(name = "LikeMeeting", urlPatterns = { "/likeMeeting" })
public class LikeMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeMeetingServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LoginMember login = (LoginMember)session.getAttribute("member");
		String memberId = login.getMemberId();
		
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		LikeList like = new LikeList();
		Meeting m = new Meeting();
		m.setMeetingNo(meetingNo);
		m.setLikeCount(Integer.parseInt(request.getParameter("like")));
		m = new MeetingDetailService().likeMeeting(m, memberId);
		int num = m.getMeetingNo()-1;
		like.setMeet(m);
		like.setMyId(memberId);
		request.setAttribute("like", like);
		request.setAttribute("num", num);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetingDetail/meetingDetail.jsp");
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
