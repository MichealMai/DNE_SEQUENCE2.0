package service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Algorithm;
import bean.Experiments;
import bean.Files;
import bean.LibsvmParameterValue;
import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class ExcuteLibsvmExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		
		String Path=this.getServletContext().getRealPath("/WebContent/WEB-INF/upload");
		//System.out.println(Path);
		
		String expid=request.getParameter("expid");
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userbean");
		
		String userid=user.getUserid();
		String expcondition="username='"+userid+"'";
		
		List<String> userlist=new ArrayList<String>();
		userlist.add("userid");
		
		Experiments exp=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+expid+"';", Experiments.class);
		
		
		//Get experiment file
		String sqlA="select * from DS_Files where fileid='"+exp.getInputfileid()+"';";
		Files file=DataBaseUtils.queryForBean(sqlA,Files.class);
		String filename=file.getFilename();
		
		//Get Excute command
		String sqlB="select * from DS_Algorithm where algid='"+exp.getAlgid()+"';";
		Algorithm alg=DataBaseUtils.queryForBean(sqlB,Algorithm.class);
		String basedcommand=alg.getAlgcommand();
		
		//Get parameter value
		List<Map<String,Object>> parameter=new ArrayList<Map<String,Object>>();
		
		String sqlC="select paraname,paracommand,paratype from DS_ParameterCommand natrual join (select * from DS_Experiments natural join DS_user where userid='"+userid+"') as alg";
		parameter=DataBaseUtils.queryForList(sqlC);
//		String sqlD="select ";
//		for(Integer i=0;i<parameter.size()-1;i++) 
//		{
//			sqlD=sqlD+parameter.get(i).get("paraname")+",";
//		}
//		sqlD=sqlD+parameter.get(parameter.size()-1).get("paraname");
//		sqlD=sqlD+" from DNA_Sequence.DS_LibsvmParameterValue natrual join (select * from DS_Experiments natural join DS_user where userid='"+userid+"' ) as alg;";
		String sqlD="select * from DS_LibsvmParameterValue where expid='"+expid+"';";
		
		LibsvmParameterValue parameterValue;
		parameterValue=DataBaseUtils.queryForBean(sqlD,LibsvmParameterValue.class);
		
		String executedCommand=basedcommand;
		
		//Judge the parameter
		if(parameterValue.getThresholdhigh()=="yes") 
		{
			executedCommand=executedCommand+" "+parameter.get(2).get("paracommand")+ " "+parameterValue.getThresholdhighvalue();
		}
		
		if(parameterValue.getThresholdlow()=="yes") 
		{
			executedCommand=executedCommand+" "+parameter.get(5).get("paracommand")+ " "+parameterValue.getThresholdlowvalue();
		}
		
		if(parameterValue.getVerbose()=="yes") 
		{
			executedCommand=executedCommand+" "+parameter.get(0).get("paracommand");
		}
		
		if(parameterValue.getGeneratenewfeatures()=="yes") 
		{
			executedCommand=executedCommand+" "+parameter.get(4).get("paracommand");
		}
		
		if(parameterValue.getCrossvalidtiontest()=="yes") 
		{
			executedCommand=executedCommand+" "+parameter.get(3).get("paracommand");
		}

			executedCommand=executedCommand+" "+filename;
		
		//File path=new File(alg.getAlgpath());
		
		File path=new File(this.getServletContext().getRealPath(alg.getAlgpath()).substring(0, this.getServletContext().getRealPath(alg.getAlgpath()).lastIndexOf("/.metadata"))+alg.getAlgpath());
		List<String> expresult=new ArrayList<String>();
		try {
			Process p=Runtime.getRuntime().exec(executedCommand,null,path);
			
			BufferedInputStream in=new BufferedInputStream(p.getInputStream());
			BufferedReader inBr=new BufferedReader(new InputStreamReader(in));
			
			String lineStr;
			
			while((lineStr=inBr.readLine())!=null)
			{
				expresult.add(lineStr);
				System.out.println(lineStr);
			}
			if(p.waitFor()!=0) {
				if(p.exitValue()==1)
					System.err.println("Excute faile");
			}
			inBr.close();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		String resultpath=this.getServletContext()
				.getRealPath("/DNASequenceVerify/WebContent/silver-clusters-master/data/training_data").substring(0, this.getServletContext()
				.getRealPath("/DNASequenceVerify/WebContent/silver-clusters-master/data/training_data").lastIndexOf("/.metadata"))
				+"/DNASequenceVerify/WebContent/silver-clusters-master/data/training_data/"
				+filename.substring(0, filename.lastIndexOf(".csv"))+".combinedfeatures.csv";
		System.out.println(resultpath);
		
		
		String fileid=UUID.randomUUID().toString();
		List<String> column=new ArrayList<String>();
		
		column.add("fileid");
		column.add("filename");
		column.add("filepath");
		column.add("userid");
		
		List<String> context=new ArrayList<String>();
		context.add(fileid);
		context.add(filename.substring(0, filename.lastIndexOf(".csv"))+".combinedfeatures.csv");
		context.add("/DNASequenceVerify/WebContent/silver-clusters-master/data/training_data/"
				+filename.substring(0, filename.lastIndexOf(".csv"))+".combinedfeatures.csv");
		context.add(userid);
		
		String sqlE=TableUtils.getInsertSQL(context,column,Files.class);
		
		List<Map<String,Object>> rsfile=new ArrayList<Map<String,Object>>();
		rsfile=DataBaseUtils.queryForList("Select * from DS_Files where userid='"+userid+"' and filename='"+filename.substring(0, filename.lastIndexOf(".csv"))+".combinedfeatures.csv"+"';");
		if(rsfile.size()<=0) 
		{
			DataBaseUtils.update(sqlE);
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
		request.setAttribute("resultfileid", fileid);
		request.setAttribute("expresult", expresult);
		request.setAttribute("expid", expid);
		request.getRequestDispatcher("/common/ExperimentResult.jsp").forward(request, response);
	}
		
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
