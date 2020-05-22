package notice.ask.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.ask.model.service.AskService;
import notice.ask.model.vo.Ask;

/**
 * Servlet implementation class AskSendServlet
 */
@WebServlet(name = "AskSend", urlPatterns = { "/askSend" })
public class AskSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AskSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/close.jsp");
			request.setAttribute("msg", "[enctype]확인");
			rd.forward(request, response);
			return;
		}
		//파일 업로드 준비
		//1) 파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/photo";
		//2) 파일 최대 크기 설정(최대 10메가)
		int maxSize = 10*1024*1024;
		//3) request->MultipartRequest 바꿔주고 파일 업로드 진행
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		String memberId = mRequest.getParameter("memberId");
		String askContent = mRequest.getParameter("askContent");
		String askTitle = mRequest.getParameter("askTitle");
		String filename= mRequest.getOriginalFileName("askFilename");
		String filepath = mRequest.getFilesystemName("askFilename");
		Ask ask = new Ask();
		ask.setAskFilename(filename);
		ask.setAskContent(askContent);
		ask.setAskFilepath(filepath);
		ask.setAskTitle(askTitle);
		ask.setMemberId(memberId);
		
		
		//비지니스 로직 처리
		int result = new AskService().askSend(ask);
		//결과 처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/closeUpdate.jsp");
		
		if(result>0) {
			request.setAttribute("msg", "문의 등록성공");
		}else {
			request.setAttribute("msg", "문의 등록실패");
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
