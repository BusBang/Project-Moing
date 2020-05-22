package category.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import category.model.vo.Category;
import common.JDBCTemplate;

public class CategoryDao {

	public ArrayList<Category> selectAllCategory(Connection conn) {
		PreparedStatement pstmt = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ResultSet rset = null;
		Category category = null;
		String query = "select * from category";
		
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				category = new Category();
				category.setCategoryNo(rset.getInt("category_no"));
				category.setCategoryName(rset.getString("category_name"));
				category.setCategoryLevel(rset.getInt("category_level"));
				category.setCategoryRef(rset.getInt("category_ref"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return categoryList;
	}

	public ArrayList<Category> selectAllCategory(Connection conn, int cateSel1) {
		PreparedStatement pstmt = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ResultSet rset = null;
		Category category = null;
		String query = "select * from category where category_ref=?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, cateSel1);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				category = new Category();
				category.setCategoryNo(rset.getInt("category_no"));
				category.setCategoryName(rset.getString("category_name"));
				category.setCategoryLevel(rset.getInt("category_level"));
				category.setCategoryRef(rset.getInt("category_ref"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return categoryList;
	}

	public Category selectOneCategory(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Category category = new Category();
		String query = "select * from category where category_no = ?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				category.setCategoryNo(rset.getInt("category_no"));
				category.setCategoryName(rset.getString("category_name"));
				category.setCategoryLevel(rset.getInt("category_level"));
				category.setCategoryRef(rset.getInt("category_ref"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return category;
	}



}
