package login.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.service.LoginService;
import login.model.vo.CategorySubjectList;
import login.model.vo.LoginMember;

/**
 * Servlet implementation class MypageModifyFrmServlet
 */
@WebServlet(name = "MypageModifyFrm", urlPatterns = { "/mypageModifyFrm" })
public class MypageModifyFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageModifyFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //마이페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); //세션에 등록된 아이디
		System.out.println("서블릿!!!"+id);
		LoginMember m = new LoginService().selectOneMember(id);
		request.setAttribute("member", m);
		System.out.println(id);
		System.out.println(m.getMemberId());
		
		ArrayList<LoginMember> list = new LoginService().selectAllMember();	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/moim/mypageModify2.jsp");
		request.setAttribute("list", list);
		request.setAttribute("id", id);
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
