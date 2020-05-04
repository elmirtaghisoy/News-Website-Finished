<%@ page import="kadr25.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Elmir Taghyev
  Date: 28.11.2019
  Time: 2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-body">
    <form id="addAuthorFrom">
        <div class="form-row">
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="editAuthorName" value="${author.name}"
                       placeholder="Ad">
            </div>
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="editAuthorSurname" value="${author.surname}"
                       placeholder="Soyad">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="editAuthorProfession" value="${author.profession}"
                       placeholder="İş">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="editAuthorEmail" value="${author.email}"
                       placeholder="Email">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="editAuthorPhone" value="${author.phone}"
                       placeholder="Mobil nömrə">
            </div>
        </div>
    </form>
</div>
