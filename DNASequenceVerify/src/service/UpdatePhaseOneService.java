package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import bean.Experiments;
import bean.Files;
import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class UpdatePhaseOneService extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userbean");
		String userid=user.getUserid();
		String expid=request.getParameter("expid");
		String file=request.getParameter("filename");
		String fp=request.getParameter("fp");
		String fn=request.getParameter("fn");
		String maximummotifs=request.getParameter("maximummotifs");
		String fileid=UUID.randomUUID().toString();
		List<String> columnA=new ArrayList<String>();
		//Update database
		columnA.add("fn");
		columnA.add("fp");
		columnA.add("phaseonefileid");
		columnA.add("maximummotifs");
		columnA.add("phase");
		List<String> contextA=new ArrayList<String>();
		contextA.add(fn);
		contextA.add(fp);
		contextA.add(fileid);
		contextA.add(maximummotifs);
		contextA.add("2");
		//update experiment
		String updateExp=TableUtils.getUpdateSQL("expid='"+expid+"'",contextA,columnA,Experiments.class);
		DataBaseUtils.update(updateExp);
		//Save the result file
		String filepath="userfile/"+file;
		List<String> column=new ArrayList<String>();
		column.add("fileid");
		column.add("filename");
		column.add("filepath");
		column.add("userid");
		List<String> context=new ArrayList<String>();
		context.add(fileid);
		context.add(file);
		context.add(filepath);
		context.add(userid);
		copy("/home/micheal/Documents/userfile/"+file,"/var/lib/tomcat7/webapps/DNASequenceVerify/userfile/"+file);
		
		String link;
		if(user.getIsAdmin().equals("yes"))
		{
			link="<a href=\"/DNASequenceVerify/servlet/ListUserService\">User list</a>";
		}
		else
		{
			link="";
		}
		
		String sql=TableUtils.getInsertSQL(context,column,Files.class);
		DataBaseUtils.update(sql);
		Experiments exp=(Experiments) session.getAttribute("expbean");
		//Get input file name
		Files inputfile=DataBaseUtils.queryForBean("select * from DS_Files where fileid='"+exp.getInputfileid()+"';", Files.class);
		//Set up return parameter
		Experiments newexpbean=DataBaseUtils.queryForBean("select * from DS_Experiments where expid='"+exp.getExpid()+"';", Experiments.class);
		session.removeAttribute("expbean");
		session.setAttribute("expbean", newexpbean);
		request.setAttribute("inputfilename", inputfile.getFilename());
		request.setAttribute("userbean", user);
		request.setAttribute("expbean", newexpbean);
		request.setAttribute("filename", file);
		request.setAttribute("phaseonefilename", file);
		request.setAttribute("phaseonefileid", fileid);
		request.setAttribute("link", link);
		request.getRequestDispatcher("/common/PhaseTwo.jsp").forward(request, response);
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
