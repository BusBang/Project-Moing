package moim.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import category.model.service.CategoryService;
import meetingDetail.model.service.MeetingDetailService;
import meetingDetail.model.vo.MeetingPerson;
import member.model.vo.Member;
import moim.model.service.MoimService;
import moim.model.vo.Meeting;

/**
 * Servlet implementation class MoimInsertServlet
 */
@WebServlet(name = "MoimInsert", urlPatterns = { "/moimInsert" })
public class MoimInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimInsertServlet() {
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
		
		System.out.println("-----테스트");
		System.out.println(meetingTitle);
		System.out.println(meetingName);
		System.out.println(meetingMinPerson);
		System.out.println(meetingMaxPerson);
		System.out.println(meetingContent);
		System.out.println(meetingFilename);
		System.out.println(meetingFilepath);
		System.out.println(meetingDate);
		System.out.println(memberId);
		System.out.println("cate1"+category1);
		System.out.println("cate2"+category2);
		System.out.println("categoryNo"+categoryNo);
		System.out.println("count1"+country1);
		System.out.println("count2"+country2);
		System.out.println("countryNo"+countryNo);
		System.out.println("meetingAddr : "+meetingAddr);
		System.out.println("---------");
		Meeting meet = new Meeting(); 
		meet.setMeetingTitle(meetingTitle);
		meet.setMeetingName(meetingName);
		meet.setMeetingMinPerson(meetingMinPerson);
		meet.setMeetingMaxPerson(meetingMaxPerson);
		meet.setMeetingContent(meetingContent);
		meet.setMeetingFilename(meetingFilename);
		meet.setMeetingFilepath(meetingFilepath);
		meet.setMeetingDate(meetingDate);
		meet.setMeetingAddr(meetingAddr);
		meet.setMemberId(memberId);
		meet.setCategoryNo(categoryNo);
		meet.setCountryNo(countryNo);
		

		
		int resultArr = new MoimService().insertMoim(meet);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(resultArr>0) {
			request.setAttribute("msg", "업로드 완료");
		}else {
			request.setAttribute("msg", "업로드 실패");
		}
		request.setAttribute("loc", "/moimMain?reqPage=1");
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
