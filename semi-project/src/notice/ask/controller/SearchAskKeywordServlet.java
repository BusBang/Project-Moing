package notice.ask.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.ask.model.service.AskService;
import notice.ask.model.vo.AskPageData;


/**
 * Servlet implementation class SearchAskKeywordServlet
 */
@WebServlet(name = "SearchAskKeyword", urlPatterns = { "/searchAskKeyword" })
public class SearchAskKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAskKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String opt = request.getParameter("opt");
		String keyword = request.getParameter("keyword");
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
	
		AskPageData apd = new AskService().searchKeyword(opt, keyword, memberId, reqPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ask/askList.jsp");
		request.setAttribute("opt", opt);
		request.setAttribute("keyword", keyword);
		request.setAttribute("list", apd.getList());
		request.setAttribute("pageNavi", apd.getPageNavi());
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
