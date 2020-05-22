package moing.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moing.notice.service.NoticeService;
import moing.notice.vo.NoticePageData;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet(name = "NoticeSearch", urlPatterns = { "/noticeSearch" })
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String type = request.getParameter("type");
		System.out.println(type);
		String keyword =request.getParameter("keyword");
		System.out.println(keyword);
		NoticePageData pd = new NoticeService().searchPageNotice(reqPage,type,keyword);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/notice/noticeSearch.jsp");
		request.setAttribute("list", pd.getList());
		request.setAttribute("keyword", keyword);
		request.setAttribute("type", type);
		request.setAttribute("pageNavi", pd.getPageNavi());
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
