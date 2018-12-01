package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Excutefile extends HttpServlet{
		
		
		private String path;       //file save path
		private String filename;
		private String upbound;    //The threshold upbound
		private String downbound;  //The threshold downbound
		private String verbose;
		private String gnf;       //If generate new feacture
		private String cvt;       //If perform cross validation
		
		public void setFileSavePath(String path) {
			this.path=path;
		}
		
		public String getFileSavePath() {
			return this.path;
		}
		
		public void setFileName(String filename) {
			this.filename=filename;
		}
		
		public String getFileName() {
			return this.filename;
		}
		
		public void setUpbound(String upboundnum, String upboundcoff) {
			this.upbound=upboundnum+"E+"+upboundcoff;
		}
		
		public String getUpbound() {
			return this.upbound;
		}
		public void setDownbound(String downboundnum, String downboundcoff) {
			this.downbound=downboundnum+"E+"+downboundcoff;
		}
		
		public String getDownbound() {
			return this.downbound;
		}
		
		public void setVerbose(String verbose) {
			this.verbose=verbose;
		}
		
		public String getVerbose() {
			return this.verbose;
		}
		
		public void setGnf(String gnf) {
			this.verbose=verbose;
		}
		
		public String getGnf() {
			return this.gnf;
		}
		
		public void setCvt(String cvt) {
			this.cvt=cvt;
		}
		
		public String getCvt() {
			return this.cvt;
		}
		
	private boolean checkVaildInput(String number) {
		
		    char ch;
			Pattern pattern=Pattern.compile("[0-9]*$");
			Matcher isNum=pattern.matcher(number);
			if(!isNum.matches()) {
				int numOfPoint=0,i;
				for(i=0;i<number.length();i++) {
					
					if(!Character.isDigit(number.charAt(i))) {
						if(number.charAt(i)=='.')
							numOfPoint++;
						else return false;
					}
				}
				if(numOfPoint>1)
					return false;
				else
					return true;
			}
			return true;
	}
		
		
		public void ExcuteCode(String dir) {
			String lineStr;
			File pycodepath=new File("/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/start");
			
			String excuteCommond;
			
			if(this.upbound!=null&&this.downbound!=null) 
			{
				excuteCommond="python run_all.py"+" -th "+this.upbound+" -tl "+this.downbound;
			if(this.verbose=="on")
				excuteCommond=excuteCommond+" -v ";
			if(this.gnf=="on")
				excuteCommond=excuteCommond+" -g ";
			if(this.cvt=="on")
				excuteCommond=excuteCommond+" -c ";
			excuteCommond=excuteCommond+" "+this.filename;
			}
			else 
			{
				excuteCommond="python run_all.py "+this.filename+" -v";
			}
			System.out.println(excuteCommond);
			try {
				Process p=Runtime.getRuntime().exec(excuteCommond,null,pycodepath);
				
				BufferedInputStream in=new BufferedInputStream(p.getInputStream());
				BufferedReader inBr=new BufferedReader(new InputStreamReader(in));
				
				while((lineStr=inBr.readLine())!=null)
					System.out.println(lineStr);
				if(p.waitFor()!=0) {
					if(p.exitValue()==1)
						System.err.println("Excute faile");
				}
				inBr.close();
				in.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String savePath="/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/data/input_data";
		String upboundnum=request.getParameter("upboundnum");
		String upboundcoff=request.getParameter("upboundcoff");
		String downboundnum=request.getParameter("downboundnum");
		String downboundcoff=request.getParameter("downboundcoff");
		String filename=request.getParameter("filename");
		
		setFileSavePath(savePath);
		if(upboundnum==""||upboundcoff==""||downboundnum==""||downboundcoff=="") {
			System.out.println("Thresold has not set"+"\n");	
		}
		else {
			if(checkVaildInput(upboundnum)==true&&checkVaildInput(upboundcoff)==true)
				setUpbound(upboundnum,upboundcoff);
		    if(checkVaildInput(downboundnum)==true&&checkVaildInput(downboundcoff)==true)
			    setDownbound(downboundnum,downboundcoff);
		    setCvt(request.getParameter("cvt"));
		    setGnf(request.getParameter("gnf"));
		    setVerbose(request.getParameter("verbose"));
		}
		
		setFileName(filename);
		String resultFolder="/Users/maimac/Documents/JavaEEPRO/DNASequenceVerify/WebContent/silver-clusters-master/data/training_data/";
	    String resultPath=resultFolder+this.filename.substring(0, this.filename.lastIndexOf("."))+".combinedfeatures.csv";
	    System.out.println("The direct is :"+resultPath);
	    request.setAttribute("path",resultPath);
		request.getRequestDispatcher("/resultDownload.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
		ExcuteCode(this.path);
		//runpycode(this.path.substring(this.path.lastIndexOf("/")+1));
	}
}
