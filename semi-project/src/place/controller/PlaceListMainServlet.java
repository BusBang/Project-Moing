package place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import place.model.service.PlaceService;
import place.model.vo.Country;
import place.model.vo.Place;
import place.model.vo.PlaceKind;
import place.model.vo.PlaceKindAndCountry;
import place.model.vo.PlacePageData;

/**
 * Servlet implementation class PlaceListMainServlet
 */
@WebServlet(name = "PlaceListMain", urlPatterns = { "/placeListMain" })
public class PlaceListMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceListMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//변수에 값 저장
		PlaceKindAndCountry pkAndCList = new PlaceService().selectCountryAndPlaceKind(); //지역명 정보, 장소형식 정보
		
		ArrayList<PlaceKind> pkList = pkAndCList.getPkList();
		ArrayList<Country> cList = pkAndCList.getcList();
		
		//페이징 처리
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		PlacePageData pageData = new PlaceService().selectListPlace(reqPage);
		
		//결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/place/placeListMain.jsp");
			request.setAttribute("pkList", pkList);
			request.setAttribute("cList", cList);
			request.setAttribute("pList", pageData.getpList());
			request.setAttribute("pageNavi", pageData.getPageNavi());
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
