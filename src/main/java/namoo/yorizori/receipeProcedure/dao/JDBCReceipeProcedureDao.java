package namoo.yorizori.receipeProcedure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import namoo.yorizori.common.factory.ServiceFactory;
import namoo.yorizori.common.factory.ServiceFactoryImpl;
import namoo.yorizori.common.web.Params;
import namoo.yorizori.cookbook.service.CookbookService;
import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

/**
 * UserDao 구현체(JDBC API 활용)
 * 
 * @author Lee KyuHeon
 *
 */
public class JDBCReceipeProcedureDao implements ReceipeProcedureDao {

	private DataSource dataSource;

	// 생성자
	public JDBCReceipeProcedureDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 레시피 상세 정보를 생성하기 위한 메서드
	 */
	@Override
	public void create(ReciepeProcedure ReciepeProcedure) throws RuntimeException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO recipe_procedure(RECIPE_ID, SEQ_NUM, PROCEDURE) ")
				.append("   VALUES ( (SELECT recipe_id FROM recipe WHERE recipe_id = ?) ,?,?) ");

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sb.toString());

			String[] procedures = ReciepeProcedure.getProcedure();

			for (int SeqNum = 1; SeqNum <= ReciepeProcedure.getSeqNum(); SeqNum++) {
				pstmt.setInt(1, ReciepeProcedure.getReceipeId());
				pstmt.setInt(2, SeqNum);
				pstmt.setString(3, procedures[SeqNum-1]);	
				pstmt.addBatch();
			}

			// Query Update
			pstmt.executeBatch();
			 conn.commit();
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
	 * recipe와 recipeProcedure 테이블을 조인해서, detail정보를 보여주기 위한 메서드
	 */
	@Override
	public List<Map<String, Object>> findRecipeContents(int id) throws RuntimeException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT r.RECIPE_ID, r.recipe_name, r.recipe_time, r.recipe_level, r.writer_id,r.INGREDIENTS, rp.SEQ_NUM,rp.PROCEDURE,r.img_cont_type, r.img_file_name")
				.append(" FROM recipe r")
				.append(" JOIN recipe_procedure rp on r.RECIPE_ID = rp.RECIPE_ID")
				.append(" WHERE r.book_id = ?");
				
				
				try {
					conn = dataSource.getConnection();
					pstmt = conn.prepareStatement(sb.toString());
					pstmt.setInt(1, id);
					result = pstmt.executeQuery();
					while (result.next()) {
						Map<String, Object> row = new HashMap<String, Object>();
						row.put("recipeId", result.getInt("RECIPE_ID"));
						row.put("recipeName", result.getString("recipe_name"));
						row.put("ingredients", result.getString("INGREDIENTS"));
						row.put("writerId", result.getString("writer_id"));
						row.put("recipeTime", result.getInt("recipe_time"));
						row.put("recipeLevel", result.getInt("recipe_level"));
						row.put("seqNum", result.getInt("SEQ_NUM"));
						row.put("procedure", result.getString("PROCEDURE"));
						row.put("imgFileName", result.getString("img_file_name"));

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
