package util;

import java.lang.reflect.Field;
import java.util.List;

import annotation.Table;
import annotation.column;

public class TableUtils {
	
	//Create the table
	
	public static String getCreateTableSQL(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		
		//get table name
		Table table=(Table)clazz.getAnnotation(Table.class);
		String tableName=table.tableName();
		sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
		sb.append("create table ");
		sb.append(tableName).append("(\n");
		
		Field[] fields=clazz.getDeclaredFields();
		String primaryKey="";
		//loop all charater
		
		for (int i=0; i<fields.length;i++) {
			column column=(column)fields[i].getAnnotations()[0];
			String field=column.field();
			String type=column.type();
			boolean defaultNull=column.defaultNull();
			
			sb.append("\t"+field).append(" ").append(type);
			if(defaultNull) {
				if(type.toUpperCase().equals("TIMESTAMP")) {
					sb.append(",\n");
				}
				else {
					sb.append(" DEFAULT NULL,\n");
				}
			}
			else {
				sb.append(" NOT NULL,\n");
				if(column.primaryKey()) {
					primaryKey="PRIMARY KEY ("+field+")";
				}
			}
		}
		
		if(!StringUtils.isEmpty(primaryKey)) {
			sb.append("\t").append(primaryKey);
		}
		sb.append("\n) DEFAULT CHARSET=utf8");
		
		return sb.toString();
	}
	
	//Insert new record into database
	public static String getInsertSQL(List<String> context,List<String> column,Class<?> clazz) 
	{
		//get table name
		Table table=(Table)clazz.getAnnotation(Table.class);
		String tableName=table.tableName();
		
		//Build up the sql sentence
		Integer i;
		String SQLA="INSERT INTO "+tableName+" (";
		String SQLB=" VALUES (";
		
		for(i=0;i<column.size()-1;i++)
			SQLA=SQLA+column.get(i)+",";
		SQLA=SQLA+column.get(column.size()-1)+")";
		
		for(i=0;i<context.size()-1;i++)
		    SQLB=SQLB+"'"+context.get(i)+"',";
		SQLB=SQLB+"'"+context.get(context.size()-1)+"')";
		
		String SQL=SQLA+SQLB+";";
		
		return SQL;
	}
	
	//Update the record
	public static String getUpdateSQL(String condition,List<String> context, List<String> column,Class<?> clazz) 
	{
		//get table name
		Table table=(Table)clazz.getAnnotation(Table.class);
		String tableName=table.tableName();
		
		//Build up the sql sentence
		Integer i;
		String SQLA="UPDATE "+tableName+" SET ";
		String SQLB="WHERE "+condition;
		for(i=0;i<context.size()-1;i++)
			SQLA=SQLA+column.get(i)+"='"+context.get(i)+"', ";
		SQLA=SQLA+column.get(column.size()-1)+"='"+context.get(context.size()-1)+"' ";
		String SQL=SQLA+SQLB+";";
		return SQL;
	}
	
	//Delete the record
	public static String getDeleteSQL(String condition,Class<?> clazz) 
	{
		//get table name
		Table table=(Table)clazz.getAnnotation(Table.class);
		String tableName=table.tableName();
		//Build up sql sentence
		String SQL="DELETE FROM "+tableName+" WHERE "+condition+";";
		return SQL;
	}
	
	//Search the record
	public static String getSearchSQL(String condition,List<String> Attribute,Class<?> clazz) 
	{
		//get table name
		Table table=(Table)clazz.getAnnotation(Table.class);
		String tableName=table.tableName();
		
		String SQLA="SELECT ";
		String SQLB=" FROM "+tableName+" WHERE "+condition;
		Integer i;
		for(i=0;i<Attribute.size()-1;i++)
			SQLA=SQLA+Attribute.get(i)+",";
		SQLA=SQLA+Attribute.get(Attribute.size()-1);
		
		String SQL=SQLA+SQLB;
		return SQL;
	}
}
