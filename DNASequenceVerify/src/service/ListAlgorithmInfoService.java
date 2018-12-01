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

import bean.Experiments;
import bean.LibsvmParameterValue;
import bean.User;
import util.DataBaseUtils;

public class ListAlgorithmInfoService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String algid=request.getParameter("algid");
		String sql="select DS_ParameterCommand.algid,algname,paraname,statement from DS_Algorithm natrual join DS_ParameterCommand where DS_ParameterCommand.algid='"+algid+"';";
		
		
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		
		rs=DataBaseUtils.queryForList(sql);
		
        Map<String,String> paratable=new HashMap<String,String>();
		
		for(Integer i=0;i<rs.size();i++) 
		{
			paratable.put(rs.get(i).get("paraname").toString(),rs.get(i).get("statement").toString());
			
		}
		
		//Remove the session information
		HttpSession session=request.getSession(true);
		LibsvmParameterValue parameterValue=(LibsvmParameterValue)session.getAttribute("parametervalue");
		if(parameterValue!=null)
		{
			session.removeAttribute("parametervalue");
		}
		
		Experiments exp=(Experiments)session.getAttribute("expbean");
		if(exp!=null)
		{
			session.removeAttribute("expbean");
		}
		
		Map<String,String> paratypetable=new HashMap<String,String>();
		paratypetable=(Map<String,String>)session.getAttribute("paratypetable");
		if(paratypetable!=null)
		{
			session.removeAttribute("paratypetable");
		}
		
		HttpSession hs=request.getSession();
		User user=(User) hs.getAttribute("userbean");
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
		request.setAttribute("paratable",paratable);
		request.setAttribute("algname", rs.get(0).get("algname").toString());
		request.getRequestDispatcher("/common/AlgorithmInfo.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
