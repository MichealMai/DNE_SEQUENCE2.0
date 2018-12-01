package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class LoginService extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Revice the message from user layer
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String SQL="select * from DS_user where username='"+username+"';";
		User temp=new User();
		temp=DataBaseUtils.queryForBean(SQL,User.class);
		JSONObject obj=new JSONObject();
		PrintWriter pw=response.getWriter();
		if(temp==null) {
			try {
				obj.put("message", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//seesion.setAttribute("The user is not exits",message);
		}
		else 
		{
			if(!username.equals(temp.getUsername())||!password.equals(temp.getPassword())) 
			{
				System.out.println("Username or password is not match");
				try {
					obj.put("message", 1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//request.setAttribute("message","Username or password is not match");
			}
			else
			{
				//Saving cookies
				HttpSession session=request.getSession();
				session.setAttribute("userbean", temp);
				
				if(temp.getIsAdmin().equals("yes")) 
				{
					try {
						obj.put("message", 2);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//request.getRequestDispatcher("/servlet/ListUserService").forward(request, response);
				}
				else
				{
					try {
						obj.put("message", 3);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//request.getRequestDispatcher("/servlet/ListExperimentService").forward(request, response);
				}
			}
			
		}
		
		pw.println(obj);
		pw.close();
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}

}
