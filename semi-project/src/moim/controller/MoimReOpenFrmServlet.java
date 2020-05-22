package moim.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.model.service.CategoryService;
import category.model.vo.Category;
import country.model.service.CountryService;
import country.model.vo.Country;
import moim.model.service.MoimService;
import moim.model.vo.Meeting;

/**
 * Servlet implementation class MoimReOpenFrmServlet
 */
@WebServlet(name = "MoimReOpenFrm", urlPatterns = { "/moimReOpenFrm" })
public class MoimReOpenFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimReOpenFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		Meeting meeting = new MoimService().SelectOneMeeting(meetingNo);
		
		//카테고리, 지역 셀렉트바 객체 생성
		ArrayList<Category> categoryList = new CategoryService().seleteAllCategory();
		ArrayList<Country> countryList = new CountryService().selectAllCountry();
		//해당글 카테고리, 지역 넘버 추출
		Category category = new CategoryService().seleteOneCategory(meeting.getCategoryNo()); 
		Country country = new CountryService().selectOneCountry(meeting.getCountryNo());
		int category1No;
		int category2No;
		int country1No;
		int country2No;
		if(category.getCategoryRef() != 0) {
			category1No = category.getCategoryRef();
			category2No = category.getCategoryNo();
		}else {
			category1No = category.getCategoryNo();
			category2No = 0;
		}
		country1No = country.getCountryRef();
		country2No = country.getCountryNo();
		//
		//주소 집코드, 지번 나누기
		String meetingAddr = meeting.getMeetingAddr();		
		String [] meetingAddrArr = meetingAddr.split(" ");
		String meetingZipCode = "";
		String meetingRoadAddr ="";
		
		if(meetingAddrArr.length!=0 && meetingAddrArr.length>=5) {	
			meetingZipCode = meetingAddrArr[0];
			for(int i=1; i<5; i++) {
				//상세주소는 뺀다.
				meetingRoadAddr += meetingAddrArr[i]+" ";
			}
		}
		//////////글제목 글자수
		int meetingTitleLength = meeting.getMeetingTitle().length();
		request.setAttribute("meetingTitleLength", meetingTitleLength);
		//
		System.out.println("meetingTilte"+meeting.getMeetingTitle());
		request.setAttribute("cateSel1", category1No);
		request.setAttribute("cateSel2", category2No);
		request.setAttribute("conSel1", country1No);
		request.setAttribute("conSel2", country2No);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("countryList", countryList);
		request.setAttribute("meeting", meeting);
		request.setAttribute("meetingZipCode", meetingZipCode);
		request.setAttribute("meetingRoadAddr", meetingRoadAddr);
		request.getRequestDispatcher("/WEB-INF/views/moim/moimReOpenFrm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
