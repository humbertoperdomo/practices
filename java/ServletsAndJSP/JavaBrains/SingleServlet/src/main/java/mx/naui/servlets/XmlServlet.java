package mx.naui.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description="A XML servlet", urlPatterns={"/xmlServlet"})
public class XmlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public XmlServlet() {
        super();
        System.out.println("XmlServlet");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init(ServletConfig)");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet(HttpServletRequest, HttpServletResponse)");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String userName = request.getParameter("userName");
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        writer.println("<h3>Hello " + userName + "</h3>");
        writer.println("<h3>Hello "
                + (String) session.getAttribute("savedUserName") 
                + "</h3>");
        writer.println("<h3>Hello "
                + (String) context.getAttribute("savedUserName") 
                + "</h3>");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("doPost(HttpServletRequest, HttpServletResponse)");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            String userName = request.getParameter("userName");
            String fullName = request.getParameter("fullName");
            String profession = request.getParameter("profession");
            String a = ""; 

        if (profession.equals("developer")) {
            a = "You are a developer.";
        } else if (profession.equals("architect")) {
            a = "You are an architect.";
        }

        String location = request.getParameter("location");


        writer.println("<h3>Hello " + userName 
                + "</h3> We know your full name is: " + fullName 
                + "<br />and " + a
                + "<br />You are located " + location);
        String[] foods = request.getParameterValues("food");

        writer.println("<br />Your favorite food is: ");
        if (foods != null)
            for (String food : foods) {
                writer.println(food);
            }
    }
}
