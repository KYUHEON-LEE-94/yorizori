package namoo.yorizori.receipeProcedure.dao;

import java.util.List;
import java.util.Map;

import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

/**
 * 회원관련 데이터 처리 추상 메소드 선언
 * 
 * @author LEE
 */
public interface ReceipeProcedureDao {
	public void create(ReciepeProcedure ReciepeProcedure) throws RuntimeException;
	public List<Map<String, Object>> findRecipeContents(int RECIPE_ID) throws RuntimeException;

}
