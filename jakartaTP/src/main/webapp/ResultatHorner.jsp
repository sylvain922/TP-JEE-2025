<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String title = "Résultat Horner"; %>
<%@ include file="fragment/header.jsp" %>

<%
  String user = (String) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<%@ include file="fragment/barnav-login.jsp" %>
<main>
  <h1>Liste des résultats enregistrés</h1>

  <%
    String message = (String) request.getAttribute("message");
    if (message != null) {
  %>
  <p style="color: red;"><strong><%= message %></strong></p>
  <%
    }

    java.util.List<com.example.jakartatp.ServletResult.Result> results =
            (java.util.List<com.example.jakartatp.ServletResult.Result>) request.getAttribute("results");

    if (results == null || results.isEmpty()) {
  %>
  <p>Aucun résultat enregistré.</p>
  <%
  } else {
  %>

  <table border="1" style="margin: auto; border-collapse: collapse; text-align: center;">
    <thead>
    <tr>
      <th>a</th>
      <th>b</th>
      <th>c</th>
      <th>x</th>
      <th>P(x)</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
      for (com.example.jakartatp.ServletResult.Result r : results) {
    %>
    <tr>
      <td><%= r.a %></td>
      <td><%= r.b %></td>
      <td><%= r.c %></td>
      <td><%= r.x %></td>
      <td><%= String.format("%.6f", r.resultat) %></td>
      <td>
        <form method="POST" action="ServletResultDelete" onsubmit="return confirm('Confirmer la suppression ?');">
          <input type="hidden" name="id" value="<%= r.id %>">
          <input type="submit" value="Supprimer">
        </form>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>

  <%
    }
  %>

  <br>
  <a href="ServletHorner">Retour au calcul</a>

</main>

<%@ include file="fragment/footer.jsp" %>
