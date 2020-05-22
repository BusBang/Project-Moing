package notice.ask.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.ask.model.service.AskService;

/**
 * Servlet implementation class SendReplyServlet
 */
@WebServlet(name = "SendReply", urlPatterns = { "/sendReply" })
public class SendReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int askNo = Integer.parseInt(request.getParameter("askNo"));
		String replyContent = request.getParameter("replyContent");
		
		int result = new AskService().sendReply(askNo, replyContent);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/closeUpdate.jsp");
		if(result>0) {
			request.setAttribute("msg", "답변 완료");
		}else {
			request.setAttribute("msg", "답변 실패");
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
