package bean;

import annotation.Table;
import annotation.column;

@Table(tableName="DS_Files")
public class Files {
	@column(type="VARCHAR(100)",field="fileid",primaryKey=true,defaultNull=false)
	private String fileid;       //Files primary key
	@column(type="VARCHAR(80)",field="filename")
	private String filename;     //Files name
	@column(type="VARCHAR(200)",field="filepath")
	private String filepath;     //file path
	@column(type="VARCHAR(100)",field="userid")
	private String userid;      //File owner id
	//set up data
	public void setFileid(String fid) 
	{
		this.fileid=fid;
	}
	public void setFilename(String fname) 
	{
		this.filename=fname;
	}
	public void setFilepath(String fpath) 
	{
		this.filepath=fpath;
	}
	public void setUserid(String uid) 
	{
		this.userid=uid;
	}
	
	//get the data
	public String getFileid() 
	{
		return this.fileid;
	}
	public String getFilename() 
	{
		return this.filename;
	}
	public String getFilepath() 
	{
		return this.filepath;
	}
	public String getUserid() 
	{
		return this.userid;
	}

}
