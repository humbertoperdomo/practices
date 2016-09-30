<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Init page</title>
    </head>
    <%!
    @Override
    public void jspInit() {
        String defaultUser = getServletConfig().getInitParameter("defaultUser");
        // ServletContext is available across the application.
        ServletContext context = getServletContext();
        context.setAttribute("defaultUser", defaultUser);
    }
    %>
    <body>
    The default user from the servlet config is: <%=getServletConfig().getInitParameter("defaultUser") %>
    <br/>
    The value in the servlet context is: <%=getServletContext().getAttribute("defaultUser") %>
    </body>
</html>
