package Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Listinput extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws ServletException, IOException{
		//Get the upload file path
		//String uploadFilePath=this.getServletContext().getRealPath("/WEB-INF/upload/");
		String uploadFilePath="/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data";
		//Save the download file name
		Map<String,String> fileNameMap=new HashMap<String,String>();
		File inputFolder=new File(uploadFilePath);
		//String realName=inputfile.getName().substring(inputfile.getName().lastIndexOf(".")+1);
		listInput(inputFolder,fileNameMap);
		//return information of map to jsp page
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/excuteCode.jsp").forward(request, response);
	}
	
	public void listInput(File file,Map<String,String> map) {
		String filename;
		String filetype="csv";
		//if file is a folder
		if(!file.isFile()) {
			//list all file and folder
			File files[]=file.listFiles();
			//loop files array
			for(File f:files) {
				//Recursion
				listInput(f,map);
			}
		}else {
			filename=file.getName();
			if(filetype.equals(filename.substring(filename.lastIndexOf(".")+1))) 
			{
			    //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			    map.put(file.getName(), filename);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	       throws ServletException, IOException{
		doGet(request,response);
	}
}
