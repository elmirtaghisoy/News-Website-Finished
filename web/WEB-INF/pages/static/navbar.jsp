<%--
  Created by IntelliJ IDEA.
  User: Elmir Taghyev
  Date: 21.11.2019
  Time: 3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">KADR25</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Ana Səhifə</a>
            </li>
            <li class="nav-item">
                <input type="button" class="nav-link" id="postsBtnId" value="Postlar">
            </li>
            <li class="nav-item active">
                <input type="button" class="nav-link" id="usersBtnId" value="İstifadəçilər">
            </li>
            <li class="nav-item">
                <input type="button" class="nav-link" id="categoriesBtnId" value="Kateqoriyalar">
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Siteİnfo</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    -
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
        <form action="logout.jsp" style="margin-left: auto" class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Çıxış</button>
        </form>
    </div>
</nav>