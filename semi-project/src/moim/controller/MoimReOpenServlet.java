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

import meetingDetail.model.vo.MeetingPerson;
import meetingDetail.model.vo.Review;
import moim.model.service.MoimService;
import moim.model.vo.Meeting;

/**
 * Servlet implementation class MoimReOpenServlet
 */
@WebServlet(name = "MoimReOpen", urlPatterns = { "/moimReOpen" })
public class MoimReOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimReOpenServlet() {
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
//		meet.setLikeCount(likeCount);
		meet.setMeetingMinPerson(meetingMinPerson);
		meet.setMeetingMaxPerson(meetingMaxPerson);
		meet.setMeetingContent(meetingContent);

		meet.setMeetingDate(meetingDate);
		meet.setMeetingAddr(meetingAddr);
		meet.setMemberId(memberId);
		meet.setCategoryNo(categoryNo);
		meet.setCountryNo(countryNo);
		int result = 0;
	
		//모임에 참여한 회원, 리뷰 전부 불러오기 -> 새모임 개최 -> 개최한 모임에 모임에 참여한 회원 넣기
		//좋아요 개수, 현재 인원 가져오기
		//기존 모임 데이터 가져오기 -> meet에 넣기
		Meeting meeting = new MoimService().SelectOneMeeting(meetingNo);		
		meet.setLikeCount(meeting.getLikeCount());
		meet.setMeetingNowPerson(meeting.getMeetingNowPerson());
		//기존 모임에 참여한 회원 불러오기
		ArrayList<MeetingPerson> meetingPersonList = new MoimService().SelectAllMeetingPerson(meet.getMeetingNo());
		System.out.println("meetingPersonList:"+meetingPersonList.size());
		//기존 모임의 리뷰 전부 가져오기
		ArrayList<Review> reviewList = new MoimService().SelectAllReview(meet.getMeetingNo());
		System.out.println("reviewList:"+reviewList.size());
		System.out.println("meetname:"+meet.getMeetingName());
		System.out.println("meetno:"+meet.getMeetingNo());
		//새모임 개최
		if(meetingFilename != null && meetingFilepath != null) {
			meet.setMeetingFilename(meetingFilename);
			meet.setMeetingFilepath(meetingFilepath);			
		}else {
			meet.setMeetingFilename(meeting.getMeetingFilename());
			meet.setMeetingFilepath(meeting.getMeetingFilepath());			
		}			
		result = new MoimService().MoimReopen(meet);
		System.out.println("모임 개최 result : "+result);
		//새모임 기준으로 참여 회원과 리뷰 넣기. 새모임 meetingNo 조회
		int resultSeq = new MoimService().MoimReopenSeqSelect();
		System.out.println("새모임 미팅 넘버 : "+resultSeq);
		//새모임기준으로 회원과 리뷰 넣기
		//회원
		int resultInsertMP = new MoimService().insertMeetingPerson(resultSeq, meetingPersonList);
		System.out.println("새모임기준 회원 : "+resultInsertMP);
		//리뷰넣기
		int resultInsertR = new MoimService().insertReview(resultSeq, reviewList);
		System.out.println("새모임기준리뷰 : "+resultInsertR);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정 완료");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		//세션만들기 - 글번호 속성 만들기
		HttpSession session = request.getSession();
		session.setAttribute("meetingNo", resultSeq);
		
		request.setAttribute("loc", "/meetingDetail?MeetingNo="+resultSeq+"&reqPage=1");
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
