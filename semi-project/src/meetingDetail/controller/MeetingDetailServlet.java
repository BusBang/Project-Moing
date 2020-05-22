package meetingDetail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.vo.LoginMember;
import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.Meeting;

import meetingDetail.model.vo.MeetingDetailAllList;

/**
 * Servlet implementation class MeetingDetailServlet
 */
@WebServlet(name = "MeetingDetail", urlPatterns = { "/meetingDetail" })
public class MeetingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String memberId = null;
		
		if(session.getAttribute("member") != null) {
			LoginMember login = (LoginMember)session.getAttribute("member");
			memberId = login.getMemberId();
			
			int reqPage = Integer.parseInt(request.getParameter("reqPage"));
//	         int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
	         
	         //변경사항
	         int meetingNo = 0;
	         if(session.getAttribute("meetingNo") != null) {
	            meetingNo = (int)session.getAttribute("meetingNo");
	            session.removeAttribute("meetingNo");
	         }else {         
	            meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
	         }
			
			
			MeetingDetailAllList mdAll = new MeetingDetailService().selectAllMD(reqPage,meetingNo);
			int result =0;
			if(memberId != null) {
				ArrayList<Meeting> likeList = new MeetingDetailService().likeList(memberId);
				for(int i=0;i<likeList.size();i++) {
					if(likeList.get(i).getMeetingNo() == meetingNo) {
						result=1;
					}
				}
			}
			
			request.setAttribute("meet",mdAll.getMd().getMeet());
			request.setAttribute("review", mdAll.getMd().getReview());
			request.setAttribute("page", mdAll.getMd().getPageNavi());
			request.setAttribute("mp", mdAll.getMp());
			request.setAttribute("meetingNo", mdAll.getMd().getMeet().getMeetingNo());;
			request.setAttribute("result", result);
			
			
		}else {
			int reqPage = Integer.parseInt(request.getParameter("reqPage"));
			
			int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
			Meeting meet = new Meeting();
			meet.setMeetingNo(meetingNo);
			
			MeetingDetailAllList mdAll = new MeetingDetailService().selectAllMD(reqPage,meetingNo);
			
			request.setAttribute("meet",mdAll.getMd().getMeet());
			request.setAttribute("review", mdAll.getMd().getReview());
			request.setAttribute("page", mdAll.getMd().getPageNavi());
			request.setAttribute("mp", mdAll.getMp());
			request.setAttribute("meetingNo", mdAll.getMd().getMeet().getMeetingNo());;
			
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetingDetail/meetingDetail.jsp");
		}
			
				

		/*int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		
		//가데이터들
		//int reqPage = 1;
		int meetingNo = 1;
		
		Meeting meet = new Meeting();
		meet.setMeetingNo(meetingNo);
		
		MeetingDetailAllList mdAll = new MeetingDetailService().selectAllMD(reqPage,meetingNo);
		int result =0;
		if(memberId != null) {
			ArrayList<Meeting> likeList = new MeetingDetailService().likeList(memberId);
			for(int i=0;i<likeList.size();i++) {
				if(likeList.get(i).getMeetingNo() == meetingNo) {
					result=1;
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetingDetail/meetingDetail.jsp");
		request.setAttribute("meet",mdAll.getMd().getMeet());
		
		request.setAttribute("review", mdAll.getMd().getReview());
		request.setAttribute("page", mdAll.getMd().getPageNavi());
		request.setAttribute("mp", mdAll.getMp());
		//request.setAttribute("likeList", likeList);
		request.setAttribute("meetingNo", mdAll.getMd().getMeet().getMeetingNo());;
		request.setAttribute("result", result);*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetingDetail/meetingDetail.jsp");
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
