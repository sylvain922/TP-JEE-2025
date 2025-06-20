package com.example.jakartatp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletResultDelete", value = "/ServletResultDelete")
public class ServletResultDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "")) {
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM resultat WHERE id = ?");
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            } catch (Exception e) {
                request.setAttribute("message", "Erreur lors de la suppression : " + e.getMessage());
                request.getRequestDispatcher("ServletResult").forward(request, response);
                return;
            }
        }
        response.sendRedirect("ServletResult");
    }
}
