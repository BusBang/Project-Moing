package mypage.letter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.letter.model.service.LetterService;
import mypage.letter.model.vo.Letter;

/**
 * Servlet implementation class LetterViewServlet
 */
@WebServlet(name = "LetterView", urlPatterns = { "/letterView" })
public class LetterViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int letterNo = Integer.parseInt(request.getParameter("letterNo"));
		String memberId = request.getParameter("memberId");
		Letter letter = new LetterService().selectOneLetter(letterNo, memberId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterView.jsp");
		request.setAttribute("letter", letter);
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
