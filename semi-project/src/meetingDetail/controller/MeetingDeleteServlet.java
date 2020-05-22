package meetingDetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Meeting;

/**
 * Servlet implementation class MeetingDeleteServlet
 */
@WebServlet(name = "MeetingDelete", urlPatterns = { "/meetingDelete" })
public class MeetingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Meeting meet = new Meeting();
		System.out.println("print:"+request.getParameter("meetingNo"));
		int meetingNo = (Integer.parseInt(request.getParameter("meetingNo")));
		
		System.out.println("Integer meetingNo :"+meetingNo);
		int result = new MeetingDetailService().deleteMeeting(meetingNo);
		if(result>0) {
			request.setAttribute("msg", "해당 글이 삭제되었습니다.");	
		}else {
			request.setAttribute("msg", "삭제 실패. 다시 시도해주세요");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/moimMain?reqPage=1");
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
