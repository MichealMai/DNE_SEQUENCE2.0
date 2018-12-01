package InitialConfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.LogUtils;


/*
 * Initial system parameter
 */
public class InitialServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	@Override
	public void init() {
		try {
			LogUtils.info("毛主席万岁，共产主义万岁， 永无   bug： 初始化中：(((o(*ﾟ▽ﾟ*)o)))....");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LogUtils.info("Initial has been done.");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		doGet(request,response);
	}

}
