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

public class ListExperimentCreatedForm extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
//		List<Map<String,Object>> rsA=new ArrayList<Map<String,Object>>();
//		List<Map<String,Object>> rsB=new ArrayList<Map<String,Object>>();
//		Map<String,String> filetable=new HashMap<String,String>();
//		Map<String,String> algtable=new HashMap<String,String>();
		
		//get userbean
//		HttpSession hs=request.getSession();
//		User user=(User)hs.getAttribute("userbean");
		
//		rsA=DataBaseUtils.queryForList("select * from DS_Algorithm");
//		rsB=DataBaseUtils.queryForList("select * from DS_Files where userid='"+user.getUserid()+"';");
//		
//		for(Integer i=0;i<rsA.size();i++)
//		{
//			algtable.put(rsA.get(i).get("algname").toString(), rsA.get(i).get("algid").toString());
//		}
//		
//		for(Integer i=0;i<rsB.size();i++)
//		{
//			filetable.put(rsB.get(i).get("filename").toString(), rsB.get(i).get("fileid").toString());
//		}
		
//		request.setAttribute("algtable", algtable);
//		request.setAttribute("filetable", filetable);
		request.getRequestDispatcher("/common/ExperimentAdd.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		doGet(request,response);
	}
}
