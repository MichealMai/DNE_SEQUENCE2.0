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

public class ListProfileService extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String userid=request.getParameter("userid");
		String sql="select * from DS_user where userid='"+userid+"';";
		User user= new User();
		
		user=DataBaseUtils.queryForBean(sql,User.class);
		request.setAttribute("userinfo",user);
		request.getRequestDispatcher("/Administrator/UserInfo.jsp").forward(request, response);
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
