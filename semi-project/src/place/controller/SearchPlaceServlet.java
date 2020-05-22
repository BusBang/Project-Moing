package place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import place.model.service.PlaceService;
import place.model.vo.Country;
import place.model.vo.Place;
import place.model.vo.PlaceKind;
import place.model.vo.PlaceKindAndCountry;
import place.model.vo.PlacePageData;

/**
 * Servlet implementation class SearchPlaceServlet
 */
@WebServlet(name = "SearchPlace", urlPatterns = { "/searchPlace" })
public class SearchPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPlaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//사용자가 검색에 사용한 옵션들 저장
		String keyword = request.getParameter("keyword"); //검색 키워드
		String check = request.getParameter("check_option2"); //체크박스 옵션
		String sel2 = request.getParameter("countryName"); //2차 지역명(1차는 필요없음!)
		String placeKind = request.getParameter("placeKindName"); //장소형식
		String useDate = request.getParameter("placeUseDate"); // 이용하려는 날짜 : string -> 오라클 Date로 형변환
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		if(sel2.equals("2차 지역명")) {
			sel2="";
		}
		if(placeKind.equals("장소형식")) {
			placeKind="";
		}
		PlacePageData pageData = new PlaceService().selectSearchPlaceList(reqPage, keyword, check, sel2, placeKind, useDate);
		
		PlaceKindAndCountry pkAndCList = new PlaceService().selectCountryAndPlaceKind(); //지역명 정보, 장소형식 정보
		
		ArrayList<PlaceKind> pkList = pkAndCList.getPkList();
		ArrayList<Country> cList = pkAndCList.getcList();
		
		String sel1 = "";
		int sel2No = 0;
		
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCountryName().equals(sel2)) { //2차지역명과 같으면 No 저장
				sel2No = cList.get(i).getCountryRef(); //2차 지역명의 Ref
			}
		}
		;
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCountryNo() == sel2No) { //1차지역명의 No랑 2차지역명의 Ref가 같으면
				sel1 = cList.get(i).getCountryName();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/place/searchListMain.jsp");
		request.setAttribute("pkList", pkList);
		request.setAttribute("cList", cList);
		request.setAttribute("pList", pageData.getpList());
		request.setAttribute("pageNavi", pageData.getPageNavi());
		
		//이전에 입력한 값들을 유지시켜주기 위해 jsp로 전송
		request.setAttribute("keyword", keyword);
		request.setAttribute("check", check);
		request.setAttribute("sel1", sel1);
		request.setAttribute("sel2", sel2);
		request.setAttribute("placekindname", placeKind);
		request.setAttribute("useDate", useDate);
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
