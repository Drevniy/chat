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
		PrintWriter writer = response.getWriter();
		
		ArrayList<Person> listPerson = new ArrayList<Person>();
		listPerson = read();
		String jsonString = toJSON(listPerson);
        out.print("{\"success\":true,\"msg\":\"Consignment updated\",\"persons\":"+jsonString+"}");
        out.flush();
		out.close();
  
	
	%>

    <%!
    public static String toJSON(Object object){
    	Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
		
	}
	public void delete (String idrow, PrintWriter writer) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        Statement st = conn.createStatement();
        st.execute("delete from person where id = "+idrow);

        writer.append("<cmd>").append("delete").append("</cmd>");
		writer.append("<person>");
		writer.append("<id>").append(idrow).append("</id>");
		writer.append("</person>");

        st.close();
        conn.close();
	}
	public void create (String id, String firstName, String lastName, String age, PrintWriter writer) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        Statement st = conn.createStatement();
        st.execute("insert into person values ("+Integer.parseInt(id)+", '"+firstName+"', '"+lastName+"', "+Integer.parseInt(age)+")");

        writer.append("<cmd>").append("create").append("</cmd>");

			writer.append("<person>");
			writer.append("<id>").append(id).append("</id>");
			writer.append("<firstname>").append(firstName).append("</firstname>");
			writer.append("<lastname>").append(lastName).append("</lastname>");
			writer.append("<age>").append(age).append("</age>");
			writer.append("</person>");

		st.close();
        conn.close();
	}
	public void update (String id, String firstName, String lastName, String age, PrintWriter writer) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        Statement st = conn.createStatement();
        st.execute("Update PERSON set id = "+Integer.parseInt(id)+", firstname = '"+firstName+"', lastname = '"+lastName+"', age = " +Integer.parseInt(age)+"where id = "+Integer.parseInt(id));

		writer.append("<cmd>").append("update").append("</cmd>");

			writer.append("<person>");
			writer.append("<id>").append(id).append("</id>");
			writer.append("<firstname>").append(firstName).append("</firstname>");
			writer.append("<lastname>").append(lastName).append("</lastname>");
			writer.append("<age>").append(age).append("</age>");
			writer.append("</person>");

		st.close();
        conn.close();
	}
	
	public ArrayList<Person> read() throws ClassNotFoundException, SQLException
	{
		
		 	
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        
        Statement st = conn.createStatement();
        ResultSet result;
        result = st.executeQuery("select * from person");
		
		String id = "";
		String fName = "";
		String lName = "";
		String age = "";
		
		ArrayList<Person> listPerson = new ArrayList<Person>(); 
		while (result.next())
        {	
			Person person = new Person();
			person.setId(result.getString("id"));
			person.setFirstname(result.getString("firstname"));
			person.setLastname(result.getString("lastname"));
			person.setAge(result.getString("age"));
			listPerson.add(person);
	    }
		st.close();
        conn.close();
        
        return listPerson;
	}
	
	private class Person {
		private String id = "";
		private String firstname = "";
		private String lastname = "";
		private String age = "";
		
		public void setId(String id)
		{
			this.id = id;
		}
		
		public void setFirstname(String firstname)
		{
			this.firstname = firstname;
		}
		
		public void setLastname(String lastname)
		{
			this.lastname = lastname;
		}
		
		public void setAge(String age)
		{
			this.age = age;
		}
	}
		
	%>