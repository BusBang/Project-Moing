package mypage.letter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.letter.model.service.LetterService;
import mypage.letter.model.vo.Letter;

/**
 * Servlet implementation class LetterSendServlet
 */
@WebServlet(name = "LetterSend", urlPatterns = { "/letterSend" })
public class LetterSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String letterGetMemberIds = request.getParameter("letterGetMemberId");
		StringTokenizer st = new StringTokenizer(letterGetMemberIds, ",");
		ArrayList<String> arr = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			arr.add(st.nextToken());
		}
		ArrayList<Letter> arrLetter = new ArrayList<Letter>();
		for(int i = 0; i<arr.size();i++) {
			Letter l = new Letter();
			l.setLetterContent(request.getParameter("letterContent"));
			l.setLetterTitle(request.getParameter("letterTitle"));
			l.setLetterSendMemberId(request.getParameter("letterSendMemberId"));
			
			l.setLetterGetMemberId(arr.get(i));
			arrLetter.add(l);
		}
		
		int result = new LetterService().sendLetter(arrLetter);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/closeUpdate.jsp");
		if(result==arrLetter.size()) {
			request.setAttribute("msg", "쪽지 전송 완료");
		}else {
			request.setAttribute("msg", "쪽지 전송 실패");
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
