package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import util.DataBaseUtils;

public class DeleteFileService extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		
		String fileid=request.getParameter("fileid");
		
		
		JSONObject json=new JSONObject();
				
		PrintWriter pw=response.getWriter();

		
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		
		rs=DataBaseUtils.queryForList("select * From DS_Experiments where fileid='"+fileid+"';");
		
		if(rs.size()>0) 
		{
			try {
				json.put("message", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else 
		{
			DataBaseUtils.update("delete from DS_Files where fileid='"+fileid+"';");
			try {
				json.put("message", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		pw.println(json);
		pw.close();
	
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
