package moim.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moim.model.service.MoimService;
import moim.model.vo.Meeting;

/**
 * Servlet implementation class MoimModifyServlet
 */
@WebServlet(name = "MoimModify", urlPatterns = { "/moimModify" })
public class MoimModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "[enctype]확인");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		//파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDriectory = root+"upload";
		int maxSize = 10*1024*1024;
		
		MultipartRequest mRequest = new MultipartRequest(request, saveDriectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		int meetingNo = Integer.parseInt(mRequest.getParameter("meetingNo"));
		String meetingTitle = mRequest.getParameter("moimTitle");
		String meetingName =mRequest.getParameter("moimName");		
		int meetingMinPerson = Integer.parseInt(mRequest.getParameter("minPerson"));
		int meetingMaxPerson = Integer.parseInt(mRequest.getParameter("maxPerson"));
		String meetingContent = mRequest.getParameter("content");
		String meetingFilename = mRequest.getOriginalFileName("filename");
		String meetingFilepath = mRequest.getFilesystemName("filename");
		java.sql.Date meetingDate = java.sql.Date.valueOf(mRequest.getParameter("date"));	
		String memberId = mRequest.getParameter("moimWriter");
		
		String category1 = mRequest.getParameter("category1");
		String category2 = mRequest.getParameter("category2");
		int categoryNo;
		if(category2!=null) {
			categoryNo = Integer.parseInt(category2);
		}else {
			categoryNo = Integer.parseInt(category1);
		}
		String country1 = mRequest.getParameter("country1");
		String country2 = mRequest.getParameter("country2");
		int countryNo;
		if(country2!=null) {
			countryNo = Integer.parseInt(country2);
		} else {
			countryNo = Integer.parseInt(country1);
		}
		String addrCode = mRequest.getParameter("addrCode");
		String addrRoad = mRequest.getParameter("addrRoad");
//		String addrJibun = mRequest.getParameter("addrJibun");
		String addrDetail = mRequest.getParameter("addrDetail");
		
		String meetingAddr = addrCode+" "+addrRoad+" "+addrDetail;
		
		Meeting meet = new Meeting(); 
		meet.setMeetingNo(meetingNo);
		meet.setMeetingTitle(meetingTitle);
		meet.setMeetingName(meetingName);
		meet.setMeetingMinPerson(meetingMinPerson);
		meet.setMeetingMaxPerson(meetingMaxPerson);
		meet.setMeetingContent(meetingContent);
		meet.setMeetingDate(meetingDate);
		meet.setMeetingAddr(meetingAddr);
		meet.setMemberId(memberId);
		meet.setCategoryNo(categoryNo);
		meet.setCountryNo(countryNo);
		int result = 0;
		if(meetingFilename != null && meetingFilepath != null) {
			meet.setMeetingFilename(meetingFilename);
			meet.setMeetingFilepath(meetingFilepath);			
			result = new MoimService().MoimModify(meet);
		}else {
			result = new MoimService().MoimModifyFileNull(meet);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정 완료");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		//세션만들기 - 글번호 속성 만들기
		HttpSession session = request.getSession();
		session.setAttribute("meetingNo", meetingNo);
		
		request.setAttribute("loc", "/meetingDetail?MeetingNo="+meet.getMeetingNo()+"&reqPage=1");
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
