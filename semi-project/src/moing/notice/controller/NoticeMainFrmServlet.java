package moing.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moing.notice.service.NoticeService;
import moing.notice.vo.Notice;
import moing.notice.vo.NoticePageData;

/**
 * Servlet implementation class NoticeMainServlet
 */
@WebServlet(name = "NoticeMainFrm", urlPatterns = { "/noticeMainFrm" })
public class NoticeMainFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeMainFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//reqPage 생성후 정렬하여 출력
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		NoticePageData pd = new NoticeService().selectList(reqPage);
		RequestDispatcher rd =  request.getRequestDispatcher("WEB-INF/views/notice/noticeMain.jsp");
		request.setAttribute("list", pd.getList());
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
