package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import com.google.gson.*;

public final class usersOnline_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    
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
