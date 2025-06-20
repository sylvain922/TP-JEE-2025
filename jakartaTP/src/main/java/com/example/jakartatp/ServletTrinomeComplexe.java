package com.example.jakartatp;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletTrinomeComplexe", value = "/ServletTrinomeComplexe")
public class ServletTrinomeComplexe extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double a = Double.parseDouble(request.getParameter("a"));
        double b = Double.parseDouble(request.getParameter("b"));
        double c = Double.parseDouble(request.getParameter("c"));

        double discriminant = b * b - 4 * a * c;

        String x1 = "", x2 = "", message = "";

        if (discriminant < 0) {
            double partieReel = -b / (2 * a);
            double partieImg = Math.sqrt(-discriminant) / (2 * a);
            x1 = String.format("%.2f", partieReel) + " + " + String.format("%.2f", partieImg) + "i";
            x2 = String.format("%.2f", partieReel) + " - " + String.format("%.2f", partieImg) + "i";
            message = "Le discriminant est négatif. Les racines sont complexes.";
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
