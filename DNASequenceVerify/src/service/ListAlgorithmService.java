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
import javax.servlet.http.HttpSession;

import bean.User;
import util.DataBaseUtils;

public class ListAlgorithmService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String sql="select * from DS_Algorithm";
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		
		rs=DataBaseUtils.queryForList(sql);
		
		Map<String,String> algtable=new HashMap<String,String>();
		
		for(Integer i=0;i<rs.size();i++) 
		{
			algtable.put(rs.get(i).get("algname").toString(),rs.get(i).get("algid").toString());
			
		}
		
		HttpSession hs=request.getSession();
		User user=(User)hs.getAttribute("userbean");
		String link;
		if(user.getIsAdmin().equals("yes"))
		{
			link="<a href=\"/DNASequenceVerify/servlet/ListUserService\">User list</a>";
		}
		else
		{
			link="";
		}
		
		request.setAttribute("link",link);
		request.setAttribute("algtable",algtable);
		//System.out.println(usertable.get("admin"));
		request.getRequestDispatcher("/common/Algorithm.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
