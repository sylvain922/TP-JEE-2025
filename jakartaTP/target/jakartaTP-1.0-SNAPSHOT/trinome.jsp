<%@ page pageEncoding="UTF-8"%>
<%! String title = "Trinôme"; %>
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
<main>
    <h1>Résolution d'équations de degré 2</h1>
    <p>L'équation est : <strong><em>ax² + bx + c = 0</em></strong> où a,b et c sont des réels</p>

    <form action="ServletTrinome" method="POST">
        <label for="a">a :</label>
        <input type="number" id="a" name="a" required><br><br>

        <label for="b">b :</label>
        <input type="number" id="b" name="b" required><br><br>

        <label for="c">c :</label>
        <input type="number" id="c" name="c" required><br><br>

        <input type="submit" value="Calculer">
    </form>
</main>

<%@include file="fragment/footer.jsp"%>
