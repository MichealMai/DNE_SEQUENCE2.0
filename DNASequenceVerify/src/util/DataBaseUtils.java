package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataBaseUtils {
	
	private static String username;      //user name
	private static String password;      //password
	private static String dataBaseName;  // database name
	
	/**
	 * Configuare the basic information of database
	 * @param path
	 * @return void
	 */
	public static void config(String path) {
		InputStream inputStream=DataBaseUtils.class.getClassLoader().getResourceAsStream(path);
		Properties p=new Properties();
	
	try {
		p.load(inputStream);
		username=p.getProperty("db.username");
		password=p.getProperty("db.password");
		dataBaseName=p.getProperty("db.dataBaseName");
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	/*
	 * Default config
	 */
	static {
		config("jdbc.properties");
	}
	
	/*
	 * Database Connection
	 * @return connection
	 */
	public static Connection getConnection() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBaseName+"?useUnicode=true&characterEncoding=utf8",username,password);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * connction close
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement,ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			if(statement!=null)statement.close();
			if(connection!=null)connection.close();
		} catch(Exception e) {
			e.fillInStackTrace();
		}
	}
	
	/*
	 * Data manipulation Language operate 
	 * @param sql
	 * @param objects
	 */
	
	public static void update(String sql,Object...objects) { //Objects is array of object
		Connection connection=getConnection();
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement)connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				statement.setObject(i+1, objects[i]);
			}
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection,statement,null);
		}
	}
	
	/*
	 * Search the data and return the list
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	
	public static List<Map<String,Object>> queryForList(String sql,Object...objects){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Connection connection=getConnection();
		PreparedStatement statement=null;
		ResultSet rs=null;
		try {
			statement=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++) {
				statement.setObject(i+1, objects[i]);
			}
			
			rs=statement.executeQuery();
			while(rs.next()) {
				ResultSetMetaData resultSetMetaData=rs.getMetaData();
				int count =resultSetMetaData.getColumnCount();// Get number of list
				Map<String,Object>map=new HashMap<String,Object>();
				for(int i=0;i<count;i++) {
					map.put(resultSetMetaData.getColumnName(i+1), rs.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				result.add(map);
			};
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(connection,statement,rs);
		}
		return result;
	}
	
	/*
	 * Search data return a map
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static Map<String,Object> queryForMap(String sql,Object...objects)throws SQLException{
		Map<String,Object> result=new HashMap<String,Object>();
		List<Map<String,Object>> list=queryForList(sql,objects);
		if(list.size()!=1) {
			return null;
		}
		result=list.get(0);
		return result;
	}
	
	/*
	 * search data and return javabean
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static<T>T queryForBean(String sql,Class<T> clazz,Object...objects){
		T obj = null;
		Map<String,Object>map=null;
		Field field=null;
		try {
			obj=(T) clazz.newInstance();
			map=queryForMap(sql,objects);
			
		}catch(InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(map==null) {
			return null;
		}
		//loop map
		for (String columnName:map.keySet()) {
			Method method=null;
			String propertyName =StringUtils.columnToProperty(columnName); //Attribute name
			
			try {
				field=clazz.getDeclaredField(propertyName);
			}catch(NoSuchFieldException e1) {
				e1.printStackTrace();
			}catch(SecurityException e1) {
				e1.printStackTrace();
			}
			//Get the string from java bean
			String fieldType=field.toString().split(" ")[1];// Get the string type
			Object value = map.get(columnName);
			if(value==null) {
				continue;
			}
			//get set method name
			String setMethodName="set"+StringUtils.upperCaseFirstCharacter(propertyName);
			try {
				//Get the type of value
				String valueType=value.getClass().getName();
				//Search the type if match of not
				if(!fieldType.equalsIgnoreCase(valueType)) {
					if(fieldType.equalsIgnoreCase("java.lang.Integer")) {
					value=Integer.parseInt(String.valueOf(value));
					
				}else if(fieldType.equalsIgnoreCase("java.lang.String")) {
					value=String.valueOf(value);
				}else if(fieldType.equalsIgnoreCase("java.util.Date")) {
					valueType="java.util.Date";
					//Change value to java.util.Date
					String dateStr=String.valueOf(value);
					Timestamp ts=Timestamp.valueOf(dateStr);
					Date date=new Date(ts.getTime());
					value=date;
				}
			}
			
			//Get method set
			method=clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
			//Execute method set
			method.invoke(obj, value);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		return obj;
  }
	

	
}
