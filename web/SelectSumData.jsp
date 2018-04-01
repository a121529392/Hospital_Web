
<%@ page contentType="text/html;"  pageEncoding="Big5" language="java" %>
<jsp:useBean id="SelectData" class="fitbitpack.SelectData" />
<%
    request.setCharacterEncoding("UTF-8");
    String user, startdate,enddate, table;
    user = request.getParameter("user");
    startdate = request.getParameter("startdate");
    enddate = request.getParameter("enddate");
    table = request.getParameter("table");
    String[][] json = SelectData.getdata(user, startdate,enddate,table); 

%>
<h1>Summary TABLE</h1>
<table style="border:3px #418fc6 solid;padding:5px;" rules="all" cellpadding='5' id="SumCSVTable">
    <%    for (int i = 0; i < json.length; i++) {
    %>
    <tr>
        <%
            for (int j = 0; j < json[i].length; j++) {
        %>
        <td><%
            out.print(json[i][j]);%>
        </td><%
                }
                out.print("\n");
            }

        %>
</table>

    <br/>
    