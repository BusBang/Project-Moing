package mypage.letter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.letter.model.service.LetterService;
import mypage.letter.model.vo.LetterPageData;

/**
 * Servlet implementation class SearchLetterKeywordServlet
 */
@WebServlet(name = "SearchLetterKeyword", urlPatterns = { "/searchLetterKeyword" })
public class SearchLetterKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLetterKeywordServlet() {
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
		int num = Integer.parseInt(request.getParameter("num"));
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		System.out.println(opt);
		System.out.println(memberId);
		System.out.println(keyword);
		System.out.println(reqPage);
		System.out.println(num);
		LetterPageData lpd = new LetterService().searchKeywordGet(opt, keyword, memberId, reqPage, num);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterList.jsp");
		if(num==0) {
			request.setAttribute("opt", opt);
			request.setAttribute("keyword", keyword);
			request.setAttribute("num", num);
			request.setAttribute("listGet", lpd.getList());
			request.setAttribute("pageNaviGet", lpd.getLetterPageNavi());
			rd.forward(request, response);
			
		}else {
			request.setAttribute("opt", opt);
			request.setAttribute("keyword", keyword);
			request.setAttribute("num", num);
			request.setAttribute("listSend", lpd.getList());
			request.setAttribute("pageNaviSend", lpd.getLetterPageNavi());
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
