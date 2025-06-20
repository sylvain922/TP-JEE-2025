<%@ page pageEncoding="UTF-8" language="java" %>
<%! String title = "Accueil"; %>

<%
  session = request.getSession(false);
  if (session == null || session.getAttribute("user") == null) {
    response.sendRedirect("connexion.jsp");
    return;
  }
  String user = (String) session.getAttribute("user");
%>

<%@ include file="fragment/header.jsp" %>
<%@ include file="fragment/barnav-login.jsp" %>

<main>
  <h2>Bienvenue, <%= user %> !</h2>
</main>

<%@ include file="fragment/footer.jsp" %>