package moim.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.model.service.CategoryService;
import category.model.vo.Category;
import country.model.service.CountryService;
import country.model.vo.Country;

/**
 * Servlet implementation class MoimWriteFrmServlet
 */
@WebServlet(name = "MoimWriteFrm", urlPatterns = { "/moimWriteFrm" })
public class MoimWriteFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoimWriteFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category> categoryList = new CategoryService().seleteAllCategory();
		ArrayList<Country> countryList = new CountryService().selectAllCountry();
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("countryList", countryList);
		request.getRequestDispatcher("/WEB-INF/views/moim/moimWriteFrm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
