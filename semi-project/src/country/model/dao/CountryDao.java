package country.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import category.model.vo.Category;
import common.JDBCTemplate;
import country.model.vo.Country;

public class CountryDao {

	public ArrayList<Country> selectAllCountry(Connection conn) {
		PreparedStatement pstmt = null;
		ArrayList<Country> countryList = new ArrayList<Country>();
		ResultSet rset = null;
		Country country = null;
		String query = "select * from country";
		
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				country = new Country();
				country.setCountryNo(rset.getInt("country_no"));
				country.setCountryName(rset.getString("country_name"));
				country.setCountryLevel(rset.getInt("country_level"));
				country.setCountryRef(rset.getInt("country_ref"));
				countryList.add(country);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return countryList;

	}

	public ArrayList<Country> selectAllCountry(Connection conn, int countrySel1) {
		PreparedStatement pstmt = null;
		ArrayList<Country> countryList = new ArrayList<Country>();
		ResultSet rset = null;
		Country country = null;
		String query = "select * from country where country_ref=?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, countrySel1);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				country = new Country();
				country.setCountryNo(rset.getInt("country_no"));
				country.setCountryName(rset.getString("country_name"));
				country.setCountryLevel(rset.getInt("country_level"));
				country.setCountryRef(rset.getInt("country_ref"));
				countryList.add(country);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return countryList;
	}

	public Country selectOneCountry(Connection conn, int countryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Country country = new Country();
		String query = "select * from country where country_no=?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, countryNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				country.setCountryNo(rset.getInt("country_no"));
				country.setCountryName(rset.getString("country_name"));
				country.setCountryLevel(rset.getInt("country_level"));
				country.setCountryRef(rset.getInt("country_ref"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return country;
	}

}
