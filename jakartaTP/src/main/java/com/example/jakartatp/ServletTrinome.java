package com.example.jakartatp;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletTrinome", value = "/ServletTrinome")
public class ServletTrinome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("trinome.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double a = Double.parseDouble(request.getParameter("a"));
        double b = Double.parseDouble(request.getParameter("b"));
        double c = Double.parseDouble(request.getParameter("c"));

        double discriminant = b * b - 4 * a * c;

        String x1 = "", x2 = "", message = "";

        if (discriminant < 0) {
            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletTrinomeComplexe");
            dispatcher.forward(request, response);
        } else {
            if (discriminant > 0) {
                x1 = String.format("%.2f", (-b + Math.sqrt(discriminant)) / (2 * a));
                x2 = String.format("%.2f", (-b - Math.sqrt(discriminant)) / (2 * a));
                message = "Les racines sont réelles et distinctes.";
            } else if (discriminant == 0) {
                x1 = x2 = String.format("%.2f", -b / (2 * a));
                message = "Il y a une racine réelle double : x = " + x1;
            }

            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            request.setAttribute("x1", x1);
            request.setAttribute("x2", x2);
            request.setAttribute("message", message);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ResultatTrinome.jsp");
            dispatcher.forward(request, response);
        }
    }
}
