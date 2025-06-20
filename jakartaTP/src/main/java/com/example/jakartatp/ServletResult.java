package com.example.jakartatp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletResult", value = "/ServletResult")
public class ServletResult extends HttpServlet {

    public static class Result {
        public int id;
        public double a, b, c, x, resultat;

        public Result(int id, double a, double b, double c, double x, double resultat) {
            this.id = id;
            this.a = a;
            this.b = b;
            this.c = c;
            this.x = x;
            this.resultat = resultat;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Result> results = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student", "root", "");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM resultat");

            while (rs.next()) {
                results.add(new Result(
                        rs.getInt("id"),
                        rs.getDouble("a"),
                        rs.getDouble("b"),
                        rs.getDouble("c"),
                        rs.getDouble("x"),
                        rs.getDouble("resultat")
                ));
            }

            conn.close();

            request.setAttribute("results", results);

        } catch (Exception e) {
            request.setAttribute("message", "Erreur : " + e.getMessage());
        }

        request.getRequestDispatcher("ResultatHorner.jsp").forward(request, response);
    }
}
