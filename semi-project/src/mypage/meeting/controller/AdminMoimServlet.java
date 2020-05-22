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
 * Servlet implementation class AdminMoimServlet
 */
@WebServlet(name = "AdminMoim", urlPatterns = { "/adminMoim" })
public class AdminMoimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMoimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));//요청 페이지
		
		MyMeetingPageData mmpd = new MeetingService().adminMeetingList(reqPage);//페이지 데이터
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mymoim.jsp");
		request.setAttribute("list", mmpd.getList());
		request.setAttribute("pageNavi", mmpd.getPageNavi());
		
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
