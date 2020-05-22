package meetingDetail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class LikeListServlet
 */
@WebServlet(name = "LikeList", urlPatterns = { "/likeList" })
public class LikeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/likeList.jsp");
		HttpSession session = request.getSession(false);
		LoginMember login = (LoginMember)session.getAttribute("member");
		String memberId = login.getMemberId();
		ArrayList<Meeting> likeList = new MeetingDetailService().likeList(memberId);
		request.setAttribute("likeList", likeList);
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
