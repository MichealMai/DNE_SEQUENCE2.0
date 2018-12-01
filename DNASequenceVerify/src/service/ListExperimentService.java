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

public class ListExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userbean");

		String admin=user.getIsAdmin();
		String userid=user.getUserid();
		String sql;
		List<Map<String,Object>> expList=new ArrayList<Map<String,Object>>();
		if(admin.equals("no")) 
		{
			sql="select expname,expid,fn,fp,phase from DS_Experiments where userid='"+userid+"';";
			expList=DataBaseUtils.queryForList(sql);
		}
		
		else
		{
			sql="select expname,expid,fn,fp,phase from DS_Experiments;";
			expList=DataBaseUtils.queryForList(sql);
		}
		
		Map<String,String> expMap = new HashMap<String,String>();
		Map<String,String> expFPMap = new HashMap<String,String>();
		Map<String,String> expFNMap = new HashMap<String,String>();
		Map<String,String> expPhaseMap = new HashMap<String,String>();
		String expName,expid,fn,fp,phase;
		for(Integer i=0;i<expList.size();i++) 
		{
			phase=expList.get(i).get("phase").toString();
			fn=expList.get(i).get("fn").toString();
			fp=expList.get(i).get("fp").toString();
			expName=expList.get(i).get("expname").toString();
			expid=expList.get(i).get("expid").toString();
			expMap.put(expName, expid);
			expPhaseMap.put(expid,phase);
		}

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
		
		request.setAttribute("expNameMap", expMap);
		request.setAttribute("expPhaseMap", expPhaseMap);
		} catch(Exception e) 
		{
			e.getStackTrace();
		}

		request.getRequestDispatcher("/common/experimentlist.jsp").forward(request, response);
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
