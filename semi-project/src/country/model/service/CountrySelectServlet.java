package country.model.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import category.model.service.CategoryService;
import category.model.vo.Category;
import country.model.vo.Country;

/**
 * Servlet implementation class CountrySelectServlet
 */
@WebServlet(name = "CountrySelect", urlPatterns = { "/countrySelect" })
public class CountrySelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountrySelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		JSONArray arr = new JSONArray();
		if(request.getParameter("countrySel1") != "" ) {
			int countrySel1 = Integer.parseInt(request.getParameter("countrySel1"));
			ArrayList <Country> Country2List = new CountryService().selectLevel2Country(countrySel1); 		

			for(Country c : Country2List) {
				JSONObject obj = new JSONObject();
				obj.put("name", c.getCountryName());
				obj.put("no", c.getCountryNo());
				arr.add(obj);
			}
		} else {
			JSONObject obj = new JSONObject();
			obj.put("name", "소분류");
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
