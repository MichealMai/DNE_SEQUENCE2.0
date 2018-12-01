package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Downloadfile extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		//上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		//String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
		//String fileSaveRootPath="/Users/maimac/Documents/JavaEEPRO/FileUploadAndDownload/WebContent/WEB-INF/upload";
		String path=request.getParameter("path");
		System.out.println(path);
		File file=new File(path);
		if(!file.exists()) {
			request.setAttribute("message", "The file has been deleted");
			request.getRequestDispatcher("/servlet/Listinput").forward(request,response);
			return;
		}
		response.setHeader("content-disposition", "attachment;filename=" + path.substring(path.lastIndexOf("/")+1));
		FileInputStream in =new FileInputStream(path);
		OutputStream out=response.getOutputStream();
		byte buffer[]=new byte[1024];
		int len=0;
		while((len=in.read(buffer))>0) {
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
		//request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
