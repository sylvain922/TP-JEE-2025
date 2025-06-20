<%@ page pageEncoding="UTF-8" %>
<%! String title = "Connexion"; %>

<%@ include file="fragment/header.jsp" %>
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

<main>
    <h1>Connexion</h1>

    <form action="ServletLogin" method="POST">
        <label for="login">Login :</label>
        <input type="text" id="login" name="login" required><br><br>

        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Se connecter">
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p style="color:red;"><%= error %></p>
    <%
        }
    %>
</main>

<%@ include file="fragment/footer.jsp" %>
