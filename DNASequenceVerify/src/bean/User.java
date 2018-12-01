package bean;
import annotation.*;

@Table(tableName="DS_user")
public class User {
	
	@column(type="VARCHAR(100)",field="userid",primaryKey= true ,defaultNull=false)
	private String userid;     //User primary key
	@column(type="VARCHAR(20)",field="username")
	private String username;  //user name
	@column(type="VARCHAR(20)",field="password")
	private String password; //password
	@column(type="VARCHAR(60)",field="email")
	private String email;   //email
	@column(type="VARCHAR(200)",field="address")
	private String address;  //address
	@column(type="VARCHAR(15)",field="telephone")
	private String telephone; //phone number
	@column(type="VARCHAR(2)",field="sex")
	private Integer sex;    //user sex
	@column(type="datetime", field="createtime")
	private String createtime;  //create time
	@column(type="timestamp",field="updatetime")
	private String updatetime; //lastest update time
	@column(type="VARCHAR(20)",field="isAdmin")
	private String isAdmin;  //User is administrator or not


	//set up data
	public void setUserid(String id) 
	{
		this.userid=id;
	}
	
	public void setUsername(String username) 
	{
		this.username=username;
	}
	
	public void setPassword(String password) 
	{
		this.password=password;
	}
	
	public void setEmail(String email) 
	{
		this.email=email;
	}
	
	public void setAddress(String address) 
	{
		this.address=address;
	}
	
	public void setTelephone(String telephone) 
	{
		this.telephone=telephone;
	}
	
	public void setSex(Integer sex) 
	{
		this.sex=sex;
	}
	
	public void setCreatetime(String createtime) 
	{
		this.createtime=createtime;
	}
	
	public void setUpdatetime(String updatetime) 
	{
		this.updatetime=updatetime;
	}
	
	public void setIsAdmin(String isadmin)
	{
		this.isAdmin=isadmin;
	}
	
	//sent the data
	public String getUserid() 
	{
		return userid;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public String getTelephone() 
	{
		return telephone;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	
	public String getIsAdmin()
	{
		return isAdmin;
	}
	
}
