package service;

import java.beans.IntrospectionException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import bean.Experiments;
import bean.LibsvmParameterValue;
import bean.User;
import util.DataBaseUtils;

public class ListExperimentInfoService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String expid=request.getParameter("expid");
		
		HttpSession hs=request.getSession();
		
		//get experimentbean
		Experiments expbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+expid+"';", Experiments.class);
		hs.setAttribute("expbean", expbean);
		//get algorithm name;
		
		String sqlA="select algname,DS_Experiments.algid from DS_Algorithm natrual join DS_Experiments where expid='"+expid+"';";
		List<Map<String,Object>> rsA=new ArrayList<Map<String,Object>>();
		rsA=DataBaseUtils.queryForList(sqlA);
		
		String algName=rsA.get(0).get("algname").toString();
		String algid=rsA.get(0).get("algid").toString();
		
		//get input file name
		String sqlB="select filename,DS_Files.fileid from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.inputfileid where expid='"+expid+"';";

		List<Map<String,Object>> rsB=new ArrayList<Map<String,Object>>();
		rsB=DataBaseUtils.queryForList(sqlB);
		String inputFileName=rsB.get(0).get("filename").toString();
		
		//get parameter name
		List<Map<String,Object>> parameter=new ArrayList<Map<String,Object>>();
		Map<String,String> parastatetable=new HashMap<String,String>();			//parameter name and statement
		Map<String,String> paratypetable=new HashMap<String,String>();
		String sqlC="select paraname,paracommand,paratype,statement from DS_ParameterCommand natrual join DS_Algorithm where DS_Algorithm.algid='"+algid+"';";
		parameter=DataBaseUtils.queryForList(sqlC);
		
		String sqlD;
		for(Integer i=0;i<parameter.size();i++) 
		{
			paratypetable.put(parameter.get(i).get("paraname").toString(),parameter.get(i).get("paratype").toString());
			parastatetable.put(parameter.get(i).get("paraname").toString(),parameter.get(i).get("statement").toString());
//			try {
//				paratypetable.put(parameter.get(i).get("paraname").toString(),parameter.get(i).get("paratype").toString());
//				parastatetable.put(parameter.get(i).get("paraname").toString(),parameter.get(i).get("statement").toString());
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		//paratypetable.put(parameter.get(parameter.size()-1).get("paraname").toString(),parameter.get(parameter.size()-1).get("paratype").toString());
		//parastatetable.put(parameter.get(parameter.size()-1).get("paraname").toString(),parameter.get(parameter.size()-1).get("statement").toString());
		
		if(algid.equals("8061653e-aa94-42dd-bd43-de939bd6e1d8")) 
		{
			sqlD="select * from DNA_Sequence.DS_LibsvmParameterValue where expid='"+expid+"';";
			LibsvmParameterValue parameterValue;
			parameterValue=DataBaseUtils.queryForBean(sqlD,LibsvmParameterValue.class);
			hs.setAttribute("parametervalue",parameterValue);
			hs.setAttribute("paratypetable",paratypetable);
		}
		
		//get all file which are owned by current user
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userbean");
		String sqlE="select * from DS_Files where userid='"+user.getUserid()+"';";
		List<Map<String,Object>> rsC=new ArrayList<Map<String,Object>>();
		Map<String,String> filemap=new HashMap<String,String>();
		rsC=DataBaseUtils.queryForList(sqlE);
		
		for(Integer i=0;i<rsC.size();i++) 
		{
			filemap.put(rsC.get(i).get("filename").toString(),rsC.get(i).get("fileid").toString());
		}
		
		
		String link;
		if(user.getIsAdmin().equals("yes"))
		{
			link="<a href=\"/DNASequenceVerify/servlet/ListUserService\">UserList</a>";
		}
		else
		{
			link="";
		}
		

		
		request.setAttribute("link",link);
		request.setAttribute("expbean", expbean);
//		request.setAttribute("filemap",filemap);
//		request.setAttribute("paratypetable",paratypetable);
//		request.setAttribute("parastatetable",parastatetable);
		request.setAttribute("expid", expid);
		request.setAttribute("inputfilename", inputFileName);
//		request.setAttribute("algname", algName);
		if(expbean.getPhase().equals("1")) 
		{

			request.getRequestDispatcher("/common/experimentInfo.jsp").forward(request, response);
		}
		if(expbean.getPhase().equals("2")) 
		{
			//Get phase one file
			String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+expid+"';";
			List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
			rsF=DataBaseUtils.queryForList(sqlF);
			String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
			request.setAttribute("phaseonefilename", PhaseOneResultFileName);
			request.getRequestDispatcher("/common/PhaseTwo.jsp").forward(request, response);
		}
		if(expbean.getPhase().equals("3")) 
		{
			//Get phase one file
			String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+expid+"';";
			List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
			rsF=DataBaseUtils.queryForList(sqlF);
			//String PhaseOneResultFileId=rsF.get(0).get("fileid").toString();
			String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
			//request.setAttribute("PhaseOneResultFileId", PhaseOneResultFileId);
			request.setAttribute("phaseonefilename", PhaseOneResultFileName);
			
			//Get phase two file
			String sqlG="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasetwofileid where expid='"+expid+"';";
			List<Map<String,Object>> rsG=new ArrayList<Map<String,Object>>();
			rsG=DataBaseUtils.queryForList(sqlG);
			//String PhaseTwoResultFileId=rsG.get(0).get("fileid").toString();
			String PhaseTwoResultFileName=rsG.get(0).get("filename").toString();
			//request.setAttribute("PhaseTwoResultFileId", PhaseTwoResultFileId);
			request.setAttribute("phasetwofilename", PhaseTwoResultFileName);
			request.getRequestDispatcher("/common/PhaseThree.jsp").forward(request, response);
		}
		if(expbean.getPhase().equals("4")) 
		{
			//Get Phase one file
			String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+expid+"';";
			List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
			rsF=DataBaseUtils.queryForList(sqlF);
			//String PhaseOneResultFileId=rsF.get(0).get("fileid").toString();
			String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
			//request.setAttribute("PhaseOneResultFileId", PhaseOneResultFileId);
			request.setAttribute("phaseonefilename", PhaseOneResultFileName);
			
			//Get Phase two file
			String sqlG="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasetwofileid where expid='"+expid+"';";
			List<Map<String,Object>> rsG=new ArrayList<Map<String,Object>>();
			rsG=DataBaseUtils.queryForList(sqlG);
			//String PhaseTwoResultFileId=rsG.get(0).get("fileid").toString();
			String PhaseTwoResultFileName=rsG.get(0).get("filename").toString();
			//request.setAttribute("PhaseTwoResultFileId", PhaseTwoResultFileId);
			request.setAttribute("phasetwofilename", PhaseTwoResultFileName);
			
//			//Get Phase three file
//			String sqlH="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasethreefileid where expid='"+expid+"';";
//			List<Map<String,Object>> rsH=new ArrayList<Map<String,Object>>();
//			rsH=DataBaseUtils.queryForList(sqlH);
//			//String PhaseThreeResultFileId=rsH.get(0).get("fileid").toString();
//			String PhaseThreeResultFileName=rsH.get(0).get("filename").toString();
//			//request.setAttribute("PhaseThreeResultFileId", PhaseThreeResultFileId);
//			request.setAttribute("phasethreefilename", PhaseThreeResultFileName);
			request.getRequestDispatcher("/common/PhaseFour.jsp").forward(request, response);
		}
		if(expbean.getPhase().equals("5")) 
		{
			//Get phase one file
			String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+expid+"';";
			List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
			rsF=DataBaseUtils.queryForList(sqlF);
			//String PhaseOneResultFileId=rsF.get(0).get("fileid").toString();
			String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
			//request.setAttribute("PhaseOneResultFileId", PhaseOneResultFileId);
			request.setAttribute("phaseonefilename", PhaseOneResultFileName);
			
			//Get phase two file
			String sqlG="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasetwofileid where expid='"+expid+"';";
			List<Map<String,Object>> rsG=new ArrayList<Map<String,Object>>();
			rsG=DataBaseUtils.queryForList(sqlG);
			//String PhaseTwoResultFileId=rsG.get(0).get("fileid").toString();
			String PhaseTwoResultFileName=rsG.get(0).get("filename").toString();
			//request.setAttribute("PhaseTwoResultFileId", PhaseTwoResultFileId);
			request.setAttribute("phasetwofilename", PhaseTwoResultFileName);
			
//			//Get phase three file
//			String sqlH="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasethreefileid where expid='"+expid+"';";
//			List<Map<String,Object>> rsH=new ArrayList<Map<String,Object>>();
//			rsH=DataBaseUtils.queryForList(sqlH);
//			//String PhaseThreeResultFileId=rsH.get(0).get("fileid").toString();
//			String PhaseThreeResultFileName=rsH.get(0).get("filename").toString();
//			//request.setAttribute("PhaseThreeResultFileId", PhaseThreeResultFileId);
//			request.setAttribute("phasethreefilename", PhaseThreeResultFileName);
			
//			//Get phase four file
//			String sqlI="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasefourfileid where expid='"+expid+"';";
//			List<Map<String,Object>> rsI=new ArrayList<Map<String,Object>>();
//			rsI=DataBaseUtils.queryForList(sqlI);
//			//String PhaseFourResultFileId=rsI.get(0).get("fileid").toString();
//			String PhaseFourResultFileName=rsI.get(0).get("filename").toString();
//			//request.setAttribute("PhaseFourResultFileId", PhaseFourResultFileId);
//			request.setAttribute("phasefourfilename", PhaseFourResultFileName);
			request.getRequestDispatcher("/common/PhaseFive.jsp").forward(request, response);
		}
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
