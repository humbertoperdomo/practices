<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Objects</title>
    </head>
    <body>
    <%
    String userName = request.getParameter("userName");

    if (userName != null) {
        //session.setAttribute("sessionUserName", userName);
        //application.setAttribute("applicationUserName", userName);
        pageContext.setAttribute("pageContextUserName", userName);
        pageContext.setAttribute("applicationUserName", userName, PageContext.APPLICATION_SCOPE);
        pageContext.setAttribute("sessionUserName", userName, PageContext.SESSION_SCOPE);
    }
    %>
    user name in the page context is: <%=pageContext.getAttribute("pageContextUserName") %>
    <br/>
    user name in the request object is: <%=userName %>
    <br/>
    user name in the session object is: <%=session.getAttribute("sessionUserName") %>
    <br/>
    user name in the application context is: <%=application.getAttribute("applicationUserName") %>
    </body>
</html>
