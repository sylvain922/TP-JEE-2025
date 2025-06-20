<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String title = "Résultat Tinôme"; %>
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
<!DOCTYPE html>
<html>
<head>
  <title>Résultats du Trinôme</title>
  <link href="public/style.css" rel="stylesheet">
  <meta charset="UTF-8">
</head>
<body>
<h1>Résultats de l'équation du second degré</h1>

<table border="1">
  <thead>
  <tr>
    <th>Variable</th>
    <th>Valeur</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td><strong>a</strong></td>
    <td>${a}</td>
  </tr>
  <tr>
    <td><strong>b</strong></td>
    <td>${b}</td>
  </tr>
  <tr>
    <td><strong>c</strong></td>
    <td>${c}</td>
  </tr>
  <tr>
    <td><strong>Racine 1 (x1)</strong></td>
    <td>${x1}</td>
  </tr>
  <tr>
    <td><strong>Racine 2 (x2)</strong></td>
    <td>${x2}</td>
  </tr>
  <tr>
    <td><strong>Message</strong></td>
    <td>${message}</td>
  </tr>
  </tbody>
</table>

<main><a href="ServletTrinome">Retour à la page de calcul</a></main>
</body>
</html>

<%@ include file="fragment/footer.jsp"%>
