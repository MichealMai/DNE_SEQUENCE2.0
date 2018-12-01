package bean;

import annotation.Table;
import annotation.column;

@Table(tableName="DS_Algorithm")
public class Algorithm {
	
	//Define data column
	@column(type="VARCHAR(100)",field="algid",primaryKey=true,defaultNull=false)
	private String algid;      //Algorithm primary key
	@column(type="VARCHAR(50)",field="algname")
	private String algname;    //algorithm name
	@column(type="VARCHAR(50)",field="algcommand")
	private String algcommand; //Algorithm excute commond
	@column(type="VARCHAR(50)",field="algparatable")
	private String algparatable; //Algorithm excute commond
	@column(type="VARCHAR(200)",field="algpath")
	private String algpath;    //Algorithm document save path
	
	//Set up data
	public void setAlgid(String id) 
	{
		this.algid=id;
	}
	public void setAlgname(String name) 
	{
		this.algname=name;
	}
	public void setAlgcommand(String command) 
	{
		this.algcommand=command;
	}
	public void setAlgparatable(String table) 
	{
		this.algparatable=table;
	}
	public void setAlgpath(String path) 
	{
		this.algpath=path;
	}
	
	//Get the data
	public String getAlgid() 
	{
		return this.algid;
	}
	
	public String getAlgname()
	{
		return this.algname;
	}
	public String getAlgcommand() 
	{
		return this.algcommand;
	}
	public String getAlgparatable()
	{
		return this.algparatable;
	}
	public String getAlgpath()
	{
		return this.algpath;
	}
	
}
