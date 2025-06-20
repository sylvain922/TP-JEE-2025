<%@ page pageEncoding="UTF-8" session="true" %>
<%
  if (session != null) {
    session.invalidate();
  }
  response.sendRedirect("ServletLogin");
%>
