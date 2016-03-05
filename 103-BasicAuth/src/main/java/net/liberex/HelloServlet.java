package net.liberex;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.debug("Startin the login servlet.");

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            out.println("<h1>Hello " + principal.getName() + "</h1>");
        }
        else {
            out.println("<h1>Failed Authentication</h1>");
        }
    }
}
