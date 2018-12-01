package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import util.TableUtils;
import util.DataBaseUtils;

public class Login extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	{
		//Revice the message from user layer
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String condition="username='"+username+"'";
		List<String> Attribute=new ArrayList<String>();
		Attribute.add("password");
		String SQL=TableUtils.getSearchSQL(condition,Attribute,User.class);
		User temp=new User();
		temp=DataBaseUtils.queryForBean(SQL,User.class,temp);
		if(temp==null) {
			System.out.println("The user is not exits");
			//seesion.setAttribute("The user is not exits",message);
		}
		else 
		{
			if(!username.equals(temp.getUsername())||!password.equals(temp.getPassword())) 
			{
				System.out.println("Username or password is not match");
				//request.setAttribute("Username or password is not match",message);
			}
			else
			{
				System.out.println("Matching");
				request.setAttribute("username",temp.getUsername());
				if(temp.getIsAdmin()=="yes") 
				{
					request.getRequestDispatcher("/User/AdministrtorIndex.jsp");
				}
				else
				{
					request.getRequestDispatcher("/User/UserIndex.jsp");
				}
			}
			
		}
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
