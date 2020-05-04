<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 22.12.2019
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-body">
    <form>
        <div class="form-row">
            <div class="form-group col-md-12">
                <input type="text" class="form-control" id="updateCategoryName" name="addCategory" value="${catName}">
            </div>
            <div class="form-group col-md-12">
                <ul style="list-style: none;padding-left:.3rem;">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Alt Kateqoriylar</th>
                            <th scope="col" colspan="2" class="text-center">
                                <a href="javascript: getUnUsedCategories('${catName}')">
                                    <button type="button" data-toggle="modal" data-target="#addSubCategory" class="add"><i
                                            class="far fa-plus-square"></i></button>
                                </a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${subCategories}" var="sc">
                            <tr>
                                <th scope="row">${sc.id}</th>
                                <td>${sc.categoryName}</td>
                                <td></td>
                                <td class="buttonList text-center">
                                    <a href="javascript: updateSubsOfParent(${sc.id})">
                                        <button type="button" data-toggle="modal" data-id="1" data-target="#deleteFromSubModal"
                                                class="btnDelete">
                                            <i class="far fa-trash-alt"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </ul>
            </div>
        </div>
    </form>
</div>