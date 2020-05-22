package place.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

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
import place.model.service.PlaceService;
import place.model.vo.Place;

/**
 * Servlet implementation class InsertPlaceServlet
 */
@WebServlet(name = "InsertPlace", urlPatterns = { "/insertPlace" })
public class InsertPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPlaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩(필터)
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "[enctype] 확인");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		
		String root = request.getSession().getServletContext().getRealPath("/"); //루트 경로?
		String saveDirectory = root + "upload/place_photo"; //저장경로
		//파일 최대 크기 설정
		int maxSize = 10*1024*1024;
		//request를 multipartRequest로 바꾸고, 파일 업로드 진행
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//2.변수에 값 저장
		
		
		Place p = new Place();
		p.setPlaceTitle(mRequest.getParameter("placeTitle")); //제목
		p.setPlaceContent(mRequest.getParameter("placeContent")); //내용
		p.setPlaceAddr(mRequest.getParameter("placeAddr")); //주소
		
		java.sql.Date placeStartDate = java.sql.Date.valueOf(mRequest.getParameter("placeStartDate")); //스트링 -> 오라클  Date 형변환
		java.sql.Date placeEndDate = java.sql.Date.valueOf(mRequest.getParameter("placeEndDate"));
		
		p.setPlaceStartDate(placeStartDate); //시작 날짜
		p.setPlaceEndDate(placeEndDate);  //마감날짜
		if(mRequest.getParameter("placeDeposit").equals("")) { //예약금은 필수 입력 항목이 아님!!
			//예약금을 입력하지 않은 경우에 ""으로 값이 전달되는데 Integer로 형변환 하려고 해서 에러남
			//그래서 입력 안하면 0으로 값 세팅
			p.setPlaceDeposit(0);
		}else {
			p.setPlaceDeposit(Integer.parseInt(mRequest.getParameter("placeDeposit"))); //예약금			
		}
		p.setPlaceAccountNumber(mRequest.getParameter("placeAccountNumber")); //계좌번호
		p.setPlacefilename(mRequest.getOriginalFileName("placefilename")); //실제 파일명
		p.setPlacefilepath(mRequest.getFilesystemName("placefilename")); //서버에 저장될 파일명
		p.setMemberId(mRequest.getParameter("memberId")); //세션에 저장된 회원이름
		
		p.setCountryNo(new PlaceService().selectCountryNo(mRequest.getParameter("countryName"))); //장소글에는 외래키인 번호로 등록해야하기 때문에
		p.setPlaceKindName(mRequest.getParameter("placeKindName"));
		
		String arr [] = mRequest.getParameterValues("check_option2"); //
		String check_list= "";
		for(int i=0; i<arr.length; i++) {
			if(arr[i].equals("y")) {
				check_list += "1";
			}else {
				check_list += "0";
			}
		}
		p.setCheckOption(check_list);
		
		
		//3.비즈니스 로직
		
		int result = new PlaceService().insertPlace(p);
		
		
		//결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "게시글이 등록되었습니다.");
			request.setAttribute("loc", "/placeListMain?reqPage=1");
			
		}else {
			request.setAttribute("msg", "게시글 등록 실패");
			request.setAttribute("loc", "/");
			//등록 실패 시 다시 작성할 수 있도록(단, 입력했던 값들이 표시되어 있어야 함)
		}
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
