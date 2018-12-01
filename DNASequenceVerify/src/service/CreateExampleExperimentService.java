package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Experiments;
import bean.Files;
import bean.User;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import util.DataBaseUtils;
import util.TableUtils;

public class CreateExampleExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession hs=request.getSession();
		User user=(User)hs.getAttribute("userbean");
		String message="";
		String userid=user.getUserid();
		String Expid=UUID.randomUUID().toString();
		String fileid=UUID.randomUUID().toString();
		String filename="all_classes.csv";
		String filepath="userfile/"+filename;
		String Expname=request.getParameter("expname");
		//Insert new experiments
		List<String> columnA = new ArrayList<String>();
		columnA.add("expid");
		columnA.add("userid");
		columnA.add("inputfileid");
		columnA.add("phase");
		columnA.add("motifsnumber");
		columnA.add("featureselection");
		columnA.add("extrafeature");
		columnA.add("fp");
		columnA.add("fn");
		columnA.add("algid");
		columnA.add("expname");
		

		
		List<String> contextA = new ArrayList<String>();
		contextA.add(Expid);
		contextA.add(userid);
		contextA.add(fileid);
		contextA.add("1");
		contextA.add("1");
		contextA.add("1");
		contextA.add("False");
		contextA.add("1");
		contextA.add("1");
		contextA.add("8061653e-aa94-42dd-bd43-de939bd6e1d8");
		contextA.add(Expname);
		
		String sqlC=TableUtils.getInsertSQL(contextA, columnA, Experiments.class);
		DataBaseUtils.update(sqlC);
		
		
		List<String> column=new ArrayList<String>();
		
		column.add("fileid");
		column.add("filename");
		column.add("filepath");
		column.add("userid");
		
		
		List<String> context=new ArrayList<String>();
		context.add(fileid);
		context.add(filename);
		context.add(filepath);
		context.add(userid);
		
		String sql=TableUtils.getInsertSQL(context,column,Files.class);//="Insert DS_files (fileid,filename,filepath,userid) Values('"+fileid+"','"+filename+"','"+filepath+"','"+userid+"');";
		DataBaseUtils.update(sql);
		
		message="file upload successfully!";
		//sending the inputfile
		Connection conn = new Connection("169.226.135.21");
		conn.connect();
		boolean isAuth = conn.authenticateWithPassword("debosmita", "DNAtest");
		SCPClient client = new SCPClient(conn);
		client.put("/var/lib/tomcat7/webapps/DNASequenceVerify/userfile/"+filename, "/home/debosmita/DNAproject/project/silvercopy/data/input_data"); conn.close();
		request.getRequestDispatcher("/servlet/ListExperimentService").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		doGet(request,response);
	}

}
