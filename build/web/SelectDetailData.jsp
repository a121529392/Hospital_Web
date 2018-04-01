
<%@ page contentType="text/html;"  pageEncoding="Big5" language="java" %>
<jsp:useBean id="GetChartElement" class="fitbitpack.GetChartElement" />
<%
    request.setCharacterEncoding("UTF-8");
    String user, date, table;
    user = request.getParameter("user");
    date = request.getParameter("date");
    table = request.getParameter("table");
    String[][] ans = GetChartElement.getdata(user, date, table);
    if (ans.length != 0) {

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                if (j == ans[i].length - 1) {
                    out.println(ans[i][j] + "?");
                } else {
                    out.println(ans[i][j]);
                }
            }
        }
    }
%>
