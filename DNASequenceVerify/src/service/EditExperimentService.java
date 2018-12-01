package service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Experiments;
import bean.LibsvmParameterValue;
import util.DataBaseUtils;
import util.TableUtils;

public class EditExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		Experiments exp=(Experiments) session.getAttribute("expbean");
		Map<String,String> paratypetable=new HashMap<String,String>();
		List<String> column=new ArrayList<String>();
		List<String> context=new ArrayList<String>();
		paratypetable=(Map<String,String>)session.getAttribute("paratypetable");
		
		//check experiment name
		if(!request.getParameter("expname").equals(exp.getExpname())) 
		{
			
			String sqlA="update DS_Experiments set expname='"+request.getParameter("expname")+"' where expid='"+exp.getExpid()+"';";
			//System.out.println(sqlA);
			DataBaseUtils.update(sqlA);
		}
		//check inputfile
		if(!request.getParameter("fileid").equals(exp.getInputfileid())) 
		{
			String sqlB="update DS_Experiments set fileid='"+request.getParameter("fileid")+"' where expid='"+exp.getExpid()+"';";
			DataBaseUtils.update(sqlB);
		}
		
		//check parameter
		if(exp.getAlgid().equals("8061653e-aa94-42dd-bd43-de939bd6e1d8")) 
		{
			String beanvalue = null,beankey;
			LibsvmParameterValue parameterValue=(LibsvmParameterValue)session.getAttribute("parametervalue");
			
			Iterator iter=paratypetable.entrySet().iterator();
			while(iter.hasNext())
			{
				Map.Entry entry=(Map.Entry)iter.next();
				Object key=entry.getKey();

				beankey=(String)key;
				try {
					beanvalue=(String)parameterValue.get(beankey, parameterValue);
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!request.getParameter(beankey).equals(beanvalue))
				{
					column.add(beankey);
					context.add((String)request.getParameter(beankey));
				}
			}
			if(context.size()>0) 
			{
				String sql=TableUtils.getUpdateSQL("expid='"+exp.getExpid()+"';", context, column, LibsvmParameterValue.class);
				DataBaseUtils.update(sql);
			}
			
		}
		
		request.getRequestDispatcher("/servlet/ListExperimentService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
