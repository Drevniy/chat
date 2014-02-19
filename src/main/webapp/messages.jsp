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
		
	  if(request.getParameter("message")==null)
	  {
		  String jsonString = toJSON(getListMessages());
		  out.print(jsonString);
	      out.flush();
		  //out.close();
	  }else{
		  setMessages2ListMessages(request.getParameter("userName"),request.getParameter("message"));
		  
		  String jsonString = toJSON(getListMessages());
		  out.print("{\"success\":true,\"msg\":\"user added\",\"persons\":"+jsonString+"}");
	      out.flush();
		  //out.close();
	  }
	  
      
	    
	%>

    <%!
    
    private static ArrayList<Messages> listMessages = new ArrayList<Messages>();
    
    public void setMessages2ListMessages(String userName, String message){
    	
    	Messages messages = new Messages();
    	
    	messages.setUserName(userName);
    	messages.setMessage(userName+": "+message);
    	listMessages.add(messages);
    }
    
    public static ArrayList<Messages> getListMessages(){
    	return listMessages;
    }
    
    public static String toJSON(Object object){
    	Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
		
	}
	
	private class Messages {
		
		private String date = "";
		private String userName = "";
		private String message = "";
		
		public void setDate(String date)
		{
			this.date = date;
		}
		public void setUserName(String userName)
		{
			this.userName = userName;
		}
		public void setMessage(String message)
		{
			this.message = message;
		}
		
		
	}
		
	%>