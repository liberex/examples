package net.liberex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemPropertiesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        out.println("<h1>System Properties</h1>");
        out.println("<ul>");
        for (Entry<Object, Object> e : System.getProperties().entrySet()) {
            out.println("<li>" + e.getKey().toString() + "=" + e.getValue().toString() + "</li>");
        }

        out.println("</ul>");
    }
}
