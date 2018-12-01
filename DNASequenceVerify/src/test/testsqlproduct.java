package test;

import util.DataBaseUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import bean.Algorithm;
import bean.Experiments;
import bean.Files;
import bean.LibsvmParameterValue;
import bean.ParameterCommand;
import bean.User;
import util.TableUtils;


public class testsqlproduct {
	public static void main(String[] args) 
	{
//		String sql=TableUtils.getCreateTableSQL(LibsvmParameterValue.class);
//		System.out.println(sql);
		
//		List<String> cot =new ArrayList<String>();
//
//		UUID id=UUID.randomUUID();
//		cot.add(id.toString());
//		cot.add("8061653e-aa94-42dd-bd43-de939bd6e1d8");
//		cot.add("threshold high value");
//		cot.add("value");
//
//////		alg.add("/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/start");
//		
//		
//		List<String> col=new ArrayList<String>();
//		col.add("paraid");
//		col.add("algid");
//		col.add("paraname");
//		col.add("paratype");
//		
//		String sql=TableUtils.getInsertSQL(cot, col, ParameterCommand.class);
//		System.out.println(sql);
		
		
		
//		List<String> cont=new ArrayList<String>();
//		cont.add("username");
//		String sql=TableUtils.getSearchSQL("where 1", cont, User.class);
//		
//		
//		List<String> cont=new ArrayList<String>();
//		UUID id=UUID.randomUUID();
//		cont.add("c66122e6-a1b8-4530-9d1f-4b1f3909b43f");
//		cont.add("no");
//		cont.add("no");
//		cont.add("no");
//		cont.add("no");
//		cont.add("no");
//		List<String> col=new ArrayList<String>();
//		col.add("expid");
//		col.add("thresholdhigh");
//		col.add("thresholdlow");
//		col.add("verbose");
//		col.add("crossvalidtiontest");
//		col.add("generatenewfeatures");
//
//		String sql=TableUtils.getInsertSQL(cont, col, LibsvmParameterValue.class);
//		System.out.println(sql);

//		String username="admin";
//		String sqlA="select fileid,filename,filepath from DS_Files natural join DS_user where username='"+username+"'";
//		List<Map<String,Object>> fileMap=new ArrayList<Map<String,Object>>();
//		fileMap=DataBaseUtils.queryForList(sqlA);
//		for(Integer i=0;i<fileMap.size();i++) 
//		{
//			System.out.println(fileMap.get(i).get("filename"));
//		}
//		System.out.println(sqlA);
		
//		List<Map<String,Object>> parameter=new ArrayList<Map<String,Object>>();
//		
//		
//		String sqlC="select paraname,paracommand,paratype from DS_ParameterCommand natrual join (select * from DS_Experiments natural join DS_user where username='"+username+"') as alg";
//		parameter=DataBaseUtils.queryForList(sqlC);
//		String sqlD="select ";
//		for(Integer i=0;i<parameter.size()-1;i++) 
//		{
//			sqlD=sqlD+parameter.get(i).get("paraname")+",";
//		}
//		sqlD=sqlD+parameter.get(parameter.size()-1).get("paraname");
//		sqlD=sqlD+" from DNA_Sequence.DS_LibsvmParameterValue natrual join (select * from DS_Experiments natural join DS_user where username='"+username+"' ) as alg;";
//		
//		System.out.println(sqlD);
		//System.out.println(parameter.size());
//		List<String> att=new ArrayList<String>();
//		att.add("username");
//		att.add("password");
//		String condition="sex='0'";
//		String sql=TableUtils.getSearchSQL(condition, att,User.class);
//		String username="admin";
//		String sql="select fileid,filename,filepath,userid from DS_Files natural join (DS_Experiments natural join DS_user) where username='"+username+"'";
//		Files file=DataBaseUtils.queryForBean(sql,Files.class);
//		String filename=file.getFilename();
//		System.out.println(filename);
		String a="DS_LibsvmParameterCommand";
		String b=a.substring(a.lastIndexOf("_")+1,a.lastIndexOf("P"))+"ParameterValue";
		System.out.println(b);

	}

}
