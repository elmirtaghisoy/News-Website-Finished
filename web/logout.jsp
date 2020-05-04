<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 12.01.2020
  Time: 5:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("login");
    session.invalidate();
    response.sendRedirect("login.jsp");
%>