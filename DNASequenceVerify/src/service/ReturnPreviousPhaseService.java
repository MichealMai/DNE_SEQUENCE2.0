package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Experiments;
import bean.User;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import util.DataBaseUtils;
import util.TableUtils;

public class ReturnPreviousPhaseService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		Experiments exp=(Experiments)session.getAttribute("expbean");
		List<String> columnA=new ArrayList<String>();
		List<String> contextA=new ArrayList<String>();
		String updateExp;
		Experiments newexpbean = null;
		if(exp.getPhase().equals("2")) 
		{
			//Delete file
			DataBaseUtils.update("delete from DS_Files where fileid='"+exp.getPhaseonefileid()+"';");
			DataBaseUtils.update("UPDATE DS_Experiments SET phase='1',phaseonefileid=NULL where expid='"+exp.getExpid()+"';");
			newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
			session.removeAttribute("expbean");
			session.setAttribute("expbean", newexpbean);
		}
		else if(exp.getPhase().equals("3")) 
		{
			//Get phase one file
			String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+exp.getExpid()+"';";
			List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
			rsF=DataBaseUtils.queryForList(sqlF);
			String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
			//send phase one result file
			Connection conn = new Connection("169.226.135.21");
			conn.connect();
			boolean isAuth = conn.authenticateWithPassword("debosmita", "DNAtest");
			if(!isAuth)
				try {
					throw new Exception("Authentication failed");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			SCPClient client = new SCPClient(conn);
			client.put("/var/lib/tomcat7/webapps/DNASequenceVerify/userfile/"+PhaseOneResultFileName, "/home/debosmita/DNAproject/project/silvercopy/data/input_data"); conn.close();
			//Delete phase 2 file
			DataBaseUtils.update("delete from DS_Files where fileid='"+exp.getPhasetwofileid()+"';");
			DataBaseUtils.update("UPDATE DS_Experiments SET phase='2',phasetwofileid=NULL where expid='"+exp.getExpid()+"';");
			newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
			session.removeAttribute("expbean");
			session.setAttribute("expbean", newexpbean);
		}
		else if(exp.getPhase().equals("4")) 
		{
			//Delete file
			DataBaseUtils.update("delete from DS_Files where fileid='"+exp.getPhasethreefileid()+"';");
			DataBaseUtils.update("UPDATE DS_Experiments SET phase='3',phasethreefileid=NULL where expid='"+exp.getExpid()+"';");
			newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
			session.removeAttribute("expbean");
			session.setAttribute("expbean", newexpbean);
		}
		else if(exp.getPhase().equals("5")) 
		{
			//Delete file
			DataBaseUtils.update("delete from DS_Files where fileid='"+exp.getPhasefourfileid()+"';");
			DataBaseUtils.update("UPDATE DS_Experiments SET phase='4',phasefourfileid=NULL where expid='"+exp.getExpid()+"';");
			newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
			session.removeAttribute("expbean");
			session.setAttribute("expbean", newexpbean);
		}
		request.getRequestDispatcher("/servlet/ListExperimentInfoService?expid="+newexpbean.getExpid()).forward(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
