package bean;

import annotation.column;
import annotation.Table;

@Table(tableName="DS_Administrator")
public class Administrator {
	
	//Define data column
	@column(type="VARCHAR(100)",field="admid",primaryKey= true ,defaultNull=false)
	private String admid;     //User primary key
	@column(type="VARCHAR(20)",field="admname")
	private String admname;  //user name
	@column(type="VARCHAR(20)",field="admpassword")
	private String admpassword; //password
	
	//set up the data
	public void setAdmid(String id)
	{
		this.admid=id;
	}
	
	public void setAdmname(String name)
	{
		this.admname=name;
	}
	
	public void setAdmpassword(String password)
	{
		this.admid=password;
	}
	
	
	//Get the data
	public String getAdmid()
	{
		return this.admid;
	}
	
	public String getAdmname()
	{
		return this.admname;
	}
	
	public String getAdmpassword()
	{
		return this.admid;
	}
	

}
