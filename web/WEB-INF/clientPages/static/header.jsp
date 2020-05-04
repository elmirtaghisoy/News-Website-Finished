<%@ page import="kadr25.model.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 15.01.2020
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Categorie> mainCategories = (List<Categorie>) request.getAttribute("mainCategories");%>
<li class="nav-item"><a href="javascript: mainPage()" class="nav-link">Ana Sehife</a></li>
<% for (Categorie mc : mainCategories) {
    Integer id = mc.getId();
    if (mc.getChild() != 1) {
%>
<li class="nav-item dropdown">
    <a ondblclick="getPostsByParentId(<%=id%>)" onclick="getCategoriesByParentId(<%=id%>)" class="nav-link dropdown-toggle" href="#"
       id="navbarDropdownMenuLinks<%=id%>" role="button" data-toggle="dropdown" aria-haspopup="true"
       aria-expanded="false">
        <%=mc.getCategoryName()%>
    </a>
    <div class="dropdown-menu ">
        <ul class="subCategories">

        </ul>
    </div>
</li>
<%} else { %>
<li class="nav-item"><a href="javascript: getPostsByCatId(<%=mc.getId()%>)" class="nav-link"><%=mc.getCategoryName()%>
</a></li>
<%
        }
    }
%>