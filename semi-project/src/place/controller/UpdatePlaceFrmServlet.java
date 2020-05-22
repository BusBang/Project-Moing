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
 * Servlet implementation class UpdatePlaceFrmServlet
 */
@WebServlet(name = "UpdatePlaceFrm", urlPatterns = { "/updatePlaceFrm" })
public class UpdatePlaceFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlaceFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Place p = new PlaceService().selectOnePlace(Integer.parseInt(request.getParameter("updateNo"))); //장소게시글 수정버튼 누를시 보내주는 placeNo
		
		PlaceKindAndCountry pkAc = new PlaceService().selectCountryAndPlaceKind();		
		ArrayList<PlaceKind> pkList = pkAc.getPkList(); //장소형식 테이블에서 장소형식명 정보 불러오기
		ArrayList<Country> cList = pkAc.getcList(); //지역테이블에서 지역명 불러오기
		
		
		//CountryNo는 2차지역명에 대한 No가 저장되어있음. 이거로 1차 CountryName 구해와서 2차 Name은 있는 ajax로 처리
		int countryNo2 = p.getCountryNo(); //수정할 글의 2차 지역번호
		int countryRef = 0; //수정할 글의 2차 지역 Ref
		String countryName = ""; //1차 지역명 저장할 변수
		String countryName2 = ""; //2차 지역명 저장할 변수
		
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCountryNo() == countryNo2) { //2차 지역
				countryRef = cList.get(i).getCountryRef();
				countryName2 = cList.get(i).getCountryName();
				break;
			}
		}
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCountryNo() == countryRef) { //2차지역의 ref와 같은 1차 no
				countryName = cList.get(i).getCountryName();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/place/updatePlace.jsp");
			request.setAttribute("p", p);
			request.setAttribute("placekindname", p.getPlaceKindName());
			request.setAttribute("sel1", countryName);
			request.setAttribute("sel2", countryName2);
			request.setAttribute("cList", cList);
			request.setAttribute("pkList", pkList);
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
