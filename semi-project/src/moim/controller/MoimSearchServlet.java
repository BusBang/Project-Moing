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
import jdk.nashorn.internal.runtime.Undefined;
import moim.model.service.MoimService;
import moim.model.vo.MoimPageData;

/**
 * Servlet implementation class MoimSearchServlet
 */
@WebServlet(name = "MoimSearch", urlPatterns = { "/moimSearch" })
public class MoimSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		String country1 = request.getParameter("country1");
		String country2 = request.getParameter("country2");
		System.out.println(category1);
		System.out.println(category2);
		System.out.println(country1);
		System.out.println(country2);

		request.setAttribute("cateSel1", category1);
		request.setAttribute("cateSel2", category2);
		request.setAttribute("conSel1", country1);
		request.setAttribute("conSel2", country2);
		String lineUp = "";
		if(request.getParameter("lineUp")!=null) {
			System.out.println("널이아니라면,");
			lineUp = request.getParameter("lineUp");
		}
		System.out.println("lineup : "+lineUp);
		
		
		System.out.println(category2);
		int categoryNo = 0;
		int countryNo = 0;
		if(category1.equals("12")) {
			categoryNo = 12;							
		}else {
			if(category2 != "") {
				if(category2 != null) {	
					categoryNo = Integer.parseInt(category2);
				}
			}else {
				if(category1 != "") {
					categoryNo = Integer.parseInt(category1);				
				}else if (category1 == "12") {
					categoryNo = 12;
				}
			}
		}
		System.out.println("categoryNo"+categoryNo);
		if(country2 != "") {
			countryNo = Integer.parseInt(country2);
		}else {
			if(country1 != "") {				
				countryNo = Integer.parseInt(country1);
			}
		}
		
		//category1이 전체가 아닐때.
		String keyword = request.getParameter("keywordSearch");
		int reqPage = 1;
		MoimPageData md = new MoimPageData();
		if(lineUp.equals("최신순")) {
			System.out.println("최신순진입");
			if(categoryNo != 0) {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingList(reqPage, categoryNo, countryNo, keyword);				
				}else {
					md = new MoimService().selectMeetingListCategory(reqPage, categoryNo, keyword);								
				}
			}else {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListCountry(reqPage, countryNo, keyword);								
				}else {
					md = new MoimService().selectMeetingList(reqPage, keyword);								
				}			
			}			
		}else if(lineUp.equals("인기순")){
			//인기순인경우
			System.out.println("인기순진입");
			if(categoryNo != 0) {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListLike(reqPage, categoryNo, countryNo, keyword);				
				}else {
					md = new MoimService().selectMeetingListCategoryLike(reqPage, categoryNo, keyword);								
				}
			}else {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListCountryLike(reqPage, countryNo, keyword);								
				}else {
					md = new MoimService().selectMeetingListNoNumberLike(reqPage, keyword);								
				}			
			}				
		}else if(lineUp.equals("이름순")) {
			//이름순인경우
			System.out.println("이름순진입");
			if(categoryNo != 0) {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListName(reqPage, categoryNo, countryNo, keyword);				
				}else {
					md = new MoimService().selectMeetingListCategoryName(reqPage, categoryNo, keyword);								
				}
			}else {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListCountryName(reqPage, countryNo, keyword);								
				}else {
					md = new MoimService().selectMeetingListName(reqPage, keyword);								
				}			
			}				
		}else {
			System.out.println("널진입");
			if(categoryNo != 0) {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingList(reqPage, categoryNo, countryNo, keyword);				
				}else {
					md = new MoimService().selectMeetingListCategory(reqPage, categoryNo, keyword);								
				}
			}else {
				if(countryNo != 0) {
					md = new MoimService().selectMeetingListCountry(reqPage, countryNo, keyword);								
				}else {
					md = new MoimService().selectMeetingList(reqPage, keyword);								
				}			
			}		
		}
		

		ArrayList<Category> categoryList = new CategoryService().seleteAllCategory();
		ArrayList<Country> countryList = new CountryService().selectAllCountry();
		request.setAttribute("lineUp", lineUp);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("countryList", countryList);
		request.setAttribute("meetingList", md.getList());
		request.setAttribute("pageNavi", md.getPageNavi());
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
