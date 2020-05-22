package mypage.letter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.letter.model.service.LetterService;

/**
 * Servlet implementation class LetterDeleteFrmServlet
 */
@WebServlet(name = "LetterDeleteFrm", urlPatterns = { "/letterDeleteFrm" })
public class LetterDeleteFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterDeleteFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int letterNo = Integer.parseInt(request.getParameter("letterNo"));
		int num = Integer.parseInt(request.getParameter("num"));
		int result = new LetterService().deleteLetter(letterNo, num);
		System.out.println(result);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/closeUpdate.jsp");
		if(result>0) {
			request.setAttribute("msg", "삭제 성공");
		}else {
			request.setAttribute("msg", "삭제 실패");
		}
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
