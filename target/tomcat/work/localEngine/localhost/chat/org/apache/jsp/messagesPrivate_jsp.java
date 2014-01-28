package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import com.google.gson.*;

public final class messagesPrivate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    
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
		
	
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t       \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("  ");

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
	    
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
