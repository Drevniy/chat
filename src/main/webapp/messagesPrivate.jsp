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
	
	if(request.getParameter("messageTo")==null)
	{
		for(int i=0;i<listPrivateMessages.size();i++)
		{
			PrivateMessages pm = listPrivateMessages.get(i);
			if(pm.getMessageTo().equals(request.getParameter("messageFrom")))
			{
				String jsonString = toJSON(getlistPrivateMessages(pm.getMessageFrom(), pm.getMessageTo()));
				out.print(jsonString);
				out.flush();
				//out.close();
			}
		}

	}else{
		
	  if(request.getParameter("message")!=null)
	  {
		setMessages2listPrivateMessages(request.getParameter("messageFrom"), request.getParameter("messageTo"),request.getParameter("message"));
	  }
	  String jsonString = toJSON(getlistPrivateMessages(request.getParameter("messageFrom"), request.getParameter("messageTo")));
		  out.print(jsonString);
	      out.flush();
		  //out.close();
    }  
	    
	%>

    <%!
    
    private static ArrayList<PrivateMessages> listPrivateMessages = new ArrayList<PrivateMessages>();
    
    public void setMessages2listPrivateMessages(String messageFrom, String messageTo, String message){
    	
    	PrivateMessages privateMessages = new PrivateMessages();
    	
    	privateMessages.setMessageFrom(messageFrom);
		privateMessages.setMessageTo(messageTo);
    	privateMessages.setMessage(messageFrom+": "+message);
    	listPrivateMessages.add(privateMessages);
    }
    
    public ArrayList<PrivateMessages> getlistPrivateMessages(String messageFrom, String messageTo)
	{
    	ArrayList<PrivateMessages> resListPrivateMessages = new ArrayList<PrivateMessages>();
    	
		for(int i=0;i<listPrivateMessages.size();i++)
		{
			PrivateMessages pm = listPrivateMessages.get(i);
			if((pm.getMessageFrom().equals(messageFrom)&&pm.getMessageTo().equals(messageTo))||(pm.getMessageFrom().equals(messageTo)&&pm.getMessageTo().equals(messageFrom)))
			{
				PrivateMessages privateMessages = new PrivateMessages();
				privateMessages.setMessage(pm.getMessage());
				privateMessages.setMessageTo(pm.getMessageTo());
				privateMessages.setMessageFrom(pm.getMessageFrom());
				resListPrivateMessages.add(privateMessages);
			}
		}
	
    	return resListPrivateMessages;
    }
    
    public static String toJSON(Object object){
    	Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
		
	}
	
	private class PrivateMessages {
		
		private String messageFrom = "";
		private String messageTo = "";
		private String message = "";
		
		public void setMessageFrom(String messageFrom)
		{
			this.messageFrom = messageFrom;
		}
		public void setMessageTo(String messageTo)
		{
			this.messageTo = messageTo;
		}
		public void setMessage(String message)
		{
			this.message = message;
		}
		
		public String getMessageFrom()
		{
			return this.messageFrom;
		}
		public String getMessageTo()
		{
			return this.messageTo;
		}
		public String getMessage()
		{
			return this.message;
		}
		
		
	}
		
	%>