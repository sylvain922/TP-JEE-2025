package com.example.jakartatp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "ServletHorner", value = "/ServletHorner")
public class ServletHorner extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("connexion.jsp").forward(request, response);

            return;
        }
        request.getRequestDispatcher("horner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));
            double x = Double.parseDouble(request.getParameter("x"));

            double resultat = (a * x + b) * x + c;

            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            request.setAttribute("x", x);
            request.setAttribute("resultat", resultat);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Veuillez saisir des nombres valides.");
        }

        request.getRequestDispatcher("horner.jsp").forward(request, response);
    }
}
