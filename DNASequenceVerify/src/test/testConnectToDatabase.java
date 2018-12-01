package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mysql.jdbc.Connection;

import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class testConnectToDatabase {
	public static void main(String arg[]) {
		DataBaseUtils.config("jdbc.properties");
		
//		List<String> cont=new ArrayList<String>();
//		cont.add("*");
//		String sql=TableUtils.getSearchSQL("1", cont, User.class);
//		System.out.println(sql);
//		User user= DataBaseUtils.queryForBean(sql,User.class);
		
		String fid=UUID.randomUUID().toString();
		String uid=UUID.randomUUID().toString();
		String name="integrated-intensity2.csv";
		String filepath="/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data/integrated-intensity.csv";
		String sql="Insert DS_files (fileid,filename,filepath,userid) Values('"+fid+"','"+name+"','"+filepath+"','"+uid+"');";
		
		DataBaseUtils.update(sql);
		System.out.println(sql);
		//select * from DS_Files where filename='integrated-intensity.csv' and userid ='6c33f360-7667-491d-9822-4bef6c8a5513';
	}

}
