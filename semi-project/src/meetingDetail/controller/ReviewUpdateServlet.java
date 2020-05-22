package meetingDetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet(name = "ReviewUpdate", urlPatterns = { "/reviewUpdate" })
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Review r = new Review();		//리뷰 객체에 내용, 현재 게시물번호, 로그인된 멤버 아이디 담아서 전달
		r.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
		r.setMeetingNo(Integer.parseInt(request.getParameter("meetingNo")));
		r.setReviewContent(request.getParameter("update"));
		System.out.println(r.getReviewNo());
		System.out.println(r.getReviewContent());
		int result = new MeetingDetailService().updateReview(r);
		RequestDispatcher rd = request.getRequestDispatcher("/meetingDetail?meetingNo="+r.getMeetingNo()+"&reqPage=1");
		if(result>0) {
			request.setAttribute("msg", "후기가 수정되었습니다.");
		}else {
			request.setAttribute("msg", "다시 시도해주세요");
		}
		request.setAttribute("review", r);
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
