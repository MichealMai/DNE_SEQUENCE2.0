package bean;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import annotation.Table;
import annotation.column;

@Table(tableName="DS_LibsvmParameterValue")
public class LibsvmParameterValue {
	@column(type="VARCHAR(20)",field="thresholdhighvalue")
	private String thresholdhighvalue;
	@column(type="VARCHAR(20)",field="thresholdlowvalue")
	private String thresholdlowvalue;
	@column(type="VARCHAR(10)",field="thresholdhigh")
	private String thresholdhigh;
	@column(type="VARCHAR(10)",field="thresholdlow")
	private String thresholdlow;
	@column(type="VARCHAR(10)",field="verbose")
	private String verbose;
	@column(type="VARCHAR(10)",field="crossvalidtiontest")
	private String crossvalidtiontest;
	@column(type="VARCHAR(10)",field="generatenewfeatures")
	private String generatenewfeatures;
	@column(type="VARCHAR(100)",field="expid",defaultNull=false)
	private String expid;       //Experiment primary key
	
	//dynamical call method
	public static Object get(String field,Object obj) throws IntrospectionException 
	{
		Object result=null;
		try 
		{
			Class clazz=obj.getClass();
			PropertyDescriptor pd=new PropertyDescriptor(field,clazz);
			Method getMethod=pd.getReadMethod();
			result=getMethod.invoke(obj);	
		}catch(SecurityException e) {
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(IllegalAccessException e) {
			e.printStackTrace();
		}catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void set(Object obj, String field, String value) {

		try 
		{
			Class clazz = obj.getClass();

			PropertyDescriptor pd = new PropertyDescriptor(field, clazz);

			Method setMethod = pd.getWriteMethod();

			setMethod.invoke(obj, value);
		}	catch (SecurityException e) {

			e.printStackTrace();

			} catch (IllegalArgumentException e) {

			e.printStackTrace();

			} catch (IntrospectionException e) {

			e.printStackTrace();

			} catch (IllegalAccessException e) {

			e.printStackTrace();

			} catch (InvocationTargetException e) {

			e.printStackTrace();
			}
	}

		


	
	//set up data
	public void setThresholdhigh(String sth)
	{
		this.thresholdhigh=sth;
	}
	
	public void setThresholdlow(String stl)
	{
		this.thresholdlow=stl;
	}
	
	public void setThresholdhighvalue(String sthv)
	{
		this.thresholdhighvalue=sthv;
	}
	
	public void setThresholdlowvalue(String stlv)
	{
		this.thresholdlowvalue=stlv;
	}
	
	public void setVerbose(String verbose) 
	{
		this.verbose=verbose;
	}
	
	public void setCrossvalidtiontest(String cvt) 
	{
		this.crossvalidtiontest=cvt;
	}
	public void setGeneratenewfeatures(String gnf) 
	{
		this.generatenewfeatures=gnf;
	}
	public void setExpid(String id)
	{
		this.expid=id;
	}
	
	//get the data
	
	public String getThresholdhigh()
	{
		return this.thresholdhigh;
	}
	
	public String getThresholdlow()
	{
		return this.thresholdlow;
	}
	
	public String getThresholdhighvalue()
	{
		return this.thresholdhighvalue;
	}
	
	public String getThresholdlowvalue()
	{
		return this.thresholdlowvalue;
	}
	
	public String getVerbose() 
	{
		return this.verbose;
	}
	
	public String getCrossvalidtiontest() 
	{
		return this.crossvalidtiontest;
	}
	public String getGeneratenewfeatures() 
	{
		return this.generatenewfeatures;
	}
	
	public String getExpid()
	{
		return this.expid;
	}
	
	
	

}
