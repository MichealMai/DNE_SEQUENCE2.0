package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataBaseUtils;

public class ListUserService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String sql="select userid, username from DS_user";
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		
		rs=DataBaseUtils.queryForList(sql);
		
		Map<String,String> usertable=new HashMap<String,String>();
		
		for(Integer i=0;i<rs.size();i++) 
		{
			usertable.put(rs.get(i).get("username").toString(),rs.get(i).get("userid").toString());
			
		}
		
		request.setAttribute("usertable",usertable);
		request.getRequestDispatcher("/Administrator/AdministerIndex.jsp").forward(request, response);
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
