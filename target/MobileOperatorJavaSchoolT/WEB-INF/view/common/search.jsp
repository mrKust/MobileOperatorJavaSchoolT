<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%
    try {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ecare?useSSL=false");
        Statement statement = connection.createStatement();
        String number = request.getParameter("phoneNumber");
        String sqlString = "select * from client where phone_number='"+number+"'";
        ResultSet res = statement.executeQuery(sqlString);
        if (res != null) {
            response.sendRedirect("/allClients");
        } else {
            response.sendRedirect("/");
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());

    }
%>