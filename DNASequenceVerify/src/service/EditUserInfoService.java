package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class EditUserInfoService extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		
		String userid=request.getParameter("userid");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String telephone=request.getParameter("telephone");
		
		User user=DataBaseUtils.queryForBean("select * from DS_user where userid='"+userid+"';", User.class);

		List<String> column=new ArrayList<String>();
		List<String> context=new ArrayList<String>();
		
		if(!username.equals(user.getUsername()))
		{
			column.add("username");
			context.add(username);
		}
		
		if(!password.equals(user.getPassword()))
		{
			column.add("password");
			context.add(password);
		}
		
		if(!email.equals(user.getEmail()))
		{
			column.add("email");
			context.add(email);
		}
		
		if(!telephone.equals(user.getTelephone()))
		{
			column.add("telephone");
			context.add(telephone);
		}
		
		if(!address.equals(user.getAddress()))
		{
			column.add("address");
			context.add(address);
		}
		
		String isadmin=request.getParameter("isadmin");
		if(!isadmin.equals(user.getIsAdmin()))
		{
			column.add("isadmin");
			context.add(isadmin);
		}
		
		if(column.size()!=0)
		{
			String sql=TableUtils.getUpdateSQL("userid='"+user.getUserid()+"'", context, column, User.class);
			DataBaseUtils.update(sql);
		}
		request.getRequestDispatcher("/servlet/ListUserService").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}
}
