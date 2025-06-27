<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String title = "Horner - Polynôme degré 2"; %>
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
  <h1>Méthode de Horner</h1>
  <p>Le polynôme est : <strong><em>P(x) = ax² + bx + c</em></strong> où a,b et c sont des réels</p>
  <p><em>La méthode Horner permet de calculer la valeur de P(x0) pour un x0 donné</em></p>

  <form action="ServletHorner" method="POST">
    <label for="a">a :</label>
    <input type="number" step="any" id="a" name="a" required><br><br>

    <label for="b">b :</label>
    <input type="number" step="any" id="b" name="b" required><br><br>

    <label for="c">c :</label>
    <input type="number" step="any" id="c" name="c" required><br><br>

    <label for="x">Valeur de x :</label>
    <input type="number" step="any" id="x" name="x" required><br><br>

    <input type="submit" value="Calculer">
  </form>

  <%
    Double resultat = (Double) request.getAttribute("resultat");
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");

    if (error != null) {
  %>
  <p style="color:red;"><strong><%= error %></strong></p>
  <%
  } else if (resultat != null) {
    double a = (double) request.getAttribute("a");
    double b = (double) request.getAttribute("b");
    double c = (double) request.getAttribute("c");
    double x = (double) request.getAttribute("x");
  %>

  <%
    if (message != null) {
  %>
  <p style="color: green;"><strong><%= message %></strong></p>
  <%
    }
  %>

  <h2>Résultat</h2>
  <table border="1" style="margin: auto; border-collapse: collapse; text-align: center;">
    <thead>
    <tr>
      <th>a</th>
      <th>b</th>
      <th>c</th>
      <th>x</th>
      <th>P(x)</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><%= a %></td>
      <td><%= b %></td>
      <td><%= c %></td>
      <td><%= x %></td>
      <td><%= String.format("%.6f", resultat) %></td>
    </tr>
    </tbody>
  </table>

  <br>

  <form id="saveForm" action="ServletSauvegarde" method="POST" style="display: inline;">
    <input type="hidden" name="a" value="<%= a %>">
    <input type="hidden" name="b" value="<%= b %>">
    <input type="hidden" name="c" value="<%= c %>">
    <input type="hidden" name="x" value="<%= x %>">
    <input type="hidden" name="resultat" value="<%= resultat %>">
    <button type="submit">Enregistrer le résultat</button>
  </form>

  <form action="ServletResult" method="GET" style="display: inline;">
    <button type="submit">Afficher les résultats enregistrés</button>
  </form>

  <%
    }
  %>
</main>

<%@ include file="fragment/footer.jsp" %>
