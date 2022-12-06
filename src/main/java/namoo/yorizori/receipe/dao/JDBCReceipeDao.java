package namoo.yorizori.receipe.dao;

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
import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

/**
 * UserDao 구현체(JDBC API 활용)
 * 
 * @author Lee KyuHeon
 *
 */
public class JDBCReceipeDao implements ReceipeDao {

	private DataSource dataSource;

	// 생성자
	public JDBCReceipeDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * recipe생성 메서드
	 */
	@Override
	public void create(Reciepe Reciepe) throws RuntimeException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(
				" INSERT INTO recipe (recipe_id, book_id, recipe_name, recipe_time, recipe_level, ingredients, img_cont_type, img_file_name, writer_id) ")
				.append("   VALUES (?,?, ?, ?, ?, ?, ?, ?, ?) "); //9개

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, Reciepe.getReceipeId());
			pstmt.setInt(2, Reciepe.getBookId());
			pstmt.setString(3, Reciepe.getReceipeName());
			pstmt.setInt(4, Reciepe.getReceipeTime());
			pstmt.setInt(5, Reciepe.getReceipeLevel());
			pstmt.setString(6, Reciepe.getIngredients());
			pstmt.setString(7, Reciepe.getImgContType());
			pstmt.setString(8, Reciepe.getImgFileName());
			pstmt.setString(9, Reciepe.getWriterId());
			
			ReciepeProcedure receipeProcedure = new ReciepeProcedure();

			receipeProcedure.setReceipeId(Reciepe.getReceipeId());

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

	
	
	/**
	 * 등록된 레시피 총개수 파악을 위한 메서드 for paging처리를 위하여
	 */
	@Override
	public int reciepCount(int id) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt").append(" FROM recipe")
		.append(" WHERE book_id = ?");

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
		
				pstmt.setInt(1, id);			  
			
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
	 * paging처리가 가능하도록 param을 받고, 조회된 bookid를 추가로 받는 메서드
	 *	쿡북리스트에서 -> 레시피 목록으로 넘어갈때, 해당 bookid를 인자로 받아서 그 쿡북의 레시피 목록을 조회하기 위함
	 */
	@Override
	public List<Map<String, Object>> findAllRecipe(Params params, int id) throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT recipe_id, book_id, recipe_name, recipe_time, recipe_level, img_cont_type, img_file_name, writer_id")
		.append(" FROM ( SELECT CEIL(rownum / ?) request_page, recipe_id, book_id, recipe_name, recipe_time, recipe_level, img_cont_type, img_file_name, writer_id")
		.append(" FROM   ( SELECT recipe_id, book_id, recipe_name, recipe_time, recipe_level, img_cont_type, img_file_name, writer_id ")
		.append(" FROM recipe WHERE book_id = ?))")
		.append(" WHERE  request_page = ?");

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			//페이지 사이즈는 default로 10개 무조건!
			pstmt.setInt(1, params.getPageSize());
			pstmt.setInt(2, id);
			pstmt.setInt(3, params.getRequestPage());


			result = pstmt.executeQuery();
			while(result.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("recipeId", result.getInt("recipe_id"));
				row.put("bookId", result.getInt("book_id"));
				row.put("recipeName", result.getString("recipe_name"));
				row.put("recipeTime", result.getInt("recipe_time"));
				row.put("recipeLevel", result.getInt("recipe_level"));
				row.put("imgContType", result.getString("img_cont_type"));
				row.put("imgFileName", result.getString("img_file_name"));
				row.put("writerId", result.getString("writer_id"));
				
				list.add(row);
			}
		} catch (SQLException e) {
			// SQLException을 RuntimeException으로 변환
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (result != null)    result.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)   conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}



}
