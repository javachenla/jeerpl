package cn.jbolt.activelist;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.ServActivelist;
 
public class ActivelistService {
 
	private ServActivelist dao = new ServActivelist().dao();
	
	public ServActivelist findById(String id) {
		return dao.findById(id);
	}
	
	public boolean deleteById(String id) {
		return dao.deleteById(id);
	}
	
	public ServActivelist detail(String id){
		ServActivelist servActivelist=dao.findById(id);
		return servActivelist;
	}
	 
	public  Page<ServActivelist> paginate(int pageNumber, int pageSize){
		return dao.paginate(pageNumber, pageSize, "select *", "from serv_activelist");
	}
   
}
