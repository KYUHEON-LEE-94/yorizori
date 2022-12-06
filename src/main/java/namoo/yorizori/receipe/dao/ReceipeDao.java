package namoo.yorizori.receipe.dao;

import java.util.List;
import java.util.Map;

import namoo.yorizori.common.web.Params;
import namoo.yorizori.receipe.dto.Reciepe;

/**
 * 회원관련 데이터 처리 추상 메소드 선언
 * 
 * @author LEE
 */
public interface ReceipeDao {
	public void create(Reciepe Reciepe) throws RuntimeException;
	public List<Map<String, Object>> findAllRecipe(Params params, int id) throws RuntimeException;
	public int reciepCount(int id) throws RuntimeException;
}
