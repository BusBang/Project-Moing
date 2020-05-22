package mypage.meeting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.meeting.model.service.MeetingService;
import mypage.meeting.model.vo.MyMeetingPageData;

/**
 * Servlet implementation class MyMoimServlet
 */
@WebServlet(name = "MyMoim", urlPatterns = { "/myMoim" })
public class MyMoimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMoimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));//요청 페이지
		String memberId = request.getParameter("memberId");//로그인 아이디
		
		MyMeetingPageData mmpd = new MeetingService().myMeetingList(reqPage, memberId);//페이지 데이터
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mymoim.jsp");
		request.setAttribute("list", mmpd.getList());
		request.setAttribute("pageNavi", mmpd.getPageNavi());
		request.setAttribute("id", memberId);
		
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
