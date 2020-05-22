package meetingDetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.vo.LoginMember;
import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Review;

/**
 * Servlet implementation class ReviewInsertServlet
 */
@WebServlet(name = "ReviewInsert", urlPatterns = { "/reviewInsert" })
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String memberId = null;
		LoginMember login = (LoginMember)session.getAttribute("member");
		memberId = login.getMemberId();
		
		Review r = new Review();		//리뷰 객체에 내용, 현재 게시물번호, 로그인된 멤버 아이디 담아서 전달
		r.setReviewContent(request.getParameter("reviewContent"));
		r.setMeetingNo(Integer.parseInt(request.getParameter("meetingNo")));
		r.setMemberId(request.getParameter("reviewWriter"));
		int result = new MeetingDetailService().insertReview(r, memberId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("msg", "후기가 등록되었습니다.");
		}else {
			request.setAttribute("msg", "다시 시도해주세요");
		}
		//후기 등록 성공, 실패 관계 없이 현재 게시물에 스테이
		request.setAttribute("loc", "/meetingDetail?meetingNo="+r.getMeetingNo()+"&reqPage=1");
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
