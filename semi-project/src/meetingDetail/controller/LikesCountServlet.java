package meetingDetail.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.vo.LoginMember;
import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Meeting;

/**
 * Servlet implementation class LikesCountServlet
 */
@WebServlet(name = "LikesCount", urlPatterns = { "/likesCount" })
public class LikesCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LoginMember login = (LoginMember)session.getAttribute("member");
		String memberId = login.getMemberId();
		
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		/*
		Meeting m = new Meeting();
		m.setMeetingNo(meetingNo);
		*/
		int count = new MeetingDetailService().likes(meetingNo, memberId);
		
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(count);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
