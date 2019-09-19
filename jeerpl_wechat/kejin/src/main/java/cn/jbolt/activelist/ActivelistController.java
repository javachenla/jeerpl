package cn.jbolt.activelist;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.ServActivelist;
 
public class ActivelistController extends Controller {
	 
	
	@Inject
	ActivelistService activelistService;
	
	public void findex() {
		render("/webpage/index.html");
	}
	 
	
	public void paginate(){
		int pageNumber= getParaToInt("pageNumber");
		int pageSize=getParaToInt("pageSize");
		Page<ServActivelist> servActivelist=activelistService.paginate(pageNumber,pageSize);
		renderJson(servActivelist); 
	}
	
	public void detail(){
		ServActivelist servActivelist=activelistService.findById(getPara("id"));
		renderJson(servActivelist); 
	}
 
   
}