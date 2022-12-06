package namoo.yorizori.cookbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import namoo.yorizori.common.web.Params;
import namoo.yorizori.cookbook.dto.Cookbook;

public class JDBCCookbookDao implements CookbookDao {

	private DataSource dataSource;

	// 생성자
	public JDBCCookbookDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * cookbook생성 메서드
	 */
	@Override
	public void create(Cookbook cookbook) throws RuntimeException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO cookbook (book_id, book_name, book_desc, author_id)")
				.append(" VALUES(cookbook_seq.nextval,?,?,?)");

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cookbook.getBookName());
			pstmt.setString(2, cookbook.getBookDesc());
			pstmt.setString(3, cookbook.getAuthorId());

			// Query Update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// SQL Exception을 RuntimeException으로 변환
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//Cookbook에 등록된 리스트들의 개수를 파악하기 위한 메서드
	@Override
	public int countBySearchOption(String type, String value) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt").append(" FROM cookbook");
		
		switch (type) {
		case "id": sb.append(" WHERE  id = ?"); break;
		case "name": sb.append(" WHERE name LIKE ?"); break;		
		}

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			if(!(type.equals(""))) {			
				pstmt.setString(1, value);			  
			}
			result = pstmt.executeQuery();
			while (result.next()) {
				count = result.getInt("cnt");
				
			}

		} catch (SQLException e) {
			//SQL Exception을 RuntimeException으로 변환
			throw new RuntimeException(e.getMessage());
		} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (result != null) result.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return count;
	}
	
	/**
	 * paging 처리가 가능한 리스트 함수
	 */
	public List<Map<String, Object>> findAllBySearchOption(Params params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT book_id, book_name, book_desc, author_id, u.name authorName")
		  .append(" FROM ( SELECT CEIL(rownum / ?) request_page,  book_id, book_name, book_desc, author_id")
		  .append("       FROM   ( SELECT  book_id, book_name, book_desc, author_id")
		  .append("                 FROM cookbook) )c")
		  .append(" JOIN users u on c.author_id = u.id")
		  .append("	WHERE  request_page = ?");
		
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			//페이지 사이즈는 default로 10개 무조건!
			pstmt.setInt(1, params.getPageSize());
			
			//searchType이 없다 = 전체검색이다.
				//WHERE절이 없다.
					//2번째 ?  .append("	WHERE  request_page = ?");에 요청하는 페이지 번째를 넣어줌
			if(params.getSearchType().equals("")) {
				pstmt.setInt(2, params.getRequestPage());
			}else {
				//검색하는 getSearchType()이 있다는건 검색원하는 타입을 입력받는다: 2번째 ?에 입력
					//3번쩨 ?에 입력하는 건 요청하는 페이지번째
				pstmt.setString(2, params.getSearchValue());
				pstmt.setInt(3, params.getRequestPage());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("bookId", rs.getInt("book_id"));
				row.put("bookName", rs.getString("book_name"));
				row.put("bookDesc", rs.getString("book_desc"));
				row.put("authorId", rs.getString("author_id"));
				row.put("authorName", rs.getString("authorName"));
				
				list.add(row);
			}
		} catch (SQLException e) {
			// SQLException을 RuntimeException으로 변환
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (rs != null)    rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null)   con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}



	//모든 쿡북리스트 조회 메서드
	@Override
	public List<Map<String, Object>> finAllCookbooks() throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				StringBuilder sb = new StringBuilder();
				sb.append(" SELECT c.book_id bookId, c.book_name bookName, c.book_desc bookDesc, c.author_id authorId, u.name authorName")
				.append(" FROM cookbook c")
				.append(" JOIN users u on c.author_id = u.id");
				
				
				try {
					conn = dataSource.getConnection();
					pstmt = conn.prepareStatement(sb.toString());
					result = pstmt.executeQuery();
					while (result.next()) {
						Map<String, Object> row = new HashMap<String, Object>();
						row.put("bookId", result.getInt("bookId"));
						row.put("bookName", result.getString("bookName"));
						row.put("bookDesc", result.getString("bookDesc"));
						row.put("authorId", result.getString("authorId"));
						row.put("authorName", result.getString("authorName"));
						
						list.add(row);
					}

				} catch (SQLException e) {
					//SQL Exception을 RuntimeException으로 변환
					throw new RuntimeException(e.getMessage());
				} finally {
						try {
							if (pstmt != null) pstmt.close();
							if (result != null) result.close();
							if (conn != null) conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				
				return list;
				
	}
	
	
	/***
	 * Main화면에서 등록된 날짜순으로 8개까지만 보여주기 위한 List 메서드
	 */
	@Override
	public List<Map<String, Object>> MainIndexList () throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				StringBuilder sb = new StringBuilder();
				sb.append(" SELECT id, book_id, recipe_name, recipe_time, recipe_level, book_desc, img_file_name, regdate")
				.append(" FROM ( SELECT CEIL(rownum / 8), s.id, s.book_id, s.recipe_name, s.recipe_time, s.recipe_level, s.book_desc, s.img_file_name, s.regdate")
					.append(" FROM   (SELECT u.id, c.book_id, r.recipe_name, r.recipe_time, r.recipe_level, c.book_desc, r.img_file_name, u.regdate")
						.append(" FROM cookbook c")
						.append(" JOIN recipe r ON c.book_id = r.book_id")
						.append(" JOIN users u ON r.writer_id = u.id)s ")
					.append(" ORDER BY regdate)");


				
				try {
					conn = dataSource.getConnection();
					pstmt = conn.prepareStatement(sb.toString());
					result = pstmt.executeQuery();
					while (result.next()) {
						Map<String, Object> row = new HashMap<String, Object>();
						row.put("id", result.getString("id"));
						row.put("bookId", result.getInt("book_id"));
						row.put("recipeName", result.getString("recipe_name"));
						row.put("recipeTime", result.getInt("recipe_time"));
						row.put("recipeLevel", result.getInt("recipe_level"));
						row.put("bookDesc", result.getString("book_desc"));
						row.put("imgFileName", result.getString("img_file_name"));
						row.put("regdate", result.getString("regdate"));
						
						list.add(row);
					}

				} catch (SQLException e) {
					//SQL Exception을 RuntimeException으로 변환
					throw new RuntimeException(e.getMessage());
				} finally {
						try {
							if (pstmt != null) pstmt.close();
							if (result != null) result.close();
							if (conn != null) conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				
				return list;
				
	}
	

}
