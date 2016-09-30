<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testing JSPs</title>
    </head>
    <body>
        <h3>Testing JSPs</h3>
<%!
    public int add(int a, int b) {
        return a + b;
    }
%>
<%
    int i = 3;
    int j = 4;
    int k;
    k = add(i, j);
%>
Variable k value is: <%=k %>
<ol>
<% for (int l = 0; l < 5; l++) {
%>
        <li><%=l %></li>
<% }
%>
</ol>
    </body>
</html>
