package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class runSystemCall {
	
	public static void main(String[] args) {
		File dir=new File("/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/start/");
		try {
			
			//String cmdStr="ping www.baidu.com";
			String cmdStr2="python run_all.py integrated-intensity.csv -v";
			
			Process p2=Runtime.getRuntime().exec(cmdStr2,null,dir);
			//p1.waitFor();
			p2.waitFor();
			BufferedInputStream in=new BufferedInputStream(p2.getInputStream());
			BufferedReader inBr=new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while((lineStr=inBr.readLine())!=null)
				System.out.println(lineStr);
			if(p2.waitFor()!=0) {
				if(p2.exitValue()==1)
					System.err.println("Excute faile");
			}
			inBr.close();
			in.close();
			System.out.println("All is done");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
