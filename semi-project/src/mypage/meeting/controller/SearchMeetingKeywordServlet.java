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
 * Servlet implementation class SearchMeetingKeywordServlet
 */
@WebServlet(name = "SearchMeetingKeyword", urlPatterns = { "/searchMeetingKeyword" })
public class SearchMeetingKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMeetingKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String opt = request.getParameter("opt");
		String memberId = request.getParameter("memberId");
		String keyword = request.getParameter("keyword");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		MyMeetingPageData mmpd = new MeetingService().searchKeywordGet(opt, keyword, memberId, reqPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mymoim.jsp");
		request.setAttribute("opt", opt);
		request.setAttribute("keyword", keyword);
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
