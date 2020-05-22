package moim.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.model.service.CategoryService;
import category.model.vo.Category;
import country.model.service.CountryService;
import country.model.vo.Country;
import login.model.vo.LoginMember;
import member.model.service.MemberService;
import member.model.vo.Member;
import moim.model.service.MoimService;
import moim.model.vo.Meeting;
import moim.model.vo.MoimPageData;

/**
 * Servlet implementation class MoimMainServlet
 */
@WebServlet(name = "MoimMain", urlPatterns = { "/moimMain" })
public class MoimMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		System.out.println("reqPage"+reqPage);
		
		MoimPageData md = new MoimService().selectMeetingList(reqPage);
		
		ArrayList<Category> categoryList = new CategoryService().seleteAllCategory();
		ArrayList<Country> countryList = new CountryService().selectAllCountry();

		Member member = new Member();
		LoginMember loginMember = new LoginMember();
		HttpSession session = request.getSession(true);
		loginMember= (LoginMember) session.getAttribute("member");
		
		if(loginMember != null) {
			member.setMemberId(loginMember.getMemberId());
			member.setMemberPw(loginMember.getMemberPw());
			member.setMemberName(loginMember.getMemberName());
			member.setMemberAddr(loginMember.getMemberAddr());
			member.setMemberEmail(loginMember.getMemberEmail());
			member.setMemberPhone(loginMember.getMemberPhone());
		}
		

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("countryList", countryList);
		request.setAttribute("meetingList", md.getList());
		request.setAttribute("pageNavi", md.getPageNavi());
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/views/moim/moimMain.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
