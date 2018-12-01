package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Experiments;
import bean.Files;
import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class UpdatePhaseThreeService extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userbean");
		Experiments exp=(Experiments) session.getAttribute("expbean");
		String userid=user.getUserid();
		String kfold=request.getParameter("kfold");
		String cvalue=request.getParameter("cvalue");
		String file=request.getParameter("filename");
		String fileid=UUID.randomUUID().toString();
		List<String> columnA=new ArrayList<String>();
		//Update Experiment record
		//columnA.add("phasethreefileid");
		columnA.add("kfold");
		columnA.add("cvalue");
		columnA.add("phase");
		List<String> contextA=new ArrayList<String>();
		//contextA.add(fileid);
		contextA.add(kfold);
		contextA.add(cvalue);
		contextA.add("4");
		String updateExp=TableUtils.getUpdateSQL("expid='"+exp.getExpid()+"'",contextA,columnA,Experiments.class);
		DataBaseUtils.update(updateExp);
		//Save the result file
//		String filepath="userfile/"+file;
//		List<String> column=new ArrayList<String>();
//		column.add("fileid");
//		column.add("filename");
//		column.add("filepath");
//		column.add("userid");
//		List<String> context=new ArrayList<String>();
//		context.add(fileid);
//		context.add(file);
//		context.add(filepath);
//		context.add(userid);
//		copy("/home/micheal/Documents/userfile/"+file,"/var/lib/tomcat7/webapps/DNASequenceVerify/userfile/"+file);
//		//insert the file record
//		String sql=TableUtils.getInsertSQL(context,column,Files.class);
//		DataBaseUtils.update(sql);
		String link;
		if(user.getIsAdmin().equals("yes"))
		{
			link="<a href=\"/DNASequenceVerify/servlet/ListUserService\">User list</a>";
		}
		else
		{
			link="";
		}
		
		//Get phaseonefilename
		String sqlF="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phaseonefileid where expid='"+exp.getExpid()+"';";
		List<Map<String,Object>> rsF=new ArrayList<Map<String,Object>>();
		rsF=DataBaseUtils.queryForList(sqlF);
		String PhaseOneResultFileName=rsF.get(0).get("filename").toString();
		request.setAttribute("phaseonefilename", PhaseOneResultFileName);
		
		//Get phasetwofilename
		String sqlG="select DS_Files.filename from DS_Files join DS_Experiments on DS_Files.fileid=DS_Experiments.phasetwofileid where expid='"+exp.getExpid()+"';";
		List<Map<String,Object>> rsG=new ArrayList<Map<String,Object>>();
		rsG=DataBaseUtils.queryForList(sqlG);
		String PhaseTwoResultFileName=rsF.get(0).get("filename").toString();
		request.setAttribute("phasetwofilename", PhaseTwoResultFileName);
		
		//Get input file name
		Files inputfile=DataBaseUtils.queryForBean("select * from DS_Files where fileid='"+exp.getInputfileid()+"';", Files.class);
		//Get Phase one result filename
		Files phaseonefile=DataBaseUtils.queryForBean("select * from DS_Files where fileid='"+exp.getPhaseonefileid()+"';", Files.class);
		//Get Phase two result filename
		Files phasetwofile=DataBaseUtils.queryForBean("select * from DS_Files where fileid='"+exp.getPhasetwofileid()+"';", Files.class);
		//Set up return parameter
		Experiments newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
		session.removeAttribute("expbean");
		session.setAttribute("expbean",newexpbean);
		request.setAttribute("expbean",newexpbean);
		request.setAttribute("inputfilename", inputfile.getFilename());
		request.setAttribute("phaseonefile", phaseonefile.getFilename());
		request.setAttribute("phasetwofile", phasetwofile.getFilename());
		request.setAttribute("userbean", user);
		request.setAttribute("filename", file);
		request.setAttribute("phasetwofilename", PhaseTwoResultFileName);
		request.setAttribute("phaseonefilename", PhaseOneResultFileName);
		//request.setAttribute("phasethreefilename", file);
		//request.setAttribute("phasethreefileid", fileid);
		request.setAttribute("link", link);
		request.getRequestDispatcher("/common/PhaseFour.jsp").forward(request, response);
	}
	public void copy(String scrpath,String destpath) throws IOException 
	{
		try {
			InputStream in=new FileInputStream(scrpath);
			FileOutputStream out=new FileOutputStream(destpath);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=in.read(buffer))>0) {
				System.out.println(length);
				out.write(buffer,0,length);
			}
			in.close();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
