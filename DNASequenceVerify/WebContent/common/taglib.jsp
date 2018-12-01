<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.lang.String"
    pageEncoding="UTF-8"%>

<%
    String path =request.getContextPath();
    int port =request.getServerPort();
    	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    request.setAttribute("basePath",basePath);
%>
