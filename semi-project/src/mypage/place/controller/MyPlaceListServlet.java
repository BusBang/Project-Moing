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
 * Servlet implementation class MyPlaceListServlet
 */
@WebServlet(name = "MyPlaceList", urlPatterns = { "/myPlaceList" })
public class MyPlaceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPlaceListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		int num=Integer.parseInt(request.getParameter("num")); //1
				
		int reqPagePlace0= Integer.parseInt(request.getParameter("reqPagePlace0"));//장소 이용자 리스트 요청 페이지
		int reqPagePlace1= Integer.parseInt(request.getParameter("reqPagePlace1"));//장소 제공자 리스트 요청페이지
		
		
		PlaceInfoPageData pipd0 = new PlaceService().place0List(memberId, reqPagePlace0);//장소 이용자 리스트 페이지 데이터
		PlaceInfoPageData pipd1 = new PlaceService().place1List(memberId, reqPagePlace1);//장소 제공자 리스트 페이지 데이터
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/placeList.jsp");
		request.setAttribute("list0", pipd0.getList());//장소 이용자의 장소 리스트
		request.setAttribute("pageNavi0", pipd0.getPageNavi());//장소 이용자의 장소 리스트 네비
		
		request.setAttribute("list1", pipd1.getList());//장소 제공자의 장소 리스트
		request.setAttribute("pageNavi1", pipd1.getPageNavi());//장소 제공자의 장소 리스트 네비
		
		request.setAttribute("reqPagePlace0", reqPagePlace0);//장소 이용자 리스트 요청페이지(예약 리스트 서블릿 때 사용)
		request.setAttribute("reqPagePlace1", reqPagePlace1);//장소 제공자 리스트 요청페이지(예약 리스트 서블릿 때 사용)
		
		request.setAttribute("num", num);
		request.setAttribute("id", memberId);
		
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
