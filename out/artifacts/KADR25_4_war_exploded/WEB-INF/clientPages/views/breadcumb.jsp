<%@ page import="kadr25.model.Categorie" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 17.01.2020
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Categorie categorie = (Categorie) request.getAttribute("categorie");%>
<section class="top-post-area pt-10">
    <div class="container no-padding">
        <div class="row">
            <div class="col-lg-12">
                <div class="hero-nav-area">
                    <h1  class="BRCgeneral text-white"><%=categorie.getCategoryName()%></h1>
                    <p class="BRCchild"><i class="fas fa-angle-double-left"></i> Digər kateqoriyalar <i class="fas fa-angle-double-right"></i></p>
                    <p id="subCategoriesInBRC" class="text-white link-nav">
                    </p>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="news-tracker-wrap">
                    <h6><span>Breaking News:</span> <a href="#">Astronomy Binoculars A Great Alternative</a>
                    </h6>
                </div>
            </div>
        </div>
    </div>
</section>