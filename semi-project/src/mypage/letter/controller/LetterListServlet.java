package mypage.letter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.letter.model.service.LetterService;
import mypage.letter.model.vo.LetterPageData;

/**
 * Servlet implementation class LetterListServlet
 */
@WebServlet(name = "LetterList", urlPatterns = { "/letterList" })
public class LetterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 변수에 값 저장
		String memberId = request.getParameter("memberId");//로그인 아이디
		int num = Integer.parseInt(request.getParameter("num"));//받은쪽지: 0/ 보낸쪽지:1
		
		int reqPageGetLetter = Integer.parseInt(request.getParameter("reqPageGetLetter"));//받은 쪽지함 요청 페이지
		LetterPageData lpdGet = new LetterService().selectLetterGetList(reqPageGetLetter, memberId);//받은 쪽지함 페이지 데이터(쪽지 리스트(letter/rnum),페이지 네비)
		int reqPageSendLetter = Integer.parseInt(request.getParameter("reqPageSendLetter"));//보낸 쪽지함 요청 페이지
		LetterPageData lpdSend = new LetterService().selectLetterSendList(reqPageSendLetter,memberId);//보낸 쪽지함 페이지데이터
		
		//3. 비지니스 로직 처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterList.jsp");
		request.setAttribute("listGet", lpdGet.getList());
		request.setAttribute("pageNaviGet", lpdGet.getLetterPageNavi());
		request.setAttribute("listSend", lpdSend.getList());
		request.setAttribute("pageNaviSend", lpdSend.getLetterPageNavi());
		request.setAttribute("num", num);
		System.out.println(lpdGet.getList().size());
		System.out.println(lpdSend.getList().size());
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
