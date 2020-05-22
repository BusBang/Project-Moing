package category.model.service;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import category.model.vo.Category;

/**
 * Servlet implementation class CategorySelectServlet
 */
@WebServlet(name = "CategorySelect", urlPatterns = { "/categorySelect" })
public class CategorySelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorySelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		JSONArray arr = new JSONArray();
		if(request.getParameter("cateSel1") != "" && request.getParameter("cateSel1") != "12") {
			int cateSel1 = Integer.parseInt(request.getParameter("cateSel1"));
			ArrayList <Category> Category2List = new CategoryService().selectLevel2Cateory(cateSel1); 		
			for(Category c : Category2List) {
				JSONObject obj = new JSONObject();
				obj.put("name", c.getCategoryName());
				obj.put("no", c.getCategoryNo());
				arr.add(obj);
			}
		} else {
			JSONObject obj = new JSONObject();
			obj.put("name", "카테고리 소분류");
			obj.put("no", "");
			arr.add(obj);			
		} 
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(arr);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
