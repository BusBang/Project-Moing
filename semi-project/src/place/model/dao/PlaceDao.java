package place.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import place.model.vo.Country;
import place.model.vo.Place;
import place.model.vo.PlaceKind;

public class PlaceDao {
	
	//지역 정보 전체를 불러옴
	public ArrayList<Country> selectCountry(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Country> list = new ArrayList<Country>();
		
		String query = "select * from country";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Country c  = new Country();
				c.setCountryNo(rset.getInt("country_no"));
				c.setCountryName(rset.getString("country_name"));
				c.setCountryLevel(rset.getInt("country_level"));
				c.setCountryRef(rset.getInt("country_ref"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	
	
	//장소형식 정보 전체를 불러옴
	public ArrayList<PlaceKind> selectPlaceKind(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PlaceKind> list = new ArrayList<PlaceKind>();
		
		String query = "select * from place_kind";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PlaceKind pk  = new PlaceKind();
				pk.setPlaceKindName(rset.getString("place_kind_name"));
				list.add(pk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	//장소글 등록 시 2차지역명에 대한 no(외래키)로 등록하기 위해 Name에 대한 No를 구해오는 작업
	public int selectCountryNo(Connection conn, String countryName) { 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select country_no from country where country_name=?";
		int countryNo = 0;
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setString(1, countryName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				countryNo = rset.getInt("country_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return countryNo;
	}


	//장소글을 등록하는 작업
	public int insertPlace(Connection conn, Place p) {
		PreparedStatement pstmt = null;
		String query = "insert into place values(seq_place.nextval, ?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, p.getPlaceTitle());
				pstmt.setString(index++, p.getPlaceContent());
				pstmt.setString(index++, p.getPlaceAddr());
				pstmt.setDate(index++, p.getPlaceStartDate());
				pstmt.setDate(index++, p.getPlaceEndDate());
				pstmt.setInt(index++, p.getPlaceDeposit());
				pstmt.setString(index++, p.getPlaceAccountNumber());
				pstmt.setString(index++, p.getPlacefilename());
				pstmt.setString(index++, p.getPlacefilepath());
				pstmt.setString(index++, p.getMemberId());
				pstmt.setInt(index++, p.getCountryNo());
				pstmt.setString(index++, p.getPlaceKindName());
				pstmt.setString(index++, p.getCheckOption());
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	//장소글 등록 시 1차 지역명을 선택하면 2차지역명이 필요하기 때문에 값을 구해오는 작업
	public ArrayList<String> selectCountryNames(Connection conn, String countryName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> names = new ArrayList<String>();
		// 지역명 from 지역테이블 where 참조번호 = 첫번째 지역명의 번호
		String query = "select country_name from country where country_ref=(select country_no from country where country_name=?)";
		
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setString(1, countryName);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				names.add(rset.getString("country_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return names;
	}



	//처음 장소 찾기 글 선택 시 게시물의 총 갯수를 구해오는 작업(페이징 처리 위함)
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from place";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	//처음 장소 찾기 눌렀을 시 전체 게시물 수를 페이징에 맞춰 구해오기
	public ArrayList<Place> selectListPlace(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Place> list = new ArrayList<Place>();
		
		String query = "select * from (select ROWNUM as rnum, n.* from (select * from place order by place_no desc )n)where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Place p = new Place();
				p.setPlaceNo(rset.getInt("place_no"));
				p.setPlaceTitle(rset.getString("place_title"));
				p.setPlaceContent(rset.getString("place_content"));
				p.setPlaceAddr(rset.getString("place_addr"));
				p.setPlaceStartDate(rset.getDate("place_start_date"));
				p.setPlaceEndDate(rset.getDate("place_end_date"));
				p.setPlaceDeposit(rset.getInt("place_deposit"));
				p.setPlaceAccountNumber(rset.getString("place_account_number"));
				p.setPlacefilename(rset.getString("place_filename"));
				p.setPlacefilepath(rset.getString("place_filepath"));
				p.setMemberId(rset.getString("member_id"));
				p.setCountryNo(rset.getInt("country_no"));
				p.setPlaceKindName(rset.getString("place_kind_name"));
				p.setCheckOption(rset.getString("check_option"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}


	//검색 필터 적용 후  검색 조건에 맞는 게시물 갯수 구해오기
	public int searchTotalCount(Connection conn, String keyword, String check, String sel2, String placeKind,
			String useDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		String query = "select count(*) as cnt from place where place_title like ?";
		int result = 0;
				
		if(!sel2.equals("")) { //지역명을 선택한 경우에만
			query += " and country_no=(select country_no from country where country_name=?)";
		}
		
		query += " and check_option like '";
		
		check = check.replaceAll("0", "_"); //미체크 옵션 _로 바꾸기
		query +=check;
		
		query += "' and place_kind_name like ?"; // like 구절 끝 맺음 + 장소형식 조건
		
		java.sql.Date useDateSql = null;
		
		if(!useDate.equals("")) { //이용희망날짜를 선택했을 때
			useDateSql = java.sql.Date.valueOf(useDate); //이용희망날짜 String -> java.sql.Date 형변환
			query += " and ? between place_start_date and place_end_date"; //쿼리문 where 절에 구문 추가
		}
		
		
		try {
			pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, "%" + keyword + "%");
				if(!sel2.equals("")) { //지역명을 선택한 경우
					pstmt.setString(index++, sel2);
					pstmt.setString(index++, "%" + placeKind + "%");
				}else {
					pstmt.setString(index++, "%" + placeKind + "%");
				}
				
				if(!useDate.equals("")) { //이용희망날짜를 선택했을 때
					pstmt.setDate(index++, useDateSql);
				}
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	//검색 필터 적용 후 검색 조건에 맞는 게시물의 리스트 구해오기
	public ArrayList<Place> searchSelectListPlace(Connection conn, int start, int end, String keyword, String check,
			String sel2, String placeKind, String useDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Place> list = new ArrayList<Place>();
		
		String query = "select * from (select ROWNUM as rnum, n.* from (select * from place where place_title like ?";
		
		if(!sel2.equals("")) { //지역명을 선택한 경우에만
			query += " and country_no=(select country_no from country where country_name=?)";
		}
		
		query += " and check_option like '";
		
		check = check.replaceAll("0", "_"); //미체크 옵션 _로 바꾸기
		query +=check;
		
		query += "' and place_kind_name like ?"; // like 구절 끝 맺음 + 장소형식 조건
		
		java.sql.Date useDateSql = null;
		
		if(!useDate.equals("")) { //이용희망날짜를 선택했을 때
			useDateSql = java.sql.Date.valueOf(useDate); //이용희망날짜 String -> java.sql.Date 형변환
			query += " and ? between place_start_date and place_end_date"; //쿼리문 where 절에 구문 추가
		}
		
		query += " order by place_no desc )n)where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, "%" + keyword + "%");
				if(!sel2.equals("")) { //지역명을 선택한 경우
					pstmt.setString(index++, sel2);
					pstmt.setString(index++, "%" + placeKind + "%");
				}else {
					pstmt.setString(index++, "%" + placeKind + "%");
				}
				
				if(!useDate.equals("")) { //이용희망날짜를 선택했을 때
					pstmt.setDate(index++, useDateSql);
				}
				pstmt.setInt(index++, start);
				pstmt.setInt(index++, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Place p = new Place();
				p.setPlaceNo(rset.getInt("place_no"));
				p.setPlaceTitle(rset.getString("place_title"));
				p.setPlaceContent(rset.getString("place_content"));
				p.setPlaceAddr(rset.getString("place_addr"));
				p.setPlaceStartDate(rset.getDate("place_start_date"));
				p.setPlaceEndDate(rset.getDate("place_end_date"));
				p.setPlaceDeposit(rset.getInt("place_deposit"));
				p.setPlaceAccountNumber(rset.getString("place_account_number"));
				p.setPlacefilename(rset.getString("place_filename"));
				p.setPlacefilepath(rset.getString("place_filepath"));
				p.setMemberId(rset.getString("member_id"));
				p.setCountryNo(rset.getInt("country_no"));
				p.setPlaceKindName(rset.getString("place_kind_name"));
				p.setCheckOption(rset.getString("check_option"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}



	public Place selectOnePlace(Connection conn, int placeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from place where place_no=?";
		Place p = null;
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, placeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Place();
				p.setPlaceNo(rset.getInt("place_no"));
				p.setPlaceTitle(rset.getString("place_title"));
				p.setPlaceContent(rset.getString("place_content"));
				p.setPlaceAddr(rset.getString("place_addr"));
				p.setPlaceStartDate(rset.getDate("place_start_date"));
				p.setPlaceEndDate(rset.getDate("place_end_date"));
				p.setPlaceDeposit(rset.getInt("place_deposit"));
				p.setPlaceAccountNumber(rset.getString("place_account_number"));
				p.setPlacefilename(rset.getString("place_filename"));
				p.setPlacefilepath(rset.getString("place_filepath"));
				p.setMemberId(rset.getString("member_id"));
				p.setCountryNo(rset.getInt("country_no"));
				p.setPlaceKindName(rset.getString("place_kind_name"));
				p.setCheckOption(rset.getString("check_option"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return p;
	}



	public int updatePlace(Connection conn, Place p) {
		PreparedStatement pstmt = null;
		String query = "update place set place_title=?, place_content=?, place_addr=?,"
				+ " place_start_date=?, place_end_date=?, place_deposit=?,"
				+ " place_account_number=?, place_filename=?, place_filepath=?,"
				+ " country_no=?, place_kind_name=?, check_option=? where place_no=?";
		int result = 0;
		
		try {
			pstmt =conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, p.getPlaceTitle());
				pstmt.setString(index++, p.getPlaceContent());
				pstmt.setString(index++, p.getPlaceAddr());
				pstmt.setDate(index++, p.getPlaceStartDate());
				pstmt.setDate(index++, p.getPlaceEndDate());
				pstmt.setInt(index++, p.getPlaceDeposit());
				pstmt.setString(index++, p.getPlaceAccountNumber());
				pstmt.setString(index++, p.getPlacefilename());
				pstmt.setString(index++, p.getPlacefilepath());
				pstmt.setInt(index++, p.getCountryNo());
				pstmt.setString(index++, p.getPlaceKindName());
				pstmt.setString(index++, p.getCheckOption());
				pstmt.setInt(index++, p.getPlaceNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	public int deletePlace(Connection conn, int placeNo) {
		PreparedStatement pstmt = null;
		String query = "delete from place where place_no=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, placeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
