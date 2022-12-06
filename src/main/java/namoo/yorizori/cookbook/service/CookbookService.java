package namoo.yorizori.cookbook.service;

import java.util.List;
import java.util.Map;

import namoo.yorizori.common.web.Params;
import namoo.yorizori.cookbook.dto.Cookbook;
import namoo.yorizori.receipe.dto.Reciepe;
import namoo.yorizori.receipeProcedure.dto.ReciepeProcedure;

public interface CookbookService {
	public void registerCookbook(Cookbook cookBoock);
	public List<Map<String, Object>> finAllCookbooksWithName();
	public int searchCount(String type, String value);
	public List<Map<String, Object>> findAllBySearchOption(Params params);
	public List<Map<String, Object>> MainIndexList ();
	
	//recipe
	public void registerRecipe(Reciepe Reciepe);
	public List<Map<String, Object>> findAllRecipe(Params params, int id);
	public int recipeCount(int id);
	
	//recipeProcedure
	public void registerRecipeProcedure(ReciepeProcedure ReciepeProcedure);
	public List<Map<String, Object>> findRecipeContents(int RECIPE_ID);
}
