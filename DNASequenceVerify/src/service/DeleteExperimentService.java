package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Experiments;
import util.DataBaseUtils;

public class DeleteExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String expid=request.getParameter("expid");
		//get experiment bean
		Experiments exp=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+expid+"';", Experiments.class);
		
		if(exp.getAlgid().equals("8061653e-aa94-42dd-bd43-de939bd6e1d8"))
		{
			DataBaseUtils.update("delete from DS_LibsvmParameterValue where expid='"+expid+"';");
		}
		DataBaseUtils.update("delete from DS_Experiments where expid='"+expid+"';");
		
		request.getRequestDispatcher("/servlet/ListExperimentService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
