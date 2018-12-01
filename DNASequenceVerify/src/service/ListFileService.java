package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DataBaseUtils;

public class ListFileService extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("userbean");
		String userid=user.getUserid();
		String sqlA;
		if(user.getIsAdmin().equals("yes"))
		{
			sqlA="select * from DS_Files";
		}
		else 
		{
			sqlA="select * from DS_Files where userid='"+userid+"';";
		}
				
		List<Map<String,Object>> fileList=new ArrayList<Map<String,Object>>();
		fileList=DataBaseUtils.queryForList(sqlA);
		Map<String,String> fileMap = new HashMap<String,String>();
		String fileName,filekey;
		for(Integer i=0;i<fileList.size();i++) 
		{
			
//			fileName=fileList.get(i).get("filename").toString();
//			filekey=fileName.substring(0,fileName.lastIndexOf(".")+1);
			fileMap.put(fileList.get(i).get("filename").toString(), fileList.get(i).get("fileid").toString());
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
		
		request.setAttribute("fileNameMap", fileMap);
		request.getRequestDispatcher("/common/filelist.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException, IOException{
		doGet(request,response);
	}

}
