package category.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import category.model.dao.CategoryDao;
import category.model.vo.Category;
import common.JDBCTemplate;

public class CategoryService {

	public ArrayList<Category> seleteAllCategory() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Category> categoryList = new CategoryDao().selectAllCategory(conn);
		JDBCTemplate.close(conn);
		return categoryList;
	}

	public ArrayList<Category> selectLevel2Cateory(int cateSel1) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Category> categoryList = new CategoryDao().selectAllCategory(conn, cateSel1);
		JDBCTemplate.close(conn);
		return categoryList;
	}

	public Category seleteOneCategory(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		Category category = new CategoryDao().selectOneCategory(conn, categoryNo);
		JDBCTemplate.close(conn);
		return category;
	}






}
