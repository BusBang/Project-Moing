package login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.service.LoginService;
import login.model.vo.LoginMember;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet(name = "AdminDelete", urlPatterns = { "/adminDelete" })
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //admin 회원 탈퇴시키기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		int result = new LoginService().deleteMember(memberId);
		System.out.println("삭제완료");
		HttpSession session = request.getSession(false);
		LoginMember m = (LoginMember)session.getAttribute("member");
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "제거 되었습니다.");
			request.setAttribute("loc", "/mypageModifyFrm?id="+m.getMemberId() );
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
