package com.example.jakartatp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletSauvegarde", value = "/ServletSauvegarde")
public class ServletSauvegarde extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));
            double x = Double.parseDouble(request.getParameter("x"));
            double resultat = Double.parseDouble(request.getParameter("resultat"));

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student", "root", "")) {

                String sql = "INSERT INTO resultat (a, b, c, x, resultat) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setDouble(1, a);
                    stmt.setDouble(2, b);
                    stmt.setDouble(3, c);
                    stmt.setDouble(4, x);
                    stmt.setDouble(5, resultat);
                    stmt.executeUpdate();
                }
            }

            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            request.setAttribute("x", x);
            request.setAttribute("resultat", resultat);
            request.setAttribute("message", "Résultat enregistré avec succès.");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'enregistrement : " + e.getMessage());
        }

        request.getRequestDispatcher("horner.jsp").forward(request, response);
    }
}
