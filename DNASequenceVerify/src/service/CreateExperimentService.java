package service;

import bean.LibsvmParameterValue;
import bean.User;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import bean.Algorithm;
import bean.Experiments;
import bean.Files;
import util.DataBaseUtils;
import util.TableUtils;

public class CreateExperimentService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession hs=request.getSession();
		User user=(User)hs.getAttribute("userbean");
		String message="";
		String userid=user.getUserid();
		String Expid=UUID.randomUUID().toString();
		String fileid=UUID.randomUUID().toString();
		String Expname;
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
		
//		//set up default experiment parameter
//        List<Map<String,Object>> parameter=new ArrayList<Map<String,Object>>();
//		
//		String sqlD="select paraname,paracommand,paratype from DS_ParameterCommand where algid='"+algid+"';";
//		parameter=DataBaseUtils.queryForList(sqlD);
//		
//		String sqlE;
//		
//		List<String> columnB = new ArrayList<String>();
//		List<String> contextB = new ArrayList<String>();
//		for(Integer i=0;i<parameter.size();i++)
//		{
//			columnB.add(parameter.get(i).get("paraname").toString());
//			if(parameter.get(i).get("paratype").toString()=="select") 
//			{
//				contextB.add("no");
//			}
//			else
//				contextB.add("0");
//		}
//		columnB.add("expid");
//		contextB.add(Expid);
//		
//		if(algid.equals("8061653e-aa94-42dd-bd43-de939bd6e1d8")) {
//			sqlE=TableUtils.getInsertSQL(contextB, columnB,LibsvmParameterValue.class);
//			DataBaseUtils.update(sqlE);
//		}

		//upload inputfile
		String savePath=this.getServletContext().getRealPath("userfile/");
		File pathA=new File(savePath);
		if(!pathA.exists()) {
			pathA.mkdir();
		}
		try 
		{
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
					if(name.equals("expname")) 
					{
						Expname=item.getString();
						columnA.add("expname");
						contextA.add(Expname);
					}
//					}
					//String value=item.getString("UTF-8");
					//System.out.println(name+"="+value);
				}else {//如果fileitem中封装的是上传文件,得到上传的文件名称，
					
					//sql insert
					
					String filename=item.getName();

					
					//System.out.println(filename);
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
					
					String filepath="userfile/"+filename;
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
					if(!isAuth)
					throw new Exception("Authentication failed");
					SCPClient client = new SCPClient(conn);
					client.put("/var/lib/tomcat7/webapps/DNASequenceVerify/userfile/"+filename, "/home/debosmita/DNAproject/project/silvercopy/data/input_data"); conn.close();
					System.out.println("*******************************************\n"+filename);
					}
	       }
			//Insert experiment record
			String sqlC=TableUtils.getInsertSQL(contextA, columnA, Experiments.class);
			DataBaseUtils.update(sqlC);
			
		}catch(FileUploadBase.FileSizeLimitExceededException e) 
		{
			e.printStackTrace();
			String over="Overload maximum size of the signle file(10M)";
			request.setAttribute("message", over);
			
		}catch(FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("message", "Overload maximum size of the folder(100M)");
		}
		 catch(Exception e) {
			message="file fail to upload!";
			e.printStackTrace();
		}
		request.getRequestDispatcher("/servlet/ListExperimentService").forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		doGet(request,response);
	}
}
