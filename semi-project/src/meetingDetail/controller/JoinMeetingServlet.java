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
import meetingDetail.model.vo.MeetingPerson;

/**
 * Servlet implementation class JoinMeetingServlet
 */
@WebServlet(name = "JoinMeeting", urlPatterns = { "/joinMeeting" })
public class JoinMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		LoginMember m = (LoginMember)session.getAttribute("member");
		String memberId = m.getMemberId();
		MeetingPerson mp = new MeetingPerson();
		mp.setMeetingNo(Integer.parseInt(request.getParameter("meetingNo")));
		mp.setMemberId(memberId);
		
		int result = new MeetingDetailService().joinMeeting(mp);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "모임 가입 완료");
		}else {
			request.setAttribute("msg", "이미 가입한 모임입니다.");
		}
		request.setAttribute("loc", "/meetingDetail?meetingNo="+mp.getMeetingNo()+"&reqPage=1");
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
