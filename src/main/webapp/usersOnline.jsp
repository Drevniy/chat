<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

		<%@page import="java.util.ArrayList"%>       
         <%@page import = "java.sql.*" %>
        <%@page import = "java.io.PrintWriter" %>
        <%@page import = "java.lang.reflect.Type" %>
		<%@page import = "com.google.gson.*" %>

  <%
	response.setContentType("text/json");
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache");
		
	  if(request.getParameter("login")==null)
	  {
		  String jsonString = toJSON(getListUsers());
		  out.print(jsonString);
	      out.flush();
		  //out.close();
	  }else{
		  setUserName2ListUsers(request.getParameter("login"));
		  
		  String jsonString = toJSON(getListUsers());
		  out.print("{\"success\":true,\"msg\":\"user added\",\"persons\":"+jsonString+"}");
	      out.flush();
		  //out.close();
	  }
	  
      
	    
	%>

    <%!
    
    private static ArrayList<Users> listUsers = new ArrayList<Users>();
    
    public synchronized void setUserName2ListUsers(String userName){
    	Users users = new Users();
    	users.setUserName(userName);
    	listUsers.add(users);
    }
    
    public static ArrayList<Users> getListUsers(){
    	return listUsers;
    }
    
    public static String toJSON(Object object){
    	Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
		
	}
	
	private class Users {
		private String userName = "";
		
		public void setUserName(String userName)
		{
			this.userName = userName;
		}
		
	}
		
	%>