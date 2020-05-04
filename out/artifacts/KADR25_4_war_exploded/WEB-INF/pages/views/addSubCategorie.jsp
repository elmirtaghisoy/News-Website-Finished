<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 23.12.2019
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-body">
    <div class="form-row">
        <div class="form-group col-md-12">
            <select id="subCategoryName" class="form-control form-control-sm">
                <c:forEach items="${unUsedSubCategorieList}" var="uuscl">
                    <option>${uuscl.categoryName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>