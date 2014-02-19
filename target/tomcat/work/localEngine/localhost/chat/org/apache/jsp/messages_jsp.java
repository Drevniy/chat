package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import com.google.gson.*;

public final class messages_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    
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
      out.write("         \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("  ");

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
