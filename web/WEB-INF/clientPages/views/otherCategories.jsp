<%@ page import="kadr25.model.Categorie" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 16.01.2020
  Time: 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Categorie> subCategories = (List<Categorie>) request.getAttribute("subCategories");
    int i = 0;
    for (Categorie cat : subCategories) {
        i++;
        if (i % 2 != 0) {
%>
<li class="d-flex justify-content-between align-items-center">
    <a class="allCatList" href="javascript: getPostsByCatId(<%=cat.getId()%>)"><%=cat.getCategoryName()%>
    </a>

    <%} else if (i % 2 == 0) {%>
    <a class="allCatList" href="javascript: getPostsByCatId(<%=cat.getId()%>)"><%=cat.getCategoryName()%>
    </a>
</li>
<%
        }
    }
%>
