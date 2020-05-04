<%@ page import="kadr25.model.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 17.01.2020
  Time: 4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Categorie> subCategories = (List<Categorie>) request.getAttribute("subCategories");%>
<a style="text-decoration: underline" href="javascript: mainPage()">Ana Səhifə</a>
<%for (Categorie categorie : subCategories) {%>
<a style="text-decoration: underline" href="javascript: getPostsByCatId(<%=categorie.getId()%>)"><%=categorie.getCategoryName()%></a>
<%
    }
%>
