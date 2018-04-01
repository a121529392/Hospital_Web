
<%@ page contentType="text/html;"  pageEncoding="Big5" language="java" %>
<jsp:useBean id="DeleteData" class="fitbitpack.DeleteData" />
<%
    request.setCharacterEncoding("UTF-8");
    String user, startdate,enddate, table;
    user = request.getParameter("user");
    startdate = request.getParameter("startdate");
    enddate = request.getParameter("enddate");
    table = request.getParameter("table");
    DeleteData.deletedata(user, startdate,enddate,table); 

%>

