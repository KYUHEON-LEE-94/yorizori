package namoo.yorizori.common.factory;

import namoo.yorizori.cookbook.service.CookbookService;
import namoo.yorizori.user.service.UserService;

public interface ServiceFactory {
	public UserService getUserService();
	public CookbookService getCookbookService();
	public CookbookService getReceipeService();
	public CookbookService getReceipeProcedureService();
}
