package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;

import bean.Files;
import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class ReceivePhaseResultService extends  HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userbean");
		String userid=user.getUserid();
		String expid=request.getParameter("expid");
		String file=request.getParameter("fileName");
		String fileid=UUID.randomUUID().toString();
		String filepath="userfile/"+file;
		
		//Save phase one result file
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
		String sql=TableUtils.getInsertSQL(context,column,Files.class);
		DataBaseUtils.update(sql);
		
		//Save phase one result file id in experiment record
		sql="UPDATE DS_Experiments SET phaseonefileid='"+fileid+"',phase='2' where expid='"+expid+"';";
		DataBaseUtils.update(sql);	
		
		String link;
		if(user.getIsAdmin().equals("yes"))
		{
			link="<a href=\"/DNASequenceVerify/servlet/ListUserService\">User list</a>";
		}
		else
		{
			link="";
		}
		request.setAttribute("link", link);
		request.setAttribute("resultfileid", fileid);
		request.setAttribute("expid", expid);
		request.getRequestDispatcher("/common/PhaseTwo.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}
	//copy file from documents to other project direction
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
