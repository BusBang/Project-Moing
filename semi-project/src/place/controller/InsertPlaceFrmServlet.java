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

/**
 * Servlet implementation class InsertPlaceFrmServlet
 */
@WebServlet(name = "InsertPlaceFrm", urlPatterns = { "/insertPlaceFrm" })
public class InsertPlaceFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPlaceFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		PlaceKindAndCountry pkAc = new PlaceService().selectCountryAndPlaceKind();		
		ArrayList<PlaceKind> pkList = pkAc.getPkList(); //장소형식 테이블에서 장소형식명 정보 불러오기
		ArrayList<Country> cList = pkAc.getcList(); //지역테이블에서 지역명 불러오기
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/place/insertPlace.jsp");
		request.setAttribute("pkList", pkList);
		request.setAttribute("cList", cList);	
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
