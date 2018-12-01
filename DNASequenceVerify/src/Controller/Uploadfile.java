package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class Uploadfile extends HttpServlet{
	
	//file save path
	private String path;
	private String filename;

	/*
	 * Receive the user upload file
	 */
	
	public void doGetUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//Get the folder where have upload file, put the upload file in web-inf keep safty
		String savePath=this.getServletContext().getRealPath("/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data").substring(0, this.getServletContext().getRealPath("/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data").lastIndexOf("/.metadata"))+"/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data";
//		String savePath="/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data";
//		"/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data"
		File pathA=new File(savePath);
		if(!pathA.exists()) {
			pathA.mkdir();
		}
		//setUpRunPara(request);
		//message note
		String message="";
		try {
			//Create DiskFIleItemFactory factory
			DiskFileItemFactory factory =new DiskFileItemFactory();
			//设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			//设置上传时生成的临时文件的保存目录
			factory.setRepository(pathA);
			//Create file upload analysis
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			//Solve Chinese race code
			upload.setHeaderEncoding("UTF-8");
			//判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)) {
				//Tradictional way to get the data
				return;
			}
			
			//Set up single file maximum size
			upload.setFileSizeMax(1024*1024*10);
			//Set up total maximum file size
			upload.setSizeMax(1024*1024*100);
			
			//使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()) {
					String name=item.getFieldName();
					String value=item.getString("UTF-8");
					System.out.println(name+"="+value);
				}else {//如果fileitem中封装的是上传文件,得到上传的文件名称，
					String filename=item.getName();
					setFileName(filename);
					System.out.println(filename);
					if(filename==null||filename.trim().equals("")) {
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的
					
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename=filename.substring(filename.lastIndexOf("/")+1);
					//get extend name of upload file
					String fileExtName=filename.substring(filename.lastIndexOf(".")+1);
					//如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("Extend name of upload file is :"+fileExtName);
					//Get the inputstream
					InputStream in=item.getInputStream();
					//save file path
					//create file output stream
					FileOutputStream out=new FileOutputStream(savePath+"/"+filename);
					//create buffer
					byte buffer[]=new byte[1024];
					//判断输入流中的数据是否已经读完的标识
					int len=0;
					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while((len=in.read(buffer))>0) {
						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						out.write(buffer,0,len);
					}
					//close input stream
					in.close();
					//close output stream
					out.close();
					//删除处理文件上传时生成的临时文件
					item.delete();
					
					setFileSavePath(savePath+"/"+filename);
					message="file upload successfully!";
					
					}
	       }
		}catch(FileUploadBase.FileSizeLimitExceededException e) {
				e.printStackTrace();
				String over="Overload maximum size of the signle file(10M)";
				request.setAttribute("message", over);
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				//return;
			}catch(FileUploadBase.SizeLimitExceededException e) {
				e.printStackTrace();
				request.setAttribute("message", "Overload maximum size of the folder(100M)");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				//return;
			}
			 catch(Exception e) {
				message="file fail to upload!";
				e.printStackTrace();
			}
		    
			request.setAttribute("message",message);

			request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	
	
	public void setFileSavePath(String path) {
		this.path=path;
	}
	
	public String getFileSavePath() {
		return this.path;
	}
	
	public void setFileName(String filename) {
		this.filename=filename;
	}
	
	public String getFileName() {
		return this.filename;
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGetUpload(request,response);
		//runpycode(this.path.substring(this.path.lastIndexOf("/")+1));
	}
	
}
