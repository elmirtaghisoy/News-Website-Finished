<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 01.01.2020
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="add">
<div class="form-row">
    <div class="form-group col-md-12">
        <div class="input-group-prepend">
            <label class="input-group-text" for="addUser">İstifadəçi</label>
        </div>
        <select name="userName" class="custom-select" id="addUser">
            <c:forEach var="al" items="${authorList}">
                <option name="" value="${al.id}">${al.name} ${al.surname}</option>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-12">
        <div class="input-group-prepend">
            <label class="input-group-text" for="addCategory">Kateqoriya</label>
        </div>
        <select name="catName" class="custom-select" id="addCategory">
            <c:forEach var="cat" items="${categories}">
                <option name="" value="${cat.id}">${cat.categoryName}</option>
            </c:forEach>
        </select>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-4">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" id="addPicLable1">Esas Sekil</label>
            </div>
            <div class="custom-file">
                <input type="file" multiple=" " name="fileName[]" class="custom-file-input" id="addPic1"
                       aria-describedby="addPic1">
                <label class="custom-file-label" for="addPic1">Choose file</label>
            </div>
        </div>
    </div>
    <div class="form-group col-md-4">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" id="addPicLable2">Diger Fayllar</label>
            </div>
            <div class="custom-file">
                <input type="file"  class="custom-file-input" id="addPic2"
                       aria-describedby="addPic2">
                <label class="custom-file-label" for="addPic2">Choose file</label>
            </div>
        </div>
    </div>
    <div class="form-group col-md-4">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" id="addPicLable3">Diger Fayllar</label>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" multiple="" id="addPic3"
                       aria-describedby="addPic3">
                <label class="custom-file-label" for="addPic3">Choose file</label>
            </div>
        </div>
    </div>
</div>


