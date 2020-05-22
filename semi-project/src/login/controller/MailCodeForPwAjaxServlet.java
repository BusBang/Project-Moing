package login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import login.model.service.LoginService;
import login.model.vo.LoginMember;

/**
 * Servlet implementation class MailCodeAjaxServlet
 */
@WebServlet(name = "MailCodeForAjax", urlPatterns = { "/mailCodeForPwAjax" })
public class MailCodeForPwAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailCodeForPwAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //비밀번호 재설정을 위한 이메일 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		int result = new LoginService().pwSearch(memberEmail);
		String str = "";
		if(result > 0) {
			str = "Yes";
		} else {
			str = "No";
		}
		System.out.println(str);
		PrintWriter out = response.getWriter();
		out.print(str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
