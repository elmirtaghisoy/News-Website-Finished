<%@ page import="kadr25.model.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 15.01.2020
  Time: 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Categorie> subCats = (List<Categorie>) request.getAttribute("subCategories");%>
<% for (Categorie categorie : subCats) {%>
<li><a href="javascript: getPostsByCatId(<%=categorie.getId()%>)" class="dropdown-item" href="getPostsByCatId(<%=categorie.getId()%>)"><%=categorie.getCategoryName()%>
</a>
</li>
<%}%>