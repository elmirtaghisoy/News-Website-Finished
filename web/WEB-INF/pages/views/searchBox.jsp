<%@ page import="kadr25.model.Categorie" %>
<%@ page import="kadr25.model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 09.01.2020
  Time: 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categories");
    List<Author> authorList = (List<Author>) request.getAttribute("authorList");
%>
<div class="input-group">
    <div class="searchOption mydatepicker">
        <label class="input-group-text">Tarix</label>
        <input class="form-control" id="datepicker1" width="100%"/>
        <script>
            $('#datepicker1').datepicker({
                uiLibrary: 'bootstrap4'
            });
        </script>
    </div>
    <div class="searchOption mydatepicker">
        <label class="input-group-text">Tarix</label>
        <input class="form-control" id="datepicker2" width="100%"/>
        <script>
            $('#datepicker2').datepicker({
                uiLibrary: 'bootstrap4'
            });
        </script>
    </div>
    <div class="searchOption">
        <label class="input-group-text" for="userFrmSrc">İstifadəçi</label>
        <select class="custom-select" id="userFrmSrc">
            <option selected value="0">Choose...</option>
            <% for (Author author : authorList) {%>
            <option name="" value="<%=author.getId()%>">
                <%=author.getName()%> <%=author.getSurname()%>
            </option>
            <%}%>
        </select>
    </div>
    <div class="searchOption">
        <label class="input-group-text" for="likeFrmSrc">Like</label>
        <select class="custom-select" id="likeFrmSrc">
            <option selected value="0">Choose...</option>
            <option value="1">Az</option>
            <option value="2">Cox</option>
        </select>
    </div>
    <div class="searchOption">
        <label class="input-group-text" for="viewFrmSrc">Baxış</label>
        <select class="custom-select" id="viewFrmSrc">
            <option selected value="0">Choose...</option>
            <option value="1">Az</option>
            <option value="2">Cox</option>
        </select>
    </div>
    <div class="searchOption">
        <label class="input-group-text" for="catFrmSrc">Kateqoriya</label>
        <select class="custom-select" id="catFrmSrc">
            <option selected value="0">Choose...</option>
            <% for (Categorie categorie : categorieList) {%>
            <option name="" value="<%=categorie.getId()%>"><%=categorie.getCategoryName()%>
            </option>
            <%}%>
        </select>
    </div>
    <div class="searchOption">
        <label class="input-group-text" for="searchNews">Basliq</label>
        <input type="text" class="form-control" id="searchNews" placeholder="Xeber Basligi">
    </div>
    <div class="searchOption">
        <div style="height: 100%; width: 100%;">
            <a href="javascript: getSearchedData()">
                <button style="transform: translate(50%, 50%);" type="button" class="btnOk">
                    Axtar
                </button>
            </a>
        </div>
    </div>
</div>