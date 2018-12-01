package bean;

import annotation.Table;
import annotation.column;

@Table(tableName="DS_ParameterCommand")
public class ParameterCommand {
	
	//Define data column
	@column(type="VARCHAR(100)",field="paraid",primaryKey=true,defaultNull=false)
	private String paraid;      //parameter primary key
	@column(type="VARCHAR(100)",field="algid",defaultNull=false)
	private String algid;      //Algorithm id
	@column(type="VARCHAR(50)",field="paraname",defaultNull=false)
	private String paraname;  //Parameter name
	@column(type="VARCHAR(10)",field="paratype")
	private String paratype;  //Parameter type ('select' type or 'value' type)
	@column(type="VARCHAR(10)",field="paracommand")
	private String paracommand;//parameter command
	@column(type="VARCHAR(1000)",field="statement")
	private String statement;  //parameter statement
	
	//Set up data
	public void setParaid(String pid) 
	{
		this.paraid=pid;
	}
	public void setAlgid(String id) 
	{
		this.algid=id;
	}
	public void setParaname(String name) 
	{
		this.paraname=name;
	}
	public void setParatype(String type)
	{
		this.paratype=type;
	}
	public void setParacommand(String command)
	{
		this.paracommand=command;
	}
	public void setStatement(String sta)
	{
		this.statement=sta;
	}
	
	//Get the data
	public String getParaid() 
	{
		return this.paraid;
	}
	public String getAlgid() 
	{
		return this.algid;
	}
	public String getParaname()
	{
		return this.paraname;
	}
	public String getParatype()
	{
		return this.paratype;
	}
	public String getParacommand()
	{
		return this.paracommand;
	}
	
	public String getStatement()
	{
		return this.statement;
	}



}
