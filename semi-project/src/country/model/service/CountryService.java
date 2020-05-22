package country.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import category.model.dao.CategoryDao;
import category.model.vo.Category;
import common.JDBCTemplate;
import country.model.dao.CountryDao;
import country.model.vo.Country;


public class CountryService {

	public ArrayList<Country> selectAllCountry() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Country> countryList = new CountryDao().selectAllCountry(conn);
		JDBCTemplate.close(conn);
		return countryList;
	}

	public ArrayList<Country> selectLevel2Country(int countrySel1) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Country> countryList = new CountryDao().selectAllCountry(conn, countrySel1);
		JDBCTemplate.close(conn);
		return countryList;
	}

	public Country selectOneCountry(int countryNo) {
		Connection conn = JDBCTemplate.getConnection();
		Country country = new CountryDao().selectOneCountry(conn, countryNo);
		JDBCTemplate.close(conn);
		return country;
	}



}
