package cn.jbolt.index;
import com.jfinal.core.Controller;
/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn
 * @email 909854136@qq.com
 * @date 2018年11月4日 下午9:02:52
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 */
	public void index() {
		render("index.html");
	}
}