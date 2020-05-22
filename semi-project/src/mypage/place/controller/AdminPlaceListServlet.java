package mypage.place.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.place.model.service.PlaceService;
import mypage.place.model.vo.PlaceInfoPageData;

/**
 * Servlet implementation class AdminPlaceListServlet
 */
@WebServlet(name = "AdminPlaceList", urlPatterns = { "/adminPlaceList" })
public class AdminPlaceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPlaceListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPagePlace= Integer.parseInt(request.getParameter("reqPagePlace"));//장소 리스트 요청페이지
		
		PlaceInfoPageData pipd = new PlaceService().adminPlaceList(reqPagePlace);//장소리스트 페이지 데이터
		
		System.out.println("구한 길이"+pipd.getList().size());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/adminPlaceList.jsp");
		
		
		request.setAttribute("list", pipd.getList());//장소 제공자의 장소 리스트
		request.setAttribute("pageNavi", pipd.getPageNavi());//장소 제공자의 장소 리스트 네비
		request.setAttribute("reqPagePlace", reqPagePlace);//장소 제공자 리스트 요청페이지(예약 리스트 서블릿 때 사용)
		
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
