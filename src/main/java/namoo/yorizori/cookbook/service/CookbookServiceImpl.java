package namoo.yorizori.cookbook.service;

import java.util.List;
/*
 * 요리책 관련 비즈니스 메소드 구현
 * 복잡한 비즈니스 로직의 경우 트랜잭션 관리
 * @author Lee
 */
import java.util.Map;

import namoo.yorizori.common.web.Params;
import namoo.yorizori.cookbook.dao.CookbookDao;
import namoo.yorizori.cookbook.dto.Cookbook;
import namoo.yorizori.receipe.dao.ReceipeDao;
import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dao.ReceipeProcedureDao;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

public class CookbookServiceImpl implements CookbookService {
	
	private CookbookDao Cookbookdao;
	private ReceipeDao ReceipeDao;
	private ReceipeProcedureDao ReceipeProcedureDao;

	public CookbookServiceImpl(CookbookDao Cookbookdao) {
		this.Cookbookdao = Cookbookdao;
	}
	public CookbookServiceImpl(ReceipeDao ReceipeDao) {
		this.ReceipeDao = ReceipeDao;
	}
	public CookbookServiceImpl(ReceipeProcedureDao ReceipeProcedureDao) {
		this.ReceipeProcedureDao = ReceipeProcedureDao;
	}

	@Override
	public void registerCookbook(Cookbook cookBoock) {
		Cookbookdao.create(cookBoock);
	}



	
	@Override
	public List<Map<String, Object>> findAllBySearchOption(Params params) {
		if(params.getSearchType().equalsIgnoreCase("name")) {
			params.setSearchValue("%" + params.getSearchValue() + "%");
		}
		return Cookbookdao.findAllBySearchOption(params);
	}

	@Override
	public int searchCount(String type, String value) {
		if(type.equalsIgnoreCase("name")) {
			value = "%"+value+"%";
		}
		return Cookbookdao.countBySearchOption(type, value);
	}


	@Override
	public List<Map<String, Object>> finAllCookbooksWithName() {
		return Cookbookdao.finAllCookbooks();
	}
	

	@Override
	public List<Map<String, Object>> MainIndexList() {
		return Cookbookdao.MainIndexList();
	}
	//-----------레시피 서비스-----------------

	@Override
	public void registerRecipe(Reciepe Reciepe) {
		ReceipeDao.create(Reciepe);
	
	}	
	
	@Override
	public List<Map<String, Object>> findAllRecipe(Params params, int id) {
		return ReceipeDao.findAllRecipe(params,id);
	}
	@Override
	public int recipeCount(int id) {
		return ReceipeDao.reciepCount(id);
	}
	//-------------레시피프로시저
	@Override
	public void registerRecipeProcedure( ReciepeProcedure ReciepeProcedure) {
		ReceipeProcedureDao.create(ReciepeProcedure);
	
	}
	@Override
	public List<Map<String, Object>> findRecipeContents(int id){
		return ReceipeProcedureDao.findRecipeContents(id);
		
	}
	
	
	

}
