package com.example.jakartatp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/student";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM users WHERE login = ? AND password = md5(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                response.sendRedirect("ServletAccueil");
            } else {
                request.setAttribute("error", "Login ou mot de passe incorrect");
                request.getRequestDispatcher("connexion.jsp").forward(request, response);
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur de connexion à la base de données.");
            request.getRequestDispatcher("connexion.jsp").forward(request, response);
        }
    }
}
