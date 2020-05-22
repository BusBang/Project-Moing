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

import mypage.meeting.model.service.MeetingService;

/**
 * Servlet implementation class LetterWriteServlet
 */
@WebServlet(name = "LetterWrite", urlPatterns = { "/letterWrite" })
public class LetterWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int to = Integer.parseInt(request.getParameter("to"));
		if(to==0) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterWrite.jsp");
			request.setAttribute("to", to);
			rd.forward(request, response);
		}else if(to==2){//답장 
			
			String letterGetMemberId = request.getParameter("letterGetMemberId");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterWrite.jsp");
			request.setAttribute("arrString", letterGetMemberId);
			request.setAttribute("to", to);
			rd.forward(request, response);
		}else {
			int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
			String letterSendMemberId = request.getParameter("letterSendMemberId");
			String letterGetMemberId = new MeetingService().meetingMember(meetingNo);
			StringTokenizer st = new StringTokenizer(letterGetMemberId, ",");
			ArrayList<String> arr = new ArrayList<String>();
			while(st.hasMoreTokens()) {
				arr.add(st.nextToken());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/letterWrite.jsp");
			request.setAttribute("arr", arr);
			request.setAttribute("arrString", letterGetMemberId);
			request.setAttribute("to", to);
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
