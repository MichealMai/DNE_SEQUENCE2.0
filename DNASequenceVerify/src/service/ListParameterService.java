package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DataBaseUtils;

public class ListParameterService extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException,IOException
	{
		Integer i;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userbean");
		
		String Expname=request.getParameter("expid");
		String userid=user.getUserid();
		String sqlA="select paraname,paracommand,paratype from DS_ParameterCommand natrual join (select * from DS_Experiments natural join DS_user where username='"+userid+"') as alg;";
		
		List<Map<String,Object>> parameter=new ArrayList<Map<String,Object>>();
		
		String sqlB="select paraname,paracommand,paratype from DS_ParameterCommand natrual join (select * from DS_Experiments natural join DS_user where username='"+userid+"') as alg;";
		parameter=DataBaseUtils.queryForList(sqlB);
		
		//get algorithm parameter table name
		List<Map<String,Object>> tablename=new ArrayList<Map<String,Object>>();
		Map<Integer,Object> parameterNameMap=new Hashtable<Integer,Object>();
		String sqlC="select algparatable from DS_alg natrual join (DS_user natrual join DS_Experiments) where username='"+userid+"'";
		tablename=DataBaseUtils.queryForList(sqlC);
		
		//get experiment value
		String sqlD="select ";
		for(i=0;i<parameter.size()-1;i++) 
		{
			sqlD=sqlD+parameter.get(i).get("paraname")+",";
			parameterNameMap.put(i, parameter.get(i).get("paraname"));
		}
		sqlD=sqlD+parameter.get(parameter.size()-1).get("paraname");
		parameterNameMap.put(parameter.size()-1, parameter.get(parameter.size()-1).get("paraname"));
		
		sqlD=sqlD+" from DNA_Sequence."+tablename.get(0).get("algparatable")+" natrual join (select * from DS_Experiments natural join DS_user where username='"+userid+"' ) as alg;";
		
		List<Map<String,Object>> pv=new ArrayList<Map<String,Object>>();
		Map<String,Object> parameterValueMap=new Hashtable<String,Object>();
		pv=DataBaseUtils.queryForList(sqlD);
		
		for(i=0;i<pv.size();i++) 
		{
			parameterValueMap.put(parameter.get(i).get("paraname").toString(), pv.get(i).get(parameter.get(i).get("paraname").toString()));
		}
		
		request.setAttribute("parameterNameMap", parameterNameMap);
		request.setAttribute("fparameterValueMap", parameterValueMap);
		
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
	       throws ServletException, IOException
	{
		doGet(request,response);
	}

}
