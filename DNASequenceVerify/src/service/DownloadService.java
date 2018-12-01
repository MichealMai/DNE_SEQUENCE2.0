package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DataBaseUtils;
import util.DownloadUtils;

public class DownloadService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		//Get file download path
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userbean");
		String fileid=request.getParameter("fileid");
		String sqlA="select filepath from DS_Files where fileid='"+fileid+"';";
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		result=DataBaseUtils.queryForList(sqlA);
		String path=result.get(0).get("filepath").toString();
		
		String savepath=this.getServletContext().getRealPath(path);//.substring(0, this.getServletContext().getRealPath(path).lastIndexOf("/.metadata"))+path;
		//String savepath=this.getServletContext().getRealPath(path).substring(0, this.getServletContext().getRealPath(path).lastIndexOf("/DNASequenceVerify"))+path.substring(path.lastIndexOf("/silver-clusters-master/"), path.length());
		//File file=new File(path);
		//File file=new File(savepath);
		String filename=path.substring(path.lastIndexOf("/")+1);
		String url="file://"+savepath;
		DownloadUtils du=new DownloadUtils();
		du.download(filename, url, response);
//		URL url=new URL(saveurl);
//		URLConnection conn=url.openConnection();
//		int filesize=conn.getContentLength();
//		BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
////		System.out.println(path);
////		System.out.println(sp);
////		System.out.println(savepath);
////		if(!url.exists()) {
////			request.setAttribute("message", "The file has been deleted");
////			//request.getRequestDispatcher("/servlet/Listinput").forward(request,response);
////			return;
////		}
//		response.reset();
//		response.setHeader("content-disposition","attachment;filename="+path.substring(path.lastIndexOf("/")+1));
//		//FileInputStream in=new FileInputStream(savepath);
//		
//		BufferedOutputStream out=new BufferedOutputStream(response.getOutputStream());
//		
//		byte buffer[]=new byte[65536];
//		Integer len=0;
//		while((len=in.read(buffer,0,65536))>0)  
//		{
//			out.write(buffer,0,len);
//		}
//		in.close();
//		out.flush();
//		out.close();
		//request.getRequestDispatcher("/servlet/ListFileService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
