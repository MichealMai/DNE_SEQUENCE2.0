package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	public void download(String filename,String saveurl,HttpServletResponse response) throws IOException 
	{
		URL url=new URL(saveurl);
		URLConnection conn=url.openConnection();
		int filesize=conn.getContentLength();
		BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
		response.reset();
		response.setHeader("content-disposition","attachment;filename="+filename);
		BufferedOutputStream out=new BufferedOutputStream(response.getOutputStream());
		byte buffer[]=new byte[65536];
		Integer len=0;
		while((len=in.read(buffer,0,65536))>0)  
		{
			out.write(buffer,0,len);
		}
		in.close();
		out.flush();
		out.close();
	}

}
