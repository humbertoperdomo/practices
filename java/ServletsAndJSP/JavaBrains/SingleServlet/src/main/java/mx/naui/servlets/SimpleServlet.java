package mx.naui.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description="A simple servlet", urlPatterns={"/SimpleServlet"},
        initParams={@WebInitParam(name="defaultUser", value="John Dow")})
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SimpleServlet() {
        super();
        System.out.println("SimpleServlet()");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init(ServletConfig)");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
            System.out.println("doGet(HttpServletRequest, HttpServletResponse)");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            String userName = request.getParameter("userName");
            HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        if (userName != null && !userName.equals("")) {
            session.setAttribute("savedUserName", userName);
            context.setAttribute("savedUserName", userName);
        }
        writer.println("<h3>Hello " + userName + "</h3>");
        writer.println("<h3>Hello "
                + (String) session.getAttribute("savedUserName")
                + "</h3>");
        writer.println("<h3>Hello "
                + (String) context.getAttribute("savedUserName")
                + "</h3>");
        writer.println("<h3>Hello "
                + getServletConfig().getInitParameter("defaultUser")
                + "</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost(HttpServletRequest, HttpServletResponse)");
        doGet(request, response);
    }
}
