package com.example.jakartatp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "ServletDamier", value = "/ServletDamier")
public class ServletDamier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("damier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rowsStr = request.getParameter("rows");
        String colsStr = request.getParameter("cols");
        String blackColor = request.getParameter("blackColor"); // couleur personnalisée du "noir"

        Integer rows = null;
        Integer cols = null;

        try {
            rows = Integer.parseInt(rowsStr);
            cols = Integer.parseInt(colsStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Veuillez entrer des nombres valides pour les lignes et colonnes.");
        }

        if (rows != null && cols != null && blackColor != null && !blackColor.isEmpty()) {
            request.setAttribute("rows", rows);
            request.setAttribute("cols", cols);
            request.setAttribute("blackColor", blackColor);
        } else {
            // Valeur par défaut si aucune couleur choisie
            request.setAttribute("blackColor", "#000000");
        }

        request.getRequestDispatcher("damier.jsp").forward(request, response);
    }
}
