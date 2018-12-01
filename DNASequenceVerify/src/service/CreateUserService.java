package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Experiments;
import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class CreateUserService extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String userid=UUID.randomUUID().toString();
		String username=request.getParameter("username");
		
		User temp=(User)DataBaseUtils.queryForBean("select * from DS_User where username='"+username+"';", User.class);
		
		if(temp!=null)
		{
			request.setAttribute("errormessage", "The user is exist,Please use the another one");
			request.setAttribute("returnlink", "/DNASequenceVerify/Administrator/UserRegister.jsp");
			request.getRequestDispatcher("/common/ErrorMessage.jsp").forward(request, response);
		}
		else
		{
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String telephone=request.getParameter("telephone");
			String isadmin=request.getParameter("isadmin");
			
			List<String> column=new ArrayList<String>();
			List<String> context=new ArrayList<String>();
			
			column.add("userid");
			context.add(userid);
			column.add("username");
			context.add(username);
			column.add("password");
			context.add(password);
			column.add("email");
			context.add(email);
			column.add("address");
			context.add(address);
			column.add("telephone");
			context.add(telephone);
			column.add("isAdmin");
			context.add(isadmin);
			
			String sql=TableUtils.getInsertSQL(context, column, User.class);
			System.out.println(sql);
			DataBaseUtils.update(sql);
			
			request.getRequestDispatcher("/servlet/ListUserService").forward(request, response);
		}
		
		

	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}
}
