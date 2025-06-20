<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%! String title = "Damier"; %>
<%@include file="fragment/header.jsp"%>
<%
    String user = (String) session.getAttribute("user");
    if (user != null) {
%>
<%@ include file="fragment/barnav-login.jsp" %>
<%
} else {
%>
<%@ include file="fragment/barnav.jsp" %>
<%
    }
%>
<style>
    table.damier {
        border-collapse: collapse;
        border: 2px solid black;
        margin: 20px auto;
        width: auto !important;
    }
    table.damier td {
        border: none !important;
    }
    table.damier td.noir {
    }
    table.damier td.blanc {
        background-color: white !important;
    }
</style>
<main>
    <h1>Créer un damier</h1>
    <form action="ServletDamier" method="POST">
        <label for="rows">Nombre de lignes :</label>
        <input type="number" id="rows" name="rows" min="1" max="50"
               value="<%= request.getAttribute("rows") != null ? request.getAttribute("rows") : "" %>" required><br><br>

        <label for="cols">Nombre de colonnes :</label>
        <input type="number" id="cols" name="cols" min="1" max="50"
               value="<%= request.getAttribute("cols") != null ? request.getAttribute("cols") : "" %>" required><br><br>

        <label for="blackColor">Couleur des cases</label>
        <input type="color" id="blackColor" name="blackColor"
               value="<%= request.getAttribute("blackColor") != null ? request.getAttribute("blackColor") : "#000000" %>"><br><br>

        <input type="submit" value="Générer le damier">
    </form>
</main>

<%
    Integer rows = (Integer) request.getAttribute("rows");
    Integer cols = (Integer) request.getAttribute("cols");
    String blackColor = (String) request.getAttribute("blackColor");
    String error = (String) request.getAttribute("error");

    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }

    if (rows != null && cols != null) {
        if (blackColor == null || blackColor.isEmpty()) {
            blackColor = "#000000"; // valeur par défaut
        }
%>

<h2>Damier : <%= rows %> lignes x <%= cols %> colonnes</h2>

<table class="damier">
    <%
        for (int i = 0; i < rows; i++) {
    %>
    <tr>
        <%
            for (int j = 0; j < cols; j++) {
                boolean isBlack = ((i + j) % 2 == 0);
                String color = isBlack ? blackColor : "#ffffff";
        %>
        <td style="width:30px; height:30px; background-color: <%= color %>;"></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>

<%
    }
%>

<%@ include file="fragment/footer.jsp" %>
